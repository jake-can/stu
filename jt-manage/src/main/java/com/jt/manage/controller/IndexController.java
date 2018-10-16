package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	/**
	 * 通过restful实现页面的通用跳转
	 * restful格式要求：
	 * 1，参数拼接在url中，并且以“/”进行分割
	 * 2，参数的位置必须固定
	 * 3，后台服务端接收参数使用{}包裹变量，之后使用
	 * @PathVariable 实现数据的动态取值
	 * @return
	 */
	
	@RequestMapping("/page/{module}")
	public String module(@PathVariable String module){
		return module;
	}
}
