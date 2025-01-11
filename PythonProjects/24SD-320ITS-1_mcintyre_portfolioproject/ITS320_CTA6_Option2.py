#7/19/2024
#24SD_ITS320-1
#Cameron McIntyre

def main():
    #This is my main function.
    list_a = []
    list_b = []
    final_list = []
    print("Let's get your first input list of numbers. ")
    list_a = input_calls(list_a)
    print("Ok, thanks.  Now let's get your second list of numbers. ")
    list_b = input_calls(list_b)
    final_list = cat_coord_function(list_a, list_b)
    #Boy, I had a hard time getting the output format correct.  Python wants to put commas after every list item, and the assignment had a specific output format without commas.
    print("Here are the cartesian coordinates of the two lists you entered: [", end="")
    print(*final_list, sep="", end="") 
    print("]")


def input_calls(list_c):
    #This function handles the input calls for the program.  Althought the assignment specifies no more than 10 numbers, my program can handle larger lists.
    counter = 0
    while counter < 2:
        x = int(input("Enter a coordinate.\n"))
        list.append(list_c, x)
        counter = counter + 1
    return list_c

def cat_coord_function(list_a, list_b):
    #This function will combine the two lists.
    temp_string = ''
    final_list = []
    #It uses two loops to iterate through each of the lists.
    for x in list_a:
        for y in list_b:
            temp_string = '(' + str(x) + ',' + str(y) + ')'
            list.append(final_list, temp_string)

    return final_list

main()