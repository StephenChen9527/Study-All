# 本项目为基本的 Redis demo

## 基本情况

* JDK：1.8.0_181
* jedis：2.9.0
* redis：
* one包为单机demo

## 基本数据类型

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
* 的
* 的
* 的
* 的
* aaa
* aa
* a
* a
* 
* 的
* 

### 集合（sets）

### 有序集合（sorted sets）

## 高级数据类型

### 