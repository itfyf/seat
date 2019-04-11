package com.fyf.example.demo.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 座位类型
 * @author 初九
 *
 */
public class SeatType implements Serializable{

	/**
	 * 座位类型
	 * 如 window,aisle,random
	 */
	private String type;
	/**
	 * 座位个数
	 */
	private Integer totalCount;
	
	/**
	 * 座位序号
	 * 如 A、L
	 */
	private String lineNo;
	
	/**
	 * 起始位置
	 */
	private Integer start;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "SeatType [type=" + type + ", totalCount=" + totalCount + ", lineNo=" + lineNo + ", start=" + start
				+ "]";
	}
	
	
	
	
}
