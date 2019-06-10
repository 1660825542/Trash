# file = open("abc1",'rb')
file = open("abc1",'r',encoding="utf-8")
print(file.readable())
#print(file.read(6)) # 6: 读取留个字符
# print(file.readline().strip())
# print(file.readline())
print(file.read()) # 文件读取时会记录标记，再读取从新的标记开始向下读取 而不是重头开始
print(file.tell()) # 读取文件句柄时 现在读取了多少个字符 换言之 读到哪了
file.seek(0) # 将标记移动到开头
print("+++++++++++++++++")
print(file.read())
file.seek(0)
print(file.readlines()) # 内容添加到列表中
"""
ssssssss
ssssssss
sdsdsdsd
aaaaaaaa
"""
#d全部替换成？
lines = ""
# with open("abc1","r",encoding="utf-8") as f:
#     for line in f:
#         print(line)
f = open("abc1","r",encoding="utf-8")
# for line in f:
#     if "d" in line:
#         line = line.replace("d","?")
#     lines +=line
lines = f.read().replace("?","d");
f = open("abc1","w",encoding="utf-8")
f.write(lines)
f.flush()
f.close()