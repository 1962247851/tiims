package com.example.tiims.dao;

import com.example.tiims.entity.TiClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qq1962247851
 * @date 2020/7/15 21:33
 **/
public interface TiClassDao extends JpaRepository<TiClass, Long> {

}
