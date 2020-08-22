package com.example.tiims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.tiims.entity.SysUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysUserDao extends JpaRepository<SysUser, Long> {

    Optional<SysUser> findByUserName(String userName);

    @Query(value = "select * from sys_user limit ?#{#limit} offset ?#{#offset}", nativeQuery = true)
    List<SysUser> queryAllByLimit(@Param("offset") Long offset, @Param("limit") Long limit);
}
