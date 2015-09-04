<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/pages/common/taglib.jsp" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>广西农产品流通网</title>
    <link rel="stylesheet" type="text/css" href="/static/gac/css/style.css">
    <script src="/static/gac/js/jquery.min.js"></script>
    <script src="/static/gac/js/ind.js"></script>
    <script src="/static/gac/js/avalon.js"></script>
    <script src="/static/gac/js/components.js"></script>
    <script src="/static/gac/js/service.js"></script>
    <script src="/static/gac/js/controller.js"></script>
  <!--[if IE]>
    <style>
    .groom_head li {
        width: 24.9%;
    }
    </style>
    <![endif]-->
   <!-- 供应图片临时设置，请以后统一调整 -->
     <style>
     
    .supImg {
        width: 160px;
        height: 240px;
        padding-top: 10px;
    }
        
    .memberRec {
        width: 150px;
        height: 240px;
    }
      </style>
    
</head>
<script>


</script>
<body id="IndexCtrl" class="ms-controller">
<!-- mean -->
	<div class="width_per_100 height_62 mean_bg">
		<div class="mean width_1000 Float_atuo">
			<div class="ML_classify Float_left border_left_2">
				<div class="aherf"><a href="#">所有分类</a></div>
				<div class="sub_classify">
					<ul class="Float_left ML_classify_ul">
						
						<c:forEach items="${topList}" var="top">
						<li class="Float_left">
						<h1><a href="#" target="_blank">${top.productcatCatName}</a></h1>
						
							<div class="sub_classify_by2">
							</div>
						
						</li>
						</c:forEach>
						
						
					
						</li>
					</ul>

				</div>
				
			</div>
			
			<ul class="mean_ul border_right_2 Float_left">
				<li class="Float_left border_left_2"><a href="/gac/index.html">首页</a></li>
				<li class="Float_left border_left_2"><a href="/gac/nongchanpinhangqing.html" target="_self">农产品行情</a></li>
				<li class="Float_left border_left_2"><a href="/gac/yunjiahangqing.html" target="_self">运价行情</a></li>
				<li class="Float_left border_left_2"><a href="/gac/exhibition.html" target="_self">展示大厅</a></li>
				<li class="Float_left border_left_2"><a href="/brandShop/search.html" target="_blank">品牌专卖店</a></li>
			</ul>
		</div>
	
	</div>
	
	
	<!-- mean -->	


	<!-- mean 
	<div class="width_per_100 height_62 mean_bg">
		<div class="mean width_1000 Float_atuo">
			<div class="ML_classify Float_left border_left_2">
				<div class="aherf"><a href="#">所有分类</a></div>
				<div class="sub_classify">
					<ul id="nav" class="Float_left ML_classify_ul">
						<c:forEach items="${TopList}" var="top">
							<h5 class="width_230">
							<a href="#" target="_blank" >${top.productcatCatName}</a>
							</h5>
							<li class="Float_left">
							&nbsp;
							<c:forEach items="${FirstMap}" var="map1">
							<c:if test="${map1.key==top.productcatId}">
								${map1.value}
							</c:if>
							</c:forEach>
							<ul>
							<c:forEach items="${SecondMap}" var="map2">
							<c:if test="${map2.key==top.productcatId}">
								<a href="#"class="width_200" >${map2.value}</a>
							</c:if>
							</c:forEach>
							</ul>
							</li>
						</c:forEach>
					</ul>

				</div>
				
			</div>
			
			<ul class="mean_ul border_right_2 Float_left">
				<li class="Float_left border_left_2"><a href="/gac/index.html">首页</a></li>
				<li class="Float_left border_left_2"><a href="/gac/nongchanpinhangqing.html" target="_self">农产品行情</a></li>
				<li class="Float_left border_left_2"><a href="/gac/yunjiahangqing.html" target="_self">运价行情</a></li>
				<li class="Float_left border_left_2"><a href="/gac/exhibition.html" target="_self">展示大厅</a></li>
				<li class="Float_left border_left_2"><a href="/brandShop/search.html" target="_blank">品牌专卖店</a></li>
			</ul>
		</div>
	
	</div>
	
	-->
	<!-- mean -->	 
	<div class="clear"></div>
	<!-- banner -->  
	<div class="banner width_per_100">
		<div class="banNav">
		 <span class="banHov"></span>
		 <span></span>
		 <span></span>
		</div>
		<ul class="banUl width_per_100">
		<c:forEach items="${Advertisement}" var="list">
		
		 <li><img src="${list.url}" alt="${list.remark}" /></li>
		</c:forEach>
		 <!--<li><img src="/static/gac/images/banner01.jpg" /></li>
		 <li><img src="/static/gac/images/banner02.jpg" /></li>
		--></ul>
		
	</div>	

	<!-- banner -->  
	<div class="clear"></div>
	<!-- main --> 
	<div class="main width_1000 Float_atuo">
		<!-- 推荐 --> 
		<div class="container Float_left border_left_3 border_right_3 border_btm_3">
		 <div class="groom_head width_per_100">
			<ul>
				<li class="Float_left ML_cur groom_hov">推荐产品</li>
				<li class="Float_left ML_cur">推荐会员</li>
				<li class="Float_left ML_cur">应季推荐</li>
				<li class="Float_left ML_cur">特色推荐</li>
			</ul>
		 </div>
		 <div class="groom_body container">
			<ul class="container">
				<c:forEach items="${recommendProducts}" var="item">
					<li class="Float_left">
						<a href="store.html" target="_blank">
						<p><img src="${fn:contains(item.productPicUrl,',')?fn:substringBefore(item.productPicUrl, ',') : item.productPicUrl}" /></p>
						<span>${item.productName}</span>
						<span>价格:<b>${item.productPrice}</b></span>
						</a>
					</li>
				</c:forEach>

			</ul>
			
			<ul class="container">
				<c:forEach items="${recommendMembers}" var="item">
					<li class="Float_left">
						<a href="#" target="_blank">
						<p><img src="${item.memberPhotoUrl}" /></p>
						<span>${item.memberName}</span>
						<span>${item.memberlevelName}</span>
						</a>
					</li>
				</c:forEach>
			</ul>
			
			<ul class="container">
				<c:forEach items="${seasonalProducts}" var="item">
					<li class="Float_left">
						<a href="store.html" target="_blank">
						<p><img src="${fn:contains(item.productPicUrl,',')?fn:substringBefore(item.productPicUrl, ',') : item.productPicUrl}" /></p>
						<span>${item.productName}</span>
						<span>价格:<b>${item.productPrice}</b></span>
						</a>
					</li>
				</c:forEach>
			</ul>
			
			<ul class="container">
				<c:forEach items="${featureProducts}" var="item">
					<li class="Float_left">
						<a href="store.html" target="_blank">
						<p><img src="${fn:contains(item.productPicUrl,',')?fn:substringBefore(item.productPicUrl, ',') : item.productPicUrl}" /></p>
						<span>${item.productName}</span>
						<span>价格:<b>${item.productPrice}</b></span>
						</a>
					</li>
				</c:forEach>
			</ul>
		 </div>
		 
		</div>
		<!-- 推荐 --> 
		<div class="clear"></div>
		
		
		<div class="guild container Float_left border_left_3 border_right_3 border_btm_3">
		 <!-- 购销 --> 
			<div class="sale width_215 Float_left padd_left_20 height_105 guild_bg">
				<h1>购销物流</h1>
				<c:forEach items="${agrArticleTypes1}" var="articleType">
					<a href="/articletype/getarticletype.html?id=${articleType.id}" target="_blank">${articleType.articletypename}</a>
				</c:forEach>
			</div>
		 <!-- 购销 -->
		 
		 <!-- 推荐互动 --> 
			<div class="interact Float_left padd_left_20 height_105 guild_bg">
				<h1>推荐互动</h1>
				<c:forEach items="${agrArticleTypes2}" var="articleType">
					<a href="/articletype/getarticletype.html?id=${articleType.id}" target="_blank">${articleType.articletypename}</a>
				</c:forEach>
			</div>
		 <!-- 推荐互动 -->
		 
		 <!-- 新闻参考 --> 
			<div class="consult Float_left padd_left_20 height_105 guild_bg">
				<h1>新闻参考</h1>
				<c:forEach items="${agrArticleTypes3}" var="articleType">
					<a href="/articletype/getarticletype.html?id=${articleType.id}" target="_blank">${articleType.articletypename}</a>
				</c:forEach>
			</div>
		 <!-- 新闻参考 -->
		 
		 <!-- 分析资讯 --> 
			<div class="sale width_210 Float_left padd_left_20 height_105">
				<h1>分析资讯</h1>
				<c:forEach items="${agrArticleTypes4}" var="articleType">
					<a href="/articletype/getarticletype.html?id=${articleType.id}" target="_blank">${articleType.articletypename}</a>
				</c:forEach>
			</div>
		 <!-- 分析资讯 -->
		 
		</div>
		<div class="clear"></div>
		
		<div class="container Float_left border_left_3 border_right_3 border_btm_3">
		<!-- 物流信息 -->
		 <div class="width_500 Float_left border_right_3">
			<div class="Logistics Float_left">
				<div class="title width_460 Float_left border_btm_3 height_39">
					<h1 class="Float_left">物流信息</h1>
					<ul class="title_ul Float_right" id="Logistics_tit_ul">
						<li class="ML_cur Float_left title_ul_li">物流供应</li>
						<li class="ML_cur Float_left">物流需求</li>
					</ul>
				</div>
				<div class="Logistics_body Float_left">
					<ul>
						<c:forEach items="${apipLogisticsSups.object}" var="item">
							<li class="font_size_16">
								<a href="/customDetail/logisticsSupplyDetail.do?logisticsId=${item.id}" target="_blank" title="" class="txt_splice">
									<span class="Float_left txt_splice" title="从${item.shipplacename}到${item.destplacename}...11">
										从${item.shipplacename}到${item.destplacename}
									</span>
									<span class="Float_right">
									${fn:substring(item.createdate,0,10)}     
									</span>
								</a>
							</li>
						</c:forEach>
					</ul>
					<ul>
						<c:forEach items="${apipLogisticsReqs.object}" var="item">
							<li class="font_size_16">
								<a href="/customDetail/LogisticsPurchaseDetail.do?logisticsId=${item.reqid}" target="_blank" title="" class="txt_splice">
									<span class="width_290 Float_left txt_splice" title="从${item.shipplacename}到${item.destplacename}">
										从${item.shipplacename}到${item.destplacename}
									</span>
									<span class="Float_right" >
										${fn:substring(item.createdate,0,10)}     
									</span>
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		 </div>
		<!-- 物流信息 -->
		
		<!-- 购销信息 -->
		<div class="width_498 Float_left">
			<div class="buying Float_left">
				<div class="title width_460 Float_left border_btm_3 height_39" id="buying">
					<h1 class="Float_left">购销信息</h1>
					<ul class="title_ul Float_right">
						<li class="ML_cur Float_left title_ul_li">供应信息</li>
						<li class="ML_cur Float_left">需求信息</li>
					</ul>
				</div>
				<div class="buying_body Float_left">
					<div class="buying_box width_per_100">
						<!-- <div class="buying_img Float_left">
						
						<c:forEach items="${productsups.object}" begin="0" end="0" var="item0">
						<img class="supImg" src="${fn:contains(item0.productPicUrl,',')?fn:substringBefore(item0.productPicUrl, ',') : item0.productPicUrl}" />
						</c:forEach>
						
						</div> -->
						<ul  class="Float_right">
							<c:forEach items="${productsups.object}" var="item">
								<li>
									<a href="/customDetail/supplyDetails.do?supplyId=${item.productsupId}" target="_blank" title="" class="txt_splice">
										${item.memberName} 于${fn:substring(item.productsupCreateTime,0,10)}成功发布${item.productsupProductName}信息
									</a>
								</li>
							</c:forEach>
							<!--<li ms-repeat-obj="supplys"><a href="#" target="_blank" class="txt_splice" ms-title="{{obj.productsupContactPerson}} 小宁于{{obj.productsupCreateTime.substring(2,10)}}成功发布{{obj.catalog.productcatCatName}}信息">{{obj.productsupContactPerson}} 小宁于{{obj.productsupCreateTime.substring(2,10)}}成功发布{{obj.catalog.productcatCatName}}信息</a></li>
							<li ms-repeat-obj="supplys"><a href="#" target="_blank" class="width_240 txt_splice" ms-title=" 小宁于 成功发布信息"> 小宁于 15-08-02  成功发布信息</a></li>-->
						</ul>
					</div>
				
					<div class="buying_box">
						<!--  <div class="buying_img Float_left"><img src="/static/gac/images/p_02.jpg" /></div>-->
						<ul class="Float_right">
							<c:forEach items="${productreqs.object}" var="item">
								<li>
									<a href="/customDetail/purchaseDetails.do?purchaseId=${item.productreqId}" target="_blank" title="" class="txt_splice">
											${item.memberName}  于${item.productreqCreateTime} 成功发布${item.productreqProductName}信息
									</a>
								</li>
							</c:forEach>
							<!--<li ms-repeat-obj="requests" ><a href="#" target="_blank" class="txt_splice" >{{obj.productreqContactPerson}} 小宁于{{obj.productreqCreateTime.substring(2,10)}}成功发布{{obj.catalog.productcatCatName}}信息</a></li>
							<li ms-repeat-obj="requests"><a href="#" target="_blank" title=""> 吕先生    于2015-08-02成功发布信息</a></li>-->
						</ul>
					</div>
				</div>
			</div>
		 </div>
		<!-- 购销信息 -->
		</div>
		
		<!-- 购销交易 -->
		<div class="container Float_left border_left_3 border_right_3 border_btm_3 padd_top_btm_10">
		 <div class="width_960 title trade">
			<h1 class="Float_left">购销交易</h1>
			<div class="clear"></div>
			<div class="table width_668 border_left_3 border_top_3 Float_left margin_top_10 margin_btm_10">
				<div class="tr Float_left text_center border_btm_3">
					<div class="th width_120">产品名称</div>
					<div class="th width_140">数量</div>
					<div class="th width_159">产品产地</div>
					<div class="th width_120">交易时间</div>
					<div class="th width_120 border_right_3">交易状态</div>
				</div>
				<c:forEach items="${bsList}" var="buySell">
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="${buySell.orderdetailproductname }">${buySell.orderdetailproductname }</div>
					<div class="td width_140 txt_splice" title="${buySell.orderdetailproductquatity}">${buySell.orderdetailproductquatity}</div>
					<div class="td width_159 txt_splice" title="${buySell.orderdetailproductarea}"> ${buySell.orderdetailproductarea}</div>
					<div class="td width_120 txt_splice" title="${buySell.agrOrder.ordertime}">${buySell.agrOrder.ordertime}</div>
					<div class="td width_121 txt_splice" title="${buySell.agrOrder.statusName}">${buySell.agrOrder.statusName}</div>
				</div>
					
					
					</c:forEach>
				<!-- div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="圣女果金币圣女果金币圣女果金币">圣女果金币圣女果金币圣女果金币</div>
					<div class="td width_140 txt_splice" title="30,000千克">30,000千克</div>
					<div class="td width_159 txt_splice" title="广西百色市田阳县">广西百色市田阳县</div>
					<div class="td width_120 txt_splice" title="2014-11-01">2014-11-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="糖心桔子">糖心桔子</div>
					<div class="td width_140 txt_splice" titl="37,000千克">37,000千克</div>
					<div class="td width_159 txt_splice" title="山西百色市田阳县">山西百色市田阳县</div>
					<div class="td width_120 txt_splice" title="2014-11-06">2014-11-06</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="圣女果金币">圣女果金币</div>
					<div class="td width_140 txt_splice" title="30,000千克">30,000千克</div>
					<div class="td width_159 txt_splice" title="河北省石家庄">河北省石家庄</div>
					<div class="td width_120 txt_splice" title="2014-12-01">2014-12-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="糖心葡萄">糖心葡萄</div>
					<div class="td width_140 txt_splice" title="38,000千克">38,000千克</div>
					<div class="td width_159 txt_splice" title="广西百色市田阳县">广西百色市田阳县</div>
					<div class="td width_120 txt_splice" title="2013-11-01">2013-11-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>

				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="圣女果金币">圣女果金币</div>
					<div class="td width_140 txt_splice" title="30,000千克">30,000千克</div>
					<div class="td width_159 txt_splice" title="湖北省南阳市">湖北省南阳市</div>
					<div class="td width_120 txt_splice" title="2014-12-01">2014-12-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>-->
				
			</div> 
			
			<div class="count Float_right border_3 margin_top_10">
				<div class="count_title border_btm_3 text_center">
				<i><img src="/static/gac/images/icon1.jpg" /></i> <span>购销交易统计</span>
				</div>
				<ul class="Float_left">
					<c:forEach items="${PurchaseSaleStatisticsMap}" var="map">
					<li><span class="Float_left">${map.key}</span><span class="Float_right"><font color="#f73c3c">${map.value}笔</font></span></li>
					</c:forEach>
				</ul>
			</div>
			
			<div>
			
			</div>
			
		 </div>
		</div>
		
		<!-- 购销交易 -->
		
		<!-- 购销交易 -->
		<div class="container Float_left border_left_3 border_right_3 border_btm_3 padd_top_btm_10">
		 <div class="width_960 title trade">
		 <h1 class="Float_left">物流交易</h1>
			<div class="clear"></div>
			
			<div class="table width_668 border_left_3 border_top_3 Float_left margin_top_10 margin_btm_10">
				<div class="tr Float_left text_center border_btm_3">
					<div class="th width_120">出发地</div>
					<div class="th width_140">目的地</div>
					<div class="th width_159">货物</div>
					<div class="th width_120">交易时间</div>
					<div class="th width_120 border_right_3">交易状态</div>
				</div>
				<c:forEach items="${logisticsDeals}" var="logistics">
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice">${logistics.agrOrder.ordersendaddress }</div>
					<div class="td width_140 txt_splice">${logistics.agrOrder.orderreceiveaddress }</div>
					<div class="td width_159 txt_splice">${logistics.orderdetailproductname }</div>
					<div class="td width_120 txt_splice">${logistics.agrOrder.ordertime }</div>
					<div class="td width_121 txt_splice">${logistics.agrOrder.statusName }</div>
				</div>
				</c:forEach>
				<!--  div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="辽宁省">辽宁省</div>
					<div class="td width_140 txt_splice" title="广西百色市田阳县">广西百色市田阳县</div>
					<div class="td width_159 txt_splice" title="圣女果万福">圣女果万福</div>
					<div class="td width_120 txt_splice" title="2014-11-01">2014-11-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="河南省">河南省</div>
					<div class="td width_140 txt_splice" title="河南省新乡市">河南省新乡市</div>
					<div class="td width_159 txt_splice" title="香蕉">香蕉</div>
					<div class="td width_120 txt_splice" title="2013-11-01">2013-11-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="湖南省">湖南省</div>
					<div class="td width_140 txt_splice" title="湖南省长沙市">湖南省长沙市</div>
					<div class="td width_159 txt_splice" title="糖心葡萄">糖心葡萄</div>
					<div class="td width_120 txt_splice" title="2012-11-01">2012-11-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="吉林省">吉林省</div>
					<div class="td width_140 txt_splice" title="广西百色市田阳县">广西百色市田阳县</div>
					<div class="td width_159 txt_splice" title="圣女果万福">圣女果万福</div>
					<div class="td width_120 txt_splice" title="2012-12-01">2012-12-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_120 txt_splice" title="吉林省">吉林省</div>
					<div class="td width_140 txt_splice" title="吉林省长春市">吉林省长春市</div>
					<div class="td width_159 txt_splice" title="富士苹果">富士苹果</div>
					<div class="td width_120 txt_splice" title="2013-11-01">2013-11-01</div>
					<div class="td width_121 txt_splice" title="已成交">已成交</div>
				</div>
				-->
			</div>
			
			<div class="count Float_right border_3 margin_top_10">
				<div class="count_title border_btm_3 text_center">
				<i><img src="/static/gac/images/icon2.jpg" /></i> <span>物流交易统计</span>
				</div>
				<ul class="Float_left">
					<c:forEach items="${LogisticsTradeStatistics}" var="map">
					<li><span class="Float_left">${map.key}</span><span class="Float_right"><font color="#f73c3c">${map.value}笔</font></span></li>
					</c:forEach>
				</ul>
			</div>
			
			<div>
			
			</div>
			
		 </div>
		</div>
		
		<!-- 购销交易 -->
		
		<div class="container Float_left border_left_3 border_right_3 border_btm_3">
		<!-- 展示大厅 -->
			<div class="width_668 Float_left padd_left_20 border_right_3">
				<div class="title width_600 Float_left margin_top_10">
				<h1 class="Float_left">展示大厅</h1>
				<ul class="shows_ul Float_right">
					<li class="Float_left border_3 ML_cur"><img src="/static/gac/images/nav_icon_3.jpg" /></li>
					<li class="Float_left border_3 ML_cur"><img src="/static/gac/images/nav_icon_4.jpg" /></li>
				</ul>
				</div>
				
				<div class="shows_box Float_left">
				<div class="shows_body">
					<ul class="fruit text_center">
						<c:forEach items="${productsupList}" var="productsup" varStatus="status" begin="0" end="6">
							<c:if test="${status.index % 6 == 0 || status.index % 6 ==1 || status.index % 6 ==2}">
								<li>
								<a href="/customDetail/supplyDetails.do?supplyId=${productsup.productsupId}" target="_blank" title="">
								<img src="${fn:contains(productsup.productPicUrl,',')?fn:substringBefore(productsup.productPicUrl, ',') : productsup.productPicUrl}" style="width: 200px;height: 140px" />
								<span class="txt_splice"> ${productsup.productsupDesc}</span>
								</a></li>
							</c:if>
						</c:forEach>
						<!--<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p4.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>-->
					</ul>
					<ul class="Vegetables text_center">
						<c:forEach items="${productsupList}" var="productsup" varStatus="status"  begin="0" end="6">
							<c:if test="${status.index % 6 == 3 || status.index % 6 == 4 || status.index % 6 == 5}">
								<li><a href="/customDetail/supplyDetails.do?supplyId=${productsup.productsupId}" target="_blank" title="">
								<img src="${fn:contains(productsup.productPicUrl,',')?fn:substringBefore(productsup.productPicUrl, ',') : productsup.productPicUrl}" style="width: 200px;height: 120px" />
									<span class="txt_splice"> ${productsup.productsupDesc}</span>
								</a></li>
							</c:if>
						</c:forEach>
						<!--<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p6.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p7.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>-->
					</ul>
					
					<!--<ul class="fruit">
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p3.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p4.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p3.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p4.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p3.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p4.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p3.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p4.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p3.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="#" target="_blank" title=""><img src="/static/gac/images/p5.jpg" /></a></li>
					</ul>
					<ul class="Vegetables">
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p6.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p7.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p6.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p7.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p6.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p7.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p7.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>
						<li><a href="exhibition.html" target="_blank" title=""><img src="/static/gac/images/p6.jpg" /></a></li>
					<li><a href="#" target="_blank" title=""><img src="/static/gac/images/p7.jpg" /></a></li>
						<li><a href="#" target="_blank" title=""><img src="/static/gac/images/p8.jpg" /></a></li>

					</ul>-->
				</div>
				</div>
				
			</div>
		<!-- 展示大厅 -->
		
		<!-- 用户评论 -->
		 <div class="comment Float_left">
				<div class="title Float_left width_per_100 border_btm_3 padd_top_btm_10">
				<h1 class=" Float_left">用户评论</h1>
				</div>
				
				<div class="renav Float_left width_per_100">
				<ul class="comment_ul">
					<li>
						<div class="li_left_img"><img src="/static/gac/images/top_1.jpg" /></div>
						<span>罗玲</span>
						<p>宁夏领鲜物流公司</p>
						<p>网络好，速度快，不卡</p>
					</li>
					<li>
						<div class="li_left_img"><img src="/static/gac/images/top_1.jpg" /></div>
						<span>王敏</span>
						<p>银川鸿运通物流货运公司</p>
						<p>服务态度好，点个赞</p>
					</li>
					<li>
						<div class="li_left_img"><img src="/static/gac/images/top_1.jpg" /></div>
						<span>夏冰</span>
						<p>佳捷专业物流有限公司</p>
						<p>网络好，速度快，不卡。总体很好</p>
					</li>
					<li>
						<div class="li_left_img"><img src="/static/gac/images/top_1.jpg" /></div>
						<span>李烁宁</span>
						<p>呼和浩特华泰物流有限公司</p>
						<p>挺好的</p>
					</li>
					<li>
						<div class="li_left_img"><img src="/static/gac/images/top_1.jpg" /></div>
						<span>王栋栋</span>
						<p>呼和浩特华泰物流有限公司</p>
						<p>网络好，速度快，不卡</p>
					</li>
				</ul>
				</div>
				
		 </div>
		<!-- 用户评论 -->
		</div>
		
		<div class="container Float_left border_left_3 border_right_3 border_btm_3">
		
		
		<div class="newleft width_668 Float_left border_right_3">
		<!-- 资讯中心 -->
			<div class="width_668 Float_left border_btm_3 padd_left_20">
				<div class="title Float_left width_645 margin_top_10" id="News">
					<h1 class="Float_left">资讯中心</h1>
					<ul class="Float_right">
					<c:forEach var="agrArticleType" items="${agrArticleTypes}" varStatus="status">
						<li class="ML_cur <c:if test="${status.index==0}">News_li_hov</c:if> ">${agrArticleType.articletypename}</li>
					</c:forEach>
					</ul>
				</div>
				<div class="News_body Float_left">
				<c:forEach var="agrArticleType" items="${agrArticleTypes}" varStatus="status">
				<div class="news_box width_per_100 Float_left">
					<div class="News_body_img">
						<a href="#"><img src="${imageUrls[status.index]}" width="340px" height="240px"/></a>
						<a href="#">${imageTitles[status.index]}</a>
					</div>
					
					<div class="NewsList Float_right">
					<ul class="Float_left">
						<c:forEach var="article" items="${agrArticleType.agrArticle}" begin="0" end="9">
								<li style="font-size: 10pt;height: 25px"><a href="/article/showagrarticle.html?id=${article.id}" target="_blank" class="width_280 txt_splice" title="${article.articletitle}">${article.articletitle}</a></li>
						</c:forEach>
					</ul>
					</div>
				</div>
				</c:forEach>
				</div>
			</div>
		<!-- 资讯中心 -->
			
		<!-- 推荐优质会员 -->
		<div class="width_668 Float_left padd_left_20 border_right_3">
			<div class="title Float_left width_per_100 margin_top_10" id="quality">
				<h1 class="Float_left">优质会员推荐</h1>
				<ul class="Float_right quality_VIP_ul">
					<li class="ML_cur News_li_hov">优质供应商</li>
					<li class="ML_cur">优质采购商</li>
					<!--  li class="ML_cur">优质物流公司</li>-->
				</ul>
			</div>
			<div class="quality_body Float_left border_top_3">
				<ul>
					<c:forEach items="${fineProductSupList}" var="fineMemberVo" varStatus="status">
						<li>
							<div class="quality_img"><img class="memberRec" src="${fineMemberVo.photoUrl}" /></div>
							<div class="quality_content">
								<h3 title="${fineMemberVo.companyName}">${fineMemberVo.companyName}</h3>
								<p>${fineMemberVo.companyIntroduction}</p>
							</div>
						</li>
					</c:forEach>
				</ul>
				<ul>
					<c:forEach items="${fineProductReqList}" var="fineMemberVo" varStatus="status">
						<li>
							<div class="quality_img"><img class="memberRec" src="${fineMemberVo.photoUrl}" /></div>
							<div class="quality_content">
								<h3 title="${fineMemberVo.companyName}">${fineMemberVo.companyName}</h3>
								<p>${fineMemberVo.companyIntroduction}</p>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- 推荐优质会员 -->  
		
		</div>
		
		<!-- 排行榜 --> 
		<div class="comment Float_left" id="charts_box">
			<div class="title Float_left width_per_100 border_btm_3 margin_top_10">
				<h1 class="Float_left">排行榜</h1>
				<ul id="charts">
				<li class="ML_cur charts_li">供应商</li>
				<li class="ML_cur">销售商</li>
				<li class="ML_cur">物流商</li>
				</ul>
			</div>
			
			<div class="width_per_100 charts_box Float_left">
			
			<div class="width_per_100 Float_left charts_box_body" data-ml="供应商">
			<div class="tr_1 width_per_100 Float_left height_50 border_btm_4">
				<div class="th_1 width_95">供应商名称</div>
				<div class="th_1 width_95">所在地</div>
				<div class="th_1 width_80">信誉度</div>
			</div>
			
			
			<c:forEach items="${supplierList}" var="supplierMap">
			<%int io=0; 
			%>
				<div class="tr_1 width_per_100 Float_left border_btm_3">
					<div class="td_1 width_95 line_height50 txt_splice" title="${supplierMap.name}">${supplierMap.name}</div>
					<div class="td_1 width_95 line_height50 txt_splice" title="${supplierMap.location}">${supplierMap.location}</div>
					<div class="td_1 width_80"><i><img src="/static/gac/images/vip_zs_icon.jpg" /></i><br /><span>${supplierMap.cret}</span></div>
				</div>
			<% io++; %>
			</c:forEach>
		 </div>
		 
		 <div class="width_per_100 Float_left charts_box_body" data-ml="销售商" style="display: none;">
			<div class="tr_1 width_per_100 Float_left height_80 border_btm_4">
				<div class="th_1 width_95">销售商名称</div>
				<div class="th_1 width_95">所在地</div>
				<div class="th_1 width_80">信誉度</div>
			</div>
			
			<c:forEach items="${sellerList}" var="map1">
				<div class="tr_1 width_per_100 Float_left border_btm_3">
					<div class="td_1 width_95 line_height50 txt_splice" title="${map1.name}">${map1.name}</div>
					<div class="td_1 width_95 line_height50 txt_splice" title="${map1.location}">${map1.location}</div>
					<div class="td_1 width_80"><i><img src="/static/gac/images/vip_zs_icon.jpg" /></i><br /><span>${map1.cret}</span></div>
				</div>
				
			</c:forEach>
			
		 </div>
		 <div class="width_per_100 Float_left charts_box_body" data-ml="物流商" style="display: none;">
			<div class="tr_1 width_per_100 Float_left height_80 border_btm_4">
				<div class="th_1 width_95">物流商名称</div>
				<div class="th_1 width_95">所在地</div>
				<div class="th_1 width_80">信誉度</div>
			</div>
			
			<c:forEach items="${logisticsList}" var="map2">
				<div class="tr_1 width_per_100 Float_left border_btm_3">
					<div class="td_1 width_95 line_height50 txt_splice" title="${map2.name}">${map2.name}</div>
					<div class="td_1 width_95 line_height50 txt_splice" title="${map2.location}">${map2.location}</div>
					<div class="td_1 width_80"><i><img src="/static/gac/images/vip_zs_icon.jpg" /></i><br /><span>${map2.cret}</span></div>
				</div>
			</c:forEach>
			
		 </div>
		 </div>
		</div>
		<!-- 排行榜 --> 
		
		</div>
		
		<!-- 物流配套服务 -->
		<div class="width_1000 Float_left Support_bg padd_btm_10 border_btm_3">
			<div class="width_960 title trade margin_top_10">
				<h1>物流配套服务</h1>
				<div class="table width_per_100 border_left_3 border_top_3 Float_left margin_top_10 margin_btm_10">
				<div class="tr Float_left text_center border_btm_3">
					<div class="th width_125">标题</div>
					<div class="th width_250">公司名称</div>
					<div class="th width_159">联系人</div>
					<div class="th width_220">联系人电话</div>
					<div class="th width_200 border_right_3">发布时间</div>
				</div>
				<c:forEach items="${logisticsSupList}" var="lSup">
				<div class="tr Float_left text_center">
					<div class="td width_125 txt_splice" title="${lSup.infotitle }">${lSup.infotitle}</div>
					<div class="td width_250 txt_splice" title="${lSup.companyname}">${lSup.companyname}</div>
					<div class="td width_159 txt_splice" title="${lSup.contactperson}">${lSup.contactperson}</div>
					<div class="td width_220 txt_splice" title="${lSup.contactphone}">${lSup.contactphone}</div>
					<div class="td width_201 txt_splice" title="${lSup.createdate}">${fn:substring(lSup.createdate,0,10)}     </div>
				</div>
				</c:forEach>
				<!--div class="tr Float_left text_center">
					<div class="td width_125 txt_splice">香蕉</div>
					<div class="td width_250 txt_splice">佳捷专业物流有限公司</div>
					<div class="td width_159 txt_splice">谷立军</div>
					<div class="td width_220 txt_splice">15849120681</div>
					<div class="td width_201 txt_splice">2015-08-03</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_125 txt_splice">苹果</div>
					<div class="td width_250 txt_splice">呼和浩特华泰物流有限公司</div>
					<div class="td width_159 txt_splice">王一鸣</div>
					<div class="td width_220 txt_splice">15849120681</div>
					<div class="td width_201 txt_splice">2015-07-31</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_125 txt_splice">橙子</div>
					<div class="td width_250 txt_splice">九州平安物流公司有限公司</div>
					<div class="td width_159 txt_splice">杨军辉</div>
					<div class="td width_220 txt_splice">15849120681</div>
					<div class="td width_201 txt_splice">2015-07-30</div>
				</div>
				
				<div class="tr Float_left text_center">
					<div class="td width_125 txt_splice">苹果</div>
					<div class="td width_250 txt_splice">呼和浩特华泰物流有限公司</div>
					<div class="td width_159 txt_splice">刘童飞</div>
					<div class="td width_220 txt_splice">15849120681</div>
					<div class="td width_201 txt_splice">2015-07-30</div>
				</div>
				 -->
			</div>
			</div>
		</div>
		<!-- 物流配套服务 -->
		
		<!-- 品牌专营 -->
		<div class="width_per_100 Float_left padd_btm_10">
			<div class="width_960 title trade margin_top_10">
				<h1>品牌专营</h1>
				<ul class="Brand_ul border_left_3 border_top_3 margin_top_10">
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				<li class="border_btm_3 border_right_3"><a href="#"><img src="/static/gac/images/link_logo.jpg" /></a></li>
				</ul>
			</div>
		</div>
		<!-- 品牌专营 -->
		
		<div class="clear"></div>
	</div>
	<!-- main -->
	
    <jsp:include page="partial/footer.jsp" flush="true"></jsp:include>
</body>
</html>
