# REDIS 

## 概念与介绍
* 优点
> redis是高性能的key-value服务器. 支持多种数据结构, 有很丰富的功能, 支持高可用分布式.
* redis是什么?
> 基于键值对的存储服务系统. 是数据库. C语言编写. 支持多种数据结构. 高性能, 功能丰富
* redis支持的数据结构
1. String
2. HashTable (Object)
3. LinkedList
4. Set
5. SortedSet
* Redis特性
1. 速度快: 数据存储在内存中, 单线程模型.(多线程会成为并发的瓶颈)
2. 持久化: 断电不丢失数据.redis所有数据都保存在内存中, 数据的更新会异步保存到磁盘上.(RDB, AOF)
3. 支持多种数据结构: 除了以上的5种数据结构, 还支持
> BitMaps(位图,小内存实现高效存储,本质是字符串)  
> HyperLogLog(超小内存唯一值计数,缺点是有误差, 本质是字符串)  
> GEO(地理位置定位, 计算经度纬度等, 本质是集合)  
4. 支持多种客户端语言
5. 功能丰富: 发布订阅、lua脚本、事务功能、pipeline
6. 简单: 不依赖外部的库, 单线程模型, 无论服务端客户端的开发都比较容易
7. 主从复制: 主服务器数据可以复制到从服务器上.
8. 高可用分布式支持
* Redis典型使用场景
1. 缓存系统
2. 计数器, 比如微博的评论和转发计数
3. 消息队列系统
4. 排行榜
5. 社交网络
6. 实时系统, 垃圾邮件处理系统等
* Redis通用命令
1. keys * : 一般不在生产环境使用, 比较重的命令.阻塞其他命令. (有使用场景的话用scan替代)
2. dbsize : 计算key的总数
3. exist: 检查key是否存在(注意使用的问题???)
4. del: 删除指定key
5. ttl: 查看key剩余过期时间
6. persist key: 去掉key的过期时间  
7. type: 查看key是哪种数据类型  
* Redis 的数据结构和内部编码  
> redis内部实现的数据结构, 和我们外部了解的不一样. redis内部通过redisObject结构体进行转换  
* Redis 单线程  
> redis在一个瞬间, 只会执行一个命令, 单线程执行的.  
* Redis单线程为什么很快?
> 主要原因是纯内存  
> 非阻塞IO  
> 避免线程换和竞态消耗.  
---
## Redis数据结构
注: 每种数据类型,从以下4个维度进行①结构与命令②内部编码③快速实战④命令查漏补缺  
* Redis数据结构之String字符串  
1. 键值结构
> 字符串的value不能大于512m, 一般建议在100k以内, 太大影响效率       
2. 场景
> 缓存, 计数, 分布式锁等   
3. 命令
> get: 获取key对应的value   
> set: 设置key-value, 不管key是否存在都设置   
> setnx: key不存在, 才设置   
> set key value xx: key存在, 才设置, 相当于update   
> mset, mget: 批量操作  
> del: 删除key-value    
> incr: 自增1  
> decr: 自减1  
> incrby: 自增k.  
> decrby: 自减k.  
> getset: set key newvalue 并返回旧的value   
> append: 将value追加到旧的value    
> strlen: 返回字符串的长度   
> incrbyfloat key 3.5: 增加key对应的值3.5   

* Redis数据结构之hash  
1. 键值结构. key field value 结构. 相当于对象, field不能相同, value可以相同.  
> key: 键   
> field: 属性  
> value: 值  
2. 场景
> 在redis中保存对象.  
3. 命令
> hget key field: 获取hash key对应field的value  
> hset key field value: 设置hash key 对应field的value  
> hdel key field: 删除key对应field的value  
> hexists key field: 判断hash key是否含有field  
> hlen key: 获取hash key field 的数量  
> hmget key field1 field2... 批量获取hash key 的一批field对应的值  

* Redis数据结构之list  
1. 特点: 有序, 可以重复, 左右两边插入弹出.  
2. 场景

> redis中保存数据到集合, 比如微博按照时间列表进行排序  
3. 命令
> rpush key value1 value2 value3... valueN: 从列表右端插入值(1-N)  
> lpush key value1 value2 value3... valueN: 从列表左端插入值(1-N)  
> linsert key before|after value newValue: 在list指定的值前后插入newValue   
> rpop key: 从列表右侧弹出一个item  
> lrem key count value: 根据count的值, 删除所有value相等的元素    
> ltrim key start end: 按照索引范围修剪列表  
> lrange key start end(包含end): 获取列表指定索引范围所有item   

