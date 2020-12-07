# 本项目为基本的 Redis demo

## 基本情况

* JDK：1.8.0_181
* MyBatis：3.4.6

## MyBatis简介

不需要使用容器，直接使用XML配置，生成一个SqlFactory，得到一个SqlSession

```xml
<environments default="develop">
        <environment id="develop">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://local.musql.server/mybatis"/>
                <property name="username" value="mysql007"/>
                <property name="password" value="QWERasdf00."/>
            </dataSource>
        </environment>
    </environments>
```

default 指定默认的连接，支持多环境，主要使用Java获取SqlSession的时候指定id

```java
SqlSessionFactory build(InputStream inputStream, String environment)
```

​	

SqlSessionFactory 只有一个实现类：DefaultSqlSessionFactory

调用openSession，获取sqlsession，sqlsession对象里面主要报过了Executor，传入sql。

### Executor

mybatis执行sql全靠Executor，主要有以下几个实现类：

* BaseExecutor
* BatchExector
* CachingExecutor
* ClosedExecutor
* ResuseExecutor
* SimpleExecutor

默认是`SimpleExecutor`

### typeHandlers

类型处理器

在JDBC预处理阶段（PreparedStatement），设置参数的时候，或者在返回结果时，处理返回结果，

在设置参数的时候需要用在：

```xml
#{pwd,typeHandler=com.nullbugs.mybatis.handler.PassWordByteHandler}
```

在处理返回数据的时候则：

```xml
<resultMap id="person" type="com.nullbugs.mybatis.pojo.Person">
    <id column="id" property="id"></id>
    <result column="name" property="name"></result>
    <result column="age" property="age"></result>
    <result column="sex" property="sex"></result>
    <result column="desci" property="desci" typeHandler="com.nullbugs.mybatis.handler.JsonHandler"></result>
    <result column="pwd" property="pwd" typeHandler="com.nullbugs.mybatis.handler.PassWordByteHandler"></result>
</resultMap>
```

需要用在resultMap中，当获取ResultSet的时候，会调用



奇怪的是，在parameterMap中，也有此配置，但是不管用。

老式风格的参数映射。此元素已被废弃，并可能在将来被移除！请使用行内参数映射。文档中不会介绍此元素。



注解

```java
@MappedTypes(MYHandler.class)
```







