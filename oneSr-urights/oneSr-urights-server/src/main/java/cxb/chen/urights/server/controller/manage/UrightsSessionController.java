package cxb.chen.urights.server.controller.manage;

import cxb.chen.common.base.BaseController;
import cxb.chen.urights.client.shiro.session.UrightsSessionDao;
import cxb.chen.urights.common.UrightsResult;
import cxb.chen.urights.common.UrightsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 会话管理controller
 * Created by shuzheng on 2017/2/28.
 */
@Controller
@Api(value = "会话管理", description = "会话管理")
@RequestMapping("/manage/session")
public class UrightsSessionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsSessionController.class);

    @Autowired
    private UrightsSessionDao sessionDAO;

    @ApiOperation(value = "会话首页")
    @RequiresPermissions("urights:session:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/session/index.jsp";
    }

    @ApiOperation(value = "会话列表")
    @RequiresPermissions("urights:session:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {
        return sessionDAO.getActiveSessions(offset, limit);
    }

    @ApiOperation(value = "强制退出")
    @RequiresPermissions("urights:session:forceout")
    @RequestMapping(value = "/forceout/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object forceout(@PathVariable("ids") String ids) {
        int count = sessionDAO.forceout(ids);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

}