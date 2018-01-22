package cxb.chen.urights.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import cxb.chen.common.base.BaseController;
import cxb.chen.common.util.MD5Util;
import cxb.chen.common.validator.LengthValidator;
import cxb.chen.common.validator.NotNullValidator;
import cxb.chen.urights.common.UrightsResult;
import cxb.chen.urights.common.UrightsResultConstant;
import cxb.chen.urights.dao.model.*;
import cxb.chen.urights.rpc.api.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户controller
 * @author chen
 */
@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UrightsUserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserController.class);

    @Autowired
    private UrightsUserService urightsUserService;

    @Autowired
    private UrightsRoleService urightsRoleService;

    @Autowired
    private UrightsOrganizationService urightsOrganizationService;

    @Autowired
    private UrightsUserOrganizationService urightsUserOrganizationService;

    @Autowired
    private UrightsUserRoleService urightsUserRoleService;

    @Autowired
    private UrightsUserPermissionService urightsUserPermissionService;

    @ApiOperation(value = "用户首页")
    @RequiresPermissions("urights:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/user/index.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("urights:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    public String organization(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有组织
        List<UrightsOrganization> urightsOrganizations = urightsOrganizationService.selectByExample(new UrightsOrganizationExample());
        // 用户拥有组织
        UrightsUserOrganizationExample urightsUserOrganizationExample = new UrightsUserOrganizationExample();
        urightsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UrightsUserOrganization> urightsUserOrganizations = urightsUserOrganizationService.selectByExample(urightsUserOrganizationExample);
        modelMap.put("urightsOrganizations", urightsOrganizations);
        modelMap.put("urightsUserOrganizations", urightsUserOrganizations);
        return "/manage/user/organization.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("Urights:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object organization(@PathVariable("id") int id, HttpServletRequest request) {
        String[] organizationIds = request.getParameterValues("organizationId");
        urightsUserOrganizationService.organization(organizationIds, id);
        return new UrightsResult(UrightsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("Urights:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String role(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有角色
        List<UrightsRole> urightsRoles = urightsRoleService.selectByExample(new UrightsRoleExample());
        // 用户拥有角色
        UrightsUserRoleExample urightsUserRoleExample = new UrightsUserRoleExample();
        urightsUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UrightsUserRole> urightsUserRoles = urightsUserRoleService.selectByExample(urightsUserRoleExample);
        modelMap.put("urightsRoles", urightsRoles);
        modelMap.put("urightsUserRoles", urightsUserRoles);
        return "/manage/user/role.jsp";
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("urights:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleId");
        urightsUserRoleService.role(roleIds, id);
        return new UrightsResult(UrightsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("urights:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        UrightsUser user = urightsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/permission.jsp";
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("urights:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        urightsUserPermissionService.permission(datas, id);
        return new UrightsResult(UrightsResultConstant.SUCCESS, datas.size());
    }

    @ApiOperation(value = "用户列表")
    @RequiresPermissions("urights:user:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UrightsUserExample urightsUserExample = new UrightsUserExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            urightsUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            urightsUserExample.or()
                    .andRealnameLike("%" + search + "%");
            urightsUserExample.or()
                    .andUsernameLike("%" + search + "%");
        }
        List<UrightsUser> rows = urightsUserService.selectByExampleForOffsetPage(urightsUserExample, offset, limit);
        long total = urightsUserService.countByExample(urightsUserExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("urights:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/user/create.jsp";
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("urights:user:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UrightsUser urightsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(urightsUser.getPassword(), new LengthValidator(5, 32, "密码"))
                .on(urightsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        urightsUser.setSalt(salt);
        urightsUser.setPassword(MD5Util.md5(urightsUser.getPassword() + urightsUser.getSalt()));
        urightsUser.setCtime(time);
        urightsUser = urightsUserService.createUser(urightsUser);
        if (null == urightsUser) {
            return new UrightsResult(UrightsResultConstant.FAILED, "帐号名已存在！");
        }
        LOGGER.info("新增用户，主键：userId={}", urightsUser.getUserId());
        return new UrightsResult(UrightsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "删除用户")
    @RequiresPermissions("urights:user:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = urightsUserService.deleteByPrimaryKeys(ids);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("urights:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UrightsUser user = urightsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/update.jsp";
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("urights:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UrightsUser urightsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(urightsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        // 不允许直接改密码
        urightsUser.setPassword(null);
        urightsUser.setUserId(id);
        int count = urightsUserService.updateByPrimaryKeySelective(urightsUser);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

}
