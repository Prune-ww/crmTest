package com.ncu.demo.dao;

import com.ncu.demo.entity.Sale;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaleMapper {
    int deleteByPrimaryKey(Integer saleId);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(Integer saleId);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);
}