package com.example.tiims.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限表(SysPermission)实体类
 */
@Entity
public class SysPermission implements Serializable {
    private static final long serialVersionUID = -71969734644822184L;
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_permission_seq")
    @TableGenerator(name = "sys_permission_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "sys_permission_id"//表里名字字段对应的值
    )
    private Long id;
    //权限code
    @Column(nullable = false, unique = true)
    private String code;
    //权限名
    @Column(nullable = false, unique = true)
    private String name;

    public SysPermission() {
    }

    public SysPermission(String code, String name) {
        this.code = code;
        this.name = name;
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

}