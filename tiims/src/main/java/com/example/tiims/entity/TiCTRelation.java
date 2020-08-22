package com.example.tiims.entity;

import com.example.tiims.common.entity.MyJsonStringObject;

import javax.persistence.*;

/**
 * 师资信息、班级信息关联表
 *
 * @author qq1962247851
 * @date 2020/7/13 23:02
 **/
@Entity(name = "ti_ct_relation")
public class TiCTRelation extends MyJsonStringObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ti_ct_relation_seq")
    @TableGenerator(name = "ti_ct_relation_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "ti_ct_relation_id"//表里名字字段对应的值
    )
    private Long id;
    /**
     * 班级Id
     */
    private Long classId;
    /**
     * 班主任Id
     */
    private Long teacherId;

    public TiCTRelation() {
    }

    public TiCTRelation(Long classId, Long teacherId) {
        this.classId = classId;
        this.teacherId = teacherId;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
