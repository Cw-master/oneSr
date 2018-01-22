package cxb.chen.urights.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import cxb.chen.common.base.BaseController;
import cxb.chen.common.validator.LengthValidator;
import cxb.chen.urights.common.UrightsResult;
import cxb.chen.urights.common.UrightsResultConstant;
import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsPermissionExample;
import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;
import cxb.chen.urights.rpc.api.UrightsApiService;
import cxb.chen.urights.rpc.api.UrightsPermissionService;
import cxb.chen.urights.rpc.api.UrightsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
 * 权限controller
 * @author chen
 */
@Controller
@Api(value = "权限管理", description = "权限管理")
@RequestMapping("/manage/permission")
public class UrightsPermissionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsPermissionController.class);

    @Autowired
    private UrightsPermissionService urightsPermissionService;

    @Autowired
    private UrightsSystemService urightsSystemService;

    @Autowired
    private UrightsApiService urightsApiService;

    @ApiOperation(value = "权限首页")
    @RequiresPermissions("urights:permission:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/permission/index.jsp";
    }

    @ApiOperation(value = "权限列表")
    @RequiresPermissions("urights:permission:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, defaultValue = "0", value = "type") int type,
            @RequestParam(required = false, defaultValue = "0", value = "systemId") int systemId,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UrightsPermissionExample urightsPermissionExample = new UrightsPermissionExample();
        UrightsPermissionExample.Criteria criteria = urightsPermissionExample.createCriteria();
        if (0 != type) {
            criteria.andTypeEqualTo((byte) type);
        }
        if (0 != systemId) {
            criteria.andSystemIdEqualTo(systemId);
        }
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            urightsPermissionExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            urightsPermissionExample.or()
                    .andNameLike("%" + search + "%");
        }
        List<UrightsPermission> rows = urightsPermissionService.selectByExampleForOffsetPage(urightsPermissionExample, offset, limit);
        long total = urightsPermissionService.countByExample(urightsPermissionExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "角色权限列表")
    @RequiresPermissions("urights:permission:read")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {
        return urightsPermissionService.getTreeByRoleId(id);
    }

    @ApiOperation(value = "用户权限列表")
    @RequiresPermissions("urights:permission:read")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object user(@PathVariable("id") int id, HttpServletRequest request) {
        return urightsPermissionService.getTreeByUserId(id, NumberUtils.toByte(request.getParameter("type")));
    }

    @ApiOperation(value = "新增权限")
    @RequiresPermissions("urights:permission:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        UrightsSystemExample urightsSystemExample = new UrightsSystemExample();
        urightsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<UrightsSystem> UrightsSystems = urightsSystemService.selectByExample(urightsSystemExample);
        modelMap.put("urightsSystems", UrightsSystems);
        return "/manage/permission/create.jsp";
    }

    @ApiOperation(value = "新增权限")
    @RequiresPermissions("urights:permission:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UrightsPermission urightsPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        urightsPermission.setCtime(time);
        urightsPermission.setOrders(time);
        int count = urightsPermissionService.insertSelective(urightsPermission);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除权限")
    @RequiresPermissions("urights:permission:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = urightsPermissionService.deleteByPrimaryKeys(ids);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改权限")
    @RequiresPermissions("urights:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UrightsSystemExample urightsSystemExample = new UrightsSystemExample();
        urightsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<UrightsSystem> urightsSystems = urightsSystemService.selectByExample(urightsSystemExample);
        UrightsPermission permission = urightsPermissionService.selectByPrimaryKey(id);
        modelMap.put("permission", permission);
        modelMap.put("urightsSystems", urightsSystems);
        return "/manage/permission/update.jsp";
    }

    @ApiOperation(value = "修改权限")
    @RequiresPermissions("urights:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UrightsPermission urightsPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        urightsPermission.setPermissionId(id);
        int count = urightsPermissionService.updateByPrimaryKeySelective(urightsPermission);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

}
