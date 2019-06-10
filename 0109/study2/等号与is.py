# is 身份运算符
a = "abc"
b = "abc"
print(a==b)
print(a is not b)
# in
print("a" not in  a )
# 逻辑运算符 and or
num1 = 1
num2 = 2
if num1<2 and num2==2:
    print(1)
# 三元运算符
a = 11
b = 2
c = a if a>b else b
print(c)
# 交换 ab的值
a = a+b # a13  b2
b = a-b # a13 b11
a = a-b
print(a,b)
