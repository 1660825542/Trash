class1 = []
class2 = []
class_sum = []

while True:
    print("选择录入成绩的班级1,2")
    class_num = int(input("输入班级号"))
    if class_num == 1:
        bn = 1 # 控制是否继续录入
        while bn :
            class1_score = float(input("请输入成绩"))
            class1.append(class1_score)
            bn = int(input("是否继续录入：1，是； 0，否"))
        print(class1)
        result = int(input("是否返回上一级:1，是； 0，否"))
        if result:
           continue
    elif class_num == 2:
        bn = 1  # 控制是否继续录入
        while bn:
            class2_score = float(input("请输入成绩"))
            class2.append(class2_score)
            bn = int(input("是否继续录入：1，是； 0，否"))
        print(class2)
        result = int(input("是否返回上一级:1，是； 0，否"))
        if result:
            continue
    else:
        print("请认真输入班级编号")
        continue
    break
#总大榜> 逻辑功能
class_sum = class1+class2
class_sum.sort()
print(class_sum)
print(max(class_sum))
print(min(class_sum))
print(sum(class_sum)/len(class_sum))
class_sum.remove(max(class_sum))
class_sum.pop(0)
print(sum(class_sum)/len(class_sum))


