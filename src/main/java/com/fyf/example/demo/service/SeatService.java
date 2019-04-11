package com.fyf.example.demo.service;

import com.fyf.example.demo.common.JsonResponseData;
import com.fyf.example.demo.pojo.Type;

public interface SeatService {

	JsonResponseData select(String planeType);
	
	
	// 返回前端需要的数据格式
	Type getData(String planeType) throws Exception;
}
