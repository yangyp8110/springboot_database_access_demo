package com.demo.gener;

import com.demo.gener.entity.test.UserDetailsEntity;
import com.demo.gener.entity.test.predicate.UserDetailsExample;
import com.demo.gener.mapper.test.UserDetailsMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author mr.yang.demo
 * @Description:
 * @date 2018/9/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainApplication.class})
public class UserDetailsMapperTest {
    @Autowired
    private UserDetailsMapper userDetailsMapper;

    /**
     *
     DROP TABLE IF EXISTS `user`;
     CREATE TABLE `user` (
     `id` bigint unsign NOT NULL AUTO_INCREMENT COMMENT '主键',
     `user_name` varchar(20) DEFAULT NULL COMMENT '姓名',
     `age` int unsign DEFAULT NULL COMMENT '年龄',
     `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
     `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`)
     ) ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';

     DROP TABLE IF EXISTS `user_details`;
     CREATE TABLE `user_details` (
     `id` bigint unsign NOT NULL AUTO_INCREMENT COMMENT '主键',
     `u_id` bigint unsign DEFAULT NULL COMMENT '用户id',
     `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
     `user_desc` varchar(255) DEFAULT NULL COMMENT '用户描述',
     `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
     `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`),
     FULLTEXT KEY `UserDetail_FullIndex` (`user_desc`)
     ) ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户详细表';
     */

    @Test
    public void test() {
        //testAddUserDetails();

        //testBatchAddUserDetails();

        //testSelectById();
        //
        //testSingleExample();
        //
        //testAllByPredicate();
        //
        //testByPage();

        //testUpdate();

        //testOr();
    }

    private void testBatchAddUserDetails() {
        List<UserDetailsEntity> list = new ArrayList<>();

        UserDetailsEntity zhangSan = new UserDetailsEntity();
        zhangSan.setUid(1L);
        zhangSan.setNickName("zhangsan");
        zhangSan.setUserDesc("a teacher");
        list.add(zhangSan);

        UserDetailsEntity lisi = new UserDetailsEntity();
        lisi.setUid(2L);
        lisi.setNickName("lisi");
        lisi.setUserDesc("a python coder");
        list.add(lisi);

        UserDetailsEntity wangwu = new UserDetailsEntity();
        wangwu.setUid(3L);
        wangwu.setNickName("wangwu");
        wangwu.setUserDesc("a doctor");
        list.add(wangwu);

        /**
         * 2018-09-19 16:26:34.574 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.batchInsertUserDetails [debug:159] ==>  Preparing: insert into user_details ( u_id,nick_name,user_desc,inserttime,updatetime ) values ( ?,?,?,?,? ) , ( ?,?,?,?,? ) , ( ?,?,?,?,? )
         2018-09-19 16:26:34.655 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.batchInsertUserDetails [debug:159] ==> Parameters: 1(Long), zhangsan(String), a teacher(String), null, null, 2(Long), lisi(String), a python coder(String), null, null, 3(Long), wangwu(String), a doctor(String), null, null
         2018-09-19 16:26:34.665 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.batchInsertUserDetails [debug:159] <==    Updates: 3
         */
        int result = userDetailsMapper.batchInsertUserDetails(list);
        Assert.assertEquals(result, list.size());
    }

    private void testAddUserDetails() {
        /**
         * 2018-09-19 16:20:23.196 [DEBUG][main]:c.demo.gener.mapper.test.UserDetailsMapper.insert [debug:159] ==>  Preparing: insert into user_details ( u_id, nick_name, user_desc ) values ( ?, ?, ? )
         2018-09-19 16:20:23.279 [DEBUG][main]:c.demo.gener.mapper.test.UserDetailsMapper.insert [debug:159] ==> Parameters: 6(Long), zhaoliu(String), a fammer(String)
         2018-09-19 16:20:23.289 [DEBUG][main]:c.demo.gener.mapper.test.UserDetailsMapper.insert [debug:159] <==    Updates: 1
         */
        UserDetailsEntity zhaoliu = new UserDetailsEntity();
        zhaoliu.setUid(6L);
        zhaoliu.setNickName("zhaoliu");
        zhaoliu.setUserDesc("a fammer");

        int result = userDetailsMapper.insert(zhaoliu);
    }

