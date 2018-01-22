package cxb.chen.urights.rpc.mapper;



import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsRole;

import java.util.List;

/**
 * 用户VOMapper
 * @author chen
 */
public interface  UrightsApiMapper {

	// 根据用户id获取所拥有的权限
	List<UrightsPermission> selectUrightsPermissionByUrightsUserId(Integer urightsUserId);

	// 根据用户id获取所属的角色
	List<UrightsRole> selectUrightsRoleByUrightsUserId(Integer urightsUserId);
	
}