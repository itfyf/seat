package com.fyf.example.demo.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyf.example.demo.pojo.SeatType;


/**
 * 座位信息解析器
 * 
 * @author 初九
 *
 */
public class SeatResolver {

	/**
	 * 解析字符串：正则
	 */
	private final static String NUMBER = "[a-zA-Z]";
	private final static String LETTER = "\\d";
	/**
	 * 常用name
	 */
	private final static String DATA = "data";
	private final static String NUM = "num";
	/**
	 * 符号
	 */
	private final static String SYMBOL_QUOTATION_MARK = "\"";
	private final static String SYMBOL_BAR = "-";
	private final static String SYMBOL_COMMA = ",";

	/**
	 * 空串
	 */
	private final static String STRING_NULL = "";

	/**
	 * 座位信息，json字符串 name ： json
	 */
	private Map<String, String> seatInfo;

	public SeatResolver() {
		seatInfo = new HashMap<>();
	}

	/**
	 * 
	 * @param exitData 固定格式：{"data":["A47-B47-C47,J47-K47-L47"],"num":"6"}
	 * @return 固定格式：[A-37,B-37,C-37]
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public List<String> exitResolver(String exitData) throws JsonProcessingException, IOException {
		List<String> exitPositions = null;
		if (exitData != null && exitData.trim().length() != 0) {
			Map<String, Object> jsonResolver = JsonResolver(exitData);
			String data = jsonResolver.get(DATA).toString();
			exitPositions = eixtDataResolver(data);
		}
		return exitPositions;
	}

	/**
	 * 获取过道位置
	 * 
	 * @param aisleData 固定参数格式：{"data":["J35-J46,J47-J60","H36-H46,H48-H61","D36-D46,D48-D61","C35-C46,C47-C60"],"num":"102"}
	 * @return 返回结果：字符串“C-D”，“J-K”的集合
	 * @throws IOException
	 * @throws JsonProcessingException
	 * 
	 */
	public List<String> getAisleLinePosition(String aisleData) throws JsonProcessingException, IOException {
		List<String> linePositions = null;

		if (aisleData != null && aisleData.trim().length() != 0) {
			linePositions = new ArrayList<>();
			Map<String, Object> jsonData = JsonResolver(aisleData);
			String data = jsonData.get(DATA).toString();
			List<String> lineNos = aisleDataResolver(data);

			if (lineNos.size() % 2 != 0) {
				throw new RuntimeException("过道座位列数不匹配");
			} else {
				for (int i = 0; i < lineNos.size(); i += 2) {
					String linePosition = lineNos.get(i) + SYMBOL_BAR + lineNos.get(i + 1);
					linePositions.add(linePosition);
				}
			}
		}
		return linePositions;
	}

	/**
	 * 获取座位开始号(静态可直接调用)
	 * 
	 * @param seats 全部座位信息
	 */

	public static Integer getStart(List<SeatType> seats) {
		int start = seats.get(0).getStart();
		for (SeatType seatType : seats) {
			int curent = seatType.getStart();
			if (start > curent) {
				start = curent;
			}
		}
		return start;
	}

	/**
	 * 获取座位单行最大编号(静态可直接调用)
	 * 
	 * @param seats 全部座位信息
	 */

	public static Integer getRowMaxNo(List<SeatType> seats) {
		int maxNo = 0;
		int max = 0;
		for (SeatType seatType : seats) {
			int curent = seatType.getStart();
			if (max < curent) {
				max = curent;
				maxNo = max + seatType.getTotalCount() -1;
			}
		}
		return maxNo;
	}

	/**
	 * 获取出口序列号集合(静态可直接调用)
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static List<Integer> getExitNos(String json) throws JsonProcessingException, IOException {
		List<Integer> list = null;
		if (json != null && json.length() != 0) {
			list = new ArrayList<>();
			Map<String, Object> jsonResolver = JsonResolver(json);
			String data = (String) jsonResolver.get(DATA);
			String[] dataArr = data.split(SYMBOL_COMMA);
			for (String string : dataArr) {
				String[] arr = string.split(SYMBOL_BAR);
				for (String str : arr) {
					String num = getString(str, NUMBER, true);
					if (!list.contains(Integer.parseInt(num))) {
						list.add(Integer.parseInt(num));
						break;
					}
				}
			}
		}
		return list;
	}

	/**
	 * 添加进集合中
	 * 
	 * @param type
	 * @param data
	 */
	public void put(String type, String data) {
		this.seatInfo.put(type, data);
	}

