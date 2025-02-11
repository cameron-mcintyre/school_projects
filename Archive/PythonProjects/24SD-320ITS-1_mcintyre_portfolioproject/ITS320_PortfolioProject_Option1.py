#8/1/2024
#24SD_ITS320-1
#Cameron McIntyre
#Portfolio Project - Option 1

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#Here's my main program.  It has a little menu for selecting options.
#The assignment instructions were to have a 1. constructor, 2. add vehicle function, 3. remove vehicle function, 4. update attributes function, 5. output to file function.
#I've got a few more functions in there for fun.

def main():
    #This is the list that will keep all my inventory objects in order.  
    #I'll use this ordering to enable updates via indicies.
    dealershipInventory  = []

    #If you remove the octothorpes here you can start with some default vehicles in the list.
    #item1 = InventoryItem(0, "Mazda", "3", "Blue", 2015, 117000, "new", 6000.00)
    #item2 = InventoryItem(1, "BMW", "Z3", "Red", 2001, 132000, "new", 4000.00)
    #item3 = InventoryItem(2, "Chevy", "Suburban", "Blue", 1968, 56000, "classic", 35000.00)
    #dealershipInventory.append(item1)
    #dealershipInventory.append(item2)
    #dealershipInventory.append(item3)

    #Loop to keep the program going until the user chooses to quit.
    while True:
        #These are the options
        print("Welcome to Cameron's Auto World inventory program!\n")
        print("Add a new vehicle: 1")
        print("Remove a vehicle (sold): 2")
        print("Remove a vehicle (scrapped): 3")
        print("Update vehicle details: 4")
        print("Display entire inventory: 5")
        print("Remove item from inventory: 6")
        print("Write inventory to file: 7")
        print("Exit inventory program: 9")
        userSelection = int(input("Please make a selection:\n"))

        if userSelection == 1:
            AddInventoryItem(dealershipInventory)

        elif userSelection == 2:
            UpdateItemSold(dealershipInventory)

        elif userSelection == 3:
            UpdateItemScrapped(dealershipInventory)

        elif userSelection == 4:
            UpdateIventoryItem(dealershipInventory)
           
        elif userSelection == 5:
            DisplayAllInventory(dealershipInventory)

        elif userSelection == 6:
            RemoveFromInventory(dealershipInventory)

        elif userSelection == 7:
            WriteToFiles(dealershipInventory)

        elif userSelection == 9:
            print("Exiting program.")
            break
        else:
            print("Please make a valid selection.")

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#This is the class that represents what all the inventory items are supposed to look like.  
#When this class is called, it will add object items to the dealershipInventory[] list.
#I don't want other parts of my program modifying the attributes of these objects outside of the class, so I'm going to make the attribute private.
#The class includes some getters and setters for aspects of the inventory that can be updated.

class InventoryItem:
    #Add global attribute
    dealership = "Cameron's Auto World"

    #Add instance attributes
    def __init__(self, index, make, model, color, year, mileage, status, price):
        self.__index = index
        self.__make = make
        self.__model = model
        self.__color = color
        self.__year = year
        self.__mileage = mileage
        self.__status = status
        self.__price = price

    #This displays all the inventory items for an object.
    def displayInventoryItem(self):
        return self.__index, self.__make, self.__model, self.__color, self.__year, self.__mileage, self.__status, self.__price
    
    #Here's the general update function.
    def updateInventoryItem(self, make, model, color, year, mileage, status, price):
        self.__make = make
        self.__model = model
        self.__color = color
        self.__year = year
        self.__mileage = mileage
        self.__status = status
        self.__price = price

    #Here's some setters and getters to be used elsewhere.
    def updateStatus(self, status):
        self.__status = status
    
    def updatePrice(self, price):
        self.__price = price

    def updateIndicies(self, index):
        self.__index = index

    def returnPrice(self):
        return self.__price
    
    def returnMake(self):
        return self.__make
    
    def returnModel(self):
        return self.__model
    
    def returnColor(self):
        return self.__color
    
    def returnYear(self):
        return self.__year
    
    def returnMileage(self):
        return self.__mileage
    

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#This function will add items to the inventory list.

