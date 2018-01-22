package cxb.chen.urights.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import cxb.chen.common.base.BaseController;
import cxb.chen.common.validator.LengthValidator;
import cxb.chen.urights.common.UrightsResult;
import cxb.chen.urights.common.UrightsResultConstant;
import cxb.chen.urights.dao.model.UrightsOrganization;
import cxb.chen.urights.dao.model.UrightsOrganizationExample;
import cxb.chen.urights.rpc.api.UrightsOrganizationService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "组织管理", description = "组织管理")
@RequestMapping("/manage/organization")
public class UrightsOrganizationController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsOrganizationController.class);

    @Autowired
    private UrightsOrganizationService urightsOrganizationService;

    @ApiOperation(value = "组织首页")
    @RequiresPermissions("urights:organization:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/organization/index.jsp";
    }

    @ApiOperation(value = "组织列表")
    @RequiresPermissions("urights:organization:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UrightsOrganizationExample urightsOrganizationExample = new UrightsOrganizationExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            urightsOrganizationExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            urightsOrganizationExample.or()
                    .andNameLike("%" + search + "%");
        }
        List<UrightsOrganization> rows = urightsOrganizationService.selectByExampleForOffsetPage(urightsOrganizationExample, offset, limit);
        long total = urightsOrganizationService.countByExample(urightsOrganizationExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增组织")
    @RequiresPermissions("urights:organization:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/organization/create.jsp";
    }

    @ApiOperation(value = "新增组织")
    @RequiresPermissions("urights:organization:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UrightsOrganization urightsOrganization) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsOrganization.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        urightsOrganization.setCtime(time);
        int count = urightsOrganizationService.insertSelective(urightsOrganization);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除组织")
    @RequiresPermissions("urights:organization:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = urightsOrganizationService.deleteByPrimaryKeys(ids);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改组织")
    @RequiresPermissions("urights:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UrightsOrganization organization = urightsOrganizationService.selectByPrimaryKey(id);
        modelMap.put("organization", organization);
        return "/manage/organization/update.jsp";
    }

    @ApiOperation(value = "修改组织")
    @RequiresPermissions("urights:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UrightsOrganization urightsOrganization) {
        ComplexResult result = FluentValidator.checkAll()
                .on(urightsOrganization.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        urightsOrganization.setOrganizationId(id);
        int count = urightsOrganizationService.updateByPrimaryKeySelective(urightsOrganization);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

}
