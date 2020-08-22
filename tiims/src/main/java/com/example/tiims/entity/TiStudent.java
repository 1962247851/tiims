package com.example.tiims.entity;

import com.example.tiims.common.entity.MyJsonStringObject;

import javax.persistence.*;
import javax.validation.constraints.Max;

/**
 * 学生表
 *
 * @author qq1962247851
 * @date 2020/7/13 18:47
 **/
@Entity
public class TiStudent extends MyJsonStringObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ti_student_seq")
    @TableGenerator(name = "ti_student_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "ti_student_id"//表里名字字段对应的值
    )
    private Long id;
    /**
     * 学生姓名
     */
    @Max(50)
    @Column(length = 50)
    private String name;
    /**
     * 家长联系方式
     */
    @Max(50)
    @Column(length = 50)
    private String phone;

    public TiStudent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
