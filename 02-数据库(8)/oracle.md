# Oracle

## 一、数据库的前置知识：

1、数据库：是一个软件，完成数据管理的软件，DataBase（DB）
2、数据：对事物的图形化表达方式称为数据
3、信息：是对大大量数据的统计分析后得出的一个队人的行为有指导价值的数据叫信息。
4、关系型数据库管理系统：以关系模型完成数据管理的数据库叫关系型数据库关系系统：R-DBMS    RDBMS
主流：Oracle  MySQL  SQLServer (HDBS) interbase  ....    mocrosoft:C#  asp.net  vb.net      .NET  (dotnet)
备注：数据库是一个简称，全称数据库管理系统，是一个软件，  database manage system   ：DBMS（数据库管理系统）

5、Oracle数据库账号
①、数据库管理员  DBA（） SP（）（宋（））。   
​    scott账号，默认是锁定状态，需要解锁账号才能使用
​    在Oracle中怎样以Scott账号登录   conn  scott/tiger     scott:账号名    tiger：密码

②、解锁账号： alter user scott account unlock;

6、oracle的客户端  ：web方式，sqlplus   ，plsqldev，

①、sqlplus

​	A：在开始菜单中启动        输入账号信息登录

​	B：在命令窗口中通过sqlplus启动	  输入账号信息登录   sqlplus scott/tiger;

②、plsql developer

7、关系型数据库关系是通过一个基本单元完成数据管理， table（表）是一个二维结构（行和列）

8、数据类型：number，varchar2（char，varchar）,date,blob(二进制),clob（大文本）

char：			固定长度	 	char(10)		:abc       

varchar2：		不固定长度	varchar2(10)  :abc

9、SQL：结构化数据查询语言，是关系型数据库关系的工业标准

10、SQL：

> ①、数据查询语言：DQL   -->检索（查询）表中数据用的
>
> ②、数据操作语言：DML  -->表中数据的新增、删除、修改
>
> ③、数据库操作语言：DDL -->新增、删除、修改

11、DBMS完成数据管理过程中，将相关的操作分为两个目标（有点类似于java类体中有什么？变量|方法），主要有数据和数据库对象两种。

数据库对象：表table    视图view   过程procedure    函数method   触发器tigger  等都属于数据库对象。



*备注：column：列的意思*







11、Oracle中表格的创建

> ①、通过plsqldev  图形化方式创建
>
> ②、DDL创建



12、E-R图，实体关系图，是通过规定好的各种图形表达式相关的意思（5个）

> ①、长方形     ：实体
>
> ②、椭圆     	：属性
>
> ③、无向线     ：关联
>
> ④、菱形         ：实体之间的关系
>
> ⑤、比例		 ：三种    1:1    1:m   m:n

工具：word  excel   visio   pd



13、基于E-R图创建表

> ①、E-R图中所有的实体必须创建一张表
>
> ②、M：N 关系中  （M：N关系的关系需要创建一张表）,这个关系中要包含相关联的两个实体的主键列。
>
> ③、1：m关系中，在多一端的表中添加一个列，这个列是1那一端的主键。
>
> ④、1:1关系中，根据实际需求，确定在任何一端内加入另一端的主键列即可。
>
>

14、范式：用范式的概念去验证表创建的是否是合理的？

> 1范式：1NF，表中没有相同的列
>
> 2范式：2NF：表中没有重复行数据（表中必须有主键）
>
> 3范式：3NF：不允许出现传递依赖主键的列
>
> 解决方法：将传递依赖主键的列单独提出来，另创建一张表

DBMS--R-DBMS    DBA  DB



system，scott（默认是锁定状态）system登录后alter user scott account unlock解锁。



数据库对象：表格、列、视图、游标、过程、函数、序列、触发器等

SQL：

> ①、DQL：数据检索语言
>
> ②、DML：数据操作语言（操作表中数据的）（新增、删除、修改
>
> ③、DDL：数据库操作语言（ddl操作数据库对象）（新增，删除，修改）



## 二、数据库操作

1、前置知识：数据库环节不区分大小写字母，一般来讲项目中都是大写字母（表名、列名、关键字）。

2、DDL：数据库操作语言

> ①、新增：create objType objName
>
> ②、删除：drop objType objName
>
> ③、修改：alter objType ...

3、创建表

语法：create table  tblName（columnName dbtype,.....,columnName dbtype）

