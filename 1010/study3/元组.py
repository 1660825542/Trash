# 不可变的列表

nums = (1,2,3,4,5)
nums = tuple((1,2,3,4,5))
nums = 1,2,3,4,5
print(nums,type(nums))
# 单元素的元组和列表
num1 = [1]
print(type(num1))
num2 = (1,)
print(type(num2))
#元素个数
print(len(num2))
# 重复元素
# nums.count()
# 查看元素的索引
#index =  nums.index()
# 遍历
# 合并
num = num2+nums
# del num
for i in num:
    print(i)
print((1,2,3)*3)
# 切片
print(nums[:])
print(nums[2])
print(nums[2:3])
print(nums[-2])
print(nums[::2])