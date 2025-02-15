#include <iostream>

int main() {

	std::string firstName;
	std::string lastName;
	std::string address;
	std::string city;
	std::string zipCode;

	std::cout << "Please enter your first name: ";
	std::cin >> firstName;

	std::cout << "Please enter your last name: ";
	std::cin >> lastName;
	std::cin.ignore(1);

	std::cout << "Please enter your address: ";
	std::getline(std::cin, address);

	std::cout << "Please enter your city: ";
	std::cin >> city;

	std::cout << "Please enter your zip code: ";
	std::cin >> zipCode;

	std::cout << "You entered: " << lastName << ", " << firstName << "\n";
	std::cout << address << ", " << city << ", " << zipCode;

	return 0;
}
