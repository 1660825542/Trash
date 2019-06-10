import shutil
import sys
import time
print(sys.getdefaultencoding())
f1 = open("abc1");
f2 = open("abc4","w",encoding="utf-8")
# shutil.copyfileobj(f1,f2) # 复制文件内容
#shutil.copy("abc1","abc3")
# shutil.copystat("abc1","abc4")
#shutil.copymode("abc1","abc4")
#压缩
#shutil.make_archive("E:/aa","zip","D:/pythonworkspace/study1")
#将D:/pythonworkspace/study1 压缩到D:下 压缩目录
#shutil.make_archive("D:/pythonworkspace","zip","D:/HBuilder_7.2.0_windows")
#D:\pythonworkspace\study1.zip
import zipfile # 把文件添加到zip中
# z=zipfile.ZipFile("D:/b.zip","w")
# z.write("bytes.py")
# z.write("D:/first.py")
# z.write("D:/HBuilder_7.2.0_windows/")
# z.close()
#解压
start = time.time()
z = zipfile.ZipFile("D:/pythonworkspace.zip","r")
z.extractall("D:/abc")
z.close()
end = time.time()
print(end-start)