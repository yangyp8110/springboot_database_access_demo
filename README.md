springboot_data_access_demo是根据自定义模版生成的基于mybatis+mysql的数据库访问示例项目，吸取了mybatis generator的动态条件优势，同时又稍有扩展。
可以帮助生成简单易懂的sql，支持大部分单表操作，一般情况下不需要写sql，支持的方法有：
```java
    /** 根据id查询 */
    XXXXEntity getByPrimaryKey(Long id);

    /** 根据ids查询 */
    List<XXXXEntity> getByPrimaryIds(@Param("ids") List<Long> ids);

    /** 根据条件查询 */
    XXXXEntity getSingleByPredicate(XXXXExample example);

    /** 根据条件查询所有 */
    List<XXXXEntity> getAllByPredicate(XXXXExample example);

    /** 根据条件查询 */
    int queryCount(XXXXExample example);

    /** 分页查询(配合pageHelper) */
    List<XXXXEntity> getXXXXByPage(XXXXExample example);

    /** 根据key更新除了key以外的其他字段 */
    int updateByPrimaryKey(@Param("record") XXXXEntity record);

    /** 按条件更新 */
    int updateByPredicate(@Param("record") XXXXEntity record, @Param("example")XXXXExample example);

    /** 新增记录 */
    int insert(XXXXEntity entity);

    /** 批量新增 */
    int batchInsertXXXX(@Param("list") List<XXXXEntity> list);
```
