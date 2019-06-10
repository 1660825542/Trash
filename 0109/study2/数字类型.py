# 整数 int
#long
num = 1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
# float 浮点数
num = 1.1
#complex 复数类型
# num * num = -1
num = 1j
print(type(num))

#运算符
#+-*/ 加减乘除
num = 10/3
print(type(num))
# % 求模 溢出
print(10%3)
# 取整
print(10//3)
# 幂运算
print(2**2)
print(2**0.5)
# 1-10素数质数 2,3,5,7
# 质数
# bn 判断该数是否是质数
print("______________")
for i in range(2,11):
    bn = True
    for j in range(2,i):
        if i % j == 0:
            bn = False
            break
    if bn :
        print(i)

for i in range(2,11):
    for j in range(2,i):
        if i % j == 0:
            break
    else:
        print(i)

"""
4 = 2*2
16 = 4*4 
"""
for i in range(2,11):
    bn = True
    j = 2
    while j<=i**0.5:
        if i % j == 0:
            bn = False
            break
        j+=1
    if bn :
        print(i)
