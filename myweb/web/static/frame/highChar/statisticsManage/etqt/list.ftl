<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.parameterGroup.list")} - Powered By Gap</title>
<meta name="author" content="Gap Team" />
<meta name="copyright" content="Gap" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/js/slider/slider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<!-- 图表-->
<script type='text/javascript' src='${base}/resources/admin/js/highcharts/highcharts.js'></script>
<script type='text/javascript' src='${base}/resources/admin/js/highcharts/exporting.js'></script>
<script type='text/javascript' src='${base}/resources/admin/js/highcharts/highcharts-3d.js'></script>
<!-- 图表工具类-->
<script type='text/javascript' src='${base}/resources/admin/js/highcharts/higcharts-tools.js'></script>
<!-- 滑动条-->
<script type="text/javascript" src="${base}/resources/admin/js/slider/slider_extras.js"></script>
<!-- 日期控件-->
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//日期类型
	var dateType=0;
	//查询操作
	function queryChars(){
		var beginDate=$("#beginDate").val();
		var endDate=$("#endDate").val();
		if(beginDate==""){
			alert("起始日期不能为空!");
			return;
		}
		if(endDate==""){
			alert("结束日期不能为空!");
			return;
		}
		//判断 是否允许跨年查询
		if(dateType!=0){
			var beginYesr=beginDate.substring(0,4);
			var endYesr=endDate.substring(0,4);
			if(beginYesr!=endYesr){
				alert("不能跨年查询!");
				return ;
			}
		}
  		var ajaxUrl = 'getChart.jhtml?now=' + new Date().getTime();
		$.ajax({	
   			type:"GET",
    		async:false,
	   		url:ajaxUrl,
	   		data:{"beginDate":beginDate,"endDate":endDate,"dateType":dateType},
	    	dataType:"json",
	    	success:function(data){
	    		rotationColumnChart('container',data.title,data.subtitle,'M',data.yAxisTitle,data.xAxis,data.series,'upSlider',0,50,15,'rightSlider',0,50,15);
   			}
		});
	}
	//改变
	function to_change(){
		var obj  = document.getElementsByName('dataTypeName');
		for(var i=0;i<obj.length;i++){
            if(obj[i].checked==true){
                dateType=obj[i].value;
                $("#beginDate").val('');
                $("#endDate").val('');
            }
        }
	}
	//设置 选择日期
	function focusDatePicker(obj){
		if(dateType==0){
			if(obj=='begin'){
				WdatePicker({dateFmt:'yyyy',maxDate: '#F{$dp.$D(\'endDate\')}'});
			}else if(obj=='end'){
				WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'beginDate\')}'});
			}
		}else if(dateType==1){
			if(obj=='begin'){
				WdatePicker({dateFmt:'yyyy-MM',maxDate: '#F{$dp.$D(\'endDate\')}'});
			}else if(obj=='end'){
				WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'beginDate\')}'});
			}
		}else if(dateType==2){
			if(obj=='begin'){
				WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '#F{$dp.$D(\'endDate\')}'});
			}else if(obj=='end'){
				WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\')}'});
			}
		}
	}
	
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.main.etqt")} <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<input type="radio" name="dataTypeName" value="0"  checked="checked" onClick="to_change()" />${message("etqt.condition.year")}
			<input type="radio" name="dataTypeName" value="1"  onClick="to_change()"  />${message("etqt.condition.month")}
			<input type="radio" name="dataTypeName" value="2"  onClick="to_change()"  />${message("etqt.condition.week")}
			${message("etqt.condition.beginDate")}: <input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="focusDatePicker('begin');" />
			${message("etqt.condition.endDate")}: <input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="focusDatePicker('end');" />
			<input type="button" class="button" onClick="queryChars()" value="${message("admin.common.submit")}" />
		</div>
		<div>
			<div id="container" style="margin-left: 10px;min-width:700px;height:400px"></div>
			<div style="text-align:center;width:30%;margin-right: auto; margin-left: auto; ">
				<table>
					<tr id="upSlider_tr" style="display:none;">
						<td>
							${message("admin.common.UpAndDownAngle")}：
						</td>
						<td>
							<div id="upSlider" style="height:40px;margin-right: auto; margin-left: auto;">
            				</div>
						</td>
					</tr>
					<tr id="rightSlider_tr" style="display:none;">
						<td>
							${message("admin.common.LeftAndRightAngle")}： 
						</td>
						<td>
							<div id="rightSlider" style="height:40px;margin-right: auto; margin-left: auto;display: inline;">
            				</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>