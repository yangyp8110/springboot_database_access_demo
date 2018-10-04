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

## 使用示例
1、根据主键获取（XXXXEntity getByPrimaryKey(Long id)）
```java
    UserDetailsEntity byPrimaryKey = userDetailsMapper.getByPrimaryKey(1l);
    Assert.assertNotNull(byPrimaryKey);
```
输出示例：
```
    test.UserDetailsMapper.getByPrimaryKey [debug:159] ==>  Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details where id = ?
    test.UserDetailsMapper.getByPrimaryKey [debug:159] ==>  Parameters: 1(Long)
    test.UserDetailsMapper.getByPrimaryKey [debug:159] <==  Total: 1
```
2、根据条件查询所有（List<XXXXEntity> getByPrimaryIds(@Param("ids") List<Long> ids)）
```java
    ArrayList<Long> ids = Lists.newArrayList(1L, 2L);
    List<UserDetailsEntity> byPrimaryIds = userDetailsMapper.getByPrimaryIds(ids);
    Assert.assertEquals(byPrimaryIds.size(), ids.size()); 
```
输出示例：
```
   test.UserDetailsMapper.getByPrimaryIds [debug:159] ==> Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details where id in ( ? , ? )
   test.UserDetailsMapper.getByPrimaryIds [debug:159] ==> Parameters: 1(Long), 2(Long)
   test.UserDetailsMapper.getByPrimaryIds [debug:159] <== Total: 2
```
3、根据条件查询（XXXXEntity getSingleByPredicate(XXXXExample example)）
```java
    UserDetailsExample.Criteria criteria = UserDetailsExample.buildCriteria()
                .andNickNameEqualTo("zhangsan").andUidEqualTo(1L);
    UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria)
                .orderByClause(UserDetailsExample.OrderByCriteria.builder().orderByIdDesc())
                .build();
    UserDetailsEntity singleByPredicate = userDetailsMapper.getSingleByPredicate(example);
    Assert.assertNotNull(singleByPredicate); 
```
输出示例
```
    test.UserDetailsMapper.getSingleByPredicate [debug:159] ==> Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( nick_name = ? and u_id = ? ) order by id desc limit 1
    test.UserDetailsMapper.getSingleByPredicate [debug:159] ==> Parameters: zhangsan(String), 1(Long)
    test.UserDetailsMapper.getSingleByPredicate [debug:159] <== Total: 1
```
4、根据条件查询所有（List<XXXXEntity> getAllByPredicate(XXXXExample example)）
```java
    UserDetailsExample.Criteria criteria = UserDetailsExample.buildCriteria()
                .andIdGreaterThanOrEqualTo(1L);
    UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria)
                .orderByClause(UserDetailsExample.OrderByCriteria.builder().orderByIdDesc())
                .build();
    List<UserDetailsEntity> allByPredicate = userDetailsMapper.getAllByPredicate(example);
    Assert.assertNotNull(allByPredicate);
```
输出示例
```java
    test.UserDetailsMapper.getAllByPredicate [debug:159] ==> Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( id >= ? ) order by id desc
    test.UserDetailsMapper.getAllByPredicate [debug:159] ==> Parameters: 1(Long)
    test.UserDetailsMapper.getAllByPredicate [debug:159] <== Total: 3
```
5、分页查询(配合pageHelper)（List<XXXXEntity> getXXXXByPage(XXXXExample example)）
```java
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
```
输出示例
```
    getUserDetailsByPage_COUNT [debug:159] ==> Preparing: SELECT count(0) FROM user_details WHERE (id > ?)
    getUserDetailsByPage_COUNT [debug:159] ==> Parameters: 1(Long)
    getUserDetailsByPage_COUNT [debug:159] <== Total: 1
    test.UserDetailsMapper.getUserDetailsByPage [debug:159] ==> Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( id > ? ) order by id desc LIMIT 2
    test.UserDetailsMapper.getUserDetailsByPage [debug:159] ==> Parameters: 1(Long)
    test.UserDetailsMapper.getUserDetailsByPage [debug:159] <== Total: 2
    获取第1页数据2条，共2条     
```
6、根据key更新除了key以外的其他字段（updateByPrimaryKey(@Param("record") XXXXEntity record)）
```java
    UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
    userDetailsEntity.setUserDesc("a senior school teacher");
    userDetailsEntity.setId(key);
    userDetailsMapper.updateByPrimaryKey(userDetailsEntity);
```
输出示例
```
    test.UserDetailsMapper.updateByPredicate [debug:159] ==>  Preparing: UPDATE user_details SET user_desc = ? WHERE ( id = ? )
    test.UserDetailsMapper.updateByPredicate [debug:159] ==> Parameters: a senior school teacher(String), 2(Long)
    test.UserDetailsMapper.updateByPredicate [debug:159] <==    Updates: 1     
```
7、按条件更新（updateByPredicate(@Param("record") XXXXEntity record, @Param("example")XXXXExample example)）
```java
    UserDetailsExample.Criteria criteria = UserDetailsExample.buildCriteria()
                .andIdEqualTo(key).andUserDescEqualTo(userDetailsEntity.getUserDesc());
    UserDetailsExample example = UserDetailsExample.builder()
                .addCriteria(criteria)
                .build();

    userDetailsEntity.setNickName("hello kitty");
    userDetailsMapper.updateByPredicate(userDetailsEntity, example);
```
输出示例
```
   test.UserDetailsMapper.updateByPredicate [debug:159] ==> Preparing: UPDATE user_details SET nick_name = ?, user_desc = ? WHERE ( id = ? and user_desc = ? )
   test.UserDetailsMapper.updateByPredicate [debug:159] ==> Parameters: hello kitty(String), a senior school teacher(String), 2(Long), a senior school teacher(String)
   test.UserDetailsMapper.updateByPredicate [debug:159] <== Updates: 1
```
8、新增记录（insert(XXXXEntity entity)）
字段为null的不赋值，默认取数据库列的默认值。
```java
    UserDetailsEntity zhangSan = new UserDetailsEntity();
    zhangSan.setUid(1L);
    zhangSan.setNickName("zhangsan");
    zhangSan.setUserDesc("a teacher");
    
    userDetailsMapper.insert(zhangSan)
```
输出示例
```
    test.UserDetailsMapper.batchInsertUserDetails [debug:159] ==> Preparing: insert into user_details ( u_id,nick_name,user_desc,inserttime,updatetime ) values ( ?,?,?,?,? )
    test.UserDetailsMapper.batchInsertUserDetails [debug:159] ==> Parameters: 1(Long), zhangsan(String), a teacher(String), null, null
    test.UserDetailsMapper.batchInsertUserDetails [debug:159] <== Updates: 1
```
9、批量新增（batchInsertXXXX(@Param("list") List<XXXXEntity> list)）
```java
    List<UserDetailsEntity> list = new ArrayList<>();

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
    
    userDetailsMapper.batchInsertUserDetails(list);
```
输出示例
```
    test.UserDetailsMapper.batchInsertUserDetails [debug:159] ==> Preparing: insert into user_details ( u_id,nick_name,user_desc,inserttime,updatetime ) values ( ?,?,?,?,? ) , ( ?,?,?,?,? )
    test.UserDetailsMapper.batchInsertUserDetails [debug:159] ==> Parameters: 2(Long), lisi(String), a python coder(String), null, null, 3(Long), wangwu(String), a doctor(String), null, null
    test.UserDetailsMapper.batchInsertUserDetails [debug:159] <== Updates: 2   
```
10、OR条件
```java
    UserDetailsExample example = UserDetailsExample.builder()
                //下面两个Criteria是OR的关系
                .addCriteria(UserDetailsExample.buildCriteria().andIdGreaterThan(2L))
                .addCriteria(UserDetailsExample.buildCriteria().andNickNameEqualTo("zhangsan"))
                .orderByClause(UserDetailsExample.OrderByCriteria.builder().orderByIdDesc())
                .build();
```
输出示例：
```
    getUserDetailsByPage_COUNT [debug:159] ==> Preparing: SELECT count(0) FROM user_details WHERE (id > ?) OR (nick_name = ?)
    getUserDetailsByPage_COUNT [debug:159] ==> Parameters: 2(Long), zhangsan(String)
    getUserDetailsByPage_COUNT [debug:159] <== Total: 1
    test.UserDetailsMapper.getUserDetailsByPage [debug:159] ==> Preparing: select id,u_id,nick_name,user_desc,inserttime,updatetime from user_details WHERE ( id > ? ) or( nick_name = ? ) order by id desc LIMIT 2
    test.UserDetailsMapper.getUserDetailsByPage [debug:159] ==> Parameters: 2(Long), zhangsan(String)
    test.UserDetailsMapper.getUserDetailsByPage [debug:159] <== Total: 2
```
