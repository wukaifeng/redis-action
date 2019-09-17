package com.banksteel.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Unit test for simple App.
 */
public class JedisPoolTest {
    private JedisPool jedisPool = null;
   
    @Before
    public void before() {
        JedisPoolConfig config = new JedisPoolConfig(); // Jedis连接池
        config.setMaxIdle(8); // 最大空闲连接数
        config.setMaxTotal(8);// 最大连接数
        config.setMaxWaitMillis(1000); // 获取连接是的最大等待时间，如果超时就抛出异常
        config.setTestOnBorrow(false);// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnReturn(true);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379);

    }
    
    @Test
    public void testConn_1() {
        Jedis jedis = jedisPool.getResource();
        System.out.println(null == jedis ? "获取连接失败" : "获取链接成功!");
        String ping = jedis.ping();
        System.out.println("服务正在允许：" + ping);
    }
    
}
