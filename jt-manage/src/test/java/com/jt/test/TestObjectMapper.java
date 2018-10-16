package com.jt.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

public class TestObjectMapper {
	
	@Test
	public void javaJson() throws IOException{
		User user = new User();
		user.setId(10);
		user.setName("事务");
		user.setAge(18);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		System.out.println(json);
		
		User user1 = objectMapper.readValue(json, User.class);
		System.out.println(user1);
	}
	
	@Test
	public void listJson() throws IOException{
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("a");
		arrayList.add("b");
		arrayList.add("c");
		arrayList.add("d");
		arrayList.add("e");
		ObjectMapper objectMapper = new ObjectMapper();
		String listJson = objectMapper.writeValueAsString(arrayList);
		System.out.println(listJson);
		
		ArrayList<String> jsontoList = objectMapper.readValue(listJson, ArrayList.class);
		System.out.println(jsontoList);
	}
	
	@Test
	public void listToJson() throws IOException{
		User user1 = new User();
		user1.setId(1);
		user1.setName("名");
		user1.setAge(12);
		
		User user2 = new User();
		user2.setId(2);
		user2.setName("天");
		user2.setAge(15);
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		ObjectMapper objectMapper = new ObjectMapper();
		String listToJson = objectMapper.writeValueAsString(userList);
		System.out.println(listToJson);
		
		ArrayList<User> jsonToList = objectMapper.readValue(listToJson, ArrayList.class);
		System.out.println(jsonToList);
		System.out.println("*******************");
		User[] jsonList = objectMapper.readValue(listToJson, User[].class);
		List<User> javaList =  Arrays.asList(jsonList);
		System.out.println(javaList);
	}
}
