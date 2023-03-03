package com.zhenmei.wsc.demo.dao;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

public interface TestDao {


    @Update("update t_test set name='ss' where id = 1")
    public int updateSome();


}
