# 文件
# 获取文件内容
# 文件句柄:模式 ： 读r 写w  追加a   r+ w+ a+
# 读取文件
# data = open("abc1","r",encoding="utf-8")
# print(data.read())
# data = open("abc1","w",encoding="utf-8")
# print(data.read())
# 相对路径   绝对路径
#data = open("D:/first.txt",'r',encoding="utf-8")
#print(data.read())
# 覆盖
# data = open("D:/first.txt",'w',encoding="utf-8")
# data.write("11111")
# data.write("22222")
# data.write("33333")
# data.close()
# 追加 a 不能读
data = open("D:/first.txt",'a',encoding="utf-8")
# data.write("44444")
# data.close()
#print(data.read())
# 新建文件
#data = open("D:/second.txt",'w',encoding="utf-8")
# data = open("D:/third.txt",'a',encoding="utf-8")
# data = open("abc2",'w',encoding="utf-8")
# r+ 读写属性  从文件的开头写，如果文件内有内容就覆盖
data = open("abc1","r+",encoding="utf-8")
# print(data.read())
# data.write("今天2月1号了")
# data.write("明天2月12号")
# data.write("bbbbb")
# data.flush()
# data.close()
# w+  : 读写属性   先清空内容  从文件的开头写
#data = open("abc1","w+",encoding="utf-8")
# data.write("ccccc")
# data.flush()
# data.close()
#print(data.read())
# a+ 追加  以附加方式打开文件
# data = open("abc1",'a+',encoding="utf-8")
# data.write("_________________")
# data.flush()
# data.close()
# data = open("abc1",encoding="utf-8")
# print(data.read())