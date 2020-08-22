package com.example.tiims.entity;

import com.example.tiims.common.entity.MyJsonStringObject;

import java.util.Date;

/**
 * 提供给前端展示的TiWork，多了班级nature，和助教name
 *
 * @author qq1962247851
 * @date 2020/7/16 20:24
 **/
public class CustomTiWork extends MyJsonStringObject {
    private Long id;
    /**
     * 日期
     */
    private Date date;
    /**
     * 班级详细信息
     */
    private TiClass classDetail;
    /**
     * 当天助教详细信息
     */
    private TiTeacher teacherDetail;
    /**
     * 当天班级实到人数
     */
    private Integer number;
    /**
     * 更新时间
     */
    private Date updateTimestamp;

    public CustomTiWork() {
    }

    public CustomTiWork(Long id, Date date, TiClass classDetail, TiTeacher teacherDetail, Integer number, Date updateTimestamp) {
        this.id = id;
        this.date = date;
        this.classDetail = classDetail;
        this.teacherDetail = teacherDetail;
        this.number = number;
        this.updateTimestamp = updateTimestamp;
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

    public TiClass getClassDetail() {
        return classDetail;
    }

    public void setClassDetail(TiClass classDetail) {
        this.classDetail = classDetail;
    }

    public TiTeacher getTeacherDetail() {
        return teacherDetail;
    }

    public void setTeacherDetail(TiTeacher teacherDetail) {
        this.teacherDetail = teacherDetail;
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
