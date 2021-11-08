package com.eleven.icode.mallorder.mapper;

import com.eleven.icode.mallorder.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author by YingLong on 2021/11/8
 */
@Mapper
public interface OrderMapper {

    /**
     * 保存订单
     * @param record
     * @return
     */
    @Insert(value = "INSERT INTO order_tbl (user_id, commodity_code, count, status, money) VALUES (#{userId}, #{commodityCode}, #{count}, #{status}, #{money})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Order record);

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    @Update("UPDATE order_tbl SET status = #{status} WHERE id = #{id}")
    int updateOrderStatus(@Param("id") Integer id, @Param("status") int status);
}

