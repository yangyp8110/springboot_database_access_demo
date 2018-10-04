springboot_data_access_demo是根据自定义模版生成的基于mybatis+mysql的数据库访问示例项目，吸取了mybatis generator的动态条件优势，同时又稍有扩展。
可以帮助生成简单易懂的sql，支持大部分单表操作，一般情况下不需要写sql，支持的方法有：
```java
    /**
     * 根据id查询
     * @param id 查询的id
     * @return
     */
    XXXXEntity getByPrimaryKey(Long id);

    /**
     * 根据ids查询
     * @param ids 查询id的集合
     * @return
     */
    List<XXXXEntity> getByPrimaryIds(@Param("ids") List<Long> ids);

    /**
     * 根据条件查询
     * @param example 查询条件
     * @return
     */
    XXXXEntity getSingleByPredicate(XXXXExample example);

    /**
     * 根据条件查询所有
     * @param example 查询条件
     * @return
     */
    List<XXXXEntity> getAllByPredicate(XXXXExample example);

    /**
     * 根据条件查询
     * @param example 查询条件
     * @return
     */
    int queryCount(XXXXExample example);

    /**
     * 分页查询(配合pageHelper)
     * @param example 查询条件
     * @return
     */
    List<XXXXEntity> getXXXXByPage(XXXXExample example);

    /**
     * 根据key更新除了key以外的其他字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(@Param("record") XXXXEntity record);

    /**
     * 按条件更新
     * @param record 需要更新的字段
     * @param example 需要满足的条件
     * @return
     */
    int updateByPredicate(@Param("record") XXXXEntity record, @Param("example")XXXXExample example);

    /**
     * 新增记录
     * @param entity 需要新增的entity
     * @return
     */
    int insert(XXXXEntity entity);

    /**
     * 批量新增
     * @param list 批量新增的entity
     * @return
     */
    int batchInsertXXXX(@Param("list") List<XXXXEntity> list);
```
