#2018
person1 = {
    "name":"张玄烛",
    "age":21,
    "company": "中软",
    "salary":30
}
#2019
person2 = {
    "name":"张玄烛",
    "age":22,
    "company": "华为",
    "salary":25
}
# 信息同步
person1.update(person2)
print(person1)

#fromkeys 创建字典  多层慎用
b = dict.fromkeys([1,2,3],['a',{'name':'tom'}])
import  copy
d = copy.deepcopy(b)
d[2][1]["name"]="0_0"
print(type(d))
print(d)

province = {
    "辽宁省":["大连","沈阳","铁岭","鞍山","营口"],
    "吉林省":["长春","四平","吉林","公主岭","延边"],
    "云南省":["昆明","大理","西双版纳","香格里拉","腾冲"]
}
'''
for i in province:
    print(i)
citys = province.get(input("请输出查看的省份"))
if citys == None:
    print("error")
else:
    print(citys)

'''


#
for i in province:
    print(i)
citys = input("请输出查看的省份")
if citys in province.keys():
    print(province.get(citys))
else:
    print("error")
