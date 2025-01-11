class ryan_home:
    def __init__(self, address, ryan_community, price, house_type, bedrooms, bathrooms, garage):
        self.address = address
        self.ryan_community = ryan_community
        self.__price = price
        self.house_type = house_type
        self.bedrooms = bedrooms
        self.bathrooms = bathrooms
        self.garage = garage
        self.extra_features = []

    def add_house_extras(self, additional_extra_feature):
        self.extra_features.append(additional_extra_feature)

    def add_house_price(self, price, user_privs):
        if user_privs == True:
            self.__price = price
        else:
            print("Must have REA priviledges!")
    
    def retrieve_price(self):
        return self.__price

class ryan_community:
    def __init__(self, community, location, town, size, price_range):
        self.community = community
        self.location = location
        self.town = town
        self.size = size
        self.price_range = price_range
        self.extra_features = []

    def add_community_extras(self, additional_extra_feature):
        self.extra_features.append(additional_extra_feature)

community1 = ryan_community("Ironwood", "Richmond VA", "Petersburg", "6000", "500000")
house1 = ryan_home("10 Elm", community1, "500000", "townhome", 4, 3, True)

community1.add_community_extras("pickleball")
house1.add_house_extras("SAMdefense")