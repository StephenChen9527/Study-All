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

![image-20201114143930358](img/image-20201114143930358.png)

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

存在误差

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

### ACID

* A:原子
* C:一致
* I:隔离
* D:持久

### redis中的事务：一次性、顺序性、排他性地执行一些命令。

* 开启事务
  1. **MULTI** 开启事务命令
* 命令入队
  1. 在命令队列中，及时有书写错的命令，也不会有问题
* 执行事务
  1. **EXEC** 执行命令
* 放弃事务
  1. **DISCARD**
  2. 放弃事务之后，队列中的命令都不会被执行
* 监视器
  1. **WATCH key [key ...]**
  2. **UNWATCH**  事务失败需要解锁
### 用途：



## LUA脚本



## Redis发布/订阅

- 监听到订阅模式接受到消息时的回调 (onPMessage)
- 监听到订阅频道接受到消息时的回调 (onMessage )
- 订阅频道时的回调( onSubscribe )
- 取消订阅频道时的回调( onUnsubscribe )
- 订阅频道模式时的回调 ( onPSubscribe )
- 取消订阅模式时的回调( onPUnsubscribe )

可以使用基于发布订阅模式做一个：配置中心， list+发布订阅=队列

### 配置中心



### 消息队列



## Redis集群

### 单机模式

Redis最开始是单机模式，只有一个节点对外提供服务

### 主从复制

为了避免只有一个节点，当节点挂掉之后服务不可用的情况，需要另外一个节点作为备用，当主节点不可用的时候，将手动调整连接至备用节点。

备用节点相当于数据的冗余。

![7896890-c48d255bc0b13672](img/7896890-c48d255bc0b13672.gif)



当主节点挂掉之后，登录从节点，`slaveof no one`将该节点提升为主节点，当还有其他从节点时，可以使用相同命令，设置从被提升的节点进行复制，当挂掉节点启动的时候，可以使用`slaveof`从当前主节点复制即可。

不过这里客户端的连接需要重新断开，然后连接新的master节点，因为其他所有的备用节点都是只读的。



### Redis  Sentinel（哨兵模式）

虽然主从复制仍然需要手动去更改连接地址，因此产生了哨兵模式，哨兵模式，就是有一个哨兵，定时去查看Redis主节点和备用节点的情况，当主节点宕机的时候，哨兵会自动切换到备用节点，让备用节点继续对外提供服务，有点类似于注册中心的感觉。

![7896890-de8d9ce9e77bf211](img/7896890-de8d9ce9e77bf211.gif)

哨兵节点：特殊的redis节点，不存储数据，定时发送命令，让Redis各个实例返回其运行状态，当哨兵检测到Master节点宕机的时候，会将slave切换成master，通过`发布订阅模式`通知其他slave节点，修改配置（saveof命令），让他们切换主机。

但是发现当节点故障迁移的时候，如果哨兵节点与redis在本机，那么将会把master的ip改为一个127.0.0.1。导致远程无法正常连接，应该是需要一个域名映射或者是不能在一个主机上？

问题待解决。

多哨兵监控：

单个哨兵监控可能出现问题，因此可以使用多个哨兵进行监控，各个哨兵之间页可以进行监控。

#### 故障迁移

`故障切换（failover）`的过程。假设主服务器宕机，哨兵1先检测到这个结果，系统并不会马上进行failover过程，仅仅是哨兵1主观的认为主服务器不可用，这个现象成为主观下线。当后面的哨兵也检测到主服务器不可用，并且数量达到一定值时，那么哨兵之间就会进行一次投票，投票的结果由一个哨兵发起，进行failover操作。切换成功后，就会通过发布订阅模式，让各个哨兵把自己监控的从服务器实现切换主机，这个过程称为客观下线。这样对于客户端而言，一切都是透明的。

#### 主机挑选

**故障转移操作的第一步** 要做的就是在已下线主服务器属下的所有从服务器中，挑选出一个状态良好、数据完整的从服务器，然后向这个从服务器发送 `slaveof no one` 命令，将这个从服务器转换为主服务器。但是这个从服务器是怎么样被挑选出来的呢？

简单来说 Sentinel 使用以下规则来选择新的主服务器：

1. 在失效主服务器属下的从服务器当中， 那些被标记为主观下线、已断线、或者最后一次回复 PING 命令的时间大于五秒钟的从服务器都会被 **淘汰**。
2. 在失效主服务器属下的从服务器当中， 那些与失效主服务器连接断开的时长超过 down-after 选项指定的时长十倍的从服务器都会被 **淘汰**。
3. 在 **经历了以上两轮淘汰之后** 剩下来的从服务器中， 我们选出 **复制偏移量（replication offset）最大** 的那个 **从服务器** 作为新的主服务器；如果复制偏移量不可用，或者从服务器的复制偏移量相同，那么 **带有最小运行 ID** 的那个从服务器成为新的主服务器。

### Redis Cluster（redis真集群模式）

![7896890-516eb4a9465451a6](img/7896890-516eb4a9465451a6.png)

Redis Cluster典型架构，集群中的每一个Redis任意两个节点都相连，客户端连接到集群的任意一台，就可以对其他Redis节点进行读写操作

在这种模式下，有一个重要的概念：

Redis槽位

Redis中内置了`16384`个槽位（2的14次方。2字节16位，下面有详细介绍），这`16384`个槽位会分散在不同的节点之上。

当客户端连接到Redis集群之后，就会得到一分关于这个集群配置的信息，当客户端对某个key进行操作的时候，会先计算出这个key的`Hash`值，然后把这个结果对`16384`求余数，然后根据余数，如果该key不属于当前节点的槽位，则会使用`MOVED`命令进行redis节点跳转，去对应的节点进行操作。

