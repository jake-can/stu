package com.jt.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class Testcluster {
	@Test
	public void test01(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.161.134", 7000));
		nodes.add(new HostAndPort("192.168.161.134", 7001));
		nodes.add(new HostAndPort("192.168.161.134", 7002));
		nodes.add(new HostAndPort("192.168.161.134", 7003));
		nodes.add(new HostAndPort("192.168.161.134", 7004));
		nodes.add(new HostAndPort("192.168.161.134", 7005));
		nodes.add(new HostAndPort("192.168.161.134", 7006));
		nodes.add(new HostAndPort("192.168.161.134", 7007));
		nodes.add(new HostAndPort("192.168.161.134", 7008));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("1807", "双击6666");
		System.out.println("获取数据："+jedisCluster.get("1807"));
	}

}
