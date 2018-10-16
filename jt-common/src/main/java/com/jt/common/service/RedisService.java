package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.regexp.internal.recompile;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {
	
	@Autowired(required=false)
	//public ShardedJedisPool shardedJedisPool;
	private JedisSentinelPool sentinelPool;
	
	public void set(String key, String value){
		Jedis jedis = sentinelPool.getResource();
		jedis.set(key, value);
		jedis.close();
	}
	
	public String get(String key){
		Jedis jedis = sentinelPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}
	
//	public void set(String key, String value){
//		ShardedJedis resource = shardedJedisPool.getResource();
//		resource.set(key, value);
//		resource.close();
//	}
//	
//	public void setex(String key, int seconds, String value){
//		ShardedJedis resource = shardedJedisPool.getResource();
//		resource.setex(key, seconds, value);
//		resource.close();
//	}
//	public String get(String key){
//		ShardedJedis resource = shardedJedisPool.getResource();
//		String result = resource.get(key);
//		resource.close();
//		return result;
//	}
}
