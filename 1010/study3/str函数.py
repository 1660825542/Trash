lines = "撒大声地所多"
lines = "abcadefaa"
print(lines.count('a',0,3))# [0,3) 重复元素个数
print(lines.capitalize())# 首字母大写
print(lines.center(20,'X')) # 居中填充
print(lines.encode("utf-8").decode("utf-8"))# 二进制bytes类型   bytes类型数据>str
info = "________info_______name:{0}".format("name")
print(info)
print(lines.find('d',0,6))# 查询元素 找到返回所在索引 没找到-1
print(lines)
print(lines.isalpha()) # 判断纯英文
print(lines.isalnum()) # 判断含不含有数字
print(lines.isidentifier()) # 查
print(lines.ljust(30,'?')) #
print(lines.rjust(30,'?')) #
print(lines.replace('a',"X",3))
exp = "1+13*22/444"

print(exp.index("444"))
for i in  ("+","-","*","/"):
    exp = exp.replace(i,'|')
print(exp.split('|'))
# 0 2 5 8
# 1  4  7
print('''1+13*
22/444'''.splitlines())

