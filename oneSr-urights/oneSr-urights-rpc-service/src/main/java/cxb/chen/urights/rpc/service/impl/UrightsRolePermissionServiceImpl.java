package cxb.chen.urights.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsRolePermissionMapper;
import cxb.chen.urights.dao.model.UrightsRolePermission;
import cxb.chen.urights.dao.model.UrightsRolePermissionExample;
import cxb.chen.urights.rpc.api.UrightsRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* UrightsRolePermissionService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsRolePermissionServiceImpl extends BaseServiceImpl<UrightsRolePermissionMapper, UrightsRolePermission, UrightsRolePermissionExample> implements UrightsRolePermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsRolePermissionServiceImpl.class);

    @Autowired
    UrightsRolePermissionMapper urightsRolePermissionMapper;

    @Override
    public int rolePermission(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UrightsRolePermission urightsRolePermission = new UrightsRolePermission();
                urightsRolePermission.setRoleId(id);
                urightsRolePermission.setPermissionId(json.getIntValue("id"));
                urightsRolePermissionMapper.insertSelective(urightsRolePermission);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UrightsRolePermissionExample urightsRolePermissionExample = new UrightsRolePermissionExample();
            urightsRolePermissionExample.createCriteria()
                    .andPermissionIdIn(deleteIds)
                    .andRoleIdEqualTo(id);
            urightsRolePermissionMapper.deleteByExample(urightsRolePermissionExample);
        }
        return datas.size();
    }

}