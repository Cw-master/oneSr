package cxb.chen.urights.server.controller;

import cxb.chen.common.base.BaseController;
import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;
import cxb.chen.urights.dao.model.UrightsUser;
import cxb.chen.urights.rpc.api.UrightsApiService;
import cxb.chen.urights.rpc.api.UrightsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 后台controller
 * @author chen
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private UrightsSystemService urightsSystemService;

	@Autowired
	private UrightsApiService urightsApiService;

	@ApiOperation(value = "后台首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		// 已注册系统
		UrightsSystemExample urightsSystemExample = new UrightsSystemExample();
		urightsSystemExample.createCriteria()
				.andStatusEqualTo((byte) 1);
		List<UrightsSystem> urightsSystems = urightsSystemService.selectByExample(urightsSystemExample);
		modelMap.put("urightsSystems", urightsSystems);
		// 当前登录用户权限
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		UrightsUser UrightsUser = urightsApiService.selectUrightsUserByUsername(username);
		List<UrightsPermission> urightsPermissions = urightsApiService.selectUrightsPermissionByUrightsUserId(UrightsUser.getUserId());
		modelMap.put("urightsPermissions", urightsPermissions);
		return "/manage/index.jsp";
	}

}