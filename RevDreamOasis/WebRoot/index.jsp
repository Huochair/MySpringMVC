<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html >
<head>
<meta charset="utf-8">
<title>启梦绿舟，风雨同舟</title>
<link href="index.css" rel="stylesheet" type="text/css">
<script src="js01.js"></script>
</head>

<body>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<header>
	<div class="hea1">
		<span id="yonghu"><a href="login.jsp">登陆</a> ;<a href="register.jsp">免费注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好，欢迎来到启梦绿洲！</span>
		<ul class="one">
			<li onMouseOver="display(this)" onMouseOut="hide(this)" class="lix" id="li1">
				<span>个人中心</span>
				<ul id="ul1" class="ulx">
					<a href="">
						<li>已完成订单</li>
					</a>
					<a href="">
						<li>未完成订单</li>
					</a>
					<a href="">
						<li>我的保险</li>
					</a>
					<a href="">
						<li>账户安全</li>
					</a>
					<a href="">
						<li>个人信息</li>
					</a>
				</ul>
			</li>
			<li onMouseOver="display(this)" onMouseOut="hide(this)" id="li2" class="lix">
				<span>在线服务</span>
				<ul id="ul2" class="ulx">
					<a href=""><li>直播课程</li></a>
					<a href=""><li>快速问答</li></a>
				</ul>
			</li>
			<li onMouseOver="display(this)" onMouseOut="hide(this)" class="lix" id="li3">
				收藏夹
				<ul id="ul3" class="ulx">
					<a href=""><li>我的收藏</li></a>
					<a href=""><li>爱心足迹</li></a>
				</ul>
			</li>
			<li onMouseOver="display(this)" onMouseOut="hide(this)" class="lix">
				<a href="">网站导航</a>
			</li>
			    <a href="">
				   <li id="lxwm">联系我们</li>
				</a>
		</ul>
	</div>
</header>
<nav>
			<!--279 229-->
	<ul>
		<a>
			<li id="qmlogo"><img src="images1/qmlogo.png"></li>
		</a>
		<a href="">
			<li class="na1">首页</li>
		</a>
		<a href="">
			<li class="na1">资料查询</li>
		</a>
		<a href="">
			<li class="na1">快速问答</li>
		</a>
		<a href="">
			<li class="na1">树洞老人</li>
		</a>
		<a href="">
			<li class="na1">公益视频</li>
		</a>
		<a href="">
			<li class="na1">红花榜</li>
		</a>
	</ul>
</nav>
<div class="flash">
	
	<img src="images1/1.png">
	<img src="images1/2.png">
	<img src="images1/3.png">

	 <ul class="button">
	      <li class="hover"></li>
		  <li></li>
		  <li></li>
	 </ul>
</div>
<div class="jianjie">
	<p>启梦绿舟简介</p>
</div>
<div class="hi">
    <dl>
    	<dt><img src="images1/hi.png"></dt>
    	<dd>
    	    <div class="xh">你知道启梦绿舟吗？</div>
    	    <div class="content">启梦绿舟，是一个公益助学平台，是集线上直播课堂、在线课程视频、线下课堂、和爱心捐助于一体的富有综合性的公益平台。我们的服务对象是小学，初中，高中的学生，社会青年人和老年人。线下公益教学的服务对象就完全是针对于各年龄段，各年级的孩子们。从学习问题到生活问题，从知识讲解到心灵疏导，各方面包揽。对于想要学习的成年人来说这也可以是一个选择的平台。趣味课堂和户外拓展活动也是我们平台的特色之一，在“教”这一方面做到全面，而不仅仅局限于课堂知识。另外，作为一个公益支教平台，我们也希望通过“启梦绿舟”收集到大家的爱，然后给予那些更加需要爱去呵护的孩子。那么你，愿意加入我们吗？</div> 
    	</dd>
    </dl>
</div>
<div class="gonggao">
	<div class="gonggao1">
		<div class="gg1">公告 &nbsp;/ &nbsp;Notice</div>
		<div class="gg2">
			<ul>
				<li><a href="">2018大学生支教暑期支教爱心召集中</a></li>
				<li><a href="">2018乡村支教志愿者预录名单</a></li>
				<li><a href="">“启梦计划”将于2018年7月开始，现可报名参与</a></li>
				<li><a href="">“绿舟”户外拓展班开始报名啦！</a></li>
				<li><a href="">“启梦”爱心捐助现推出线上活动</a></li>
				<li><a href="">2018大学生西部计划志愿者招募协议</a></li>
				<li><a href="">“启梦绿舟”诚邀项目发展合作伙伴~</a></li>
			</ul>
		</div>
  </div>
  <div class="mdm">
		<div class="mdm1">公益面对面</div>
		<div class="mdm2">
			<ul>
				<li><a href="">《公益面对面》栏目首期视频正式上线</a></li>
				<li><a href="">“公益星期六”线下开展趣味活动</a></li>
				<li><a href="">开展线上线下公益活动，打造网络文化活动升级版</a></li>
				<li><a href="">“启梦远行”朗读大赛圆满结束</a></li>
				<li><a href="">“启梦”爱心捐助现推出线上活动</a></li>
				<li><a href="">“启梦校园行”正在进行中，加入我们吧</a></li>
				<li><a href="">当直播遇到公益，做新时代的新雷锋</a></li><!--我们来到你的地方，只为与你一同欢笑，-->
			</ul>
		</div>
	</div>