集群模式的主要作用：

1. **数据分区：** 数据分区 *(或称数据分片)* 是集群最核心的功能。集群将数据分散到多个节点，**一方面** 突破了 Redis 单机内存大小的限制，**存储容量大大增加**；**另一方面** 每个主节点都可以对外提供读服务和写服务，**大大提高了集群的响应能力**。Redis 单机内存大小受限问题，在介绍持久化和主从复制时都有提及，例如，如果单机内存太大，`bgsave` 和 `bgrewriteaof` 的 `fork` 操作可能导致主进程阻塞，主从环境下主机切换时可能导致从节点长时间无法提供服务，全量复制阶段主节点的复制缓冲区可能溢出……
2. **高可用：** 集群支持主从复制和主节点的 **自动故障转移** *（与哨兵类似）*，当任一节点发生故障时，集群仍然可以对外提供服务。

redis-cli --cluster create --cluster-replicas 1 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005

--replicas 1 希望集群中的每个主节点创建一个从节点

#### 数据分区方案简析

##### 哈希分区方案简析

##### 一致性哈希分区



## Redis持久化

### RDB

rdb是将Redis中的内存数据保存到一个RDB文件中，也叫`快照持久化`，内容主要保存的KV

#### 触发机制：save，bgsave

* save 命令会阻塞Redis服务器主进程，直到文件创建完毕为止
* bgsvae 会frok

#### 自动触发：配置文件

```bash
save 900 1  # 900S内 有1个key变化
save 300 10 # 300S内 有10个key变化
save 60 10000  # 60S内 有1W个key变化
```

RDB文件是为了在Redis服务器进程重新启动以后，还原之前存储在Redis中的数据，没有专门命令去加载RDB文件，一般伴随这Redis服务器启动时自动加载。

#### 生成RDB文件的过程

1. Redis主进程fork出一份子进程，fork完成之后，主进程继续对外提供读写服务，子进程去执行save操作
2. 由于主进程可能随时会更改内存数据结构，因此子进程保存的数据并不是实时的，保存期间的其他更改操作会丢失数据

知识点：fork()，exec()

由于这数据并不是最新的，因此rdb的文件也叫`快照`

#### 优点

1. RDB是一个紧凑的文件，保存了某个时间节点上的所有数据集，适合用于备份
2. RDB在恢复的时候比较快
3. fork出子进程来处理所有保存工作，主进程不需要任何磁盘IO操作

#### 缺点

1. RDB无法做到实时持久化
2. 每次bgsave过程都要fork子进程，频繁创建成本过高。
3. fork之后如果有大量更新操作，会产生大量内存分页错误（cory on write机制）

#### 图

![image-20201112151424003](img/image-20201112151424003.png)



### AOF

AOF文件主要保存的是Redis服务器的写命令，当服务器重启，Redis只要从头到尾执行一次AOF文件包含的命令，就能恢复到最新的数据集了。

#### 基本命令

```bash
appendonly yes #开启aof 默认为 no

appendfsync always #表示每次更新操作后手动调用fsync()将数据写入到磁盘
appendfsync everysec #表示每秒同步一次(折中方案，默认值)
appendfsync no  #表述等操作系统进行数据缓存同步到磁盘(快速响应客户端，不对AOF做数据同步，同步文件由操作系统负责，通常同步周期最长30S)
```

#### 载入AOF文件

1. 创建一个伪客户端
2. 从AOF文件中读取一条命令
3. 写入到Redis服务器中
4. 重复2、3直到文件结束

#### 优点

1. 可以保存秒级的命令，数据的实时性更高
2. 采用append模式，及时在写入过程中宕机，也不会破坏日志文件中已经存在的内容

#### 缺点

1. 由于需要每秒保存更新命令，日积月累会导致AOF文件较大。
2. 恢复速度不如RDB文件

AOF重写

由于AOF过大，因此引入了AOF重写机制，压缩AOF文件体积

```bash
auto-aof-rewrite-percentage 100 # 默认 100 当目前aof 文件大小超过上一次重写的aof 文件大小的百分之多少进行重写，即当aof 文件增长到一定大小的时候，Redis 能够调用bgrewriteaof对日志文件进行重写。当前AOF 文件大小是上次日志重写得到AOF 文件大小的二倍（设置为100）时，自动启动新的日志重写过程。
auto-aof-rewrite-min-size 64mb #设置允许重写的最小aof 文件大小，避免了达到约定百分比但尺寸仍然很小的情况还要重写
```

```bash
set a a
set a b
set a c
```

假设有以上命令，则AOF会直接生成3条命令进行保存，当体积过大时候，就会对内存遍历，将内存中的KV键值对，保存成相对应的命令，取代之前的多条`SET`记录，成为`SET a c`这条命令。

##### 需要注意的点

1. redis不希望在AOF重写期间阻塞主线程，因此原理基本与生成RDB文件类似，fork出子进程，然后主进程继续提供服务，子进程会进行AOF命令
2. 由于主线程同时也在对外提供读写服务，必定会导致数据变更，由于fork出的子进程也是类似于快照内存，因此需要主线程启动一个AOF重写缓冲区，将重写期间的命令写入缓冲区中，当子线程更新完AOF文件之后，主线程再将AOF缓冲区的重写命令添加到重写的AOF文件，最后再替换旧的AOF文件，完成重写

![image-20201112230746289](img/image-20201112230746289.png)

### 混合持久化

Redis 4.0以后支持混合持久化，