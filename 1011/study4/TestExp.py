# 1+14*3/2-1
# 字符
str_exp = "1+14*3/2-1"
fuhao_index = [] # 1 4 6 8
fuhao = []
for i in str_exp:
    if i in ("+","-","*","/"):
        fuhao.append(str_exp[str_exp.index(i)])
        fuhao_index.append(str_exp.index(i))
else:
    print(fuhao)
a = 1
b = 3
print(str_exp[:1])
print(str_exp[2:4])
print(str_exp[5:6])
print(str_exp[7:8])
print(str_exp[9:])
shuzi = [1,14,3,2,1]
fuhao = ["+","*","/","-"]
# 计算数值
# 先乘除 再加减
inx = 0
while inx < len(fuhao):
    if fuhao[inx] == "*":
        index = fuhao.index("*")
        a = shuzi[index]
        b = shuzi[index+1]
        num = a * b
        fuhao.pop(index)
        shuzi.pop(index)
        shuzi.pop(index)
        shuzi.insert(index,num)
        inx-=1
    elif fuhao[inx] == "/":
        index = fuhao.index("/")
        a = shuzi[index]
        b = shuzi[index + 1]
        num = a / b
        fuhao.pop(index)
        shuzi.pop(index)
        shuzi.pop(index)
        shuzi.insert(index, num)
        inx-=1
    inx+=1
else:
    print(fuhao,shuzi)
# + / -    1, 42, 2,1
str1 = "++-++";
num = str1.index("+")
print(str1.index("+",num+1))
