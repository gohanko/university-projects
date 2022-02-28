#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>

#include "List.h"
#include "LibStudent.h"
#include "LibBook.h"

using namespace std;

bool ReadFile(string, List *);
bool DeleteRecord(List *, char *);
bool Display(List, int, int);
bool InsertBook(string, List *);
bool SearchStudent(List *, char *id, LibStudent &);
bool computeAndDisplayStatistics(List *);
bool printStuWithSameBook(List *, char *);
bool displayWarnedStudent(List *, List *, List *);
int menu();

List student_linked_list;

// Split string based on choosen separator.
vector<string> splitstring(string to_split, char seperator) {
	stringstream streamData(to_split);
	string value;
	vector<string> outputArray;

	while (getline(streamData, value, seperator)) {
		outputArray.push_back(value);
	}

	return outputArray;
}

bool ReadFile(string filename) {
	ifstream file;
	file.open(filename, ios::in);
	if (!file.is_open()) {
		return false;
	}

	if (file.fail()) {
		cout << "File not found!" << endl;
		return false;
	}

	// student.txt parser
	string line;
	int current_line = 0;
	while (file.good()) {
		getline(file, line);
		if (line.find_first_not_of(' ') == std::string::npos) { // Skips lines with only whitespace
			continue;
		}

		current_line++;
		vector<string> output = splitstring(line, '=');
		

		if (current_line == 4) {
			current_line = 0;
		}
	}

	file.close();
	return true;
}

int main() {
	ReadFile("../Assignment-1/sample-text-files/student.txt");

	cout << "\n\n";
	system("pause");
	return 0;
}




