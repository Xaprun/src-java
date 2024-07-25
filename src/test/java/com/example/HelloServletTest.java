package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class HelloServletTest {

    @Test
    public void testRedisConnection() {
        try (Jedis jedis = new Jedis("redis-container", 6379)) {
            String pong = jedis.ping();
            assertTrue("PONG".equals(pong));
        } catch (Exception e) {
            fail("Could not connect to Redis: " + e.getMessage());
        }
    }
}
