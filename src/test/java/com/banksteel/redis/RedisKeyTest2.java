package com.banksteel.redis;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Unit test for simple App.
 */
public class RedisKeyTest2 {
    private Jedis jedis = null;
   
    @Before
    public void before() {
        jedis = new Jedis("localhost");
    }
    
    @Test
    public void testConn_1() {
        Jedis jedis = new Jedis("127.0.0.1", 6380);
        System.out.println(null == jedis ? "获取连接失败" : "获取链接成功!");
        String ping = jedis.ping();
        System.out.println("服务正在允许：" + ping);
    }
    
    @Test
    public void testRedisKeyOperate() throws InterruptedException {
        System.out.println("=============测试redis key ================");
        // 选择数据库
        jedis.select(1);
        // 清空当前数据库数据
        String flushDB = jedis.flushDB();
        System.out.println("情况所选择数据库的数据:" + flushDB);
        // 清空所有数据库数据(一般不使用)
        // jedis.flushAll();
        // 判断数据库中key是否存在
        Boolean exists = jedis.exists("key88");
        System.out.println("判断key88是否存在：" + exists);
        // 设置键值
        String set = jedis.set("key88", "value88");
        System.out.println("设置键值key88:" + set);
        // 再次判断键是否存在
        exists = jedis.exists("key88");
        System.out.println("判断key88是否存在：" + exists);
        // 设置key99 的值
        String set2 = jedis.set("key99", "value99");
        System.out.println("设置key99的值：" + set2);
        // 再次设置
        set2 = jedis.set("key99", "value99");
        System.out.println("设置key99的值：" + set2);
        // 获取所有的key
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println("key:" + key);
        }
        
        // 删除某个key 
        Long del = jedis.del("key88");
        System.out.println("删除键key88:" + del);
        // 查看删除的key是否存在
        exists = jedis.exists("key88");
        System.out.println("删除的key88是否还存在：" + exists);
        // 设置key的过期时间
        Long expire = jedis.expire("key99", 20);
        System.out.println("设置key的过期时间：" + expire);
        // 睡几秒钟
        Thread.sleep(3);
        //查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        Long ttl = jedis.ttl("key99");
        System.out.println("查看key99的剩余时间：" + ttl);
        // 移除移除某个key的生存时间
        Long persist = jedis.persist("key99");
        System.out.println("移除key99的过期数据：" + persist);
        // 查看移除key的剩余时间
        ttl = jedis.ttl("key99");
        System.out.println("查看key99的剩余时间：" + ttl);
        //查看key所储存的值的类型
        String type = jedis.type("key99");
        System.out.println("查看key99存储的值类型：" + type);
        // 修改键名：jedis.rename("key6", "key0");
        String rename = jedis.rename("key99", "key88");
        System.out.println("重命名key99：" + rename);
        // 将当前db的key移动到给定的db当中：jedis.move("foo", 1)
        Long move = jedis.move("key88", 2);
        System.out.println("将当前db的key移动到给定的db当中：" + rename);
        // 切换到其他数据库，判断key的值是否存在
        String select = jedis.select(2);
        System.out.println("切换库：" + select);
        Boolean exists2 = jedis.exists("key88");
        System.out.println("移动键之后键是否存在：" + exists2);
    }
    
}
