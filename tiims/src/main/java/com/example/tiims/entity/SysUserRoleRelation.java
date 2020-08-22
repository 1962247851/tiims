package com.example.tiims.entity;

import javax.persistence.*;

/**
 * 用户角色关联表(SysUserRoleRelation)实体类
 **/
@Entity
public class SysUserRoleRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_user_role_relation_seq")
    @TableGenerator(name = "sys_user_role_relation_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "sys_user_role_relation_id"//表里名字字段对应的值
    )
    private Long id;
    private Long userId;
    private Long roleId;

    public SysUserRoleRelation() {
    }

    public SysUserRoleRelation(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
