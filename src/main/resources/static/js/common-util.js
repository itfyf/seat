/**
 * 获取图像
 * 
 * @param {Object}
 *            code
 */
function getSvgElement(code) {
	return $('<svg class="icon" aria-hidden="true"><use xlink:href="#' + code + '"></use></svg>');
}

/**
 * 获取行div方法
 * 
 * @param {String}
 *            座位标示
 */
function getRowDiv(seat_line) {
	var $rowElement = getNullParentNode();
	$rowElement.attr("seat-line", seat_line);

	return $rowElement;
}
/**
 * 获取一个空的父节点
 */
function getNullParentNode() {
	var $rowElement = $("<div class='row'></div>");
	return $rowElement;
}

/**
 * 获取空的底层子节点
 */
function getNullSonNode() {
	var $seatElement = $("<span></span>")
	return $seatElement;
}

/**
 * 获取单个节点方法
 * 
 * @param {Number}
 *            座位号
 */
function getSingleSeatNode(seat_no) {
	var $seatElement = getNullSonNode();
	$seatElement.attr("seat-no", seat_no);

	return $seatElement;
}

/**
 * 创建座位标识号
 */
function getSeatNumLogo(start, maxNo) {
	var $parentNode = getNullParentNode();
	// 预留一个节点
	$parentNode.prepend(getNullSonNode());
	/*
	 * var state = start; while(state <= maxNo) { var $sonNode =
	 * getNullSonNode(); $sonNode.text(state) $parentNode.append($sonNode);
	 * state++; }
	 */
	
	for (var i = start;i <= maxNo; i++) {
		var $sonNode = getNullSonNode();
		$sonNode.text(i)
		$parentNode.append($sonNode);
	}
	return $parentNode;
}

/**
 * 获取节点集合方法
 * 
 * @param {Number}
 *            起始数
 * @param {Number}
 *            总数
 */
function getSeatNodes(start, count) {
	if(count != null && count > 0) {
		var nodes = new Array();
		var len = start + count;
		for(var i = start; i <= len; i++) {
			var singleNode = getSingleSeatNode(i);
			singleNode.prop("seat-no", i);
			var svg = getSvgElement("icon-yuanjiao-rect");
			singleNode.append(svg);
			nodes.push(singleNode);
		}
		return nodes;
	}
}

/**
 * 在某个节点之前插入多个节点
 * 
 * @param {Node}
 *            目标节点
 * @param {Array}
 *            需要插入的数组
 */
function insertNodesBefore(target, nodes) {
	var firstNodeNo = nodes[0].prop("seat-no");
	var parent = target.parent();
	for(var i = 0; i < nodes.length; i++) {
		if(i == 0) {
			target.before(nodes[i]);
		} else {
			parent.children("[seat-no='" + firstNodeNo + "']").after(nodes[i]);
		}
	}
}

/**
 * 在某个节点之后插入多个节点
 * 
 * @param {Node}
 *            目标节点
 * @param {Array}
 *            需要插入的数组
 */
function insertNodesAfter(target, nodes) {
	var firstNodeNo = nodes[0].prop("seat-no");
	var parent = target.parent();
	for(var i = 0; i < nodes.length; i++) {
		if(i == 0) {
			target.after(nodes[i]);
		} else {
			parent.children("[seat-no='" + firstNodeNo + "']").after(nodes[i]);
		}
	}

}

/**
 * 通过单个号码查找目标节点
 * 
 * @param {Node}
 *            父节点
 * @param {String}
 *            列标识
 * @param {Number}
 *            座位号
 */
function searchTargetBySeatNo(parent, lineNo, seatNo) {
	var nodes = parent.children("[seat-line= '" + lineNo + "']").children("[seat-no]");
	var targetNo = nodes.eq(0).get(0).getAttribute("seat-no");
	var direction = "before";

	for(var singleNode of nodes) {
		var currentNo = singleNode.getAttribute("seat-no");
		if(currentNo <= seatNo) {
			targetNo = currentNo;
		}
	}

	var targetInfo = new Map();
	targetInfo.set("targetNo", targetNo);
	targetInfo.set("direction", direction);

	return targetInfo;
}

/**
 * 通过范围寻找目标元素
 * 
 * @param {Node}
 *            父节点
 * @param {String}
 *            列标识
 * @param {Number}
 *            范围最大
 * @param {Number}
 *            范围最小
 */
