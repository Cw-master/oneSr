package cxb.chen.urights.rpc.service;

import cxb.chen.urights.dao.mapper.UrightsUserMapper;
import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsPermissionExample;
import cxb.chen.urights.dao.model.UrightsUser;
import cxb.chen.urights.rpc.api.UrightsPermissionService;
import cxb.chen.urights.rpc.api.UrightsSystemService;
import cxb.chen.urights.rpc.api.UrightsUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * 单元测试
 * Created by shuzheng on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-jdbc.xml",
        "classpath:META-INF/spring/applicationContext-listener.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UrightsServiceTest {

    @Autowired
    private UrightsSystemService urightsSystemService;

    @Autowired
    private UrightsUserMapper urightsUserMapper;

    @Autowired
    private UrightsUserService urightsUserService;

    @Autowired
    private UrightsPermissionService urightsPermissionService;

    @Test
    public void index() {
        UrightsUser urightsUser = new UrightsUser();
        urightsUser.setAvatar("");
        urightsUser.setCtime(System.currentTimeMillis());
        urightsUser.setEmail("");
        urightsUser.setLocked((byte) 0);
        urightsUser.setPassword("xxx");
        urightsUser.setPhone("");
        urightsUser.setRealname("zsz");
        urightsUser.setSex((byte) 1);
        urightsUser.setSalt("");
        urightsUser.setUsername("zsz");
        urightsUserService.insertSelective(urightsUser);
        System.out.println(urightsUser.getUserId());
    }

    @Test
    public void selectForPage() {
        // 根据条件，按页码+每页条数分页
        UrightsPermissionExample urightsPermissionExample = new UrightsPermissionExample();
        urightsPermissionExample.createCriteria()
                .andSystemIdEqualTo(1);
        List<UrightsPermission> urightsPermissions = urightsPermissionService.selectByExampleForStartPage(urightsPermissionExample, 2, 20);
        System.out.println(urightsPermissions.size());
        // 根据条件，按offset+limit分页
        urightsPermissionExample = new UrightsPermissionExample();
        urightsPermissionExample.createCriteria()
                .andSystemIdEqualTo(2);
        urightsPermissions = urightsPermissionService.selectByExampleForOffsetPage(urightsPermissionExample, 3, 5);
        System.out.println(urightsPermissions.size());
    }

}
