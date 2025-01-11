def main():
    
    #This is the initial housekeeping section where a user selects what they want to do.  They can do ANYTHING.
    while True:

        print('Option 1 = Is your number odd or even?')
        print('Option 2 = Print a mouse!')
        print('Option 3 = Multiply an even number by 22!')
        print('Option 4 = Turn a letter into an integer!')
        print('Option 5 = Guessing Game!')
        #print('put the new thing here!')

        selection = int(input('Please select an option.  You can do anything!: '))
        if(selection == 1):
            determineOddorEven()
        if(selection == 2):
            outputAMouse()
        if(selection == 3):
            multiplyEvenNumsBy22()
        if(selection == 4):
            alphabetLetterAsInteger()
        if(selection == 5):
            guessingGame()
        #if(selection == ):
            #function here!
        else:
            exit

        #Here I'm going to put in an End-Of-Job task to end the program if the user requests it.  It comes off my While True loop I started earlier.
        continuerequest = input('do you want to run the Anything program again?  (yes/no) ')
        if continuerequest != 'yes':
            print('exiting program')
            break

def determineOddorEven():

    #This module determines if a number is odd or even.  6/19/24
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
            return
    return

def outputAMouse():

    #This module outputs an ASCII mouse.  6/12/24
    print("         (\\-.")
    print('         /  _`>')
    print(' _)     /  _)=')
    print('(      / _/')
    print(' `-.__(___)_')
    return

def multiplyEvenNumsBy22():

    #This module multiplies only even numbers by 22.  Why?  Idk it just seemed fun.  6/19/24
    while True:

        userinput = int(input('Input a number to multiply.  It has to be even!: '))

        if(userinput % 2 == 0):
            userinput = userinput * 22
            print(userinput)
        else:
            print('I said it has to be even!')
        
        continueRequest = input('Do you want to try another number? (yes/no)')
        if continueRequest != 'yes':
            print('Back the main menu for you!')
            break

def alphabetLetterAsInteger():

    while True:

        uservalue = input('Please enter a letter.  I will give you the integer value of that letter: ')
        intvalue = ord(uservalue)

        if 65 <= intvalue <= 69:
            print("sunny")
        elif 74 <= intvalue <= 80:
            print("cloudy")
        elif 81 <= intvalue <= 88:
            print("rainy")
        else:
            print("Please enter a valid option")

        continueRequest = input('Do you want to try another letter? (yes/no) ')
        if continueRequest != 'yes':
            print('Back the main menu for you!')
            break

def guessingGame():
    import random
    z = int(input("This is a guess game!  How high of a number do you want to guess to? "))
    x = random.randint(0,z)
    print("I'm going to pick a random number between zero and one hundred.  Try to guess it!")
    y = int(input("Ok, pick a number! "))
    count = 0
    while y != x:
        if y < x:
            print("Guess bigger!")
        else:
            print("Guess smaller!")
        y = int(input("Guess again! "))
        count = count + 1
    print("You guessed the number!")
    print("It took", count, "guesses!")



main()