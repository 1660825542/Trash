# 字典: key唯一值 ： 若出现重复 前者key对应的值发生改变
# key 唯一   value 随意
#{key1:value1,key2:value,...}
students = {
    "name":"张奇",
    "age":27,
    "sex":"male",
    "height":"1.8",
    "name":"王齐",
    "腰围":27,
}
print(len(students))
for i,j in students.items():
    print(i,j)

print(students.items(),type(students.items()))

"""
i= input("key：")
if i in students:
    print(students.pop(i)) # pop返回这个key对应的value
else:
    print("key 不存在")
"""
students.popitem()
for i in students:
    print(i,students[i])
#查看元素的value
print(students["age"])
print(students.get("age1"))
print(students.get("age"))
# 查看所有的key
print("pwd" in students.keys())
# 查看所有的value
print(students.values())
students.update({"a":"b"})
# 替换
#students.update({"age":"18"})
students.setdefault("age1",19)
'''
update&setdefault 添加或者替换
当添加的key在原字典中不存在时，update合并  setdefault添加元素
当添加的key在原字典中存在时：
    update 就是替换 参数是字典
    setdefault 不发生替换，执行后不改变原字典
'''
print("++++++++++++++")
print(students)

