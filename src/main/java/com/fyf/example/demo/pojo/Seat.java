package com.fyf.example.demo.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * 机型
 * @author asus
 *
 */
@Data
public class Seat implements Serializable{

	private String planeType;   //机型
	private String seatWindow;   //靠窗
	private String seatAisle;   //靠座位
	private String seatGate;    //靠出口
	private String seatRandom;  //普通座位
	private String seatBaby;    //婴儿座位
	private Integer seatNum;    //总座位
}
