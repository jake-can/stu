package com.jt.test;

import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedisString {
	
	@Test
	public void test01(){
		Jedis jedis = new Jedis("192.168.161.134", 6379);
		jedis.set("1805", "可尔康");
		System.out.println(jedis.get("1805"));
	}
	
	//操作hash
	@Test
	public void test02(){
		Jedis jedis = new Jedis("192.168.161.134", 6379);
		jedis.hset("user", "id", "100");
		jedis.hset("user", "name","帅的一批");
		Map<String, String> hgetAll = jedis.hgetAll("user");
		System.out.println(hgetAll);
	}
	
	//操作list
	@Test
	public void listTest(){
		Jedis jedis = new Jedis("192.168.161.134",6379);
		jedis.rpush("老铁", "小区，大柜 气质 炫富 铭泰","qiq","sadsad");
		System.out.println(jedis.rpop("老铁"));
		System.out.println(jedis.rpop("老铁"));
		System.out.println(jedis.rpop("老铁"));
		System.out.println(jedis.rpop("老铁"));
		System.out.println(jedis.rpop("老铁"));
	}

}
