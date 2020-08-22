package com.example.tiims.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 路径权限关联表(SysRequestPathPermissionRelation)实体类
 */
@Entity
public class SysRequestPathPermissionRelation implements Serializable {
    private static final long serialVersionUID = -59197738311147860L;
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_request_path_permission_relation_seq")
    @TableGenerator(name = "sys_request_path_permission_relation_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "sys_request_path_permission_relation_id"//表里名字字段对应的值
    )
    private Long id;
    //请求路径id
    private Long requestPathId;
    //权限id
    private Long permissionId;

    public SysRequestPathPermissionRelation() {
    }

    public SysRequestPathPermissionRelation(Long requestPathId, Long permissionId) {
        this.requestPathId = requestPathId;
        this.permissionId = permissionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestPathId() {
        return requestPathId;
    }

    public void setRequestPathId(Long requestPathId) {
        this.requestPathId = requestPathId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}