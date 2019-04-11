//var data = '{"planeType":"787-9","seats":[{"type":null,"totalCount":12,"lineNo":"K","start":35},{"type":null,"totalCount":13,"lineNo":"K","start":47},{"type":null,"totalCount":11,"lineNo":"E","start":36},{"type":null,"totalCount":14,"lineNo":"E","start":48},{"type":null,"totalCount":12,"lineNo":"B","start":35},{"type":null,"totalCount":13,"lineNo":"B","start":47},{"type":null,"totalCount":12,"lineNo":"L","start":35},{"type":null,"totalCount":14,"lineNo":"L","start":47},{"type":null,"totalCount":12,"lineNo":"A","start":35},{"type":null,"totalCount":14,"lineNo":"A","start":47},{"type":null,"totalCount":12,"lineNo":"J","start":35},{"type":null,"totalCount":14,"lineNo":"J","start":47},{"type":null,"totalCount":11,"lineNo":"H","start":36},{"type":null,"totalCount":14,"lineNo":"H","start":48},{"type":null,"totalCount":11,"lineNo":"D","start":36},{"type":null,"totalCount":14,"lineNo":"D","start":48},{"type":null,"totalCount":12,"lineNo":"C","start":35},{"type":null,"totalCount":14,"lineNo":"C","start":47}],"exitPositions":["A-47","B-47","C-47","J-47","K-47","L-47","D-48","E-48","H-48","J-60","L-60","A-60","C-60","D-61","E-61","H-61"],"aislePosition":["C-D","H-J"],"startNo":35,"rowCount":61,"totalCount":229,"exitCount":4,"serials":["A","B","C","D","E","H","J","K","L"]}';
//var data = '{"planeType":"空客321NEO(32N)","seats":[{"type":null,"totalCount":25,"lineNo":"B","start":37},{"type":null,"totalCount":25,"lineNo":"J","start":37},{"type":null,"totalCount":25,"lineNo":"A","start":37},{"type":null,"totalCount":25,"lineNo":"K","start":37},{"type":null,"totalCount":25,"lineNo":"C","start":37},{"type":null,"totalCount":11,"lineNo":"H","start":37},{"type":null,"totalCount":12,"lineNo":"H","start":50}],"exitNos":null,"aislePosition":["C-H"],"startNo":37,"rowCount":62,"serials":["A","B","C","H","J","K"],"exitNo":null}';
//updataInfo(data);

$(function(){
	var type = $("#type option:selected").text();
	ajaxData(type);
//	GenerateSimulatedSeats(data,".map");
	$("#type").change(function(){
		var selectOption = $(this).children(":selected");
		var type = $("#type option:selected").text();
		var imgSrc = selectOption.get(0).getAttribute("imgSrc");
		$("#filght_image").get(0).setAttribute("src",imgSrc)
		if(imgSrc.indexOf("787-9.jpg") != -1){
			ajaxData(type);
			
//			data='{"planeType":"787-9","seats":[{"type":null,"totalCount":12,"lineNo":"K","start":35},{"type":null,"totalCount":13,"lineNo":"K","start":47},{"type":null,"totalCount":11,"lineNo":"E","start":36},{"type":null,"totalCount":14,"lineNo":"E","start":48},{"type":null,"totalCount":12,"lineNo":"B","start":35},{"type":null,"totalCount":13,"lineNo":"B","start":47},{"type":null,"totalCount":12,"lineNo":"L","start":35},{"type":null,"totalCount":14,"lineNo":"L","start":47},{"type":null,"totalCount":12,"lineNo":"A","start":35},{"type":null,"totalCount":14,"lineNo":"A","start":47},{"type":null,"totalCount":12,"lineNo":"J","start":35},{"type":null,"totalCount":14,"lineNo":"J","start":47},{"type":null,"totalCount":11,"lineNo":"H","start":36},{"type":null,"totalCount":14,"lineNo":"H","start":48},{"type":null,"totalCount":11,"lineNo":"D","start":36},{"type":null,"totalCount":14,"lineNo":"D","start":48},{"type":null,"totalCount":12,"lineNo":"C","start":35},{"type":null,"totalCount":14,"lineNo":"C","start":47}],"exitPositions":["A-47","B-47","C-47","J-47","K-47","L-47","D-48","E-48","H-48","J-60","L-60","A-60","C-60","D-61","E-61","H-61"],"aislePosition":["C-D","H-J"],"startNo":35,"rowCount":61,"totalCount":229,"exitCount":4,"serials":["A","B","C","D","E","H","J","K","L"]}';
		}else if(imgSrc.indexOf("空客321NEO(32N)") != -1){
			ajaxData(type);
//			data='{"planeType":"空客321NEO(32N)","seats":[{"type":null,"totalCount":32,"lineNo":"B","start":31},{"type":null,"totalCount":32,"lineNo":"J","start":31},{"type":null,"totalCount":6,"lineNo":"A","start":31},{"type":null,"totalCount":26,"lineNo":"A","start":37},{"type":null,"totalCount":32,"lineNo":"K","start":31},{"type":null,"totalCount":6,"lineNo":"C","start":31},{"type":null,"totalCount":26,"lineNo":"C","start":37},{"type":null,"totalCount":6,"lineNo":"H","start":31},{"type":null,"totalCount":12,"lineNo":"H","start":37},{"type":null,"totalCount":13,"lineNo":"H","start":50}],"exitPositions":["A-37","B-37","C-37","H-37","J-37","K-37","H-49","J-49","K-49","A-49","B-49","C-49","A-62","B-62","C-62","H-62","J-62","K-62"],"aislePosition":["C-H"],"startNo":31,"rowCount":62,"totalCount":191,"exitCount":6,"serials":["A","B","C","H","J","K"]}';
			
		}else if(imgSrc.indexOf("B737-300") != -1){
			ajaxData(type);
			
//			data = '{"planeType":"B737-300","seats":[{"type":null,"totalCount":20,"lineNo":"B","start":11},{"type":null,"totalCount":20,"lineNo":"K","start":11},{"type":null,"totalCount":20,"lineNo":"A","start":11},{"type":null,"totalCount":20,"lineNo":"L","start":11},{"type":null,"totalCount":20,"lineNo":"C","start":11},{"type":null,"totalCount":20,"lineNo":"J","start":11}],"exitPositions":["A-18","B-18","C-18","J-18","K-18","L-18","A-30","B-30","C-30","J-30","K-30","L-30"],"aislePosition":["C-J"],"startNo":11,"rowCount":30,"totalCount":120,"exitCount":4,"serials":["A","B","C","J","K","L"]}';
		}
		
	});
});


/**
 * 异步请求数据
 * @param type
 * @returns
 */
function ajaxData(type){
	$.ajax({
		url:"http://localhost:8080/getData/"+ type,  //请求地址
		//data:"pn=" + pn,           //请求参数
		type:"GET",             //请求类型
		success:function(va){  //回掉函数  参数为响应数据
			data = JSON.stringify(va);
			GenerateSimulatedSeats(data,".map");
			updataInfo(data);
		},
		error:function(){  //失败函数
			alert("请求失败");
		},
		dataType:"json"  //返回类型  
	});
}
