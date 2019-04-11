package com.fyf.example.demo.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * 用户表
 * @author asus
 *
 */
@Data
public class Choice implements Serializable{

	private Integer id;        //id
	 
	private String userId;      //用户id
	
	private Integer groupType;  //用来表示用户组选座类型（1：遵循组，2：不遵循组）
	
	private String userGroup;   //用户组，作为几个用户一起选座的标识
	
	private Integer gender;   //性别（1:女， 2:男）
	
	private Integer vip;     //是否为vip（1：不是，2：是）
	
	private Integer deformity;  //是否残疾（1：不是，2是）
	
	private Integer seatType;    //座位类型（1：靠窗，2：靠过道，3：登机口）
	
	private Integer random;    //不选择座位属性，随机分配（1：随机，2：不随机）
	
	private String seatNo;     //分配的座位号
	
	private Integer handle;     //是否已被分配座位(1 分配  2没有分配)
	
	private java.util.Date  createTime; //选座日期
	
	private String planeType;     //机型
	
	private Integer baby;      // 1 孕妇  2 不是孕妇
	
	private String startNum;       //出发班次，用于区分同一机型，不同航班
	
	private Integer finish;   //是否满足用户需求
}
