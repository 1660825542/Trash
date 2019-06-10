<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示页面</title>
<script src="js/echarts.min.js"></script>

</head>
<body>

		<div id="main" style="width: 1000px;height: 800px;margin: 0 auto;"></div>
		<script type="text/javascript">
		var res = ${res}
		var data = []
		var data1 = []
		for (var r in res) {
			data.push({
				value: res[r],
				name: r
			})
			data1.push(
				r		
			)
		}
		var myChart = echarts.init(document.getElementById('main'));
			
			option = {
		    title : {
		        text: '招聘岗位数排名前十的公司名称及岗位数量',
		        x: 'center'
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c}"
		    },
		    
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: data1
		    },
		    
		    series : [
		        {
		            name: '公司名称及岗位数量',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:data,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 10,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		
		
		myChart.currentIndex = -1;
		
		setInterval(function () {
		    var dataLen = option.series[0].data.length;
		    // 取消之前高亮的图形
		    myChart.dispatchAction({
		        type: 'downplay',
		        seriesIndex: 0,
		        dataIndex: myChart.currentIndex
		    });
		    myChart.currentIndex = (myChart.currentIndex + 1) % dataLen;
		    // 高亮当前图形
		    myChart.dispatchAction({
		        type: 'highlight',
		        seriesIndex: 0,
		        dataIndex: myChart.currentIndex
		    });
		    // 显示 tooltip
		    myChart.dispatchAction({
		        type: 'showTip',
		        seriesIndex: 0,
		        dataIndex: myChart.currentIndex
		    });
		}, 1500);
		// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
		</script>
	</body>
</html>