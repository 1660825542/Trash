# 列表
#列表的声明方式
nums_list1 = [59,60,70,89,97]
nums_list2 = list([94,61,72,83,96])
# 列表的长度
print(len(nums_list1))
# 索引 下标 从零开始 到长度-1
print(nums_list1[4])

# 切片: 取出局部列表
list1 = nums_list1[1:4] # [1,4)
print(nums_list1[2:])  #[2,∞）
print(nums_list1[:4])  #[0,4）
print(nums_list1[:])
print(nums_list1[0:])
print(nums_list1[0:9:2]) #取出索引是偶数的，换言之奇数项

# 遍历列表
for i in range(0,len(nums_list1)):
    print(nums_list1[i],type(nums_list1[i]))
sums = 0
for i in nums_list1:
    sums+=i
print("总成绩%d"%sums)
for index,value in enumerate(nums_list1):
    print(index,value)
# 平均值
print(sum(nums_list1)/len(nums_list1))
print(max(nums_list1))
print(min(nums_list1))
# 列表内元素 类型一定是统一的吗
nums= [1,True,1.1,[1,2,3]]
print(type(nums))