* Redis数据结构之set
1. 特点: 无序, 无重复, 集合间操作.  

---
## Redis主从复制

* 什么是主从复制?
1. redis单机模式会有一系列问题, 比如机器故障, 容量瓶颈, QPS瓶颈等等.(QPS:每秒查询率)
2. 主从复制, 在主机宕机的情况下, 提供一些支援.
3. 一主多从, 实现读写分离, 进行流量的分流和负载均衡.
4. mater: 主. slave:从. 从节点复制主节点的数据.

* 主从复制的作用是什么?
1. 数据副本: 高可用、分布式的基础 
2. 拓展读性能

* 主从复制的总结
1. 一个master可以有多个slave
2. 一个slave只能有一个master
3. 数据流向是单向的, master到slave

* 主从复制的实现(主从不能在同一台机器上)
1. slaveof 命令 : slaveof 127.0.0.1 6379. 优点: 无需重启, 缺点: 不便于管理
> 取消复制命令: slaveof no one
2. 配置文件: 在redis启动之前, 修改redis的配置文件. 优点: 统一配置, 缺点: 需要重启

* 主从复制的问题?
1. 主节点出现问题, 需要手工去处理, 就算不是手工处理也需要写脚本的. (手动故障转移)
2. 写只能写在一个节点上. (写能力和存储能力受限)
> 拓展: 如何实现故障转移? 
> 首先, 客户端要选择一个slave , 执行命令slaveof no one, 让他成为一个master.对其余的slave执行slaveof new master 命令.问题就是这个故障转移是完全依赖手工去完成.
  有几个点很复杂:
  1.怎么判断节点出问题. 
  2.怎么通知客户端. 
  3.怎么保证事务. 

---
## Redis Sentinel
* Redis Sentinel架构
1. 主从结构, 有许多的sentinel节点, sentinel节点可以判断当前redis的状态
2. 客户端并不关心谁是master, master挂掉了, sentienl会自动更换一个master, 客户端直接对新的master进行操作.
3. 很多个sentinel节点的原因也是高可用, 一个挂掉了, 还有其他sentinel节点

* sentinel故障转移原理
1. 多个sentinel发现并确认master有问题
2. 选举出一个sentinel作为领导
3. 选出一个slave作为master
4. 通知其余的slave成为新master的slave
5. 通知客户端主从变化
6. 等待老的master复活成为新的master的slave


## Redis 面试题
### 1.Memcache和Redis的区别
* Memcache: 代码层次类似Hash
1. 支持简单数据类型   
2. 不支持数据持久化存储  
3. 不支持主从   
4. 不支持分片  
* Redis 
1. 数据类型丰富  
2. 支持数据磁盘持久化存储
3. 支持主从同步
4. 支持分片

### 2.为什么Redis能这么快?
1. 100000 + QPS   
2. 完全基于内存, 绝大部分请求是纯粹的内存操作, 执行效率高   
3. 数据结构简单, 数据操作简单   
4. 单线程, 单线程能处理高并发请求, 想多核可以启动多实例.  
5. 使用多路I/O复用模型, 非阻塞IO

### 2.1 IO多路复用是什么意思?
redis是跑在单线程中的, 所有的操作是按照顺序线性执行, 但是由于读写操作等待用户输入或者是输出
是阻塞的, 所以IO操作一般不能直接返回. 
IO多路复用就可以解决这个问题.最重要的函数调用就是select, 能够监控多个文件描述符的可读可写情况, 
其中的文件描述符可读或者可写的时候, select方法就会返回文件描述符表示的可读或者可写的文件的个数.
> FD: File Descriptor, 文件描述符. 一个打开的文件通过唯一的描述符进行引用, 该描述符是打开文件的
元数据到文件本身的映射.(用一个整数来表示)

### 2.2 Redis会采用哪种IO多路复用函数?
* Redis采用的I/O多路复用函数: epoll/kqueue/evport/select?
* Redis因为在多个环境中可以运行, 所以会因地制宜选择适合的多路复用函数
* 会优先选择时间复杂度为O(1)的I/O多路复用函数作为底层实现
* 以事件复杂度为O(n)的select作为保底.
* 基于react设计模式监听I/O事件.

> react设计模式: 

