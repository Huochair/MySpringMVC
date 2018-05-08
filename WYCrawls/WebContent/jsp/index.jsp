<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
<title>EChartsDemo</title>
<script type="text/javascript"src="js/echarts.js"></script>
<script type="text/javascript">
//准备一个调用后台服务的核心对象
var xmlHttp ;
function initData() {
	// 调用后台服务器操作
	// 建立核心对象
	xmlHttp = new XMLHttpRequest();
	// 设置后台进行数据库操作的Servlet地址。
	xmlHttp.open("get","LoadDataServlet");
	// 设置读取到数据后继续处理的js回调函数
	xmlHttp.onreadystatechange = initDataCallback;
	// 发送数据
	xmlHttp.send();
}

function initDataCallback() {
	// 判断数据是否完全返回了
	if (xmlHttp.readyState == 4) {
		// 判断是否正确返回
		if (xmlHttp.status == 200) {
			// 接收结果
			var result = eval("("+xmlHttp.responseText+")");
			// 开始写到图表中。
			// 找到要绘制图表的div
			var myChart = echarts.init(document.getElementById('main'));
			
			var xArray = new Array();
			var dataArray = new Array();

			
			for (var i = 0;i < result.length;i++) {
				xArray[i] = result[i].classes;
				dataArray[i] = result[i].fav_count;
			}
			

			// 配置图表的各种属性。
          /*var option = {
				title : {
					text : '网易云'
				},
				tooltip : {},
				legend : {
					data : [ "歌曲量" ]
				},
				xAxis : {
					data : xArray
				},
				yAxis : {},
				series : [ {
					name : '歌曲量',
					type : 'bar',
					data : dataArray
				} ]
			};*/
			var dataAxis = xArray;
			var data = dataArray;
			var yMax = 1000;
			var dataShadow = [];

			for (var i = 0; i < data.length; i++) {
			    dataShadow.push(yMax);
			}

			option = {
			    title: {
			        text: '网易云',
			        subtext: '标签和音乐数量'
			    },
			    xAxis: {
			        data: dataAxis,
			        axisLabel: {
			            inside: false,
			            textStyle: {
			                color: '#000'
			            }
			        },
			        axisTick: {
			            show: false
			        },
			        axisLine: {
			            show: false
			        },
			        z: 10
			    },
			    yAxis: {
			        axisLine: {
			            show: false
			        },
			        axisTick: {
			            show: false
			        },
			        axisLabel: {
			            textStyle: {
			                color: '#999'
			            }
			        }
			    },
			    dataZoom: [
			        {
			            type: 'inside'
			        }
			    ],
			    series: [
			        { // For shadow
			            type: 'bar',
			            itemStyle: {
			                normal: {color: 'rgba(0,0,0,0.05)'}
			            },
			            barGap:'-100%',
			            barCategoryGap:'40%',
			            data: dataShadow,
			            animation: false
			        },
			        {
			            type: 'bar',
			            itemStyle: {
			                normal: {
			                    color: new echarts.graphic.LinearGradient(
			                        0, 0, 0, 1,
			                        [
			                            {offset: 0, color: '#83bff6'},
			                            {offset: 0.5, color: '#188df0'},
			                            {offset: 1, color: '#188df0'}
			                        ]
			                    )
			                },
			                emphasis: {
			                    color: new echarts.graphic.LinearGradient(
			                        0, 0, 0, 1,
			                        [
			                            {offset: 0, color: '#2378f7'},
			                            {offset: 0.7, color: '#2378f7'},
			                            {offset: 1, color: '#83bff6'}
			                        ]
			                    )
			                }
			            },
			            data: data
			        }
			    ]
			};

			// Enable data zoom when user click bar.
			var zoomSize = 6;
			myChart.on('click', function (params) {
			    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
			    myChart.dispatchAction({
			        type: 'dataZoom',
			        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
			        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
			    });
			});

			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
    }
}
</script>
</head>

<body onload="initData();">
<div id="main" style="width:1500px;height:800px;border:1px solid;"></div>
</body>
</html>