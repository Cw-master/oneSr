package cxb.chen.urights.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import cxb.chen.common.base.BaseController;
import cxb.chen.common.validator.LengthValidator;
import cxb.chen.urights.common.UrightsResult;
import cxb.chen.urights.common.UrightsResultConstant;
import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;
import cxb.chen.urights.rpc.api.UrightsSystemService;
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
 * 系统controller
 * @author chen
 */
@Controller
@Api(value = "系统管理", description = "系统管理")
@RequestMapping("/manage/system")
public class UrightsSystemController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrightsSystemController.class);

	@Autowired
	private UrightsSystemService urightsSystemService;

	@ApiOperation(value = "系统首页")
	@RequiresPermissions("urights:system:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/system/index.jsp";
	}

	@ApiOperation(value = "系统列表")
	@RequiresPermissions("urights:system:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, defaultValue = "", value = "search") String search,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		UrightsSystemExample urightsSystemExample = new UrightsSystemExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			urightsSystemExample.setOrderByClause(sort + " " + order);
		}
		if (StringUtils.isNotBlank(search)) {
			urightsSystemExample.or()
					.andTitleLike("%" + search + "%");
		}
		List<UrightsSystem> rows = urightsSystemService.selectByExampleForOffsetPage(urightsSystemExample, offset, limit);
		long total = urightsSystemService.countByExample(urightsSystemExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增系统")
	@RequiresPermissions("urights:system:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/system/create.jsp";
	}

	@ApiOperation(value = "新增系统")
	@RequiresPermissions("urights:system:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(UrightsSystem urightsSystem) {
		ComplexResult result = FluentValidator.checkAll()
				.on(urightsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(urightsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		urightsSystem.setCtime(time);
		urightsSystem.setOrders(time);
		int count = urightsSystemService.insertSelective(urightsSystem);
		return new UrightsResult(UrightsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除系统")
	@RequiresPermissions("urights:system:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = urightsSystemService.deleteByPrimaryKeys(ids);
		return new UrightsResult(UrightsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改系统")
	@RequiresPermissions("urights:system:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		UrightsSystem system = urightsSystemService.selectByPrimaryKey(id);
		modelMap.put("system", system);
		return "/manage/system/update.jsp";
	}

	@ApiOperation(value = "修改系统")
	@RequiresPermissions("urights:system:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, UrightsSystem urightsSystem) {
		ComplexResult result = FluentValidator.checkAll()
				.on(urightsSystem.getTitle(), new LengthValidator(1, 20, "标题"))
				.on(urightsSystem.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new UrightsResult(UrightsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		urightsSystem.setSystemId(id);
		int count = urightsSystemService.updateByPrimaryKeySelective(urightsSystem);
		return new UrightsResult(UrightsResultConstant.SUCCESS, count);
	}

}