### 3.说说你用过的Redis数据类型?
* String : 二进制安全, 可以保存任何数据, 最大是512M
* Hash: 适用于存储对象
* list: 列表, 按照String元素插入顺序排序(类似栈, 后进先出), 可以实现最新消息排行榜等功能
* Set: String元素组成的无序集合, 通过哈希表实现, 不允许重复 
场景: 比如关注是一个集合, 粉丝是一个集合, 可以
实现求交集等需求, 查到共同关注等信息.
* SortedSet: 通过分数来为集合中的成员进行从小到大的排序
(元素不可用是重复的, 但是分数可以是重复的)
> 命令: zrangebyscore 可以按照顺序显示按照分数进行排序的集合.
* 用于计数的hyperLoglog, 用于支持地理位置信息的GEO

### 4.从海量key里查询出某一固定前缀的key
* 首先, 要问面试官数据量到底有多大
* keys命令在数据量小的情况下可以用, 阻塞的.
* 可以用scan命令.  非阻塞.
> SCAN cursor[MATCH pattern] [COUNT count]  
> 基于游标的迭代器, 需要基于上一次的游标, 延续之前的迭代过程
> 以0作为游标开始一次新的迭代, 直到命令返回游标0完成一次遍历  
> 不保证每次执行都返回某个给定数量的元素, 支持模糊查询  
> 一次返回的数量不可控, 只能是大概率符合count参数

### 4.1 keys命令对线上业务的影响?
* keys pattern : 查找所有符合给定模式pattern的key
> keys命令执行,是一次性返回所有的key, key的数量过大, 会导致服务器卡顿.

### 5.如何通过Redis实现分布式锁?
* SETNX key value : 如果key不存在, 则创建并赋值.  操作是原子性的. 对某个key设置值, 
如果设置成功
说明没有现成占用, 可以作为独占的资源. 设置失败说明有别的代码或者是逻辑占用该资源.
> 时间复杂度: O(1)
> 返回值: 设置成功, 返回1; 设置失败, 返回0           


### 5.1 什么是分布式锁?
* 控制分布式系统, 或者是不同系统之间, 共同访问共享资源的一种锁的实现, 
如果不同的系统, 或者是一个系统的不同
主机之间共享了某个资源时, 需要互斥来防止彼此的干扰.进而保证一致性.
* 分布式锁需要解决的问题:
> 互斥性: 任意时刻只能有一个客户端获取到锁.
> 安全性: 只能由一个客户端删除, 不能由其他的客户端删除.
> 死锁: 获取了锁的客户端, 由于某些情况宕机, 而不能释放资源. 
其他客户端再也获取不到锁. 这时需要有机制避免类似情况的发生.  
> 容错: 部分节点, 比如某些redis节点宕机, 客户端仍然能够获取锁和释放锁.

### 5.2 如何解决SETNX长期有效的问题?
* SETNX没有过期时间的参数, 所以可以用命令 EXPIRE KEY SECONDS 
> 设置key的生存时间, 当Key过期时(生存时间为0), 会被自动删除.  
> 缺点: 原子性不能得到满足(如果在设置完key, 未设置过期时间时就挂了, 那么这个key就再也没有过期时间了)     
> 伪代码  
RedisService redisService = SpringUtils.getBean(RedisService.class);  
long status = redisService.setnx(key, "1");  
if(status == 1){    
    redisService.expire(key, expire);  
    //执行独占资源的逻辑   
    doOcuppiedWork();  
}

### 5.3 大量的key同时过期的注意事项?
* 集中过期, 由于清楚大量的key很耗时, 会出现短暂的卡顿的现象.
* 解决方案: 在设置key 的过期时间的时候, 给每个key加上随机值.

### 6.如何使用Redis做异步队列?
* 使用List 作为队列, RPUSH生成消息, LPOP消费消息
> 缺点: 没有等待队列中有值就直接消费.  
> 弥补: 可以通过在应用层引入Sleep机制去调用LPOP重试  
* 不想通过sleep方法去重试的话, 有没有方法. BLPOP key[key...] timeout:
 阻塞直到队列有消息或者是超时.
> blpop testlist 30 --> 意思是阻塞30秒, 直到队列中有消息.   
> 缺点: 只能供一个消费者消费
* pub/sub: 主题订阅者模式(一条消息供n个消费者消费, 
消费者也可以只获取到只需要他们关心的数据.消息的队列是无状态的.无法保证是否被消费 )
> 发送者(pub) 发送消息, 订阅者(sub)接收消息  
> 订阅者可以订阅任意数量的频道.  

