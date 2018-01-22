package cxb.chen.urights.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import cxb.chen.common.base.BaseController;
import cxb.chen.common.validator.LengthValidator;
import cxb.chen.urights.common.UrightsResult;
import cxb.chen.urights.common.UrightsResultConstant;
import cxb.chen.urights.dao.model.UrightsRole;
import cxb.chen.urights.dao.model.UrightsRoleExample;
import cxb.chen.urights.rpc.api.UrightsRolePermissionService;
import cxb.chen.urights.rpc.api.UrightsRoleService;
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

/**
 * 角色controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "角色管理", description = "角色管理")
@RequestMapping("/manage/role")
public class UrightsRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsRoleController.class);

    @Autowired
    private UrightsRoleService urightsRoleService;

    @Autowired
    private UrightsRolePermissionService urightsRolePermissionService;

    @ApiOperation(value = "角色首页")
    @RequiresPermissions("urights:role:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/role/index.jsp";
    }

    @ApiOperation(value = "角色权限")
    @RequiresPermissions("urights:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        UrightsRole role = urightsRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/permission.jsp";
    }

    @ApiOperation(value = "角色权限")
    @RequiresPermissions("urights:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = urightsRolePermissionService.rolePermission(datas, id);
        return new UrightsResult(UrightsResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "角色列表")
    @RequiresPermissions("urights:role:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UrightsRoleExample urightsRoleExample = new UrightsRoleExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            urightsRoleExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            urightsRoleExample.or()
                    .andTitleLike("%" + search + "%");
        }
        List<UrightsRole> rows = urightsRoleService.selectByExampleForOffsetPage(urightsRoleExample, offset, limit);
        long total = urightsRoleService.countByExample(urightsRoleExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增角色")
    @RequiresPermissions("urights:role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/role/create.jsp";
    }

    @ApiOperation(value = "新增角色")
    @RequiresPermissions("urights:role:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UrightsRole urightsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(urightsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        urightsRole.setCtime(time);
        urightsRole.setOrders(time);
        int count = urightsRoleService.insertSelective(urightsRole);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除角色")
    @RequiresPermissions("urights:role:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = urightsRoleService.deleteByPrimaryKeys(ids);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改角色")
    @RequiresPermissions("urights:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UrightsRole role = urightsRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/update.jsp";
    }

    @ApiOperation(value = "修改角色")
    @RequiresPermissions("urights:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UrightsRole urightsRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(urightsRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        urightsRole.setRoleId(id);
        int count = urightsRoleService.updateByPrimaryKeySelective(urightsRole);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

}
