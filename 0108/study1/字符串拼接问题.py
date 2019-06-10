name = "tom"
password = "abc"
# windows 换行\r\n   linus  换行 \n
# 拼接info = "用户名："+name+"\r\n密码："+password
# 换行拼接
info = "用户名："+name+'''
密码：'''+password
print(info)

# 控制台输入个人信息，下述格式输出
"""
info1 = '''--------信息--------
name：tom
age：18
salary：100000.5
'''
print(info1)
"""
name = input("输入用户名")
# 类型转换 str > int or float
age = int(input("输入用户年龄"))
salary = float(input("输入用户薪资"))
"""
info1 = '''--------信息--------
name:'''+name+'''
age：'''+age+'''
salary：'''+salary
print(info1)
"""
#字符串格式化 %s字符串 %d  %f
# 控制台录入>string
info1 = '''--------信息--------
name：%s
age：%d
salary：%f
'''%(name,age,salary)
print(info1)
print(type(age))
