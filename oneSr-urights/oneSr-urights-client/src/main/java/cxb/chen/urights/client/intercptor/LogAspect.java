package cxb.chen.urights.client.intercptor;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chen
 */
@Aspect
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    //开始时间
    private Long startTime = 0L;

    //结束时间
    private Long endTime = 0L;

}
