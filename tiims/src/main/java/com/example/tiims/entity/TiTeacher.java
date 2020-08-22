package com.example.tiims.entity;

import com.example.tiims.common.entity.MyJsonStringObject;

import javax.persistence.*;
import javax.validation.constraints.Max;

/**
 * 师资信息表
 *
 * @author qq1962247851
 * @date 2020/7/13 18:47
 **/
@Entity
public class TiTeacher extends MyJsonStringObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ti_teacher_seq")
    @TableGenerator(name = "ti_teacher_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "ti_teacher_id"//表里名字字段对应的值
    )
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 职称：（班级对xx）
     * 0：班主任（1对1）
     * 1：助教（1对多）
     * 2：任课老师（多对多）
     */
    @Max(1)
    @Column(length = 1)
    private Integer degree;
    /**
     * 薪资（每月）
     */
    private Float salary;
    /**
     * 联系方式
     */
    @Max(50)
    @Column(length = 50)
    private String phone;

    public TiTeacher() {
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

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
