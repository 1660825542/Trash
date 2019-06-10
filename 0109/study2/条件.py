name = "Tom"
pwd = "123"
name1 = input("输入用户名")
pwd1 = input("输入密码")
if name == name1 and pwd == pwd1:
    print("登录成功")
    score = int(input("输入学科成绩"))
    if score>100 or score<0:
        print("成绩作废")
    elif score >= 90 :
        print("优")
    elif score >= 80:
        print("良")
    elif score >= 70:
        print("中")
    elif score >= 60:
        print("及格")
    else:
        print("不及格")
else:
    print("用户名或者密码错误")

#练习:输入一个成绩 显示优良中及格 不及格  比较数值 而不是字符