案例：create table  student(stuid varchar2(10) ,stuname varchar2(50),age number)



4、删除表

语法：drop table tblName；

案例：drop table student;



5、表约束

> ①、主键约束：不能接受null值，其值不能重复
> ②、非空约束：不允许接收空值null
>
> ③、唯一值约束：可以接收null值，但是若输入了具体值，那么要求这个值是不存在的，即具有唯一性
>
> ④、检查约束：设置数据的边界
>
> ⑤、外键约束：一个表中的非主键列，引用参考与另一个表的主键。这样的话，这个外键列的数据将参考与另一张表的主键列





6、为已存在的表添加约束，以上五种类型的约束中非空和唯一值约束在创建表期间指定，其他可以通过如下方式创建约束

```sql
alter table news add constraint PK_NEWS_0001 PRIMARY KEY (NID);
alter table news add constraint CK_NEWS_0002 check(visnum >10);
alter table news add constraint FK_NEWS_0003 foreign key (tid) references news_type(tid);
```



7、SQL文（SQL语句）

DML：数据操作语言，完成表中数据的增加、删除、修改操作

> ①、增加  insert，在现有表中添加一条数据（以条为单位）
>
> ②、删除  delete，在现有表中删除一条数据（以条为单位）
>
> ③、修改  update，在现有表中对现有数据的一列（单元格）进行修改（以单元格为单位）



```sql
--删除数据
关系运算符：<   <=    >    >=   =   !=(<>)
算数运算符：
delete from user111 where userid<>'A0001';

-- 数据更新（单元格）
update emp set sal =10000 where empno=9999;
update emp set sal =10000 where empno>9900;
--需求：将工号在9900以上的员工的工资更新为12000并将其奖金设置为3000；
update emp set sal =12000,comm=3000 where empno>9900;

```



8、Oracle中的运算符

> ①、关系运算符：<   <=    >    >=   =   !=(<>)
>
> ②、算数运算符：+   -   *   /   mod()
>
> 注意：任何以上运算符和null计算时 结果为null，当一个数据和null进行关系比较时，结果false；
>
> ③、空值运算符 ：is null      is  not null
>
> ④、逻辑关键字 ： and  or
>
> ⑤、连字符：|| 

```sql
--SQL:数据检索
select 要检索数据的列名列表
from 表名列表
where 条件列表
-- 所有员工的工资和奖金是多少？
select empno,ename,sal ,comm
from emp

-- 工号小于9000的所有员工的工资和奖金以及工号和姓名是多少？
select empno,ename,sal,comm
from emp
where empno<9000;

select * from emp;   --*统配符，统配了表中的所有列名
select ename,empno,job,mgr,hiredate,sal,comm,deptno from emp;

select sal, sal+1000 from emp;

-- dual:空表，异常常量计算时可以通过dual完成
select 3+5 from dual;
select 5-3 from dual;
select 5*3 from dual;
select 5/3 from dual;
select mod(5,2) from dual;
-- 咱们单位哪些员工没有工资
select * from emp where sal is null;
select * from emp where sal is not null;
-- 哪些员工有工资但是没有奖金
select * from emp where sal is not null and comm is null;
select * from emp where sal is  null or comm is null;

--连字符
select '张'||'三' from dual;
select ename||empno from emp;

--工资大于3000 并且没有奖金的员工都有谁？
select * 
from emp
where sal>4000 and comm is null;
-- 区间检索 between ...and      [4000,6000]
select * 
from emp 
where sal between 4000 and 6000;


-- 8877和9999的详细信息是什么？
select * 
from emp
where empno=8877 or empno=9999;-- 若两个条件是同一个的话，不能用and
-- 8877、999、6655的详细信息是什么？
select * 
from emp
where empno=8877 or empno=9999 or  empno=6655;
select * 
from emp
where empno in(8877,6655,9999);-- empno=8877 or empno=6655 or empno=9999


-- 排序检索  order by
select * 
from emp
order by sal ASC;--升序

select * 
from emp
order by sal DESC;--降序


select * 
from emp
order by sal DESC ,deptno desc;--降序
-- 将deptno不为空的员工的详细数据检索出来，要求工资降序，工资相同的情况下按照deptno降序排列
select * 
from emp
where deptno is not null
order by sal DESC ,deptno desc;--降序    order by是sql文的最末端
```





9、DQL：数据查询语言

单表检索：

