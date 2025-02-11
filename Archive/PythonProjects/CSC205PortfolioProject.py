list = [5, 10, 12, 13, 6, 7, 1, 3, 2, 1]

total = 0

for item in list:
    if item % 2 == 0:
        total  = total + item
    else:
        total = total - item
    print(total)

print(total)