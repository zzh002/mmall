package com.mmall.dao.test;

import com.mmall.dao.OrderItemMapper;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.OrderItem;
import com.mmall.pojo.User;
import com.mmall.test.TestBase;

import lombok.extern.slf4j.Slf4j;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by geely on mmall.
 */
@Slf4j
public class DaoTest extends TestBase {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Ignore
    @Test
    public void testDao(){
        User a = new User();
        a.setPassword("111");
        a.setUsername("aaaaageely");
        a.setRole(0);
        a.setCreateTime(new Date());
        a.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(userMapper.insert(a));
        System.out.println("aaaaaaaaaaaaaa");
    }

    @Test
    public void orderItemTest() {
    	Long orderNo = new Long("1492091141269");
    	List<OrderItem> list = orderItemMapper.getByOrderNo(orderNo);
    	for(OrderItem orderItem:list) {
    		log.info("orderItem:{}",orderItem.toString());
    	}
    }
    

}
