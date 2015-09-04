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
<!-- 图表工具类-->
<script type='text/javascript' src='${base}/resources/admin/js/highcharts/higcharts-tools.js'></script>
<!-- 日期控件-->
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//查询 图表数据
	function queryCharts(){
		var date=$("#date").val();
  		var ajaxUrl = 'getChart.jhtml?now=' + new Date().getTime();
		$.ajax({	
   			type:"GET",
    		async:false,
	   		url:ajaxUrl,
	    	data:{"date":date},
	    	dataType:"json",
	    	success:function(data){
	    		brokenLineCharts('container',data.titleMap,data.subtitleMap,data.xAxisMap,data.yAxisList,data.tooltipMap,data.seriesList);
   			}
		});
	}
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.main.ivq")} <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			${message("ivq.condition.date")}: <input type="text" id="date" name="date" class="text Wdate" value="${(date?string('yyyy-MM-dd'))!}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
			<input type="button" class="button" onClick="queryCharts()" value="${message("admin.common.submit")}" />
		</div>
		<div id="container" style="margin-left: 10px;min-width:700px;height:400px"></div>
	</form>
</body>
</html>