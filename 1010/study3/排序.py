a  = [2,3,5,7,1,6]
# 找列表内最大值
max = a[0]
for i in range(0,5):
    if max<a[i+1]:
        max = a[i+1]
        print("max:%d" % max)
print(max)
# 冒泡
b = 0
for i in range(0,5):
    for i in range(0,5):
        if a[i]>a[i+1]:
            b = a[i+1]
            a[i+1] = a[i]
            a[i] = b
    print(a)
print(a)
# sort  快速排序