    private void testSelectById() {
        /***
         *  test.UserDetailsMapper.getByPrimaryKey [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details where id = ?
            test.UserDetailsMapper.getByPrimaryKey [debug:159] ==> Parameters: 1(Long)
            test.UserDetailsMapper.getByPrimaryKey [debug:159] <==      Total: 1
         */
        UserDetailsEntity byPrimaryKey = userDetailsMapper.getByPrimaryKey(1l);
        Assert.assertNotNull(byPrimaryKey);

        /***
         *  2018-09-19 15:50:22.658 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.getByPrimaryIds [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details where id in ( ? , ? )
            2018-09-19 15:50:22.659 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.getByPrimaryIds [debug:159] ==> Parameters: 1(Long), 2(Long)
            2018-09-19 15:50:22.661 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.getByPrimaryIds [debug:159] <==      Total: 2
         */
        ArrayList<Long> ids = Lists.newArrayList(1L, 2L);
        List<UserDetailsEntity> byPrimaryIds = userDetailsMapper.getByPrimaryIds(ids);
        Assert.assertEquals(byPrimaryIds.size(), ids.size());
    }

    private void testSingleExample() {
        /***
         * 2018-09-19 15:54:56.299 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getSingleByPredicate [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( nick_name = ? and u_id = ? ) order by id desc limit 1
         2018-09-19 15:54:56.317 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getSingleByPredicate [debug:159] ==> Parameters: zhangsan(String), 1(Long)
         2018-09-19 15:54:56.318 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getSingleByPredicate [debug:159] <==      Total: 1
         */
        UserDetailsExample.Criteria criteria = UserDetailsExample.buildCriteria()
                .andNickNameEqualTo("zhangsan").andUidEqualTo(1L);
        UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria)
                .orderByClause(UserDetailsExample.OrderByCriteria.builder().orderByIdDesc())
                .build();
        UserDetailsEntity singleByPredicate = userDetailsMapper.getSingleByPredicate(example);
        Assert.assertNotNull(singleByPredicate);

