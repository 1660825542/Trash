a = [1,2,3]
b = [1,2,3]
c = a
print(a == b)
print(id(a))
print(id(b))
print(a is c)
c[0] = 4
print(a[0])
print([1,2,3] in b) # false
print(1 in b )#True
a = [
    [1,0,0],
    [0,1,0],
    [0,0,1]
]
a[0][0] = 2
print(a)