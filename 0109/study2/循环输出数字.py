# 1-10
#1-10之间奇数
#1-10之间偶数
i = 1
"""
while i <= 10:
    if i % 2 == 1:
        print("奇数:%d" % i)
    else:
        print("偶数:%d" % i)
    i = i + 1

"""
'''
for i in range(11):
    print(i)

'''
#[1,11)
for i in range(1,11):
    print(i)
print("_______________")
#初始值是2，每次增长2 ，到11-1结束
for i in range(1,11,2):
    print(i)
else:
    print("end%d"%i)

# 1-10 不包含3的倍数
for i in range(11):
    if(i%3==0):
        pass #{ }
    else:
        print(i)
for i in range(11):
    if i % 3 == 0:
        continue#中断当前循环 ，开始下次循环
    print(i)
for i in range(11):
    if i%3 != 0:
        print(i)