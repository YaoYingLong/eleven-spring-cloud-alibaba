package com.eleven.icode.elevenredis.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author by YingLong on 2021/11/17
 */
public class SampleLockController {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String deductStock() {
        String lockKey = "product_101";
        String clientId = UUID.randomUUID().toString();
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            //Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "zhuge"); //jedis.setnx(k,v)
            //stringRedisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);

            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
//            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "product_id");
            if (!result) {
                return "error_code";
            }

            //加锁
            redissonLock.lock();  //setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get("stock")
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
            stringRedisTemplate.delete(lockKey);
        } finally {
            redissonLock.unlock();
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }

        return "end";
    }
}
