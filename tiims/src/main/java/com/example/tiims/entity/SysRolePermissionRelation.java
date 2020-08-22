package com.example.tiims.entity;

import javax.persistence.*;

/**
 * 角色权限关联表(SysRolePermissionRelation)实体类
 **/
@Entity
public class SysRolePermissionRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_role_permission_relation_seq")
    @TableGenerator(name = "sys_role_permission_relation_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "sys_role_permission_relation_id"//表里名字字段对应的值
    )
    private Long id;
    //角色id
    @Column(nullable = false)
    private Long roleId;
    //权限id
    @Column(nullable = false)
    private Long permissionId;

    public SysRolePermissionRelation() {
    }

    public SysRolePermissionRelation(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
