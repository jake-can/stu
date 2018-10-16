package com.jt.manage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUITree;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private ItemCatMapper ItemCatMapper;
	
	@Autowired
	//private Jedis jedis;
	//private RedisService redisService;
	private JedisCluster jedisCluster;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<EasyUITree> findItemCatList(Long parentId) {
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);

		List<ItemCat> itemCatList = ItemCatMapper.select(itemCat);
		List<EasyUITree> treeList = new ArrayList<>();
		for(ItemCat itemCatTemp:itemCatList){
			EasyUITree easyUITree = new EasyUITree();
			easyUITree.setId(itemCatTemp.getId());
			easyUITree.setText(itemCatTemp.getName());
			
			//如果是父级 应该设置为closed,否则open即可
			String state = itemCatTemp.getIsParent() ? "closed":"open";
			easyUITree.setState(state);
			treeList.add(easyUITree);
		}
		return treeList;
	}

	@Override
	public List<EasyUITree> findItemCatCache(Long parentId) {
		String key = "ITEM_CAT_"+parentId;
		String result = jedisCluster.get(key);
		List<EasyUITree> easyUIList = null;
		try {
			if(StringUtils.isEmpty(result)){
				easyUIList = findItemCatList(parentId);
				String easyUIListJson = objectMapper.writeValueAsString(easyUIList);
				jedisCluster.set(key, easyUIListJson);
				System.out.println("查询数据库");
				return easyUIList;
			}else{
				EasyUITree[] easyUIs = objectMapper.readValue(result, EasyUITree[].class);
				easyUIList = Arrays.asList(easyUIs);
				System.out.println("查询缓存");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return easyUIList;
	}
	
	

}
