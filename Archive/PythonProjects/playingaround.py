continput = 1

while(continput == 1):
    num1 = int(input('enter a number '))
    if(num1 % 2 == 0):
        print('its even')
    else:
        print('its odd')
    
    try:
        continput = int(input('enter a 1 if you want to continue, or any other number if you want to exit '))
    except:
        print('i said enter a number!')
        continput = 1