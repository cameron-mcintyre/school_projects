#7/6/2024
#24SD_ITS320-1
#Cameron McIntyre

#Assignment: create a program reads five floats and prints some statistics for the inputs.

import math

def main():
    print("Welcome to the floating point number calculator!")
    numberOfInputs = getNumberOfInputs()
    numsList = getInputs(numberOfInputs)

    sumNums = sum(numsList)
    print("The total of your numbers is:", sumNums)

    averageNums = sum(numsList) / len(numsList)
    print("The average of your numbers is:", averageNums)

    minNums = min(numsList)
    print("The minimum number is:", minNums)
    
    maxNums = max(numsList)
    print("The maximum number is:", maxNums)

    interestNums = []
    for num in numsList:
        interestNums.append(num * 1.2)
    print("Your values plus 20% interest are:", interestNums)

def getNumberOfInputs():

    default_numbers = 5
    user_numbers = int(input("Please enter a number between 1 and 10 to indicate the number of inputs you'd like to add.  Default = 5: "))
    if user_numbers < 1 or user_numbers > 10:
        numbers = default_numbers
        print("You picked a number less than 0 or greater than 10.  Using default number of 5.")
    else:
        numbers = user_numbers
        print("Your number of inputs will be", numbers, "inputs.")
        print("Let's start getting your inputs now.")
    return(numbers) 

def getInputs(x):
    
    numsList = []
    while x > 0:

        try:
            newNum = float(input("Please enter a floating point variable: "))
            numsList.append(newNum)
            x = x - 1

        except ValueError:
            print("Please enter a valid floating point number.")

    print("Your numbers were:", numsList)
    return numsList

main()
