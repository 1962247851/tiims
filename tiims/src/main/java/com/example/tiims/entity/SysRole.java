package com.example.tiims.entity;

import javax.persistence.*;

/**
 * 角色表(SysRole)实体类
 **/
@Entity
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_role_seq")
    @TableGenerator(name = "sys_role_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "sys_role_id"//表里名字字段对应的值
    )
    private Long id;
    //角色code
    @Column(nullable = false, unique = true)
    private String code;
    //角色名
    @Column(nullable = false, unique = true)
    private String name;
    //角色说明
    @Column(nullable = false)
    private String description;

    public SysRole() {
    }

    public SysRole(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
