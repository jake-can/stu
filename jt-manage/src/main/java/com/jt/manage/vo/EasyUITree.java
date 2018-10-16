package com.jt.manage.vo;

public class EasyUITree {

	//为了进行easyuitree的树形结构展现，需要设置如下属性：
	private Long id;
	private String text;  //元素文本内容
	private String state; //“closed”表示关闭，“open”表示打开
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
