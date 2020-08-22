package com.example.tiims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.tiims.entity.SysUserRoleRelation;

import java.util.List;

@Repository
public interface SysUserRoleRelationDao extends JpaRepository<SysUserRoleRelation, Long> {

    @Query(value = "select * from sys_user_role_relation limit ?#{#limit} offset ?#{#offset}", nativeQuery = true)
    List<SysUserRoleRelation> queryAllByLimit(@Param("offset") Long offset, @Param("limit") Long limit);

    List<SysUserRoleRelation> findAllByRoleId(Long roleId);

    List<SysUserRoleRelation> findAllByUserId(Long userId);

    SysUserRoleRelation findByUserIdAndRoleId(Long userId, Long roleId);
}
