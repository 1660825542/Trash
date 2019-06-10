bn = True
bn = False
bn = 0  # 假  非0 真
bn = 1
bn = 3
bn = -10
if bn :
    print(1)
else:
    print(2)
# 比较运算符
# == > >=  < <=  !=
print(1!=1)
# 赋值运算符 （）=
a = 10
a **=3
a = 'a'
b = 'b'
# 位运算符
num1 = 2 # 0000 0010
num2 = 3 # 0000 0011
# & 有假即假
print(num1&num2)
# | 有真即真
print(num1|num2)
# ^   异或      # 0000 0010     相同为0  不同为1
print(4^2)  #0000 0100
            #0000 0110
print(~2)# -2-1   #1111 1101      源码   反码   补码
print(~4)
# 位移
print(8>>2) #0000 1000    0000 0010
print(2<<2)