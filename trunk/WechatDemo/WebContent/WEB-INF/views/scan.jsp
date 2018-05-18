<%@ page language="java" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" " http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/> 
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
		<meta name="description" content=""/>
		<meta name="author" content="fumed E-commerce Ltd."/>
		<meta name="ROBOTS" content="NOINDEX, NOFOLLOW"/>
		<title>微信扫一扫</title>
		<script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript"></script>
		<script src="https://res.fuyitianjian.net/plugins/jQuery/jquery-1.11.3.min.js" type="text/javascript"></script>
		<!-- 
			获取微信js api权限，比如获取扫码权限，参数写scanQRCode即可
			如果想获取多个js api权限需要用英文半角逗号隔开
			例如：https://wechat.fuyitianjian.net/wechat/config.js?api=scanQRCode,onMenuShareTimeline
		-->
		<script src="https://wechat.fuyitianjian.net/wechat/config.js?api=scanQRCode" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
				$(".btn-wechat-scan").on("click", function() {
					wx.scanQRCode({
						needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
						scanType: ["qrCode"], // 可以指定扫二维码还是一维码，默认二者都有
						success: function (res) {
							var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
							alert(result);
						}
					});
				});
			})
		</script>
	</head>
	<body>
		<input class="btn-wechat-scan" type="button" value="扫一扫"/>
	</body>
</html>