### 7.Redis如何做持久化?
* RDB(快照)持久化: 保存某个时间点的全量数据快照.
> redis.conf进行rdb的配置, redis会定时将全量数据保存到dump.rdb文件当中.

> SAVE: 阻塞Redis的服务器进程, 直到RDB文件被创建完毕
> BGSAVE: Fork出一个子进程来创建RDB文件, 不阻塞服务器进程

### 7.1自动化触发RDB持久化方式?
* 根据redis.conf 配置里的SAVE m n 定时触发(用的是BGSAVE)
* 主从复制时, 主节点自动触发
* 执行Debug Reload
* 执行Shutdown且没有开启AOF持久化.

### 7.2BGSAVE原理
***** 图片: BGSAVE原理
![Image text](https://github.com/Fanxx7201/summary/blob/master/img/BGSAVE%E5%8E%9F%E7%90%86.png)

### 7.3 Copy-on-write
如果有多个调用者同时要求相同资源(如内存或者磁盘上的数据存储),
他们会共同获取相同的指针指向相同的资源,
直到某个调用者试图修改资源的内容时, 
系统才会真正复制一份专用副本给该调用者, 而其他调用者所见到的最初
的资源仍然保持不变.

### 7.4 RDB持久化方式的缺点?
* 内存数据的全量同步, 数据量大会由于I/O而严重影响性能
* 可能会因为Redis挂掉而丢失从当前至最近一次快照期间的数据.

### 8. AOF持久化(Append-Only-file): 保存写状态.(默认是关闭的)
> 记录下除了查询以外的所有变更数据库状态的指令.
> 以append的形式追加保存到AOF文件中(增量)

### 8.1 日志重写解决AOF文件大小不断增大的问题, 原理如下:
* 调用fork(), 创建一个子进程
* 子进程把新的AOF写到一个临时文件里, 不依赖原来的AOF文件
* 主进程持续将新的变动同时写到内存和原来的AOF里
* 主进程获取子进程重写AOF的完成信号, 往新AOF同步增量变动
* 使用新的AOF文件替换掉旧的AOF文件

### 8.2 AOF和RDB的优缺点?
* RDB
> 优点: 全量数据快照, 文件小, 恢复快
> 缺点: 无法保存最近一次快照之后的数据
* AOF 
> 优点: 可读性高, 适合保存增量数据, 数据不易丢失
> 缺点: 文件体积大, 恢复时间长

### 8.3 RDB-AOF混合持久化方式
* BGSAVE做镜像全量持久化, AOF做增量持久化

### 9. 使用pipeline的好处?

* Pipeline 和linux 的管道类似
* Redis基于请求/响应模型, 单个请求处理需要一一应答
* Pipeline批量执行指令, 节省多次IO往返的时间.
* 有顺序依赖的指令建议分批发送

### 9.1 什么是pipeline 流水线?
* 将一批命令进行批量打包, 在服务端进行批量的计算.
 按照顺序将结果返回给客户端. 这就是流水线.
可以大大节省网络时间开销.

### 9.2 Redis的同步机制?
* 全同步过程
> Slave发送sync命令到Master
> Master启动一个后台进程, 将Redis中数据快照保存在文件中(BGSAVE)
> Master将保存数据快照期间接收到的写命令缓存起来.
> Master完成写文件操作后, 将该文件发送给Slave
> 使用新的AOF文件替换掉旧的AOF文件
> Master将这期间收集的增量写命令发送给Slave端
* 增量同步的过程
> Master接收到用户的操作指令, 判断是否需要传播到Slave
> 将操作记录追加到AOF文件
> 将操作传播到其他Slave: 1.对齐主从库 2.往响应缓存写入指令
> 将缓存中的数据发送给Slave

### 10. Redis Sentinel
* 解决主从同步Master宕机后的主从切换问题
> 监控: 检查主从服务器是否运行正常
> 提醒: 通过API向管理员或者其他应用程序发送故障通知
> 自动故障迁移: 主从切换

### 11.流言协议Gossip
* 在杂乱无章中寻求一致
> 每个节点都随机地与对方通信, 最终所有节点的状态达成一致
> 种子节点定期随机向其他节点发送节点列表以及需要传播的消息
> 不保证信息一定会传递给所有节点, 但是最终会趋于一致.




