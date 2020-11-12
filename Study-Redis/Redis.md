# 本项目为基本的 Redis demo

## 基本情况

* JDK：1.8.0_181
* jedis：2.9.0
* redis：
* one包为单机demo

## 基本数据类型与命令行

### 通用命令行：

* **DEL key [key ...]** 删除
* **MOVE key db** 将key移动到某个db
* **EXISTS key** 查看key是否存在
* **EXPIRE key seconds** 设置key过期时间
* **KEYS pattern** 过滤key
* **TTL key** 获取对应key的过期时间

### 字符串（Strings）

* **SET key value [EX seconds] [PX milliseconds] [NX|XX]**
  
  1. EX 设置过期时间 秒
  2. PX 设置过期时间 毫秒
  3. NX 当key不存在时才设置
  4. XX 当key存在时才设置
  5. return 成功：OK 失败：nil（null）
  
* **GET key**
  1. 获取value
  2. 存在key，返回value，否则返回null
  
* **SETEX key seconds value**
  
  1.  等同于SET EX
  
* **SETNX key value**
  1. 等同于SET NX
  2. 不建议使用，因为无法设置过期时间，一般使用SET 命令
  
* **INCR key**/**INCRBY key increment**/**INCRBYFLOAT key increment**
  1. 为key加1/INCRBY则增加指定数值/增加浮点数
  2. 如果key不存在，会先将 `key` 的值设为 `0` ，再执行加法操作
  
* **DECR key**/**DECRBY key decrement**
  
  1. 为key减1
  2. 如果key不存在，会先将 `key` 的值设为 `0` ，再执行减法法操作
  
* **MSET key value [key value ...]**
  
  1. 批量设置 kv
  
* **MGET key [key ...]**

  1. 批量获取key

  

### 散列（hashes->map）

* **HSET key field value**/**HMSET key field value [field value ...]**
  1. 成功返回1或者N，失败返回0
* **HGET key field**/**HGETALL key**
  1. 通过key field 获取 value
  2. 通过key获取所有的field和value，在java中是一个Map<String,String>

### 列表（lists）

* **LPUSH key value [value ...]**/**RPUSH key value [value ...]**

  1. 从左或者从右侧，往list中放入一个value
  2. 当key不存在，则创建一个队列

* **LRANGE key start stop**

  1. 从左边获取某list的value
  2. 没有Rrange命令

* **LPOP key**/**RPOP key**

  1. 弹出左/右的第一个元素

* **LINDEX key index**

  1. 获取从左边开始第index个value

* **LPUSHX key value**/**RPUSHX key value**

  1. 从左边/右边插入一个value
  2. 当有且是队列时候才会插入，否则不插入

* **LREM key count value**

  1. count >0 从表头开始搜索，移除与value相等的count个
  2. count<0 从表尾开始搜索，移除与value相等的count的绝对值个
  3. count=0 移除所有value

* **LTRIM key start stop**

  1. 只保留 start stop区间的value
  2. -1表示倒数1，-2表示倒数2

* **LSET key index value**

  1. 设置某个位置的value
  2. 超出范围或者list不存在，报错

* **LINSERT key BEFORE|AFTER pivot value**

  1. 插入到某个value前/后

  

### 集合（sets）

set是一个集合，自动除重

* **SADD key member [member ...]**

  1. 往一个集合里面添加N个value

* **SCARD key**

  1. 返回set中个数

* **SDIFF key [key ...]**/**SDIFFSTORE destination key [key ...]**

  1. 返回差集

  2. 将差集返回，并且保存到destination中（覆盖，当destination不是set时，不进行覆盖，单貌似会删除原始值）

* **SINTER key [key ...]**/**SINTERSTORE destination key [key ...]**
  1. 返回交集
  2. 将交集返回，并且保存到destination中（覆盖，当destination不是set时，不进行覆盖，单貌似会删除原始值）

### 有序集合（sorted sets）

* **ZADD key score member [[score member] [score member] ...]**
  1. 向集合里面添加value，score是其权重
* **ZRANGE key start stop [WITHSCORES]**
  1. 返回排序，score升序
  2. start stop 包含关系，并且为index
  3. stop 为-1表示是最后一个元素，-2倒数第二个
* **ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]**
  1. min max 是score的值区间
  2. WITHSCORES是返回带score
  3. LIMIT为取几个
* **ZRANK key member**
  1. 返回排名，升序排列

## 特殊数据类型

### bitmaps

可以用于布隆过滤器

* **SETBIT key offset value**
  1. 这个相当于一个hash表，无限长度
  2. offset相当于下标值
* **GETBIT key offset**
* **BITCOUNT key [start end]**
  1. 统计1的个数

### hyperloglogs

基数：

比如数据集 {1, 3, 5, 7, 5, 7, 8}， 那么这个数据集的基数集为 {1, 3, 5 ,7, 8}, 基数(不重复元素)为5。 基数估计就是在误差可接受的范围内，快速计算基数。

存在误差： 0.81%

特点：占用内存小

用途：

1. 统计UV数量
2. 统计在线用户数
3. 统计....

一般处理统计相关，且数据量大，且容忍一定误差。

* **PFADD key element [element ...]**

  1. 往集合里面添加基数
  2. 重复元素则不会被放入

* **PFCOUNT key [key ...]**

  1. 计算某个pf集合个数
  2. 多个key则求并集个数

* **PFMERGE destkey sourcekey [sourcekey ...]**

  1. 合并多个pf集合
  2. 求并集

  

### geospatial地理位置

附近的人

* **GEOADD key longitude latitude member [longitude latitude member ...]**

  1. longitude 经度
  2. latitude 纬度
  3. member value

* **GEOPOS key member [member ...]**

  1. 获取member的经纬度信息

* **GEODIST key member1 member2 [unit]**

  1. 计算两个位置之间的直线距离
  2. 通过经纬度计算
  3. unit可选 m（米） km（千米） mi（英里）ft（英尺）

* **GEORADIUS key longitude latitude radius m|km|ft|mi [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count]**

  1. 通过当前经纬度，查询附近的人
  2. radius指定直线距离
  3. m|km|ft|mi 指定距离单位
  4. WITHCOORD 返回经纬度信息
  5. WITHDIST返回直线距离信息
  6. WHTHHASH
  7. COUNT 返回个数

  

## Redis事务

ACID

* A:原子
* C:一致
* I:隔离
* D:持久

redis的事务只有

redis中的事务：一次性、顺序性、排他性地执行一些命令。

* 开启事务
  1. **MULTI** 开启事务命令
* 命令入队
  1. 在命令队列中，及时有书写错的命令，也不会有问题
* 执行事务
  1. **EXEC** 执行命令

* 放弃事务
  1. **DISCARD**
  2. 放弃事务之后，队列中的命令都不会被执行
* 
* 
## LUA脚本

## Redis发布/订阅

## Redis持久化

