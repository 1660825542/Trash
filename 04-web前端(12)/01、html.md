# web前端技术

## 1、涉及到的科目

> ①、html		：用开发静态页面
>
> ②、css			：层叠样式表（设置页面的样式）
>
> ③、javascript	：在页面的前端可以完成诸如 校验等一些控制，
>
> ④、jquery		：是javascript含数据库
>
> ⑤、json			：数据交换技术（xml）

## 2、web前端IDE

sublime、webstorm、hbuilder、idea、eclipse都可以

## 3、c/s&  b/s

C：Client：客户端	 	发起请求的那一端（你去银行取钱，你是客户端）

S：Server：服务器端		提供了某种服务的那一端（银行、超市）

平常上网时，是通过浏览器发起一个web请求，我们电脑就是一个客户端，由于这个客户端是由浏览器承担的，所以这种特殊的c/s结构叫b/s结构，

b：browser：浏览器

s：server：服务器端

## 4、HTML

html：是超文本标记语言（解析器），用于开发静态页面

1、前置

>  标记：是用<>一组内容，叫标记
>
> 规则如下：
>
> ①、所有标记有开始标记和结束标记（空标记除外）(<a>test</a>)
>
> ②、<> 号中间的内容叫标记名（元素（节点）），<html>
>
> ③、标记的值：开始标记和结束标记中间的内容叫标记的值（标记的内容）
>
> ④、标记的属性：属性必须写在开始标记内，每个属性必须有属性名和属性值两个部分，并且属性值必须用""阔起来
>
> ⑤、所有的标点符号必须是英文半角下输入
>
> ⑥、标记可以合理嵌套不能交叉嵌套，注意：父标记|子标记    外层父标记   内层子标记
>
> ⑦、html文件必须以html或者htm为扩展名
>
> ⑧、html的解析器是浏览器

2、第一个页面

## 3、html标记

①、标题标记：以标题的样式显示内容<h1-h6>

	> 字号变化
	>
	> 加粗显示
	>
	> 自动换行

②、分割符标记   <  hr/>

​	分割符：在页面显示的一条横线，这个标记是一个空标记

​	空标记：没有值得标记叫空标记，在开始标记的>号前加上/符号即可

③、段落标记  < p>,两个段落间隔较大，并且自动换行

④、换行标记：< br/>

⑤、图片标记：< img/>

⑥、超链接：   < a>

> 锚：通过a超链接标记的name属性，可以设置一个书签
>
> 定位到锚：< a href="#part3">跳转< /a>  ：单击跳转连接时，将页面定位到part3这个书签位置

⑦、表格标记：table,tr,td,th

注意：td有两个基本属性用于跨行和跨列

colspan：跨列

rowspan：跨行

⑧、列表

> 有序列表
>
> 无序列表

⑨、块级元素内联元素，块级元素默认换行，而内联元素不换行

​	块级元素：div，p ，ul，ol,table

​	内联元素：span,td,img,a等

## 4、http协议

http协议：是一个面向无连接、无状态的，基于请求与应答模式超文本传输协议



<input type="text|password|radio|checkbox|submit"/>

<input type="text|password" name=""/>
<input type="radio|checkbox" name="" value="">
<form action="T2" method="get|post" name="">
<input type="submit">   提交按钮（提交表单的按钮）


http协议：面向无连接、无状态的基于请求应答模式的超文本传输协议

实体：在页面上显示特殊的字符
空格:&nbsp;
<   :&lt;
>   :&gt;
>   &   :&amp;
>   "   :&quot;


URL:URL - Uniform Resource Locator   :统一资源定位器


http://www.163.com

http://www.sports.163.com/photoview/00920005/165151.html

https://dl.reg.163.com/ydzj/maildl.html?product=urs&curl=https


?product=urs&curl=https:
?号是http协议的特殊符号，？代表着参数列表  若有多个参数用&号分割，每个参数由参数名和参数值组成



html框架

颜色表达方式
①、6位16进制数表达    #111fFF
②、颜色英文单词       yellow
③、RGB方式	       rgb(255,255,255)  

## 5、h5 

是h4的升级版，主要用于在移动设备（手机、平板）使用。

> placeholder属性：框内提示
> datalist元素|option元素：注意label value属性的应用
>
> autofocus属性：被浏览器载入后，默认焦点
>
> required属性：设置必填项目，在提交表单时，必须写入内容，不能为空

~~~ html
<form action="T1">
E-mail: <input type="email" name="user_email" placeholder="E-Mail"/>
Webpage: <input type="url" list="url_list" name="link" />
    <datalist id="url_list">
        <option label="W3School" value="http://www.W3School.com.cn" />
        <option label="Google" value="http://www.google.com" />
        <option label="Microsoft" value="http://www.microsoft.com" />
    </datalist>
User name: <input type="text" name="user_name"  autofocus="autofocus" />

Name: <input type="text" name="usr_name" required="required" />
<input type="submit">
</form>
~~~