function searchTargetByScope(parent, lineNo, max, min) {
	var nodes = parent.children("[seat-line= '" + lineNo + "']").children("[seat-no]");
	if(nodes.length == 0) {
		return false;
	}
	var firstNodeNo = nodes.first().get(0).getAttribute("seat-no");
	var lastNodeNo = nodes.last().get(0).getAttribute("seat-no");
	var targetNo = firstNodeNo;
	var direction = "before";

	if($("[seat-no = '" + lastNodeNo + "']").next().length == 0 || $("[seat-no = '" + lastNodeNo + "']").next().get(0).getAttribute("seat-no") != null) {
		targetNo = lastNodeNo;
		direction = "after";
	} else {
		if(max < firstNodeNo) {

		} else if(min > lastNodeNo) {
			targetNo = lastNodeNo;
			direction = "after";
		} else {
			return searchTargetBySeatNo(parent, lineNo, max);
		}
	}

	var targetInfo = new Map();
	targetInfo.set("targetNo", targetNo);
	targetInfo.set("direction", direction);
	return targetInfo;
}

/**
 * 获取出口号集合
 * 
 * @param {Object}
 *            exitPositions
 */
function getExitNos(exitPositions) {
	var exitNos = new Array();
	for(exitPosition of exitPositions) {
		var infos = exitPosition.split("-");
		var lineNo = infos[1];
		if(exitNos.indexOf(lineNo) == -1) {
			if(isSimilar(exitNos, lineNo)) {
				exitNos.push(lineNo);
			}
		}
	}
	return exitNos;
}
/**
 * 判断数组是否存在相近的值
 * 
 * @param {Object}
 *            exitNos
 * @param {Object}
 *            lineNo
 */
function isSimilar(exitNos, lineNo) {
	for(var i = 0; i < exitNos.length; i++) {
		if(Math.abs(lineNo - exitNos[i]) < 3) {
			return false;
		}
	}
	return true;
}

function isSimilarMax(exitNos, maxNo) {
	for(var i = 0; i < exitNos.length; i++) {
		if(Math.abs(exitNos[i] - maxNo) < 3) {
			return true;
		}
	}
	return false;
}

/**
 * 
 * @param {Object}
 *            数据
 * @param {NodeSelector}
 *            节点选择器
 */
