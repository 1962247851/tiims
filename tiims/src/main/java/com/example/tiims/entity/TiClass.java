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
public class TiClass extends MyJsonStringObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ti_class_seq")
    @TableGenerator(name = "ti_class_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "ti_class_id"//表里名字字段对应的值
    )
    private Long id;
    /**
     * 班级收费（每人）
     */
    private Float cost;
    /**
     * 班级人数
     */
    private Integer number;
    /**
     * 补习科目门数
     */
    private Integer lessons;
    /**
     * 班级性质（对应的升学段，如：八升九）
     */
    @Max(50)
    @Column(length = 50)
    private String nature;

    public TiClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getLessons() {
        return lessons;
    }

    public void setLessons(Integer lessons) {
        this.lessons = lessons;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }


}
