package com.imooc.mall.dao;

import com.imooc.mall.pojo.Shipping;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByIdAndUid(@Param("uid")Integer uid,
                         @Param("id") Integer id);

    List<Shipping> selectByUid(@Param("uid")Integer id);

    Shipping selectByUidAndShippingId(Integer uid,
                                      Integer shippingId);

    List<Shipping> selectByIdSet(@Param("idSet") Set<Integer> idSet);

}