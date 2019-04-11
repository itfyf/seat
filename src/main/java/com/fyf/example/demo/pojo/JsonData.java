package com.fyf.example.demo.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class JsonData implements Serializable{

	private List<String> data;
	
	private Integer num;
}
