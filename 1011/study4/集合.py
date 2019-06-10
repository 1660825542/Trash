# 集合 set 无序 无index 无key 无元素放置的先后顺序
# 不重复： 元素唯一
# 有顺序:
sets = set((1,2,3,4))
#sets = set([1,2,3,4])

print(sets,type(sets))
list1 = [2,3,4,5]
list2 = [3,4,1]
list3 = []
# 找出列表中相同元素
for i in list1:
    if i in list2:
        list3.append(i)
else:
    print(list3)
set1 = set(list1)
set2 = set(list2)
# 交集intersection ; &
#print(set1.intersection(set2))
# print(type(list1))
# list1[0]=3
# print(set1)
print(set1&set2)
# 并集 | ; union
# print(set1|set2)
print(set1.union(set2))
# 差集 - difference
print(set1.difference(set2))
print(set1-set2)
print(set2-set1)
print((set1|set2)-(set1&set2))
# 子集
set3 = set([3])
#print(set1.issubset(set3))
print(set3.issubset(set1)) # set3 是不是 set1的子集
print(set1.issuperset(set3)) # set1 是不是set3的父集
# 空集
print(set([]))
# 反向差集：set1与set2的并集 - 交集 》 （set1-set2）|（set2-set1）
print(set1.symmetric_difference(set2)) # 2345  341
set5 = set([1,3,5,2,4,2])
set5.add(1)
#删除
set5.remove(5)
# 删除上层
set5.pop()
# 删除
set5.discard(3)
print(set5)
#
print(set1,set2)
set2.pop()
set2 = set([5])
set1 = set([3])
print(set1.isdisjoint(set2)) # False : 有交集  有重复元素
#True ： 没有交集  无重复元素