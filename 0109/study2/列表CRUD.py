# 增删改查
lists = [1,2,3,4,5,2,2,2]
#添加
lists.append(6) # 在末尾添加
lists.insert(2,7) #在指定索引位置添加
#删除 从左向右删除 删除一个
# lists.remove(2)
print(lists)
lists.pop()# 删除末尾
lists.pop(0) # 索引删除
lists.pop(-1)
#索引 从左向右 0，1,2，。。。
#索引 从右向左 。。。，-2，-1
# 2重复几次
'''
    print(lists.count(2))
    for i in range(0,lists.count(2)):
    lists.remove(2)
'''
# 清空
#lists.clear()
# 删除列表
# del lists
# 获取元素在列表中的索引
print(lists.index(5))
lists[lists.index(5)]=50
print(lists)
# 翻转
lists.reverse()
lists.sort()# ASCII
print(lists)
listss1 = [1,2,3]
listss2 = [4,5,6]
print(listss1+listss2) # 合并得到一个新列表
listss1.extend(listss2) # 将列表2合并到列表1
print(listss1)
"""
作业：
班级1录入5人成绩
班级2录入3人成绩
拉大榜
排序
显示第一的成绩与倒数第一的成绩以及平均成绩
去除一个max 去除一个min 平均值
作业2：
[2,3,5,7,1,6]
从小到大
"""
