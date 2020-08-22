package com.example.tiims.entity;


import com.example.tiims.DateLongConverter;
import com.example.tiims.TimestampLongConverter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 */
@Entity
public class SysUser implements Serializable {
    private static final long serialVersionUID = 915478504870211231L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_user_seq")
    @TableGenerator(name = "sys_user_seq",
            allocationSize = 1,//自动增长，设置为1
            pkColumnName = "sequence_name", //表里用来保存主键名字的字段
            valueColumnName = "sequence_next_hi_value",//表里用来保存主键值的字段
            pkColumnValue = "sys_user_id"//表里名字字段对应的值
    )
    private Long id;
    /**
     * 用户名
     */
    @Column(unique = true, nullable = false, length = 20)
    private String userName;
    /**
     * 性别
     */
    @Column(columnDefinition = "bit(1) default 1", nullable = false)
    private Boolean sex = true;
    /**
     * 出生日期
     */
    @Column(nullable = false)
    @Convert(converter = DateLongConverter.class)
    private Long birthday;
    /**
     * 邮箱
     */
    @Column(unique = true, length = 50)
    private String email;
    /**
     * 手机号码
     */
    @Column(unique = true, length = 11)
    private String phone;
    /**
     * 用户密码
     */
    @Column(nullable = false, length = 64)
    private String password;
    /**
     * 个人介绍
     */
    private String selfIntroduction;
    /**
     * 上一次登录时间
     */
    @Convert(converter = TimestampLongConverter.class)
    private Long lastLoginTime = System.currentTimeMillis();
    /**
     * 账号是否可用。默认为1（可用）
     */
    @Column(columnDefinition = "bit(1) not null default 1")
    private Boolean enabled = true;
    /**
     * 是否过期。默认为1（没有过期）
     */
    @Column(columnDefinition = "bit(1) not null default 1")
    private Boolean notExpired = true;
    /**
     * 账号是否锁定。默认为1（没有锁定）
     */
    @Column(columnDefinition = "bit(1) not null default 1")
    private Boolean notLocked = true;
    /**
     * 密码是否过期。默认为1（没有过期）
     */
    @Column(columnDefinition = "bit(1) not null default 1")
    private Boolean passwordNotExpired = true;
    /**
     * 创建时间
     */
    @Column(nullable = false)
    @Convert(converter = TimestampLongConverter.class)
    private Long createTime = System.currentTimeMillis();
    /**
     * 修改时间
     */
    @Convert(converter = TimestampLongConverter.class)
    private Long updateTime;

    public SysUser() {
    }

    public SysUser(Long id, String userName, Boolean sex, Long birthday, String email, String phone, String password, String selfIntroduction, Long lastLoginTime, Boolean enabled, Boolean notExpired, Boolean notLocked, Boolean passwordNotExpired, Long createTime, Long updateTime) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.selfIntroduction = selfIntroduction;
        this.lastLoginTime = lastLoginTime;
        this.enabled = enabled;
        this.notExpired = notExpired;
        this.notLocked = notLocked;
        this.passwordNotExpired = passwordNotExpired;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getNotExpired() {
        return notExpired;
    }

    public void setNotExpired(Boolean notExpired) {
        this.notExpired = notExpired;
    }

    public Boolean getNotLocked() {
        return notLocked;
    }

    public void setNotLocked(Boolean notLocked) {
        this.notLocked = notLocked;
    }

    public Boolean getPasswordNotExpired() {
        return passwordNotExpired;
    }

    public void setPasswordNotExpired(Boolean passwordNotExpired) {
        this.passwordNotExpired = passwordNotExpired;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}