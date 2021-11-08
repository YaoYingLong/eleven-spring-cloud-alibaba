package com.eleven.icode.malluser.mapper;

import com.eleven.icode.malluser.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Fox
 */

@Repository
public interface AccountMapper {
    /**
     * 查询账户
     * @param userId
     * @return
     */
    @Select(value = "select id, user_id, money from account_tbl WHERE user_id = #{userId}")
    Account selectByUserId(@Param(value = "userId") String userId);
    
    /**
     * 扣减余额
     * @param userId 用户id
     * @param money 要扣减的金额
     * @return
     */
    @Update(value = "update account_tbl set money =money-#{money} where user_id = #{userId}")
    int reduceBalance(@Param(value = "userId") String userId,
                      @Param(value = "money") Integer money);
    
}
