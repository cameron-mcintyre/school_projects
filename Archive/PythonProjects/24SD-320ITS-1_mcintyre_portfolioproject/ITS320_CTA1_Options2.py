#6/15/2024
#24SD_ITS320-1
#Cameron McIntyre

#Assignment: Read two ints and do int div, float div, and mod div.

num1 = input("Enter your first number: ")
num2 = input("Enter your second number: ")

intdivnum = int(num1)//int(num2)
floatdivnum = float(num1)/float(num2)
moddivnum = float(num1)%float(num2)

print('\n')
print(f'The integer division result is: {intdivnum}')
print(f'The float division result is: {floatdivnum}')
print(f'The modulo result is: {moddivnum}')

