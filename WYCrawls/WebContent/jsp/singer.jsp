<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
  	
    <base href="<%=basePath%>">
    <title>EchartsDemo</title>
    <script type="text/javascript" src="js/echarts.js"></script>
    <script type="text/javascript" src="js/echarts-wordcloud.js"></script>
    <script type="text/javascript">
	// 准备一个调用后台服务的核心对象
	var xmlHttp ;
	
	function initData() {
		// 调用后台服务器操作
		// 建立核心对象
		xmlHttp = new XMLHttpRequest();
		// 设置后台进行数据库操作的Servlet地址。
		xmlHttp.open("get","LoadDataFirstServlet");
		// 设置读取到数据后继续处理的js回调函数
		xmlHttp.onreadystatechange = initDataCallback;
		// 发送数据
		xmlHttp.send();
	}
	function initDataCallback() {
		if (xmlHttp.readyState == 4) {
			// 判断是否正确返回
			if (xmlHttp.status == 200) {
				// 接收结果
				var keywords = eval("("+xmlHttp.responseText+")");
				// 开始写到图表中。
				// 找到要绘制图表的div
				var myChart = echarts.init(document.getElementById('main'));
				
				var data = [];
	            for (var name in keywords) {
	          	        data.push({
	                    name: name,
	                    value: Math.sqrt(keywords[name])
	                })
	            }
	            var maskImage = new Image();
	            maskImage.src = "images/f.jpg";
		// 配置图表的各种属性。
		var option = {
                tooltip: {},
                series: [ {
                    type: 'wordCloud',
                    gridSize: 2,
                    sizeRange: [10, 100],
                    rotationRange: [-90, 90],
                    shape: 'circle',
                    width: 1200,
                    height: 580,
                    maskImage:maskImage,
                    drawOutOfBound: false,
                    textStyle: {
                        normal: {
                            color: function () {
                                return 'rgb(' + [
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160)
                                ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    data:data
                } ]
            };

		maskImage.onload = function(){
			option.series[0].maskImage = maskImage;
			myChart.setOption(option);
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		}
		}
	}
</script>
    
  </head>
  
  <body  onload="initData();">
 	 
    <div id="main" style="width:1200px;height:580px;border:1px solid;"> </div>
  </body>
</html>
