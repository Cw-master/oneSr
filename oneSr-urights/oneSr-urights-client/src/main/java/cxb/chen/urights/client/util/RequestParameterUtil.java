package cxb.chen.urights.client.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * request参数处理工具
 * @author chen
 */
public class RequestParameterUtil {

    /**
     *移除url中的code、username参数
     *@param request
     *@return  String
     */
    public static String getParameterWithOutcode(HttpServletRequest request){
        StringBuffer backUrl = request.getRequestURL();
        String params = "";
        Map<String, String[]> paramMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry: paramMap.entrySet()) {
            if (!"upms_code".equals(entry.getKey()) && !"upms_username".equals(entry.getKey())) {
                if ("".equals(params)) {
                    params = entry.getKey() + "=" + entry.getValue()[0];
                } else {
                    params += "&" + entry.getKey() + "=" + entry.getValue()[0];
                }
            }
        }
        if (!StringUtils.isBlank(params)) {
            backUrl = backUrl.append("?").append(params);
        }
        return backUrl.toString();
    }
}
