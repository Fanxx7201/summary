
HashMap

### 1.集合之Map
![1550217239121](C:\Users\Lenovo\AppData\Local\Temp\1550217239121.png)

### 2.HashMap, HashTable, ConccurentHashMap
* HashMap(java 8 以前) :数组+链表实现, 结合了两者的优势. 非synchroized, 效率高.
![1550217764970](C:\Users\Lenovo\AppData\Local\Temp\1550217764970.png)
![1550217839019](C:\Users\Lenovo\AppData\Local\Temp\1550217839019.png)

### 2.1HashMap: put方法的逻辑
1. 如果HashMap未被初始化过,则初始化
2. 对Key求Hash值, 然后再计算下标
3. 如果没有碰撞, 直接放入桶中
4. 如果碰撞了, 以链表的方式链接到后面
5. 如果链表长度超过阈值, 就把链表转成红黑树
6. 如果链表长度低于6, 就把红黑树转回链表
7. 如果节点已经存在就替换旧值
8. 如果桶满了(容量16 * 加载因子0.75), 就需要resize(扩容两倍后重排)

### 2.2 HashMap: 如何有效减少碰撞
* 扰动函数: 促使元素位置分布均匀, 减少碰撞几率
* 使用final对象, 并采用合适的equals()和hashCode()方法