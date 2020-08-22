package com.example.tiims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.tiims.entity.SysRequestPath;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysRequestPathDao extends JpaRepository<SysRequestPath, Long> {

    @Query(value = "select * from sys_request_path limit ?#{#limit} offset ?#{#offset}", nativeQuery = true)
    List<SysRequestPath> queryAllByLimit(@Param("offset") Long offset, @Param("limit") Long limit);

    Optional<SysRequestPath> findByUrl(String url);
}
