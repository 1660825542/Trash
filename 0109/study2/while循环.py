name = "Tom"
pwd = "123"

"""
if name == name1 and pwd == pwd1:
    print("登录成功")
else:
    print("用户名或者密码错误")
"""
i = 1
while i<=3:
    name1 = input("输入用户名")
    pwd1 = input("输入密码")
    if name == name1 and pwd == pwd1:
        print("登录成功")
        break
    else:
        print("用户名或者密码错误,错误%d次"%i)
    i = i+1
else:
    print("欢迎下次使用")
