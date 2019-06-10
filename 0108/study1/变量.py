#_*_coding:utf-8 _*_
# 变量   单行注释
"""  注释
name = "王丽"
print(type(name))
name = "张志豪"
name = 7
print(type(name))
"""
# 常量 ： python没有常量 大写增强可读性
'''注释
PIE = 3.14
PIE = 3
print(PIE)
'''
# 控制台输入
name = input("请输入用户名：")
'''
password = input("请输入用户密码")
print(name,password)
'''
#导入模块
import getpass
password = getpass.getpass("请输入用户密码")
print(password)
