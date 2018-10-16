package com.jt.manage.controller.web;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

@Controller
@RequestMapping("/web")
public class WebJSONPController {

	//http://manage.jt.com/web/testJSONP?callback=hello&_=1537844998072
	/*@RequestMapping(value="testJSONP",produces="text/html;charset=utf-8")
	@ResponseBody*/
	public String jsonp(String callback) throws JsonProcessingException{
		User user = new User();
		user.setId(20);
		user.setName("小屁孩");
		ObjectMapper objectMapper = new ObjectMapper();
		String value = objectMapper.writeValueAsString(user);
		return callback +"("+value+")";
	}
	@RequestMapping("testJSONP")
	@ResponseBody
	public MappingJacksonValue JsonSuper(String callback){
		User user = new User();
		user.setId(20);
		user.setName("屁孩游神");
		MappingJacksonValue value = new MappingJacksonValue(user);
		value.setJsonpFunction(callback);
		return value;
	}
}
