/**
 * add 1.0
 * @author qzh 
 * @Date 2015-5-28
 * @version 1.0
 */

/**
 * 旋转 3D柱状图
 * 
 * @param divId
 *            显示图标的div标签id 不能为空
 * @param titleData
 *            一级标题 允许为""
 * @param subtitleData
 *            二级标题 允许为""
 * @param tooltipValueSuffix
 *            悬浮 title 后缀单位与y坐标 单位 允许为""
 * @param yAxisTitle
 *            y坐标标题 允许为""
 * @param xAxisArray
 *            x轴显示列数组 不能为空 格式:['A','B','C','D','E','F','G','H','I'] 不能为空
 * @param seriesData
 *            是显示信息数据 不能为空
 *            格式:[{name:'实例1',data:[1,2,3,4,5,6,7,8,9]},{name:'实例2',data:[9,8,7,6,5,4,3,2,1]}]
 *            data数据要跟xAxisArray数组长度一样 data 数组还可以
 *            data：[{color:'#4876FF',y:20},{color:'#FFFF00',y:20}]设置每个住体的颜色
 * 
 * 向上滑动条 参数
 * @param upTargetDivId
 *            向上滑动条的父节点id 可以为空 不显示
 * @param upSliderMin
 *            向上滑动条的最小值 数字 可以为空 默认为0
 * @param upSliderMax
 *            向上滑动条的最大值 数字 可以为空 默认为50
 * @param upSliderInitVal
 *            向上滑动条的默认值 数字 可以为空 默认为0
 * 
 * 向右滑动条 参数
 * @param rightTargetDivId
 *            向右滑动条的父节点id 可以为空 不显示
 * @param rightSliderMin
 *            向右滑动条的最小值 数字 可以为空 默认为0
 * @param rightSliderMax
 *            向右滑动条的最大值 数字 可以为空 默认为50
 * @param rightSliderInitVal
 *            向右滑动条的默认值 数字 可以为空 默认为0
 * @return
 */
function rotationColumnChart(divId,titleData,subtitleData,tooltipValueSuffix,yAxisTitle,xAxisArray,seriesData,upTargetDivId,upSliderMin,upSliderMax,upSliderInitVal,rightTargetDivId,rightSliderMin,rightSliderMax,rightSliderInitVal){
	// 判断是否字符串
	if((typeof divId !='string') || divId.constructor != String || divId==''){
		alert("元素id不能为空!");
		return;
	}
	var chart = new Highcharts.Chart({
        chart: {
            renderTo: divId,
            type: 'column',
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 0,
                beta: 0,
                depth: 50,
                viewDistance: 25
            }
        },
        title: {
            text: titleData
        },
        subtitle: {
            text: ''
        },
        legend: {
            align: 'center',
            verticalAlign: 'top',
            x: 0,
            y: 30
        },
        yAxis: [{

            title: {
                text: yAxisTitle,
                 x:10 // 向右移动10
            }
        },{ // Secondary yAxis
            title: {
                text: '',
                style: {
                    color: '#4572A7'
                }
            },
            labels: {
                format: '{value} mm',
                style: {
                    color: '#4572A7'
                }
            },
            opposite: true
        }],
        tooltip: {
            shared: true,
            valueSuffix: ' '+tooltipValueSuffix
        },
        plotOptions: {
            column: {
                depth: 25
            },
			series: {
               pointPadding: 0, //数据点之间的距离值
               groupPadding: 0, //分组之间的距离值
               borderWidth: 0,
               shadow: false,
               pointWidth:50 //柱子之间的距离值
           }
        },
         xAxis: {
        	categories: xAxisArray
    	},
        series: seriesData
    });
	//设置滑动条
	slider(chart,upTargetDivId,upSliderMin,upSliderMax,upSliderInitVal,rightTargetDivId,rightSliderMin,rightSliderMax,rightSliderInitVal);
}


