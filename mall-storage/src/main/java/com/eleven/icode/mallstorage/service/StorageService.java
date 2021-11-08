package com.eleven.icode.mallstorage.service;

/**
 * @author by YingLong on 2021/11/8
 */
public interface StorageService {
    /**
     * 扣减库存
     * @param commodityCode 商品编号
     * @param count 扣除数量
     */
    void deduct(String commodityCode, int count);
}