	/**
	 * 解析全部座位 包括 window,aisle,random （核心）
	 * 
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public List<SeatType> SeatParsing() throws JsonProcessingException, IOException {
		List<SeatType> seats = null;

		if (this.seatInfo != null) {
			seats = new ArrayList<>();

			for (String type : this.seatInfo.keySet()) {
				String json = this.seatInfo.get(type);
				Map<String, Object> jsonResolver = JsonResolver(json);
				String data = jsonResolver.get(DATA).toString();

				List<SeatType> seatsPart = dataResolver(data);
				if (seats == null) {
					seats = seatsPart;
				} else {
					seats.addAll(seatsPart);
				}
			}
		}
		return seats;
	}

	/**
	 * 获取序列号集合（私有）
	 * 
	 * @param data
	 * @return
	 */
	private List<String> getSerialNumber(String data) {
		List<String> list = null;
		if (data != null && data.length() != 0) {
			list = new ArrayList<>();
			String data_letter = getString(data, LETTER, true);
			char[] charArray = data_letter.toCharArray();
			for (char c : charArray) {
				if (!list.contains(String.valueOf(c))) {
					list.add(String.valueOf(c));
				}
			}
		}
		return list;
	}

	/**
	 * 解析json字符串（私有）
	 * 
	 * 固定格式，如：{"data":["L35-L46,L47-L60","A35-A46,A47-A60"],"num":"52"}
	 * 
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	private static Map<String, Object> JsonResolver(String json) throws JsonProcessingException, IOException {
		Map<String, Object> map = null;
		if (json != null && json.length() != 0) {
			map = new HashMap<>();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			String data = node.get(DATA).toString();
			map.put(DATA, data);
			int num = node.get(NUM).asInt();
			map.put(NUM, num);
		}
		return map;
	}

	/**
	 * 解析出口数据（解析）
	 * 
	 * @param exitData 固定格式：["A47-B47-C47,J47-K47-L47"]
	 * @return 返回格式 [A37,B37,C37]
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	private List<String> eixtDataResolver(String exitData) throws JsonProcessingException, IOException {
		List<String> exitPositions = null;
		if (exitData != null && exitData.length() != 0) {
			exitPositions = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode nodes = mapper.readTree(exitData);

			for (JsonNode node : nodes) {
				String[] parts = node.toString().replaceAll(SYMBOL_COMMA, SYMBOL_BAR).split(SYMBOL_BAR);

				for (String part : parts) {
					String lineNo = getString(part, NUMBER, true);
					String serial = getString(part, LETTER, true);
					String exitPosition = serial + SYMBOL_BAR + lineNo;

					if (!exitPositions.contains(exitPosition)) {
						exitPositions.add(exitPosition);
					}
				}
			}
		}
		return exitPositions;
	}

	/**
	 * 解析过道数据(私有)
	 * 
	 * @param aisleData 过道json数据data部分
	 *                  固定格式：["J35-J46,J47-J60","H36-H46,H48-H61","D36-D46,D48-D61","C35-C46,C47-C60"]
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 * 
	 *                                 返回格式：[C,D,H,J]
	 */
	private List<String> aisleDataResolver(String aisleData) throws JsonProcessingException, IOException {
		List<String> list = null;
		if (aisleData != null && aisleData.length() != 0) {
			list = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode dataArray = mapper.readTree(aisleData);

			for (JsonNode part : dataArray) {
				String nodeToString = part.toString();
				if (nodeToString.indexOf(SYMBOL_COMMA) != -1) {
					String[] split = nodeToString.split(SYMBOL_COMMA);

					for (int i = 0; i < split.length; i++) {
						String letter = getString(split[i], LETTER, true);
						String lineNo = letter.substring(0, 1);
						if (!list.contains(lineNo)) {
							list.add(lineNo);
						}
					}
				} else {
					String letter = getString(part.toString(), LETTER, true);
					String lineNo = letter.substring(0, 1);
					if (!list.contains(lineNo)) {
						list.add(lineNo);
					}
				}

			}
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * 解析data（私有） 固定格式：["L35-L46,L47-L60","A35-A46,A47-A60"]
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	private List<SeatType> dataResolver(String data) throws JsonProcessingException, IOException {
		List<SeatType> list = null;
		if (data != null && data.length() != 0) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode nodeArray = mapper.readTree(data);

			for (JsonNode jsonNode : nodeArray) {
				String part = jsonNode.toString();

				if (part.indexOf(SYMBOL_QUOTATION_MARK) != -1) {
					part = part.replaceAll(SYMBOL_QUOTATION_MARK, STRING_NULL);
				}
				List<SeatType> seats = assembleSeatType(part);

				if (list == null) {
					list = seats;
				} else {
					list.addAll(seats);
				}
			}
		}
		return list;
	}

	/**
	 * 组装SeatType对象集合（私有）
	 * 
	 * 参数格式固定 如："L35-L46,L47-L60"
	 * 
	 * @param json
	 * @return
	 */
	private List<SeatType> assembleSeatType(String json) {
		List<SeatType> list = null;
		if (json != null && json.trim().length() > 0) {
			String[] data = json.split(SYMBOL_COMMA);
			list = new ArrayList<>();

			for (String part : data) {
				String lineNo = getString(part, LETTER, false).substring(0, 1);
				String[] startAndEnd = getString(part, NUMBER, false).split(SYMBOL_BAR);
				int start = Integer.parseInt(startAndEnd[0]);
				int count = Integer.parseInt(startAndEnd[1]) - Integer.parseInt(startAndEnd[0]) + 1;

				SeatType seatType = new SeatType();
				seatType.setLineNo(lineNo);
				seatType.setTotalCount(count);
				seatType.setStart(start);
				list.add(seatType);
			}
		}

		return list;
	}

	/**
	 * 获取字符串中的指定类型字符串方法（私有）
	 * 
	 * @param prototype
	 * @param regular
	 * @return
	 */
	private static String getString(String prototype, String regular, Boolean removeTheSymbol) {
		prototype = prototype.replaceAll(SYMBOL_COMMA, STRING_NULL);
		if (removeTheSymbol) {
			prototype = prototype.replaceAll(SYMBOL_QUOTATION_MARK, STRING_NULL);
			prototype = prototype.replaceAll(SYMBOL_BAR, STRING_NULL);
		}
		return prototype.replaceAll(regular, STRING_NULL);
	}

	public static void main(String[] args) throws JsonProcessingException, IOException {
		SeatResolver resolver = new SeatResolver();

		
		  resolver.put("window","{\"data\":[\"L35-L46,L47-L60\",\"A35-A46,A47-A60\"],\"num\":\"52\"}");
		  
		  List<SeatType> seatParsing = resolver.SeatParsing();
		  
		  System.out.println(seatParsing);
		 

		/*
		 * Map<String, Object> jsonResolver = SeatResolver.JsonResolver(
		 * "{\"data\":[\"L35-L46,L47-L60\",\"A35-A46,A47-A60\"],\"num\":\"52\"}");
		 * System.out.println(jsonResolver);
		 */

		/*
		 * List<SeatType> list = resolver.assembleSeatType("L35-L46,L47-L60");
		 * System.out.println(list);
		 */

		/*
		 * List<SeatType> dataResolver = resolver.dataResolver(
		 * "[\"K35-K46,K47-K59\",\"E36-E46,E48-E61\",\"B35-B46,B47-B59\"]");
		 * System.out.println(dataResolver);
		 */

		/*
		 * List<String> aisleLinePosition = resolver.getAisleLinePosition(
		 * "{\"data\":[\"J35-J46,J47-J60\",\"H36-H46,H48-H61\",\"D36-D46,D48-D61\",\"C35-C46,C47-C60\"],\"num\":\"102\"}"
		 * ); System.out.println(aisleLinePosition);
		 */

/*		List<String> nodes = resolver.eixtDataResolver("[\"A31-C31,H32-K32,A47-C47,H47-K47,H48-K48,A60-C60,H60-K60\"]");
		System.out.println(nodes);*/
	}

}
