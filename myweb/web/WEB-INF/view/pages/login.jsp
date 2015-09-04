<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/pages/common/taglib.jsp" %>

<!doctype html>

<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="/static/gac/css/style.css">
<script src="/static/gac/js/jquery.min.js"></script>
<script src="/static/gac/js/avalon.js"></script>
<script src="/static/gac/js/ind.js"></script>
<script src="/static/gac/js/components.js"></script>
<script src="/static/gac/js/service.js"></script>
<script src="/static/gac/js/controller.js"></script>
</head>

<body id="LoginCtrl" class="ms-controller">
		<jsp:include page="partial/nav.jsp" flush="true"></jsp:include>
		<jsp:include page="partial/header.jsp" flush="true"></jsp:include>
		<jsp:include page="partial/subheader.jsp" flush="true"></jsp:include>
	<!-- linding body -->
	<div class=" width_1000 Float_atuo height_555 border_3 margin_top_30 margin_btm_30">
		<div class="lind_img_left Float_left">
			<img src="/static/gac/images/lindLFimg.jpg" />
		</div>
		<div class="lind_body_right Float_right">
			<h1 class="Float_left">
				登录
			</h1>
			<div class="linding_box margin_btm_30">
				<i class="Float_left border_left_3 border_top_3 border_btm_3">
					<img src="/static/gac/images/lind_name.jpg" />
				</i>
				<input type="text" ms-duplex="username" class="lindName Float_left lind_input_box border_3"
				id="j-username" name="" placeholder="邮箱/用户名/已验证手机" />
			</div>
			<div class="linding_box margin_btm_30">
				<i class="Float_left border_left_3 border_top_3 border_btm_3">
					<img src="/static/gac/images/lind_pwd.jpg" />
				</i>
				<input type="password" ms-duplex="password" class="lindPwd Float_left lind_input_box border_3"
				id="j-password" name="" placeholder="请输入密码" />
			</div>
			<div class="linding_box margin_btm_30 height_15 line_height15">
				<div class="Float_left">
					<em>
						<input id="j-login-auto" type="checkbox" name="" ms-duplex-checked="autoLogin"
						/>
					</em>
					自动登录
				</div>
				<div class="Float_right">
					<a href="#" target="_blank">
						忘记密码?
					</a>
				</div>
			</div>
			<div class="linding_box margin_btm_30">
				<div style="border:1px solid #cb2a2d; width:299px;">
					<button ms-click="login()" class="lindBtn">
						登&nbsp;&nbsp;&nbsp;&nbsp;录
					</button>
				</div>
			</div>
			<div class="linding_box">
				<span>
					还不是本站会员？
					<a href="reg.html" target="_blank">
						立即注册
					</a>
				</span>
			</div>
		</div>
	</div>
		<jsp:include page="partial/footer.jsp" flush="true"></jsp:include>
</body>
</html>
