package com.example.tiims.entity;

import com.example.tiims.common.entity.MyJsonStringObject;

import java.util.List;

/**
 * 提供给前端展示的TiTeacher
 *
 * @author qq1962247851
 * @date 2020/7/16 20:24
 **/
public class CustomTiTeacher extends MyJsonStringObject {
    private Long id;
    private String name;
    /**
     * 职称：（班级对xx）
     * 0：班主任（1对1）
     * 1：助教（1对多）
     * 2：任课老师（多对多）
     */
    private Integer degree;
    private Float salary;
    private String phone;
    private List<TiClass> classList;

    public CustomTiTeacher() {
    }

    public CustomTiTeacher(TiTeacher tiTeacher, List<TiClass> classList) {
        this.id = tiTeacher.getId();
        this.name = tiTeacher.getName();
        this.degree = tiTeacher.getDegree();
        this.salary = tiTeacher.getSalary();
        this.phone = tiTeacher.getPhone();
        this.classList = classList;
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

    public List<TiClass> getClassList() {
        return classList;
    }

    public void setClassList(List<TiClass> classList) {
        this.classList = classList;
    }
}
