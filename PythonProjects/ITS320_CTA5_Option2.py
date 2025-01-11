#7/13/2024
#24SD_ITS320-1
#Cameron McIntyre

#Assignment: create a program that uses a function from the main program to manipulate three strings.

def main():

    continue_selection = 'yes'
    while continue_selection == 'yes':
        
        print("Welcome to the string concatenation program!  Let's concatenate some strings!")
        string1 = input("Please enter your first statement.  You can just type stuff, it's ok. ")
        string2 = input("Please enter your second statement. ")
        string3 = input("Please enter your third statement. ")
        
        stringConcatenator(string1, string2)
        stringReverser(string3)
        
        try:
            continue_selection = input('Do you want to go again?  (yes/no) ')
        except:
            print('You have to choose yes or no!')
            return

def stringConcatenator(input_string1, input_string2):
    output_string = input_string1 + ' ' + input_string2
    print(output_string)

def stringReverser(input_string):
    string_len = len(input_string)
    output_string = ''
    while string_len != 0:
        string_len -= 1
        x = input_string[string_len]
        output_string = output_string + x   
    print(output_string)

main()