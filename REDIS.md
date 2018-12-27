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