/**
 * 旋转 3D柱状图
 * 
 * @param divId
 *            显示图标的div标签id 不能为空
 * @param titleData
 *            一级标题  
 * @param subtitleData
 *            二级标题  
 * @param tooltipData
 *            悬浮 title 是否同时显示
 * @param yAxisData
 *            y坐标标题 
 * @param xAxisData
 *            横轴显示列数组   格式:['A','B','C','D','E','F','G','H','I'] 
 * @param seriesData
 *            是显示信息数据 不能为空
 *            格式:[{name:'实例1',data:[1,2,3,4,5,6,7,8,9]},{name:'实例2',data:[9,8,7,6,5,4,3,2,1]}]
 *            data数据要跟xAxisArray数组长度一样 data 数组还可以
 *            data：[{color:'#4876FF',y:20},{color:'#FFFF00',y:20}]设置每个住体的颜色
 * 
 * 向上滑动条 参数
 * @param upTargetDivId
 *            向上滑动条的父节点id 可以为空 不显示
 * @param upSliderMin
 *            向上滑动条的最小值 数字 可以为空 默认为0
 * @param upSliderMax
 *            向上滑动条的最大值 数字 可以为空 默认为50
 * @param upSliderInitVal
 *            向上滑动条的默认值 数字 可以为空 默认为0
 * 
 * 向右滑动条 参数
 * @param rightTargetDivId
 *            向右滑动条的父节点id 可以为空 不显示
 * @param rightSliderMin
 *            向右滑动条的最小值 数字 可以为空 默认为0
 * @param rightSliderMax
 *            向右滑动条的最大值 数字 可以为空 默认为50
 * @param rightSliderInitVal
 *            向右滑动条的默认值 数字 可以为空 默认为0
 * @return
 */
function rotationColumnCharts(divId,titleData,subtitleData,tooltipData,yAxisData,xAxisData,seriesData,upTargetDivId,upSliderMin,upSliderMax,upSliderInitVal,rightTargetDivId,rightSliderMin,rightSliderMax,rightSliderInitVal){
	// 判断是否字符串
	if((typeof divId !='string') || divId.constructor != String || divId==''){
		alert("元素id不能为空!");
		return;
	}
	var chart = new Highcharts.Chart({
        chart: {
            renderTo: divId,
            type: 'column',
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 0,
                beta: 0,
                depth: 50,
                viewDistance: 25
            }
        },
        title: titleData,
        subtitle: subtitleData,
        legend: {
            align: 'center',
            verticalAlign: 'top',
            x: 0,
            y: 30
        },
        yAxis: yAxisData,
        tooltip: tooltipData,
        xAxis: xAxisData,
        plotOptions: {
            column: {
                depth: 25
            },
			series: {
               pointPadding: 0, //数据点之间的距离值
               groupPadding: 0, //分组之间的距离值
               borderWidth: 0,
               shadow: false,
               pointWidth:50 //柱子之间的距离值
           }
        },
        series: seriesData
    });
	//设置滑动条
	slider(chart,upTargetDivId,upSliderMin,upSliderMax,upSliderInitVal,rightTargetDivId,rightSliderMin,rightSliderMax,rightSliderInitVal);
}

/**
 * 设置滑动条
 * @param chart 图表对象
 * 
 * 向上滑动条 参数
 * @param upTargetDivId
 *            向上滑动条的父节点id 可以为空 不显示
 * @param upSliderMin
 *            向上滑动条的最小值 数字 可以为空 默认为0
 * @param upSliderMax
 *            向上滑动条的最大值 数字 可以为空 默认为50
 * @param upSliderInitVal
 *            向上滑动条的默认值 数字 可以为空 默认为0
 * 
 * 向右滑动条 参数
 * @param rightTargetDivId
 *            向右滑动条的父节点id 可以为空 不显示
 * @param rightSliderMin
 *            向右滑动条的最小值 数字 可以为空 默认为0
 * @param rightSliderMax
 *            向右滑动条的最大值 数字 可以为空 默认为50
 * @param rightSliderInitVal
 *            向右滑动条的默认值 数字 可以为空 默认为0
 * @return
 */
