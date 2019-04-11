package com.fyf.example.demo.service.impl;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fyf.example.demo.common.JsonListObject;
import com.fyf.example.demo.common.JsonResponseData;
import com.fyf.example.demo.common.SeatResolver;
import com.fyf.example.demo.mapper.SeatMapper;
import com.fyf.example.demo.pojo.JsonData;
import com.fyf.example.demo.pojo.Seat;
import com.fyf.example.demo.pojo.SeatType;
import com.fyf.example.demo.pojo.Type;
import com.fyf.example.demo.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService{

	@Autowired
	private SeatMapper seatMapper;
	
	@Autowired
	private RedisServiceImpl redisService;

	private JsonResponseData jsonResponseData = new JsonResponseData();
	
	@Override
	public JsonResponseData select(String planeType) {
		Seat seat = seatMapper.select(planeType);
		
		//将  同一属性进行复制
		BeanUtils.copyProperties(seat, jsonResponseData);
		
		//解析json数组
		JsonData window = JsonListObject.jsonListObject(seat.getSeatWindow());
		saveRedis(RedisServiceImpl.REDIS_WINDOW,window.getData());
		
		JsonData aisle = JsonListObject.jsonListObject(seat.getSeatAisle());
		saveRedis(RedisServiceImpl.REDIS_AISLE,aisle.getData());
		
		JsonData gate = JsonListObject.jsonListObject(seat.getSeatGate());
		saveRedis(RedisServiceImpl.REDIS_GATE,gate.getData());
		
		JsonData random = JsonListObject.jsonListObject(seat.getSeatRandom());
		saveRedis(RedisServiceImpl.REDIS_RANDOM,random.getData());
		
		JsonData bady = JsonListObject.jsonListObject(seat.getSeatBaby());
		saveRedis(RedisServiceImpl.REDIS_BADY,bady.getData());
		
		// 封装对象  
		jsonResponseData.set(window, aisle, gate, bady, random);
		
		return jsonResponseData;
	}
	
	/**
	 * 将座位导入Redis
	 * @param data
	 */
	public  void saveRedis(String redisKey,List<String> data){
		for (String string : data) {
			if(string.equals("-") || string.equals("~") || string.equals(" ")){
				continue;
			}
			 redisService.put(redisKey,string, "0", -1);
		}
	}

	
	/*
	 * 返回前端需要的数据
	 * 
	 */
	@Override
	public Type getData(String planeType) throws Exception {
		Seat seat = seatMapper.select(planeType);
		
		// 解析json字符串
		SeatResolver seatResolver = new SeatResolver();
		seatResolver.put("window", seat.getSeatWindow());
		seatResolver.put("aisle", seat.getSeatAisle());
		seatResolver.put("random", seat.getSeatRandom());
		List<SeatType> seats = seatResolver.SeatParsing();
		int start = SeatResolver.getStart(seats);
		Integer rowMaxNo = SeatResolver.getRowMaxNo(seats);
		List<String> aisleLinePosition = seatResolver.getAisleLinePosition(seat.getSeatAisle());
		List<String> exitPositions = seatResolver.exitResolver(seat.getSeatGate());
		
		//封装type 对象进行返回
		Type t = new Type();
		t.setPlaneType(seat.getPlaneType());
		t.setSeats(seats);
		t.setStartNo(start);
		t.setRowCount(rowMaxNo);
		t.setAislePosition(aisleLinePosition);
		t.setExitPositions(exitPositions);
		
		return t;
	}

	
}