        /**
         * 2018-09-19 15:54:56.320 [DEBUG][main]:c.d.gener.mapper.test.UserDetailsMapper.queryCount [debug:159] ==>  Preparing: select count(1) from user_details WHERE ( nick_name = ? and u_id = ? )
         2018-09-19 15:54:56.322 [DEBUG][main]:c.d.gener.mapper.test.UserDetailsMapper.queryCount [debug:159] ==> Parameters: zhangsan(String), 1(Long)
         2018-09-19 15:54:56.323 [DEBUG][main]:c.d.gener.mapper.test.UserDetailsMapper.queryCount [debug:159] <==      Total: 1
         */
        int i = userDetailsMapper.queryCount(example);
        System.out.println("count:" + i);
    }

    private void testAllByPredicate() {
        /**
         * 2018-09-19 15:54:56.324 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.getAllByPredicate [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( id >= ? ) order by id desc
         2018-09-19 15:54:56.325 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.getAllByPredicate [debug:159] ==> Parameters: 1(Long)
         2018-09-19 15:54:56.326 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.getAllByPredicate [debug:159] <==      Total: 3
         */
        UserDetailsExample.Criteria criteria = UserDetailsExample.buildCriteria()
                .andIdGreaterThanOrEqualTo(1L);
        UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria)
                .orderByClause(UserDetailsExample.OrderByCriteria.builder().orderByIdDesc())
                .build();
        List<UserDetailsEntity> allByPredicate = userDetailsMapper.getAllByPredicate(example);
        Assert.assertNotNull(allByPredicate);
    }

    private void testByPage() {
        /**
         * 2018-09-19 15:54:56.353 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] ==>  Preparing: SELECT count(0) FROM user_details WHERE (id > ?)
         2018-09-19 15:54:56.354 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] ==> Parameters: 1(Long)
         2018-09-19 15:54:56.355 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] <==      Total: 1
         2018-09-19 15:54:56.356 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( id > ? ) order by id desc LIMIT 2
         2018-09-19 15:54:56.356 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] ==> Parameters: 1(Long)
         2018-09-19 15:54:56.358 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] <==      Total: 2
         获取第1页数据2条，共2条
         */
        UserDetailsExample.Criteria criteria = UserDetailsExample.buildCriteria()
                .andIdGreaterThan(1L);
        UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria)
                .orderByClause(UserDetailsExample.OrderByCriteria.builder().orderByIdDesc())
                .build();

        Page<?> page = PageHelper.startPage(1,2);
        List<UserDetailsEntity> byPage = userDetailsMapper.getUserDetailsByPage(example);
        Assert.assertNotNull(byPage);
        System.out.println(String.format("获取第%d页数据%d条，共%d条", page.getPageNum(), page.getPageSize(), page.getTotal()));
    }

    private void testUpdate() {
        Long key = 2L;

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setUserDesc("a senior school teacher");
        userDetailsEntity.setId(key);
        /**
         * 2018-09-19 15:54:56.361 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.updateByPredicate [debug:159] ==>  Preparing: UPDATE user_details SET user_desc = ? WHERE ( id = ? )
         2018-09-19 15:54:56.362 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.updateByPredicate [debug:159] ==> Parameters: a senior school teacher(String), 2(Long)
         2018-09-19 15:54:56.363 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.updateByPredicate [debug:159] <==    Updates: 1
         */
        userDetailsMapper.updateByPrimaryKey(userDetailsEntity);
        UserDetailsEntity byPrimaryKey = userDetailsMapper.getByPrimaryKey(key);
        System.out.println("byPrimaryKey:" + byPrimaryKey.getNickName() + "userDesc:" + byPrimaryKey.getUserDesc());

        UserDetailsExample.Criteria criteria = UserDetailsExample.buildCriteria()
                .andIdEqualTo(key).andUserDescEqualTo(userDetailsEntity.getUserDesc());
        UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria)
                .build();

        userDetailsEntity.setNickName("hello kitty");
        /**
         * 2018-09-19 16:15:23.436 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.updateByPredicate [debug:159] ==>  Preparing: UPDATE user_details SET nick_name = ?, user_desc = ? WHERE ( id = ? and user_desc = ? )
         2018-09-19 16:15:23.453 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.updateByPredicate [debug:159] ==> Parameters: hello kitty(String), a senior school teacher(String), 2(Long), a senior school teacher(String)
         2018-09-19 16:15:23.455 [DEBUG][main]:c.d.g.m.test.UserDetailsMapper.updateByPredicate [debug:159] <==    Updates: 1
         */
        userDetailsMapper.updateByPredicate(userDetailsEntity, example);
        byPrimaryKey = userDetailsMapper.getByPrimaryKey(key);
        System.out.println("byPrimaryKey:" + byPrimaryKey.getNickName() + "userDesc:" + byPrimaryKey.getUserDesc());
    }

    private void testOr() {
        UserDetailsExample.Criteria criteria1 = UserDetailsExample.buildCriteria()
                .andIdGreaterThan(2L);
        UserDetailsExample.Criteria criteria2 = UserDetailsExample.buildCriteria()
                .andNickNameEqualTo("zhangsan");
        UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria1)
                .addCriteria(criteria2)
                .orderByClause(UserDetailsExample.OrderByCriteria.builder().orderByIdDesc())
                .build();

        Page<?> page = PageHelper.startPage(2,2);
        /**
         * 2018-09-19 16:15:23.492 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] ==>  Preparing: SELECT count(0) FROM user_details WHERE (id > ?) OR (nick_name = ?)
         2018-09-19 16:15:23.496 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] ==> Parameters: 2(Long), zhangsan(String)
         2018-09-19 16:15:23.496 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] <==      Total: 1
         2018-09-19 16:15:23.497 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( id > ? ) or( nick_name = ? ) order by id desc LIMIT 2
         2018-09-19 16:15:23.500 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] ==> Parameters: 2(Long), zhangsan(String)
         2018-09-19 16:15:23.501 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] <==      Total: 2
         获取第1页数据2条，共2条
         */
        /**
         * 2018-09-19 16:17:40.415 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] ==>  Preparing: SELECT count(0) FROM user_details WHERE (id > ?) OR (nick_name = ?)
         2018-09-19 16:17:40.541 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] ==> Parameters: 2(Long), zhangsan(String)
         2018-09-19 16:17:40.555 [DEBUG][main]:c.d.g.m.t.U.getUserDetailsByPage_COUNT [debug:159] <==      Total: 1
         2018-09-19 16:17:40.557 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( id > ? ) or( nick_name = ? ) order by id desc LIMIT 2,2
         2018-09-19 16:17:40.559 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] ==> Parameters: 2(Long), zhangsan(String)
         2018-09-19 16:17:40.560 [DEBUG][main]:c.d.g.m.t.UserDetailsMapper.getUserDetailsByPage [debug:159] <==      Total: 0
         */
        List<UserDetailsEntity> byPage = userDetailsMapper.getUserDetailsByPage(example);
        Assert.assertNotNull(byPage);
        System.out.println(String.format("获取第%d页数据%d条，共%d条", page.getPageNum(), page.getPageSize(), page.getTotal()));
    }
}
