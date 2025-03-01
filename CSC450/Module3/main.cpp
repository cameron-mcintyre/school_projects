/* main.cpp
 *Created on: Mar 1, 2025
 *Author: mcintyre*/

#include <iostream>
using namespace std;

int main() {

	int* num1; //create pointer
	num1 = new int; //allocate heap memory
	int* num2; //create pointer
	num2 = new int; //allocate heap memory
	int* num3; //create pointer
	num3 = new int; //allocate heap memory

	cout << "Welcome to the Pointer Program!" << endl;

	cout << "Please enter an integer: " << endl;
	cin >> *num1; //put the user number into the heap location this pointer points toward
	cout << "Please enter an integer: " << endl;
	cin >> *num2; //put the user number into the heap location this pointer points toward
	cout << "Please enter an integer: " << endl;
	cin >> *num3; //put the user number into the heap location this pointer points toward

	cout << "First value: " << *num1 << ": " << num1 << endl;
	cout << "Second value: " << *num2 << ": " << num2 << endl;
	cout << "Third value: " << *num3 << ": " << num3 << endl;

	delete num1; //close the heap memory
	delete num2; //close the heap memory
	delete num3; //close the heap memory
	num1 = nullptr; //make the pointer point nowhere so it doesn't access some random heap stuff
	num2 = nullptr; //make the pointer point nowhere so it doesn't access some random heap stuff
	num3 = nullptr; //make the pointer point nowhere so it doesn't access some random heap stuff
	return 0;
}





