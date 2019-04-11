package com.fyf.example.demo.common;

import java.io.Serializable;

import com.fyf.example.demo.pojo.JsonData;

import lombok.Data;


/**
 * 
 * @author asus
 *
 */
@Data
public class JsonResponseData implements Serializable{

	private String planeType; // 机型
	private JsonData seatWindow; // 靠窗
	private JsonData seatAisle; // 靠座位
	private JsonData seatGate; // 靠出口
	private JsonData seatRandom; // 普通座位
	private JsonData seatBaby;
	private Integer seatNum; // 总座位
	
	public  void set(JsonData seatWindow,JsonData seatAisle,JsonData seatGate,JsonData seatBaby,JsonData seatRandom){
		this.seatWindow = seatWindow;
		this.seatAisle = seatAisle;
		this.seatGate = seatGate;
		this.seatBaby = seatBaby;
		this.seatRandom = seatRandom;
	} 
	
}
