package com.jt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestShard {
	
	@Test
	public void Test01(){
		//1,定义连接池的大小
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(100);
		poolConfig.setTestOnBorrow(true);
		//2，定义分片list集合
		List<JedisShardInfo> shards = new ArrayList<>();
		shards.add(new JedisShardInfo("192.168.161.134",6379));
		shards.add(new JedisShardInfo("192.168.161.134",6380));
		shards.add(new JedisShardInfo("192.168.161.134",6381));
		//创建分片的对象
		ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, shards);
		ShardedJedis resource = jedisPool.getResource();
		resource.set("feipian", "分片");
		//设定超时时间的方法
		String key=null;
		int seconds = 0;
		String value = null;
		resource.setex(key, seconds, value);
		System.out.println("获取数据:"+resource.get("feipian"));
		resource.close();
	}
}
