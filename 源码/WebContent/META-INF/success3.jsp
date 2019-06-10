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
			// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var res = ${res}
		var data = []
		var data1 = []
		var data2 = []
		for (var r in res) {
			data.push(
				res[r]
			)
			data1.push(
				r		
			)

		}

        // 指定图表的配置项和数据
       var option = {
		    title: {
		        text: '岗位工资折线图(万元/年)'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['工资排名前三岗位折线走势']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        data: data1
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
				{
					name: '年薪(万元)',
					type: 'line',
					stack: '年薪',
					data: data
				}         
		    ]
		    
		};
    	// 使用刚指定的配置项和数据显示图表。
       	myChart.setOption(option);
		</script>
	</body>
</html>