package cxb.chen.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压zhengAdmin-x.x.x.jar到resources目录
 * @author chen
 */
public class ZhengAdminUtil implements InitializingBean, ServletContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZhengAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("===== 开始解压oneSr-admin =====");
        String version = PropertiesFileUtil.getInstance("oneSr-admin-client").get("oneSr.admin.version");
        LOGGER.info("oneSr-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/oneSr-admin-" + version + ".jar");
        LOGGER.info("oneSr-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/oneSr-admin";
        LOGGER.info("oneSr-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        LOGGER.info("===== 解压oneSr-admin完成 =====");
    }

}