> ①、单条件检索：利用运算符完成一个条件的检索
>
> ②、空值|非空检索  is null      is not null
>
> ③、区间检索  between ... and....	[]
>
> ④、in检索 （范围检索）    empno in(8877,6655)
>
> ⑤、排序检索 order by    列名  ASC（升序）|DESC（降序）



##  三、前课回顾

1、SQL，是一个工业标准，所有的RDBMS都支持的一种结构化查询语言。

①、DDL：完成数据库对象的操作

	> Ⅰ 、创建对象，create
	>
	> Ⅱ、删除对象，drop
	>
	> Ⅲ、修改对象，alter

②、创建表，在创建表时可以给表添加约束

> 主键约束：具有唯一性（排他性），不能接收null值   primary key
>
> 外键约束：约束某列的值的范围，一个表中的非主键列在另外一个表（主表）中是主键列，常用在1:1 和 1:m关系中。foreign key ...references
>
> 非空约束：不能为空 not null
>
> 唯一值约束：数据具有唯一性（排他性）但是可以接收null值   unique
>
> 检查约束：check （age>0 and age<200）

③、对已有表添加约束(保证数据的合法性)

​	alter table tblName add constraint conName type

④、DML：数据操作语言

> Ⅰ 、新增：是以行为单位操作的，要么添加一行，要么一行不添加
>
> Ⅱ、删除：是以行为单位操作的，要么一行都不删除，要么删除整行数据
>
> Ⅲ、修改：是单元格为基本单元操作的，是对已有数据的修改（更新），不能导致表中数据行数的变化！

~~~ sql
insert into t1(tid,tname) values(200,'刘备');

-- 不推荐使用的
insert into t1 values(201,'关羽');

delete from t1 where tid=201;
update t1 set tname='张飞', where tid=201;
update t1 set tname='赵云',txt='武将' where tid=201;
--  
select * from t1;
alter table t1 add txt varchar2(3000);
~~~

⑤、数据检索

> Ⅰ 、单条件检索  where 子句引导的条件
>
> Ⅱ、多条件检索：and  or          where userid='rock' and password='admin';
>
> Ⅲ、区间检索：between... and ...   []闭区间关系   where age>=50 and age<=60===between 50 and 60;
>
> Ⅳ、排序检索：order  by  列名 ASC|DESC  缺省是ASC升序。
>
> ​	order by子句必须在sql文的最末端
>
> ​	若根据多个列排序的时候，以先后顺序确定主排序列和辅排序列
>
> Ⅴ、in检索：  where   age in(30,40,50) =======where  age=30 or age=40 or age=50;

⑥、Oracle运算符

> 关系运算符：<    <=    >     >=    =    !=(<>)
>
> 算数运算符：+    -     *     /   mod()
>
> 连字符	   ： ||         username||address     （将两个字符合并成一个结果）
>
> null计算     ：null和任何一个数进行算数运算时结果都为null     在关系操作中都为false

作业：

# 

 ##  Oracle_sql练习题及答案

 实验一

练习1、请查询表DEPT中所有部门的情况。

练习2、查询表DEPT中的部门号、部门名称两个字段的所有信息。

练习3、请从表EMP中查询10号部门工作的雇员姓名和工资。

练习4、请从表EMP中查找工种是职员CLERK或经理MANAGER的雇员姓名、工资。

练习5、请在EMP表中查找部门号在10－30之间的雇员的姓名、部门号、工资、工作。

练习6、请从表EMP中查找姓名以J开头所有雇员的姓名、工资、职位。

练习7、请从表EMP中查找工资低于2000的雇员的姓名、工作、工资，并按工资降序排列。 

练习8、请从表中查询工作是CLERK的所有人的姓名、工资、部门号、部门名称以及部门地址的信息。

练习9、查询表EMP中所有的工资大于等于2000的雇员姓名和他的经理的名字。

练习10、在表EMP中查询所有工资高于JONES的所有雇员姓名、工作和工资。

练习11、列出没有对应部门表信息的所有雇员的姓名、工作以及部门号。

练习12、查找工资在1000～3000之间的雇员所在部门的所有人员信 

练习13、雇员中谁的工资最高。

*练习14、雇员中谁的工资第二高（考虑并列第一的情况，如何处理）。

 

实验二

**1**．    查询所有雇员的姓名、SAL与COMM之和。

**2**．    查询所有81年7月1日以前来的员工姓名、工资、所属部门的名字

**3**．    查询各部门中81年1月1日以后来的员工数

