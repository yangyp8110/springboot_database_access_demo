package com.demo.gener.mapper.test;

import com.demo.gener.entity.test.UserDetailsEntity;
import com.demo.gener.entity.test.predicate.UserDetailsExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
public interface UserDetailsMapper{
    /**
     * 根据id查询
     * @param id 查询的id
     * @return
     */
    UserDetailsEntity getByPrimaryKey(Long id);

    /**
     * 根据ids查询
     * @param ids 查询id的集合
     * @return
     */
    List<UserDetailsEntity> getByPrimaryIds(@Param("ids") List<Long> ids);

    /**
     * 根据条件查询
     * @param example 查询条件
     * @return
     */
    UserDetailsEntity getSingleByPredicate(UserDetailsExample example);

    /**
     * 根据条件查询所有
     * @param example 查询条件
     * @return
     */
    List<UserDetailsEntity> getAllByPredicate(UserDetailsExample example);

    /**
     * 根据条件查询
     * @param example 查询条件
     * @return
     */
    int queryCount(UserDetailsExample example);

    /**
     * 分页查询(配合pageHelper)
     * @param example 查询条件
     * @return
     */
    List<UserDetailsEntity> getUserDetailsByPage(UserDetailsExample example);

    /**
     * 根据key更新除了key以外的其他字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(@Param("record") UserDetailsEntity record);

    /**
     * 按条件更新
     * @param record 需要更新的字段
     * @param example 需要满足的条件
     * @return
     */
    int updateByPredicate(@Param("record") UserDetailsEntity record, @Param("example")UserDetailsExample example);

    /**
     * 新增记录
     * @param entity 需要新增的entity
     * @return
     */
    int insert(UserDetailsEntity entity);

    /**
     * 批量新增
     * @param list 批量新增的entity
     * @return
     */
    int batchInsertUserDetails(@Param("list") List<UserDetailsEntity> list);
}
