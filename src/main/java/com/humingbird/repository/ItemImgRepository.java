package com.humingbird.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.humingbird.entity.NewItemImg;

public interface ItemImgRepository extends JpaRepository<NewItemImg, Long>{

}
