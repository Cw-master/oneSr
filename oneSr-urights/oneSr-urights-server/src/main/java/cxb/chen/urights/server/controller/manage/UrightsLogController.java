package cxb.chen.urights.server.controller.manage;

import cxb.chen.common.base.BaseController;
import cxb.chen.common.util.StringUtil;
import cxb.chen.urights.common.UrightsResult;
import cxb.chen.urights.common.UrightsResultConstant;
import cxb.chen.urights.dao.model.UrightsLog;
import cxb.chen.urights.dao.model.UrightsLogExample;
import cxb.chen.urights.rpc.api.UrightsLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志controller
 * @author chen 
 */
@Controller
@Api(value = "日志管理", description = "日志管理")
@RequestMapping("/manage/log")
public class UrightsLogController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsLogController.class);

    @Autowired
    private UrightsLogService urightsLogService;

    @ApiOperation(value = "日志首页")
    @RequiresPermissions("urights:log:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/log/index.jsp";
    }

    @ApiOperation(value = "日志列表")
    @RequiresPermissions("urights:log:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UrightsLogExample urightsLogExample = new UrightsLogExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            urightsLogExample.setOrderByClause(StringUtil.humpToLine(sort) + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            urightsLogExample.or()
                    .andDescriptionLike("%" + search + "%");
        }
        List<UrightsLog> rows = urightsLogService.selectByExampleForOffsetPage(urightsLogExample, offset, limit);
        long total = urightsLogService.countByExample(urightsLogExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "删除日志")
    @RequiresPermissions("urights:log:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = urightsLogService.deleteByPrimaryKeys(ids);
        return new UrightsResult(UrightsResultConstant.SUCCESS, count);
    }

}