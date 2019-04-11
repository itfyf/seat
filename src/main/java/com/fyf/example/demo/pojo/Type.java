package com.fyf.example.demo.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * 抽取类型类
 * @author 初九
 *
 */
public class Type implements Serializable{
	
	/**
	 * 类型
	 */
	private String planeType;
	
	/**
	 * 全部座位
	 */
	private List<SeatType> seats;
	
	/**
	 * 出口位置
	 */
	private List<String> exitPositions;
	
	/**
	 * 全部序号集合
	 * <br/><br/>
	 * 格式：A,B,C,D,J,K,L
	 */
	private List<String> Serials;
	
	/**
	 * 过道位置
	 * <br/><br/>
	 * 格式：C-D
	 */
	private List<String> aislePosition;
	
	/**
	 * 起始位置号
	 */
	private Integer startNo;
	
	/**
	 * 排数
	 */
	private Integer rowCount;
	
	/**
	 * 全部座位个数
	 */
	private Integer totalCount;
	
	/**
	 * 出口个数
	 */
	private Integer exitCount;

	
	public List<String> getExitPositions() {
		return exitPositions;
	}

	public void setExitPositions(List<String> exitPositions) {
		this.exitPositions = exitPositions;
		
		List<Integer> list = new ArrayList<>();
		for (String exitPosition : exitPositions) {
			String[] info = exitPosition.split("-");
			int lineNo = Integer.parseInt(info[1]);
			if(! list.contains(lineNo)) {
				if(isSimilar(list, lineNo)) {
					list.add(lineNo);
				}
			}
		}
		this.setExitCount(list.size()*2);
	}

	public List<String> getSerials() {
		return Serials;
	}

	public void setSerials(List<String> serials) {
		if(Serials == null) {
			Serials = serials;
		}else {
			Serials.addAll(serials);
		}
	}

	public Integer getStartNo() {
		return startNo;
	}

	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public List<SeatType> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatType> seats) {
		if(this.seats == null) {
			this.seats = seats;
		}else {
			this.seats.addAll(seats);
		}
		
		int totalCount = 0;
		
		List<String> serials = new ArrayList<>();
		for (SeatType seatType : seats) {
			if(!serials.contains(seatType.getLineNo())) {
				serials.add(seatType.getLineNo());
			}
			
			totalCount += seatType.getTotalCount();
		}
		Collections.sort(serials);
		this.setSerials(serials);
		this.setTotalCount(totalCount);
	}
	
	public List<String> getAislePosition() {
		return aislePosition;
	}

	public void setAislePosition(List<String> aislePosition) {
		this.aislePosition = aislePosition;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getExitCount() {
		return exitCount;
	}

	public void setExitCount(Integer exitCount) {
		this.exitCount = exitCount;
	}
	
	private boolean isSimilar(List<Integer> info,int lineNo) {
		for (Integer integer : info) {
			if(Math.abs(lineNo - integer) < 3) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Type [planeType=" + planeType + ", seats=" + seats + ", Serials=" + Serials + ", startNo=" + startNo
				+ ", rowCount=" + rowCount + "]";
	}
	
	
}
