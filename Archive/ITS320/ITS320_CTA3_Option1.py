#6/28/2024
#24SD_ITS320-1
#Cameron McIntyre

#Assignment: create a program that outputs the value of a ferrari 250 GTO for a given year.

ferValue = {
    'value1':'$18,500',
    'value2':'$6,000',
    'value3':'$12,000',
    'value4':'$48,000',
    'value5':'$200,000',
    'value6':'$650,000',
    'value7':'$35,000,000',
    'value8':'$52,000,000'
}
while True:

    userInput = int(input('Enter the year of the time you want to value your Ferrari 250 GTO: '))

    if userInput >= 1962 and userInput <= 1964:
        print("The car's value for that year is " + ferValue['value1'])
    elif userInput >= 1965 and userInput <= 1968:
        print("The car's value for that year is " + ferValue['value2'])
    elif userInput >= 1969 and userInput <= 1971:
        print("The car's value for that year is " + ferValue['value3'])
    elif userInput >= 1972 and userInput <= 1975:
        print("The car's value for that year is " + ferValue['value4'])
    elif userInput >= 1976 and userInput <= 1980:
        print("The car's value for that year is " + ferValue['value5'])
    elif userInput >= 1981 and userInput <= 1985:
        print("The car's value for that year is " + ferValue['value6'])
    elif userInput >= 1986 and userInput <= 2012:
        print("The car's value for that year is " + ferValue['value7'])
    elif userInput >= 2013 and userInput <= 2014:
        print("The car's value for that year is " + ferValue['value8'])
    elif userInput >= 2015 and userInput <= 9999:
        print("We don't know the value for these more recent years.  Or you picked a year in the future.  I can't predict the future.")
    else:
        print('Invalid entry.')

    continueRequest = input('Do you want to try another year? (yes/no) ')
    
    if continueRequest != 'yes':
        print('Ciao!')
        break