def AddInventoryItem(dealershipInventory):
    
    _ = int(len(dealershipInventory))
    make = str(input("Please enter the make of vehicle. "))
    model =  str(input("Please enter the model of the vehicle. "))
    color = str(input("Please enter the color of the vehicle. ")) 
    while True:
        try:
            year = int(input("Please enter the year of the vehicle. "))
            mileage = int(input("Please enter the mileage of the vehicle. "))
            price = float(input("Enter the value of the vehicle. "))
            break
        except ValueError:
            print("Please enter these values as numbers.")
            continue
    status = "new"
    

    newInventoryItem = InventoryItem(_, make, model, color, year, mileage, status, price)
    print("\nHere's your newly added item:", newInventoryItem.displayInventoryItem())
    
    dealershipInventory.append(newInventoryItem)
    print("Here's the number of items so far:", len(dealershipInventory))

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~         
#This function updates an item.  Notably, it includes a feature to skip things you don't want to update.

def UpdateIventoryItem(dealershipInventory):

    updateSelection = int(input("Please enter the index number of the vehicle you would like to update."))
    itemSelection = dealershipInventory[updateSelection]

    makeUpdate = str(input("Please update the make of vehicle or press ENTER to keep the original entry. "))
    if makeUpdate == "":
        makeUpdate = itemSelection.returnMake()
    else:
        makeUpdate = makeUpdate

    modelUpdate =  str(input("Please update the model of the vehicle or press ENTER to keep the original entry. "))
    if modelUpdate == "":
        modelUpdate = itemSelection.returnModel()
    else:
        modelUpdate = modelUpdate

    colorUpdate = str(input("Please update the color of the vehicle or press ENTER to keep the original entry. "))
    if colorUpdate == "":
        colorUpdate = itemSelection.returnColor()
    else:
        colorUpdate = colorUpdate

    yearUpdate = input("Please update the year of the vehicle or press ENTER to keep the original entry. ")
    if yearUpdate == "":
        yearUpdate = itemSelection.returnYear()
    else:
        yearUpdate = int(yearUpdate)
    
    mileageUpdate = input("Please update the mileage of the vehicle or press ENTER to keep the original entry. ")
    if mileageUpdate == "":
        mileageUpdate = itemSelection.returnMileage()
    else:
        mileageUpdate = int(mileageUpdate)

    statusUpdated = "Updated"
    
    price = input("Update the value of the vehicle or press enter to keep the original price. ")
    if price == "":
        priceUpdated = itemSelection.returnPrice()
    else:
        priceUpdated = float(price)

    itemSelection.updateInventoryItem(makeUpdate, modelUpdate, colorUpdate, yearUpdate, mileageUpdate, statusUpdated, priceUpdated)
    print("\nHere's your updated item:", itemSelection.displayInventoryItem(), "\n")

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#This function just updates the state of a sold item and requests the sale price.

def UpdateItemSold(dealershipInventory):
    updateSelection = int(input("Please enter the index number of the vehicle you would like to update as SOLD."))
    itemSelection = dealershipInventory[updateSelection]

    status = "sold"

    price = int(input("Please enter the sale price. "))

    itemSelection.updateStatus(status)
    itemSelection.updatePrice(price)
    print("\nHere's your sold item:", itemSelection.displayInventoryItem(), "\n")

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#This function is for scrapping vehicles.  The price is set to 0 and status to "scrapped"
#It's possible a dealership may want to keep records of scrapped vehicles without removing them completely.

def UpdateItemScrapped(dealershipInventory):
    updateSelection = int(input("Please enter the index number of the vehicle you would like to update as SCRAPPED."))
    itemSelection = dealershipInventory[updateSelection]

    status = "scrapped"
    price = 0

    itemSelection.updateStatus(status)
    itemSelection.updatePrice(price)

    print("\nHere's your scrapped item:", itemSelection.displayInventoryItem(), "\n")

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#This is a basic display function that calls to the class for a given object in the inventory list.

def DisplayAllInventory(dealershipInventory):
    for item in dealershipInventory:
        print(item.displayInventoryItem())

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#Here's the removal function.  This function includes a refactoring of all the indicies.
#You have to refactor them or else you end up with messed up indicies for all the following objects.

def RemoveFromInventory(dealershipInventory):
    updateSelection = int(input("Please enter the index number of the vehicle you would like to remove from inventory.  WARNING: THIS CANNOT BE UNDONE. "))
    dealershipInventory.pop(updateSelection)

    for item in dealershipInventory:
        item.updateIndicies(dealershipInventory.index(item))

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
#This function writes to a file, as is a requirement of the assignment.

def WriteToFiles(dealershipInventory):
    inventoryFile = open('dealershipInventoryFile.txt', 'w')
    for item in dealershipInventory:
        inventoryFile.write(str(item.displayInventoryItem()))
        inventoryFile.write("\n")
    inventoryFile.close()

main()



