package cxb.chen.urights.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsUserPermissionMapper;
import cxb.chen.urights.dao.model.UrightsUserPermission;
import cxb.chen.urights.dao.model.UrightsUserPermissionExample;
import cxb.chen.urights.rpc.api.UrightsUserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UrightsUserPermissionService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsUserPermissionServiceImpl extends BaseServiceImpl<UrightsUserPermissionMapper, UrightsUserPermission, UrightsUserPermissionExample> implements UrightsUserPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserPermissionServiceImpl.class);

    @Autowired
    UrightsUserPermissionMapper urightsUserPermissionMapper;

    @Override
    public int permission(JSONArray datas, int id) {
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (json.getBoolean("checked")) {
                // 新增权限
                UrightsUserPermission urightsUserPermission = new UrightsUserPermission();
                urightsUserPermission.setUserId(id);
                urightsUserPermission.setPermissionId(json.getIntValue("id"));
                urightsUserPermission.setType(json.getByte("type"));
                urightsUserPermissionMapper.insertSelective(urightsUserPermission);
            } else {
                // 删除权限
                UrightsUserPermissionExample urightsUserPermissionExample = new UrightsUserPermissionExample();
                urightsUserPermissionExample.createCriteria()
                        .andPermissionIdEqualTo(json.getIntValue("id"))
                        .andTypeEqualTo(json.getByte("type"));
                urightsUserPermissionMapper.deleteByExample(urightsUserPermissionExample);
            }
        }
        return datas.size();
    }

}