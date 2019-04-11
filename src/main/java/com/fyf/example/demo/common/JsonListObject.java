package com.fyf.example.demo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fyf.example.demo.pojo.JsonData;


public class JsonListObject {

	/**
	 * 将json数据转换对象List
	 * @param ss  传入json字符串
	 * @return
	 */
	public static  JsonData jsonListObject(String ss) {
		Map mapTypes = JSON.parseObject(ss);
		
		//对象
		JsonData jsonData = new JsonData();
		
		List<String> list = new ArrayList<>();
		for (Object obj : mapTypes.keySet()) {
//			System.out.println("key为：" + obj + "值为：" + mapTypes.get(obj));   
			
			//如果key为num 将value封装到jsonData对象中
			Object object = mapTypes.get(obj);
			if(obj.toString().equals("num")){
				jsonData.setNum(Integer.parseInt(object.toString()));
			}
			
			//如果key为data 将数据进行切割
			if(obj.toString().equals("data")){
//				System.out.println(object.toString());
				String s = object.toString();     //转换字符串
				
				 //进行截取去中括号      按照 ， 进行分割
				String[] split = s.substring(1, s.length() - 1).split(",");
				for (String string : split) {
					// System.out.println(string);
					
					//去分号
					String substring = string.substring(1, string.length() - 1);
//					System.out.println(substring);
					
					//添加到集合
					list.add(substring);
				}
			}
		}
		
		// 封装对象
		jsonData.setData(list);
		
		return jsonData;
	}
	
}
