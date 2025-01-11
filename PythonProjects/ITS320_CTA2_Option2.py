#6/19/2024
#24SD_ITS320-1
#Cameron McIntyre

#Get car brand, model, year, odo reading start, odo reading end, and mpg.  Store data in dictionary and output.

car = {'brand':'', 'model':'', 'year':1900, 'odoStart':0, 'odoStop':0, 'mpg':0}

car['brand'] = input("What is the car's brand? ")
car['model'] = input("What is the car's model? ")
car['year'] = int(input("What is the car's year? "))
car['odoStart'] = float(input("What is the car's odometer at the start of travel? "))
car['odoStop'] = float(input("What is the car's odometer and end of travel? "))

gallons = float(input('How many gallons of gas did you use? '))
intodoStart = car['odoStart']
intodoStop = car['odoStop']

car['mpg'] = (intodoStop - intodoStart)/gallons

print(car)
