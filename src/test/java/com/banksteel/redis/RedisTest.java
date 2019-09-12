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
    public void testKeyOperate() {
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
        
        // 查看删除的key是否存在
        
        
        // 设置key的过期时间
        // 睡几秒钟
        
        //查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        
        
        // 移除移除某个key的生存时间
        
        // 查看移除key的剩余时间
        
        
        //查看key所储存的值的类型
        
        // 修改键名：jedis.rename("key6", "key0");
        
        
        // 将当前db的key移动到给定的db当中：jedis.move("foo", 1)
        
        // 切换到其他数据库，判断key的值是否存在

        
        
    }
    
    @Test
    public void test1() {
        String ret = jedis.set("key01", "value1");
        System.out.println(ret);
    }
}