function slider(chart,upTargetDivId,upSliderMin,upSliderMax,upSliderInitVal,rightTargetDivId,rightSliderMin,rightSliderMax,rightSliderInitVal){
	if (typeof(chart)=="undefined"){
		alert("图表对象不存在,不能设置滑动条");
		return;
	}
	// 判断是否字符串
	if((typeof upTargetDivId=='string') && upTargetDivId.constructor == String && upTargetDivId!=''){
		$("#"+upTargetDivId+"_tr").show();
		var upMin=0;
		if((typeof upSliderMin=='number') && upSliderMin.constructor==Number){
			upMin=upSliderMin;
		}
		var upMax=50;
		if((typeof upSliderMax=='number') && upSliderMax.constructor==Number){
			upMax=upSliderMax;
		}
		var upInitVal=0;
		if((typeof upSliderInitVal=='number') && upSliderInitVal.constructor==Number){
			upInitVal=upSliderInitVal;
		}
	    var upSliderObj = new neverModules.modules.slider({
	        targetId: upTargetDivId,// 父标签id
	        sliderCss: "slideInput",// css样式
	        barCss: "imageBar",// css样式
	        min: upMin,// 最小值
	        max: upMax,// 最大值
	        hints: ""// title
	    });
	    upSliderObj.onchange = function(){
	       chart.options.chart.options3d.alpha =upSliderObj.getValue();
	       chart.redraw(false);
	    };
	    upSliderObj.create();
	    upSliderObj.setValue(upInitVal);
	}
	
	// 判断是否字符串
	if((typeof rightTargetDivId=='string') && rightTargetDivId.constructor == String && rightTargetDivId!=''){
		$("#"+rightTargetDivId+"_tr").show();
		var rightMin=0;
		if((typeof rightSliderMin=='number') && rightSliderMin.constructor==Number){
			rightMin=rightSliderMin;
		}
		var rightMax=50;
		if((typeof rightSliderMax=='number') && rightSliderMax.constructor==Number){
			rightMax=rightSliderMax;
		}
		var rightInitVal=0;
		if((typeof rightSliderInitVal=='number') && rightSliderInitVal.constructor==Number){
			rightInitVal=rightSliderInitVal;
		}
	    // 左右
	    var rightSliderObj = new neverModules.modules.slider({
	        targetId: rightTargetDivId,// 父标签id
	        sliderCss: "slideInput",// css样式
	        barCss: "imageBar",// css样式
	        min: rightMin,// 最小值
	        max: rightMax,// 最大值
	        hints: ""// title
	    });
	    rightSliderObj.onchange = function(){
	       chart.options.chart.options3d.beta =rightSliderObj.getValue();
	       chart.redraw(false);
	    };
	    rightSliderObj.create();
	    rightSliderObj.setValue(rightInitVal);
	}
}

/**
 * 3D 饼图
 * @return
 */
function pieChart(divId,titleData,subtitleData,tooltipData,seriesData){
	// 判断是否字符串
	if((typeof divId !='string') || divId.constructor != String || divId==''){
		alert("元素id不能为空!");
		return;
	}
	$('#'+divId).highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: titleData,
        subtitle:subtitleData,
        tooltip: tooltipData,
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series:  seriesData
    });
}

/**
 * 折线图
 * @param divId
 *            显示图标的div标签id 不能为空
 * @param titleData
 *            一级标题  
 * @param subtitleData
 *            二级标题  
 * @param xAxisData
 *            横轴显示列数组   格式:['A','B','C','D','E','F','G','H','I'] 
 * @param yAxisData
 *            y坐标标题 
 * @param tooltipData
 *            悬浮 title 是否同时显示          
 * @param seriesData
 *            是显示信息数据 不能为空
 *            格式:[{name:'实例1',data:[1,2,3,4,5,6,7,8,9]},{name:'实例2',data:[9,8,7,6,5,4,3,2,1]}]
 *            data数据要跟xAxisArray数组长度一样 data 数组还可以
 *            data：[{color:'#4876FF',y:20},{color:'#FFFF00',y:20}]设置每个住体的颜色
 * @return
 */
function brokenLineCharts(divId,titleData,subtitleData,xAxisData,yAxisData,tooltipData,seriesData){
	// 判断是否字符串
	if((typeof divId !='string') || divId.constructor != String || divId==''){
		alert("元素id不能为空!");
		return;
	}
	$('#'+divId).highcharts({
		title: titleData,
        subtitle:subtitleData,
        xAxis: xAxisData,
        yAxis: yAxisData,
        tooltip: tooltipData,
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: seriesData
    });
}


