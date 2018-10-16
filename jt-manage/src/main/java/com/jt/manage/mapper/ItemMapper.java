package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{
	
	//@Select("select * from tb_item")
	/**
	 * mybatis中不允许多值传参，@Param这种方法底层是采用map集合封装
	 * @param start
	 * @param rows
	 * @return
	 */
	List<Item> findItemByPage(@Param("start") int start,@Param("rows") int rows);
	
	int findCount();
	
	@Select("select name from tb_item_cat where id=#{itemId}")
	String findItemCatNameById(Long itemId);

	void updateByIds(@Param("ids") Long[] ids, @Param("status") int status);


}
