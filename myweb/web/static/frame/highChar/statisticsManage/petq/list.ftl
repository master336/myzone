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
			var name=$("#name").val();
			var beginDate=$("#beginDate").val();
			var endDate=$("#endDate").val();
	  		var ajaxUrl = 'getChart.jhtml?now=' + new Date().getTime();
			$.ajax({	
	   			type:"GET",
	    		async:false,
		   		url:ajaxUrl,
		    	data:{"name":name,"beginDate":beginDate,"endDate":endDate},
		    	dataType:"json",
		    	success:function(data){
		    		rotationColumnChart('container',data.title,data.subtitle,'M',data.yAxisTitle,data.xAxis,data.series,'upSlider',0,50,15,'rightSlider',0,50,15);
	   			}
			});
		});
	});
	
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.main.petq")} <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			${message("petq.condition.nam")}: <input type="text" id="name" name="name" value="${(name)!""}" />
			${message("petq.condition.beginDate")}: <input type="text" id="beginDate" name="beginDate" class="text Wdate" value="${(beginDate?string('yyyy-MM-dd'))!}" onfocus="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			${message("petq.condition.endDate")}: <input type="text" id="endDate" name="endDate" class="text Wdate" value="${(endDate?string('yyyy-MM-dd'))!}" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});" />
			<input type="submit" class="button" value="${message("admin.common.submit")}" />
		</div>
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
					<th class="check">
						<input type="checkbox" id="selectAll" />
					</th>
					<th>
						<span>${message("petq.numberNo")}</span>
					</th>
					<th>
						<span>${message("petq.name")}</span>
					</th>
					<th>
						<span>${message("petq.num")}</span>
					</th>
				</tr>
				[#list page.content as map]
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${map.id}" />
						</td>
						<td>
							${map.numberNo}
						</td>
						<td>
							${map.name}
						</td>
						<td>
							${map.num}
						</td>
					</tr>
				[/#list]
			</table>
			[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
				[#include "/admin/include/pagination.ftl"]
			[/@pagination]
		</div>
		<div class="input tabContent">
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