**4**．    查询所有在CHICAGO工作的经理MANAGER和销售员SALESMAN的姓名、工资

**5**．    查询列出来公司就职时间超过24年的员工名单

**6**．    查询于81年来公司所有员工的总收入（SAL和COMM）

**7**．    查询显示每个雇员加入公司的准确时间，按××××年××月××日 时分秒显示。

**8**．    查询公司中按年份月份统计各地的录用职工数量

**9**．    查询列出各部门的部门名和部门经理名字

**10**．    查询部门平均工资最高的部门名称和最低的部门名称

**11**．    *查询与雇员号为7521员工的最接近的在其后进入公司的员工姓名及其所在部门名

 

## 四、数据检索

> Ⅰ 、单条件检索  where 子句引导的条件
>
> Ⅱ、多条件检索：and  or          where userid='rock' and password='admin';
>
> Ⅲ、区间检索：between... and ...   []闭区间关系   where age>=50 and age<=60===between 50 and 60;
>
> Ⅳ、排序检索：order  by  列名 ASC|DESC  缺省是ASC升序。
>
> ​	order by子句必须在sql文的最末端
>
> ​	若根据多个列排序的时候，以先后顺序确定主排序列和辅排序列
>
> Ⅴ、in检索：  where   age in(30,40,50) =======where  age=30 or age=40 or age=50;
>
> Ⅵ、模糊检索：通过%（任意多个字符）以及_任意一个字符来模糊匹配进行数据检索
>
> Ⅶ、分组查询：将值相等的分为一个组，然后对其中的数据进行统计计算。group by 
>
>

备注：Oralce的 聚合函数 ：avg()   sum()   min()  max()  count()

~~~ sql

select * from emp;
-- 检索所有姓张的员工信息   ename的首字符是张字，后面跟任意（包括0个）个字符
select * from emp where ename like '张%'
update emp set ename='张冰' where empno=7788;
commit;
-- ename只有两个字符组成，第一个字符是任何的，第二个字符是冰字
select * from emp where ename like '_张';

-- Oracle中的聚合函数（借助excel中的函数理解）
select sum(sal),avg(sal),min(sal),max(sal),count(*) from emp;

select * from emp;
-- 统计30号部门有多少人？
-- 咱们单位有多少人？
select count(empno) from emp where deptno=30;
select count(sal) from emp;

-- 统计每个部门有多少人？
select count(empno) from emp where deptno=30;   --只有30号部门
-- group by deptno :根据deptno列进行分组（将deptno列中的值相等的分为一个组），然后将对每个组进行count聚合计算
select count(deptno),deptno from emp group by deptno;

-- 每个部门的最高工资是多少？
select max(sal) from emp where deptno=30;
select max(sal),deptno from emp group by deptno;
-- 每个部门的平均新资是多少？
select avg(sal),deptno from emp group by deptno;
--程序员的平均工资是多少？
--要分组检索？
select avg(sal) from emp where job='SALESMAN';
-- 各个岗位的平均薪资
-- 分组检索中，select子句内要么是分组列，要么是聚合列
select avg(sal),job from emp group by job

-- 哪些岗位的平均工资高于2000？
条件是？平均工资>2000
由于这个条件是分组聚合之后的数据做为条件，所以这里不能使用where引导名，必须是having引导
select avg(sal),job from emp group by job having avg(sal)>2000;
select avg(sal) pjgz,job j from emp group by job having avg(sal)>2000;

--哪些非空岗位的平均工资高于2000？
--非空岗位的平均工资是多少？
select avg(sal) pjgz ,job job from emp where job is not null group by job having avg(sal)>2000;
--哪些非空岗位的平均工资高于2000？将结果以平均工资升序排列
select avg(sal) pjgz ,job job from emp where job is not null group by job having avg(sal)>2000 order by pjgz;pin


~~~

## 五、多表连接查询

1、子查询，将一个查询的结果做为另一个查询条件

注意：

①、子查询必须用（）阔起来

~~~ sql
-- 检索工资大于平均工资的所有员工信息
(select avg(sal) from emp);===> a;
select * from emp where sal >(select avg(sal) from emp);	
--哪些员工属于SALES或ACCOUNTING部门
①、检索以上两个部门的部门编号
select deptno from dept where dname='SALES' or dname='ACCOUNTING'
②、将这个结果作为条件嵌入在子查询中
select * from emp where deptno in(select deptno from dept where dname='SALES' or dname='ACCOUNTING')
~~~

