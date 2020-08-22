package com.example.tiims.entity;

import com.example.tiims.common.entity.MyJsonStringObject;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 出勤表
 *
 * @author qq1962247851
 * @date 2020/7/13 22:42
 **/
@Entity
public class TiWork extends MyJsonStringObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ti_work_seq")
    @TableGenerator(name = "ti_work_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "ti_work_id"//表里名字字段对应的值
    )
    private Long id;
    /**
     * 日期
     */
    @Temporal(TemporalType.DATE)
    private Date date;
    /**
     * 班级编号
     */
    private Long classId;
    /**
     * 当天助教编号
     */
    private Long teacherId;
    /**
     * 当天班级实到人数
     */
    private Integer number;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTimestamp;

    public TiWork() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
