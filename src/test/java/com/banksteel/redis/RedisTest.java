package com.banksteel.redis;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Unit test for simple App.
 */
public class RedisTest {
    private Jedis jedis = null;
   
    @Before
    public void before() {
        jedis = new Jedis("localhost");
    }
    
    @Test
    public void testConn() {
        Jedis testJedis = new Jedis("localhost");
        System.out.println(null == testJedis ? "连接失败!" : "连接成功!");
        String ping = jedis.ping();
        System.out.println("服务正在运行：" + ping);
        
    }
    
    @Test
    public void testKeyOperate() throws InterruptedException {
        System.out.println("-------------------测试key--------------------");
        jedis.select(0);
        // 清空数据
        String flushDB = jedis.flushDB();
        System.out.println("清空所选择数据库的数据：" + flushDB);
        // 判断key是否存在
        Boolean exists = jedis.exists("key999");
        System.out.println("判断key999是否存在：" + exists);
        // 设置健值
        String set = jedis.set("key001", "value001");
        System.out.println("设置健值key001:" + set);
        // 判断key001是否存在
        exists = jedis.exists("key001");
        System.out.println("判断key001是否存在：" + exists);
        // 设置key002的健值
        set = jedis.set("key002", "value002");
        System.out.println("设置健key002：" + set);
        // 获取所有的额key
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println("key：" + key);
        }
        
        // 删除某个key
        Long del = jedis.del("key002");
        System.out.println("删除key：" + del);
        // 查看删除的key是否存在
        exists = jedis.exists("key002");
        System.out.println("删除的key002是否存在：" + exists);
        // 设置key的过期时间
        Long expire = jedis.expire("key001", 10);
        System.out.println("设置key001的过期时间：" + expire);
        // 睡几秒钟
        Thread.sleep(3);
        //查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        Long ttl = jedis.ttl("key001");
        System.out.println("key001的过期时间剩余：" + ttl + "秒");
        
        // 移除移除某个key的生存时间
        Long persist = jedis.persist("key001");
        System.out.println("移除key001过期剩余时间：" + persist);
        // 查看移除key的剩余时间
        ttl = jedis.ttl("key001");
        System.out.println("key001的过期时间剩余：" + ttl + "秒");
        //查看key所储存的值的类型
        String type = jedis.type("key001");
        System.out.println("key001存储值的类型：" + type);
        // 修改键名：jedis.rename("key6", "key0");
        String rename = jedis.rename("key001", "key006");
        System.out.println("key001重命名：" + rename);
        // 将当前db的key移动到给定的db当中：jedis.move("foo", 1)
        Long move = jedis.move("key006", 1);
        System.out.println("key006移动到别的库中：" + move);
        // 切换到其他数据库，判断key的值是否存在
        jedis.select(1);
        Boolean exists2 = jedis.exists("key006");
        System.out.println("切换到其他数据库，判断key的值是否存在:" + exists2);
        
    }
    
    @Test
    public void test1() {
        String ret = jedis.set("key01", "value1");
        System.out.println(ret);
    }
}
