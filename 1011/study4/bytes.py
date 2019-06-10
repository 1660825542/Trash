# -*-encoding=utf-8-*-
lines = "颠三倒四"
# lines ="1"
encoding = "utf-8"
encoding = "gbk"
encoding = "gb2312"
encoding = "gb18030"
# encoding = "ascii"
# encoding = "ISO-8859-1"
line = lines.encode(encoding)
print(line.decode("gb18030","编码不一致"))