②、自连接查询    ：将一张表作为两个表进行管理，自己连接自己

~~~ sql
--检索所有员工的员工姓名以及工号以及他的领导的姓名？
--自连接查询（借助于等值连接和内连接完成） 将一个表（自己）当成两张表进行关联检索
select a.empno,a.ename,a.mgr,b.ename from emp1 a ,emp1 b where a.mgr=b.empno;
select a.empno,a.ename,a.mgr,b.ename from emp1 a join emp1 b on a.mgr=b.empno;
~~~

③、等值连接查询：用两个表中的某个列的值相等作为条件，进行检索（两个表中有公共列），不是列名而是值

~~~ sql
SELECT * FROM news;
SELECT * FROM newstype;
-- 检索每条新闻的标题和新闻类型名称
SELECT n.title,t.typename FROM news n,newstype t WHERE n.typeid=t.typeid;





SELECT * from emp1;
select * from dept;

-- 检索工资大于平均工资的所有员工信息
(select avg(sal) from emp);===> a;
select * from emp where sal >(select avg(sal) from emp);

--哪些员工属于SALES或ACCOUNTING部门
①、检索以上两个部门的部门编号
select deptno from dept where dname='SALES' or dname='ACCOUNTING'
②、将这个结果作为条件嵌入在子查询中
select * from emp where deptno in(select deptno from dept where dname='SALES' or dname='ACCOUNTING')


-- 所有员工的姓名以及办公地点在哪里？
--SMITH在哪里办公？
select deptno from emp where ename='SMITH';


select a.*,b.loc from emp a ,dept b where a.deptno=b.deptno and a.ename='SMITH'

select a.empno,a.ename,b.loc 
from emp a,dept b 
where a.deptno=b.deptno;


select * from emp;
--检索所有员工信息以及他们的部门名称
select e.*,d.dname from emp e,dept d where e.deptno=d.deptno;

~~~



④、内连接查询    ：用两个表中的某个列的值相等作为条件，进行检索（两个表中有公共列），不是列名而是值

~~~ sql
-- 检索所有员工的基本信息及工作地点
select e.*,d.loc from emp e,dept d where e.deptno=d.deptno;
-- 内连接利用 inner join on  完成内连接查询   注意：inner关键字可以省略   join   on
select e.*,d.loc from emp e inner join dept d on e.deptno=d.deptno;
select e.*,d.loc from dept d inner join emp e on e.deptno=d.deptno;

~~~

⑤、左外连接查询：将左面的表作为主表，主表中的所有记录以及右表中的相关记录检索出来

⑥、右外连接查询：

⑦、全外连接查询：

~~~ sql
-- 检索所有部门信息以及部门对应的员工都有谁？
select d.*,e.* from dept d left outer join emp e on d.deptno=e.deptno; 
select d.*,e.* from emp e right  join dept d on d.deptno=e.deptno; 
-- Oracle左外和右外
select d.*,e.* from dept d , emp e where  d.deptno=e.deptno(+);--左外连接 （借助等值连接）
select d.*,e.* from dept d , emp e where  d.deptno(+)=e.deptno;--右外连接（借助等值连接）
select d.*,e.* from dept d inner join  emp e on d.deptno(+)=e.deptno; --右外连接（借助内连接）
-- 全外连接，分别以左右表为主，所有数据都出来，若没有关联的数据，用null补齐
select d.*,e.* from emp e full join dept d on d.deptno=e.deptno;
select d.*,e.* from emp e full outer join dept d on d.deptno=e.deptno;

~~~



⑧、交叉连接查询：

~~~ sql

-- 交叉连接  笛卡尔乘积，将两个表中的每一行做一次组合，注意交叉连接没有on子句       
select a.* ,b.* from emp a cross join dept b;

-- 领导都有谁？
select mgr from emp;
-- 去重复的问题  distinct
select distinct mgr from emp where mgr is not null;
select ename from emp where empno in(select distinct mgr from emp where mgr is not null);

-- select * from 结果集  where sal<6000;
select * from (select empno,ename,job,mgr,hiredate hd,sal s from emp where mgr is not null)  where s<4000;


~~~

⑨、将一个结果集做为一个表进行检索

~~~ sql
-- select * from 结果集  where sal<6000;
select * from (select * from emp where mgr is null)  where sal<6000;
~~~







