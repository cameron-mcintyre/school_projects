#include <iostream>
#include <fstream>
#include <algorithm>
#include <stack>
using namespace std;

void appendToFile(){
	string inputString;
	ofstream firstFile("C:/Users/lenovo/school_projects/CSC450/Module5/CSC450_CT5_mod5.txt", ios::app);

	cout << "Please enter some text to append to this file: \n" << endl;
	getline(cin, inputString, '\n');
	inputString = "\n\n" + inputString;

	firstFile << inputString;
	cout << "Input appended to file!\n" << endl;

	firstFile.close();
}

void reverseAndOutputToFile(){
	string inputString;
	string tempString;
	string reversedString;

	ifstream firstFile("C:/Users/lenovo/school_projects/CSC450/Module5/CSC450_CT5_mod5.txt");
	ofstream secondFile("C:/Users/lenovo/school_projects/CSC450/Module5/CSC450-mod5-reverse.txt", ios::app);

	cout << "Reversing first file and storing in second file..." << endl;

	while(getline(firstFile, tempString)){
		reversedString = reversedString + tempString + "\n";
	}

	//reverse(reversedString.begin(), reversedString.end());

	//Using a stack instead of the reverse STL function:
	stack<char> reversingStack;

	for(char x : reversedString){
		reversingStack.push(x);
	}

	reversedString.clear();

	while(!reversingStack.empty()){
		reversedString.push_back(reversingStack.top());
		reversingStack.pop();
	}


	secondFile << reversedString << endl;
	secondFile.close();
	firstFile.close();
}

int main(){

	appendToFile();
	reverseAndOutputToFile();

	return 0;
}