function GenerateSimulatedSeats(data, selector) {
	var obj = JSON.parse(data);
	
	// 序列号集合
	var serials = obj.serials;

	var seats = obj.seats;
	
	console.log(seats);

	/**
	 * 开始号码
	 */
	var start = obj.startNo;
	/**
	 * 单行最好号码
	 */
	var maxNo = obj.rowCount;

	console.log(maxNo);
	/**
	 * 过道位置
	 */
	var aislePositions = obj.aislePosition;

	/**
	 * 出口
	 */
	var exitPositions = obj.exitPositions;

	/**
	 * 非相近出口号
	 */
	var exitNos = getExitNos(exitPositions);
	console.log(exitNos);
	// 加载父类元素
	var $parent = $(selector);

	// 清空子节点
	$parent.empty();

	// 创建二级子节点
	for(var serial of serials) {
		var serialNoNode = getNullSonNode();
		serialNoNode.text(serial);
		var $rowDiv = getRowDiv(serial);
		$rowDiv.prepend(serialNoNode);
		$parent.prepend($rowDiv);
	}

	// 创建过道行
	for(var aislePosition of aislePositions) {
		var lineNos = aislePosition.split("-");

		var lineNode = $("[seat-line = '" + lineNos[0] + "']");
		if(lineNode.next().get(0).getAttribute("seat-line") == lineNode[1]) {
			lineNode.after(getNullParentNode());
		} else {
			lineNode.before(getNullParentNode());
		}
	}

	// 创建座位标识
	var $seatNumLogo = getSeatNumLogo(start, maxNo);
	$parent.prepend($seatNumLogo);

	var count = 0;
	// 添加座位
	for(var seat of seats) {
		var $rowDiv = $("[seat-line = '" + seat.lineNo + "']");
		var targetInfo = searchTargetByScope($parent, seat.lineNo, seat.start, seat.totalCount);

		// 判断目标信息
		if(targetInfo == false) {
			var len = seat.start + seat.totalCount - 1;
			for(var i = seat.start; i <= len; i++) {
				var seatNode = getSingleSeatNode(i);
				var svg = getSvgElement("icon-yuanjiao-rect");
				seatNode.append(svg);
				$rowDiv.append(seatNode);
			}
		} else {
			var nodes = getSeatNodes(seat.start, seat.totalCount - 1);
			var targetNo = targetInfo.get("targetNo");
			var direction = targetInfo.get("direction");
			var target = $rowDiv.children("[seat-no = '" + targetNo + "']");
			var firstNodeNo = nodes[0].prop("seat-no");
			var parent = target.parent();

			if(direction == "before") {
				for(var i = 0; i < nodes.length; i++) {
					target.before(nodes[i]);
				}
			} else {
				for(var i = nodes.length - 1; i >= 0; i--) {
					target.after(nodes[i]);
				}
			}
		}
	}

	// 扫描一遍座位，添加空隙
	var $rows = $("[seat-line]");
	// 逐行扫描 -- 插入空格
	for(var row of $rows) {
		var line = $(row).children("[seat-no]");

		for(var seat of line) {
			var no = $(seat).get(0).getAttribute("seat-no");
			var last = $(seat).prev();
			var lastNo = last.get(0).getAttribute("seat-no");
			var next = $(seat).next();

			if(lastNo == null) {
				if(no > start) {
					var len = no - start;
					for(var i = 0; i < len; i++) {
						$(seat).before(getNullSonNode());
					}
				}
			} else if(next.length == 0 /*
										 * ||
										 * next.get(0).getAttribute("seat-no") !=
										 * null
										 */ ) {
				var dif = maxNo - no;
				if(dif >= 1) {
					for(var i = dif - 1; i >= 0; i--) {
						var nullSonNode = getNullSonNode();
						if(maxNo - no <= 3) {
							var currentNo = parseInt(no) + i + 1;
							nullSonNode.get(0).setAttribute("seat-no", currentNo);
						}
						$(seat).after(nullSonNode);
					}
				}

			} else {
				if(no - lastNo > 1) {
					var len = no - lastNo - 1;
					for(var i = 0; i < len; i++) {
						$(seat).before(getNullSonNode());
					}
				}
			}
		}
	}

	// 插入出口数据
	var $TAGRow = $parent.children(":first");
	var $sons = $TAGRow.children("span").not(":empty");
	var rows = $parent.children().not($TAGRow).not(":empty");
	var xiaCount = 0;

	for(exitPosition of exitPositions) {
		var info = exitPosition.split("-");
		var serial = info[0];
		var lineNo = info[1];

		if(maxNo - lineNo <= 2) {
			var lastNode = $TAGRow.children(":last");
			if(lastNode.text() == maxNo) {
				var nullNode = getNullSonNode();
				var exitIcon = getSvgElement("icon-sanjiaoxing-xia");
				exitIcon.addClass("exit");
				nullNode.append(exitIcon);
				exitNode.before(nullNode);
				$TAGRow.append(nullNode);
			}
		} else {
			var exitNode = $sons.filter("span:contains(" + lineNo + ")");
			if(exitNode.prev().text() != "" && exitNode.prev().prev().text() != "") {
				var nullNode = getNullSonNode();
				var exitIcon = getSvgElement("icon-sanjiaoxing-xia");
				exitIcon.addClass("exit");
				nullNode.append(exitIcon);
				exitNode.before(nullNode);
			}
		}
		// 添加下箭头
		var lastRow = $parent.children(":last");
		if(lastRow.get(0).getAttribute("seat-line") != null) {
			var $nullParenrNode = getNullParentNode();

			var len = (maxNo + exitNos.length + 1);
			for(var i = start; i <= len; i++) {
				$nullParenrNode.append(getNullSonNode());
			}
			$parent.append($nullParenrNode);
		}
		var nodes = lastRow.children();
		var currrentNode = nodes.eq(parseInt(lineNo) - start + 1 + xiaCount);
		/*
		 * console.log(parseInt(lineNo) - start + 1 + xiaCount);
		 * console.log(currrentNode);
		 */
		if(maxNo - parseInt(lineNo) <= 3) {
			currrentNode = nodes.filter(":last");
		}

		if(currrentNode.prev().children().length == 0 && currrentNode.children().length == 0 && currrentNode.prev().prev().children().length == 0) {
			var exitIcon = getSvgElement("icon-sanjiaoxing-xia1");
			exitIcon.addClass("exit");
			currrentNode.append(exitIcon);
			xiaCount++;
		}

		var $currentRow = $("[seat-line = '" + serial + "']");
		if(!(maxNo - lineNo <= 3)) {
			var $currentLine = $currentRow.children("[seat-no='" + lineNo + "']");
			if($currentLine.length == 0) {
				var addLineNo = parseInt(lineNo) + 1;
				$currentLine = $currentRow.children("[seat-no='" + addLineNo + "']");
			}
			$currentLine.before(getNullSonNode());
		}

	}

	if(isSimilarMax(exitNos, maxNo)) {
		// 最后填充空格
		for(serial of serials) {
			var $rowDiv = $("[seat-line='" + serial + "']");
			var lastDiv = $rowDiv.children(":last");
			var lineNo = lastDiv.get(0).getAttribute("seat-no");
			if(lineNo != null && maxNo - lineNo <= 3) {
				var len = maxNo - lineNo + 1;
				for(var i = 0; i < len; i++) {
					lastDiv.after(getNullSonNode());
				}
			}
		}
	}
}

function updataInfo(data) {
	var obj = JSON.parse(data);
	var type = obj.planeType;
	var exitCount = obj.exitCount;

	var totalCount = obj.totalCount;
	/**
	 * 开始号码
	 */
	var start = obj.startNo;
	/**
	 * 单行最好号码
	 */
	var maxNo = obj.rowCount;
	
	$("#model").text(type);

	$("#seatInfo").children(".value").text(start + "~" + maxNo);
	$("#seatInfo").find(".num").text(totalCount);
	
	$("#exitInfo").children(".value").text(exitCount);
}