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

// Trim whitespaces left and right of string.
string trim(const string& str)
{
	size_t first = str.find_first_not_of(' ');
	if (string::npos == first)
	{
		return str;
	}
	size_t last = str.find_last_not_of(' ');
	return str.substr(first, (last - first + 1));
}

// Split string based on choosen separator.
vector<string> splitstring(string to_split, char seperator) {
	stringstream streamData(to_split);
	string value;
	vector<string> outputArray;

	while (getline(streamData, value, seperator)) {
		outputArray.push_back(trim(value));
	}

	return outputArray;
}

bool isDuplicate(LibStudent &student) {
	int student_list_size = student_linked_list.size();
	if (student_list_size == 0) {
		return false;
	}

	for (int i = 0; i <= student_list_size; i++) {
		LibStudent temp;
		student_linked_list.get(i, temp);
		if (temp.compareName2(student)) {
			return true;
		}
	}

	return false;
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
	// Since the parser parse based on number of lines (every 4 line is one record), it'll
	// break if the file ununiform lines of data.
	string line;
	while (!file.eof()) {
		int current_line = 0;
		LibStudent student;

		while (current_line < 4) {
			getline(file, line);
			if (line.find_first_not_of(' ') == std::string::npos) { // Skips lines with only whitespace
				continue;
			}

			current_line++;
			vector<string> output = splitstring(line, '=');
			
			if (output[0] == "Student Id") {
				strcpy(student.id, output[1].c_str());
			}
			else if (output[0] == "Name") {
				strcpy(student.name, output[1].c_str());
			}
			else if (output[0] == "course") {
				strcpy(student.course, output[1].c_str());
			}
			else if (output[0] == "Phone Number") {
				strcpy(student.phone_no, output[1].c_str());
			}
		}
		
		if (!isDuplicate(student)) {
			student_linked_list.insert(student);
		}
	}

	file.close();
	return true;
}

enum MenuItem : int {
	READ_FILE = 1,
	DELETE_RECORD,
	SEARCH_STUDENT,
	INSERT_BOOK,
	DISPLAY_OUTPUT,
	COMPUTE_AND_DISPLAY_STATISTICS,
	STUDENT_WITH_SAME_BOOK,
	DISPLAY_WARNED_STUDENT,
	EXIT
};

int menu() {
	int menu_choice = 0;

	// Main loop
	while (true) {
		cout << "Menu\n" << endl;
		cout << "1. Read file" << endl;
		cout << "2. Delete record" << endl;
		cout << "3. Search student" << endl;
		cout << "4. Insert book" << endl;
		cout << "5. Display output" << endl;
		cout << "6. Computer and Display Statistics" << endl;
		cout << "7. Student with Same Book" << endl;
		cout << "8. Display Warned Student" << endl;
		cout << "9. Exit" << endl;
		cout << "Enter your choice: ";
		cin >> menu_choice;

		switch (menu_choice) {
		case MenuItem::READ_FILE:
			ReadFile("../Assignment-1/sample-text-files/student.txt");
			cout << "\nREAD FILE\n" << endl;
			cout << student_linked_list.size() << " records have been successfully read.\n" << endl;
			break;
		case MenuItem::DELETE_RECORD:
			break;
		case MenuItem::SEARCH_STUDENT:
			break;
		case MenuItem::INSERT_BOOK:
			break;
		case MenuItem::DISPLAY_OUTPUT:
			break;
		case MenuItem::COMPUTE_AND_DISPLAY_STATISTICS:
			break;
		case MenuItem::STUDENT_WITH_SAME_BOOK:
			break;
		case MenuItem::DISPLAY_WARNED_STUDENT:
			break;
		case MenuItem::EXIT:
			break;
		}
	}
}

int main() {
	menu();

	cout << "\n\n";
	system("pause");
	return 0;
}




