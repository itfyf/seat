package com.fyf.example.demo.mapper.sql;

import org.apache.ibatis.jdbc.SQL;

import com.fyf.example.demo.pojo.Choice;

/**
 * Choice 的动态sql
 * 
 * @author asus
 *
 */
public class ChoiceSql {

	/**
	 * 查询的动态sql
	 * 
	 * @param choice
	 * @return
	 */
	public String selectWhitChoiceSql(Choice choice) {
		return new SQL() {
			{
				SELECT("*");
				FROM("tb_choice");
				if (choice.getId() != null) {
					WHERE("id=#{id}");
				}
				if (choice.getUserId() != null) {
					WHERE("user_id=#{userId}");
				}
				if (choice.getGroupType() != null) {
					WHERE("group_type=#{groupType}");
				}
				if (choice.getUserGroup() != null) {
					WHERE("user_group=#{userGroup}");
				}
				if (choice.getGender() != null) {
					WHERE("gender=#{gender}");
				}
				if (choice.getVip() != null) {
					WHERE("vip=#{vip}");
				}
				if (choice.getDeformity() != null) {
					WHERE("deformity=#{deformity}");
				}
				if (choice.getSeatType() != null) {
					WHERE("seat_type=#{seatType}");
				}
				if (choice.getRandom() != null) {
					WHERE("random=#{random}");
				}
				if (choice.getSeatNo() != null) {
					WHERE("seat_no=#{seatNo}");
				}
				if (choice.getHandle() != null) {
					WHERE("handle=#{handle}");
				}
				if (choice.getCreateTime() != null) {
					WHERE("create_time=#{createTime}");
				}
				if (choice.getBaby() != null) {
					WHERE("baby=#{baby}");
				}
				if (choice.getPlaneType() != null) {
					WHERE("plane_type=#{planeType}");
				}
				if (choice.getStartNum() != null) {
					WHERE("start_num=#{startNum}");
				}
				if (choice.getFinish() != null) {
					WHERE("finish=#{finish}");
				}
			}
		}.toString();
	}

	/**
	 * 插入的动态sql
	 * 
	 * @param choice
	 * @return
	 */
	public String insertChoiceSql(Choice choice) {
		return new SQL() {
			{
				INSERT_INTO("tb_choice");
				if (choice.getId() != null) {
					VALUES("id", "#{id}");
				}
				if (choice.getUserId() != null) {
					VALUES("user_id", "#{userId}");
				}
				if (choice.getGroupType() != null) {
					VALUES("group_type", "#{groupType}");
				}
				if (choice.getUserGroup() != null) {
					VALUES("user_group", "#{userGroup}");
				}
				if (choice.getGender() != null) {
					VALUES("gender", "#{gender}");
				}
				if (choice.getVip() != null) {
					VALUES("vip", "#{vip}");
				}
				if (choice.getDeformity() != null) {
					VALUES("deformity", "#{deformity}");
				}
				if (choice.getSeatType() != null) {
					VALUES("seat_type", "#{seatType}");
				}
				if (choice.getRandom() != null) {
					VALUES("random", "#{random}");
				}
				if (choice.getSeatNo() != null) {
					VALUES("seat_no", "#{seatNo}");
				}
				if (choice.getHandle() != null) {
					VALUES("handle", "#{handle}");
				}
				if (choice.getCreateTime() != null) {
					VALUES("create_time", "#{createTime}");
				}
				if (choice.getBaby() != null) {
					VALUES("baby", "#{baby}");
				}
				if (choice.getPlaneType() != null) {
					VALUES("plane_type", "#{planeType}");
				}
				if (choice.getStartNum() != null) {
					VALUES("start_num", "#{startNum}");
				}
				if (choice.getFinish() != null) {
					VALUES("finish", "#{finish}");
				}
			}
		}.toString();
	}

	/**
	 * 修改的动态sql
	 * @param choice
	 * @return
	 */
	public String updateChoiceSql(Choice choice) {
		return new SQL() {
			{
				UPDATE("tb_choice");
				if (choice.getId() != null) {
					SET("id=#{id}");
				}
				if (choice.getUserId() != null) {
					SET("user_id=#{userId}");
				}
				if (choice.getGroupType() != null) {
					SET("group_type=#{groupType}");
				}
				if (choice.getUserGroup() != null) {
					SET("user_group=#{userGroup}");
				}
				if (choice.getGender() != null) {
					SET("gender=#{gender}");
				}
				if (choice.getVip() != null) {
					SET("vip=#{vip}");
				}
				if (choice.getDeformity() != null) {
					SET("deformity=#{deformity}");
				}
				if (choice.getSeatType() != null) {
					SET("seat_type=#{seatType}");
				}
				if (choice.getRandom() != null) {
					SET("random=#{random}");
				}
				if (choice.getSeatNo() != null) {
					SET("seat_no=#{seatNo}");
				}
				if (choice.getHandle() != null) {
					SET("handle=#{handle}");
				}
				if (choice.getCreateTime() != null) {
					SET("create_time=#{createTime}");
				}
				if (choice.getBaby() != null) {
					SET("baby=#{baby}");
				}
				if (choice.getPlaneType() != null) {
					SET("plane_type=#{planeType}");
				}
				if (choice.getStartNum() != null) {
					SET("start_num=#{startNum}");
				}
				if (choice.getFinish() != null) {
					SET("finish=#{finish}");
				}
				WHERE("id=#{id}");
			}
		}.toString();
	}

	/**
	 * 删除的动态sql
	 * @param choice
	 * @return
	 */
	public String deleteChoiceSql(Choice choice){
		return new SQL() {
	        {
	            DELETE_FROM("tb_employee");
	            if (choice.getId() != null) {
					WHERE("id=#{id}");
				}
				if (choice.getUserId() != null) {
					WHERE("user_id=#{userId}");
				}
				if (choice.getGroupType() != null) {
					WHERE("group_type=#{groupType}");
				}
				if (choice.getUserGroup() != null) {
					WHERE("user_group=#{userGroup}");
				}
				if (choice.getGender() != null) {
					WHERE("gender=#{gender}");
				}
				if (choice.getVip() != null) {
					WHERE("vip=#{vip}");
				}
				if (choice.getDeformity() != null) {
					WHERE("deformity=#{deformity}");
				}
				if (choice.getSeatType() != null) {
					WHERE("seat_type=#{seatType}");
				}
				if (choice.getRandom() != null) {
					WHERE("random=#{random}");
				}
				if (choice.getSeatNo() != null) {
					WHERE("seat_no=#{seatNo}");
				}
				if (choice.getHandle() != null) {
					WHERE("handle=#{handle}");
				}
				if (choice.getCreateTime() != null) {
					WHERE("create_time=#{createTime}");
				}
				if (choice.getBaby() != null) {
					WHERE("baby=#{baby}");
				}
				if (choice.getPlaneType() != null) {
					WHERE("plane_type=#{planeType}");
				}
				if (choice.getStartNum() != null) {
					WHERE("start_num=#{startNum}");
				}
				if (choice.getFinish() != null) {
					WHERE("finish=#{finish}");
				}
	        }
	    }.toString();
	}
}