</div>
<div class="tuhua">
 <div class="sp1">我们来到你的地方，只为与你一同欢笑</div>
 <div class="sp2">
 	<div class="imgbox" id="imgbox">
         <span>
             <a href="#"><img src="images1/happy1.png" /></a>
             <a href="#"><img src="images1/happy2.png" /></a>
             <a href="#"><img src="images1/happy3.png" /></a>
             <a href="#"><img src="images1/happy4.png" /></a>
         </span>
     </div>
 </div>
</div>
<div class="dongtai">
	<div class="dt1">
		<div class="dt11">表扬榜</div>
		
		<div class="dtb1">
			@Mr张 在“公益星期六”活动中为爱助力，得到了所有孩子的认可，并且带动了身边的朋友一起为爱执着。<br>@火柴人 在“绿舟”户外拓展活动中，耐心引导孩子们，完美表演“大合唱”环节！<br>
		</div>
		<span><a href="">更多&gt;&gt;</a></span>
		<div class="dtc1"></div>
	</div>
	<div class="dt2">
		<div class="dt11">公益捐赠</div>
		
		<div class="dtb1">
		    "启梦绿舟"提供的爱心捐赠平台，是一个可以让您跟进，通过平台直观看到您的爱心传送到的地方，我们会记录这一瞬间，与您分享这份喜悦！
		    <div id="jztd"><a href="">捐赠通道</a></div>
		</div>
		<span><a href="">详情&gt;&gt;</a></span>
		<div class="dtc1"></div>
	</div>
	<div class="dt3">
		<div class="dt11">树洞老人 / 急速问答</div>
	  <div class="dtb1">
		    <table>
		    	<tr id="tw1"><td>快速提问区:</td></tr>
		    	<tr><td><textarea name="liuyan" id="tw2" placeholder="" ></textarea></td></tr>
		    	<tr><td><div name="tijiao111" id="tjbutton"><a href="">提交</a></div></td></tr>
		    </table>
		</div>
		<span>更多&gt;&gt;</span>
		<div class="dtc1"></div>
	</div>
</div>
<div class="shipin">
	
</div>


<footer>
	<span><a href="">意见反馈</a> 丨 <a href="">网友投诉</a> 丨 <a href="">服务协议</a> 丨 <a href="">隐私政策</a> 丨 <a href="">开放平台</a> 丨 <a href="">广告服务</a> 丨 <a href="">客服中心</a><br>Copyright &copy; 2001-2018 QIAMENGLVZHOUcom，All rights reserved.</span><br>2001-2018，版权所有 启梦绿舟 85CP备13386656
</footer>

<script type="text/javascript">
	var index=0;//初始化序列号
var Play = null;
autoPlay();
	
	$(".button li").mouseover(function() {
		clearInterval(Play);//清除定时器
		index = $(this).index();//获取到序列号
		
		$(this).addClass("hover").siblings().removeClass("hover");
		$(".flash img").eq(index).show().siblings("img"). hide();
	}).mouseout(function(){
		autoPlay();
	});
	
	//设置定时器
	function autoPlay () {
		Play = setInterval( function(){
			index++;
			if (index > 3) {  index = -1; }
			else{
		$(".button li").eq(index).addClass("hover").siblings().removeClass("hover");
		$(".flash img").eq(index).show().siblings("img").hide();
		}
		}   ,1500);
	}
	
	function huanxiao(){
		//定义滚动速度；值越小，速度越快
		var speed = 20; 
		//获取<div id="imgbox">元素
		var imgbox = document.getElementById("imgbox");
		//复制一个<span>，用于无缝滚动
		imgbox.innerHTML += imgbox.innerHTML; //此时imgbox中有两个 span
		//获取两个<span>元素
		var spans = imgbox.getElementsByTagName("span");// spans[]中有两个spans[0],spans[1]
		//启动定时器，调用滚动函数
		var timer = window.setInterval(marquee,speed);
		//鼠标移入时暂停滚动，移出时继续滚动
		imgbox.onmouseover = function(){
			clearInterval(timer);
		}
		imgbox.onmouseout = function(){
			timer=setInterval(marquee,speed);
		}
		//滚动函数
		function marquee(){
			//当第1个<span>被完全卷出时
			if(imgbox.scrollLeft > spans[0].offsetWidth){
				//将被卷起的内容归0
				imgbox.scrollLeft = 0;
			}else{
				//否则向左滚动
				imgbox.scrollLeft ++;
			}
		}
	}
	huanxiao();

	</script>
</body>
</html>

