package com.banksteel.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Unit test for simple App.
 */
public class RedisMapTest2 {
    private Jedis jedis = null;
   
    @Before
    public void before() {
        jedis = new Jedis("localhost");
    }
    
    @Test
    public void testHash() {
        System.out.println("======================hash==========================");
        //清空数据 
        System.out.println(jedis.flushDB()); 
        
        System.out.println("=============增=============");
        System.out.println("hashs中添加key001和value001键值对："+jedis.hset("hashs", "key001", "value001")); 
        System.out.println("hashs中添加key002和value002键值对："+jedis.hset("hashs", "key002", "value002")); 
        System.out.println("hashs中添加key003和value003键值对："+jedis.hset("hashs", "key003", "value003"));
        System.out.println("新增key004和4的整型键值对："+jedis.hincrBy("hashs", "key004", 4l));
        System.out.println("hashs中的所有值："+jedis.hvals("hashs"));
        System.out.println();
        
        System.out.println("=============删=============");
        System.out.println("hashs中删除key002键值对："+jedis.hdel("hashs", "key002"));
        System.out.println("hashs中的所有值："+jedis.hvals("hashs"));
        System.out.println();
        
        System.out.println("=============改=============");
        System.out.println("key004整型键值的值增加100："+jedis.hincrBy("hashs", "key004", 100l));
        System.out.println("hashs中的所有值："+jedis.hvals("hashs"));
        System.out.println();
        
        System.out.println("=============查=============");
        System.out.println("判断key003是否存在："+jedis.hexists("hashs", "key003"));
        System.out.println("获取key004对应的值："+jedis.hget("hashs", "key004"));
        System.out.println("批量获取key001和key003对应的值："+jedis.hmget("hashs", "key001", "key003")); 
        System.out.println("获取hashs中所有的key："+jedis.hkeys("hashs"));
        System.out.println("获取hashs中所有的value："+jedis.hvals("hashs"));
        System.out.println();

    }
    
}
