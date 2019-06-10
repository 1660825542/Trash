str1 = "abcd"
print(len(str1))
print(str1[0])
print(str1[:2],type(str1[:2]))
str1 = "www.baidu.com/article/102323.html"
#以。。。开头/结尾
print(str1.startswith("www.baidu.com/"))
print(str1.endswith("html"))
str2 = "2"
print(str2.isdigit()) #是否是整数
# 加密处理
p = str.maketrans("abcdefg","1234567")
p1 = "dacdsder".translate(p)
pp =str.maketrans("1234567","abcdefg")
p2 = p1.translate(pp)
print(p2)
#
print("*".join(["1","2"]))
print(type("*".join(["1","2"])))
str3 = "1*2"# 作业  "1+3*4/2-1"

# 拆分 spilt 返回值列表
lines = "name=scott&pwd=tiger";
line1 = lines.split("&")
line2 = line1[0].split("=")
print(line2)
exp = "1+3*4/2-1"
exp_num = exp[0::2]
exp_fh = exp[1::2]
print(exp_num,exp_fh)
exp_nums = []
for i in exp_num:
    exp_nums.append(i)
print(exp_nums)