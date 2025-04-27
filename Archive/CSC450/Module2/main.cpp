/* main.cpp
 * Created on: Feb 22, 2025
 * Author: Cam Mcintyre */

#include <iostream>
#include <string>
using namespace std;

bool checkSize(string str, unsigned int strSize) {
	if (str.length() < strSize) {
		return true;
	} else {
		return false;
	}
}

int main() {

	//char str1[10];  Best to not use CStrings, but standard strings.
	//char str1[10];
	string str1;
	string str2;
	unsigned int strSize = 20;

	for (int i = 0; i < 3; i++) { //use a loop to iterate three times
		cout << "Please enter your first string: ";
		getline(cin, str1); //captures any string

		if (checkSize(str1, strSize)) { //call the method above that checks to make sure the string isn't too long
			cout << "Please enter your second string: ";
			getline(cin, str2);

			if (checkSize(str2, strSize)) {
				cout << "Your strings combined: " << str1 + str2 << "\n" << endl;
			} else {
				cout << "Input failed!";
			}
		} else {
			cout << "Input failed!";
		}
	}

	return 0;
}
