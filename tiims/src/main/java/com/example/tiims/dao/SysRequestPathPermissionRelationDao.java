package com.example.tiims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.tiims.entity.SysRequestPathPermissionRelation;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysRequestPathPermissionRelationDao extends JpaRepository<SysRequestPathPermissionRelation, Long> {

    @Query(value = "select * from sys_request_path_permission_relation limit ?#{#limit} offset ?#{#offset}", nativeQuery = true)
    List<SysRequestPathPermissionRelation> queryAllByLimit(@Param("offset") Long offset, @Param("limit") Long limit);

    List<SysRequestPathPermissionRelation> findAllByRequestPathId(Long requestPathId);

    List<SysRequestPathPermissionRelation> findAllByPermissionId(Long permissionId);

    Optional<SysRequestPathPermissionRelation> findByRequestPathIdAndPermissionId(Long requestPathId, Long permissionId);
}
