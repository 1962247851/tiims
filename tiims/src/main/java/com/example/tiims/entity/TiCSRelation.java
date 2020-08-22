package com.example.tiims.entity;

import com.example.tiims.common.entity.MyJsonStringObject;

import javax.persistence.*;

/**
 * 学生、班级信息关联表
 *
 * @author qq1962247851
 * @date 2020/7/15 21:21
 **/
@Entity(name = "ti_cs_relation")
public class TiCSRelation extends MyJsonStringObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ti_cs_relation_seq")
    @TableGenerator(name = "ti_cs_relation_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "ti_cs_relation_id"//表里名字字段对应的值
    )
    private Long id;
    /**
     * 班级Id
     */
    private Long classId;
    /**
     * 学生Id
     */
    private Long studentId;

    public TiCSRelation() {
    }

    public TiCSRelation(Long classId,Long studentId) {
        this.classId = classId;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
