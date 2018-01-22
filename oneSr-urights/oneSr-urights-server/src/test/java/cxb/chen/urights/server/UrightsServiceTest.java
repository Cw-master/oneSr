package cxb.chen.urights.server;

import cxb.chen.urights.dao.model.UrightsSystemExample;
import cxb.chen.urights.rpc.api.UrightsSystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 单元测试
 * Created by shuzheng on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:applicationContext-dubbo-consumer.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UrightsServiceTest {

    @Autowired
    private UrightsSystemService urightsSystemService;

    @Test
    public void index() {
        int count = urightsSystemService.countByExample(new UrightsSystemExample());
        System.out.println(count);
    }

}
