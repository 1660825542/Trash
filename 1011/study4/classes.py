

s= input('请输入字符串：')
alphaNum=0
numbers=0
otherNum=0
for i in s:
    if i.isalpha():
        alphaNum +=1
    elif i.isnumeric():
        numbers +=1
    else:
        otherNum +=1
print('字母=%d'%alphaNum)
print('数字=%d'%numbers)

print('其他=%d'%otherNum)
