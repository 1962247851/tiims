package com.example.tiims.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 请求路径(SysRequestPath)实体类
 */
@Entity
public class SysRequestPath implements Serializable {
    private static final long serialVersionUID = -38398465698914714L;
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_request_path_seq")
    @TableGenerator(name = "sys_request_path_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "sys_request_path_id"//表里名字字段对应的值
    )
    private Long id;
    //请求路径
    @Column(nullable = false, unique = true)
    private String url;
    //路径描述
    @Column(nullable = false)
    private String description;

    public SysRequestPath() {
    }

    public SysRequestPath(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}