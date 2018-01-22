package cxb.chen.urights.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsPermissionMapper;
import cxb.chen.urights.dao.mapper.UrightsSystemMapper;
import cxb.chen.urights.dao.mapper.UrightsUserPermissionMapper;
import cxb.chen.urights.dao.model.*;
import cxb.chen.urights.rpc.api.UrightsApiService;
import cxb.chen.urights.rpc.api.UrightsPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UrightsPermissionService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsPermissionServiceImpl extends BaseServiceImpl<UrightsPermissionMapper, UrightsPermission, UrightsPermissionExample> implements UrightsPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsPermissionServiceImpl.class);

    @Autowired
    UrightsSystemMapper urightsSystemMapper;

    @Autowired
    UrightsPermissionMapper urightsPermissionMapper;

    @Autowired
    UrightsApiService urightsApiService;

    @Autowired
    UrightsUserPermissionMapper urightsUserPermissionMapper;

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        // 角色已有权限
        List<UrightsRolePermission> rolePermissions = urightsApiService.selectUrightsRolePermisstionByUrightsRoleId(roleId);

        JSONArray systems = new JSONArray();
        // 系统
        UrightsSystemExample urightsSystemExample = new UrightsSystemExample();
        urightsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        urightsSystemExample.setOrderByClause("orders asc");
        List<UrightsSystem> urightsSystems = urightsSystemMapper.selectByExample(urightsSystemExample);
        for (UrightsSystem urightsSystem : urightsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", urightsSystem.getSystemId());
            node.put("name", urightsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                UrightsPermissionExample urightsPermissionExample = new UrightsPermissionExample();
                urightsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                urightsPermissionExample.setOrderByClause("orders asc");
                List<UrightsPermission> urightsPermissions = urightsPermissionMapper.selectByExample(urightsPermissionExample);
                if (urightsPermissions.size() == 0) {
                    continue;
                }
                // 目录
                JSONArray folders = new JSONArray();
                for (UrightsPermission urightsPermission: urightsPermissions) {
                    if (urightsPermission.getPid().intValue() != 0 || urightsPermission.getType() != 1) {
                        continue;
                    }
                    JSONObject node = new JSONObject();
                    node.put("id", urightsPermission.getPermissionId());
                    node.put("name", urightsPermission.getName());
                    node.put("open", true);
                    for (UrightsRolePermission rolePermission : rolePermissions) {
                        if (rolePermission.getPermissionId().intValue() == urightsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UrightsPermission urightsPermission2: urightsPermissions) {
                            if (urightsPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || urightsPermission2.getType() != 2) {
                                continue;
                            }
                            JSONObject node2 = new JSONObject();
                            node2.put("id", urightsPermission2.getPermissionId());
                            node2.put("name", urightsPermission2.getName());
                            node2.put("open", true);
                            for (UrightsRolePermission rolePermission : rolePermissions) {
                                if (rolePermission.getPermissionId().intValue() == urightsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UrightsPermission urightsPermission3: urightsPermissions) {
                                    if (urightsPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || urightsPermission3.getType() != 3) {
                                        continue;
                                    }
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", urightsPermission3.getPermissionId());
                                    node3.put("name", urightsPermission3.getName());
                                    node3.put("open", true);
                                    for (UrightsRolePermission rolePermission : rolePermissions) {
                                        if (rolePermission.getPermissionId().intValue() == urightsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    @Override
    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
        // 角色权限
        UrightsUserPermissionExample urightsUserPermissionExample = new UrightsUserPermissionExample();
        urightsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(usereId)
                .andTypeEqualTo(type);
        List<UrightsUserPermission> urightsUserPermissions = urightsUserPermissionMapper.selectByExample(urightsUserPermissionExample);

        JSONArray systems = new JSONArray();
        // 系统
        UrightsSystemExample urightsSystemExample = new UrightsSystemExample();
        urightsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        urightsSystemExample.setOrderByClause("orders asc");
        List<UrightsSystem> urightsSystems = urightsSystemMapper.selectByExample(urightsSystemExample);
        for (UrightsSystem urightsSystem : urightsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", urightsSystem.getSystemId());
            node.put("name", urightsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                UrightsPermissionExample urightsPermissionExample = new UrightsPermissionExample();
                urightsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                urightsPermissionExample.setOrderByClause("orders asc");
                List<UrightsPermission> urightsPermissions = urightsPermissionMapper.selectByExample(urightsPermissionExample);
                if (urightsPermissions.size() == 0) {
                    continue;
                }
                // 目录
                JSONArray folders = new JSONArray();
                for (UrightsPermission urightsPermission: urightsPermissions) {
                    if (urightsPermission.getPid().intValue() != 0 || urightsPermission.getType() != 1) {
                        continue;
                    }
                    JSONObject node = new JSONObject();
                    node.put("id", urightsPermission.getPermissionId());
                    node.put("name", urightsPermission.getName());
                    node.put("open", true);
                    for (UrightsUserPermission urightsUserPermission : urightsUserPermissions) {
                        if (urightsUserPermission.getPermissionId().intValue() == urightsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UrightsPermission urightsPermission2: urightsPermissions) {
                            if (urightsPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || urightsPermission2.getType() != 2) {
                                continue;
                            }
                            JSONObject node2 = new JSONObject();
                            node2.put("id", urightsPermission2.getPermissionId());
                            node2.put("name", urightsPermission2.getName());
                            node2.put("open", true);
                            for (UrightsUserPermission urightsUserPermission : urightsUserPermissions) {
                                if (urightsUserPermission.getPermissionId().intValue() == urightsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UrightsPermission urightsPermission3: urightsPermissions) {
                                    if (urightsPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || urightsPermission3.getType() != 3) {
                                        continue;
                                    }
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", urightsPermission3.getPermissionId());
                                    node3.put("name", urightsPermission3.getName());
                                    node3.put("open", true);
                                    for (UrightsUserPermission urightsUserPermission : urightsUserPermissions) {
                                        if (urightsUserPermission.getPermissionId().intValue() == urightsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

}