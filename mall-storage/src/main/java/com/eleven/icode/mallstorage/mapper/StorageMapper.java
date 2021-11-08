package com.eleven.icode.mallstorage.mapper;

import com.eleven.icode.mallstorage.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Fox
 */

@Repository
public interface StorageMapper {
    
    /**
     * 获取库存
     * @param commodityCode 商品编号
     * @return
     */
    @Select(value = "SELECT id,commodity_code,count FROM storage_tbl WHERE commodity_code = #{commodityCode}")
    Storage findByCommodityCode(@Param(value = "commodityCode") String commodityCode);
    
    /**
     * 扣减库存
     * @param commodityCode 商品编号
     * @param count  要扣减的库存
     * @return
     */
    @Update(value = "UPDATE storage_tbl SET count = count - #{count} WHERE commodity_code = #{commodityCode}")
    int reduceStorage(@Param(value = "commodityCode") String commodityCode,
                      @Param(value = "count") Integer count);
    
}
