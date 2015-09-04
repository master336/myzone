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
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
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
	$().ready(function() {
		$("#a-tabs-2").click(function(){
	  		var ajaxUrl = 'getChart.jhtml?now=' + new Date().getTime();
			$.ajax({	
	   			type:"GET",
	    		async:false,
		   		url:ajaxUrl,
		    	//data:{"name":name},
		    	dataType:"json",
		    	success:function(data){
		    		for(var i=0;i<data.length;i++){
		    			var dataArray=data[i];
		    			pieChart("pieCharts"+i,dataArray.titleMap,dataArray.subtitleMap,dataArray.tooltipMap,dataArray.seriesList);
		    		}
	   			}
			});
		});
	});
	
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.main.pts")} <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
	    <ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.common.tabulation")}" />
			</li>
			<li>
				<input type="button" id="a-tabs-2" value="${message("admin.common.chart")}" />
			</li>
		</ul>
		<div class="input tabContent">
			<table id="listTable" class="list">
				<tr>
					<th colspan="4">
						<span>${message("pts.purchaseSaleRelease")}</span>
					</th>
				</tr>
				[#if resultMap?has_content]
					<tr>
						<td>
							${(resultMap.get('supplyNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('supplyNumber'))!""}
						</td>
						<td>
							${(resultMap.get('supplyThroughNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('supplyThroughNumber'))!""}
						</td>
					<tr>
					<tr>
						<td>
							${(resultMap.get('purchaseNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('purchaseNumber'))!""}
						</td>
						<td>
							${(resultMap.get('purchaseThroughNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('purchaseThroughNumber'))!""}
						</td>
					<tr>
				[/#if]
				<tr>
					<th colspan="4">
						<span>${message("pts.logisticsRelease")}</span>
					</th>
				</tr>
				[#if resultMap?has_content]
					<tr>
						<td>
							${(resultMap.get('logisticsNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('logisticsNumber'))!""}
						</td>
						<td>
							${(resultMap.get('logisticsThroughNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('logisticsThroughNumber'))!""}
						</td>
					<tr>
					<tr>
						<td>
							${(resultMap.get('palletNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('palletNumber'))!""}
						</td>
						<td>
							${(resultMap.get('palletThroughNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('palletThroughNumber'))!""}
						</td>
					<tr>
				[/#if]
				<tr>
					<th colspan="4">
						<span>${message("pts.tradingRelease")}</span>
					</th>
				</tr>
				[#if resultMap?has_content]
					<tr>
						<td>
							${(resultMap.get('tradingNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('tradingNumber'))!""}
						</td>
						<td>
							${(resultMap.get('logisticsSradingThroughNumberName'))!""}
						</td>
						<td>
							${(resultMap.get('logisticsSradingThroughNumber'))!""}
						</td>
					<tr>
				[/#if]
			</table>
		</div>
		<div class="input tabContent">
			<div id="pieCharts0" style="margin-left: 10px;min-width:700px;height:400px"></div>
			<div id="pieCharts1" style="margin-left: 10px;min-width:700px;height:400px"></div>
			<div id="pieCharts2" style="margin-left: 10px;min-width:700px;height:400px"></div>
			<div id="pieCharts3" style="margin-left: 10px;min-width:700px;height:400px"></div>
			<div id="pieCharts4" style="margin-left: 10px;min-width:700px;height:400px"></div>
		</div>
	</form>
</body>
</html>