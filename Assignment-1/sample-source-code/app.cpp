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

bool Display(List, int, int);
bool InsertBook(string, List *);
bool computeAndDisplayStatistics(List *);
bool printStuWithSameBook(List *, char *);
bool displayWarnedStudent(List *, List *, List *);

string TrimString(const string& str) {
	size_t first = str.find_first_not_of(' ');
	if (string::npos == first) {
		return str;
	}

	size_t last = str.find_last_not_of(' ');
	return str.substr(first, (last - first + 1));
}

vector<string> SplitString(string to_split, char seperator) {
	stringstream streamData(to_split);
	string value;
	vector<string> outputArray;

	while (getline(streamData, value, seperator)) {
		outputArray.push_back(TrimString(value));
	}

	return outputArray;
}

bool IsStudentDuplicate(List *student_list, LibStudent &student) {
	for (int i = 1; i <= student_list->size(); i++) {
		LibStudent temp;
		student_list->get(i, temp);
		if (temp.compareName2(student)) {
			return true;
		}
	}

	return false;
}

bool ReadFile(string filename, List *student_list) {
	ifstream file;
	file.open(filename, ios::in);
	if (!file.is_open()) {
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
			vector<string> output = SplitString(line, '=');
			
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
		
		if (!IsStudentDuplicate(student_list, student)) {
			student_list->insert(student);
		}
	}

	file.close();
	return true;
}

bool DeleteRecord(List *student_list, char *id) {
	for (int i = 1; i <= student_list->size(); i++) {
		LibStudent student;
		student_list->get(i, student);

		if (strcmp(student.id, id) == 0) {
			student_list->remove(i);
			return true;
		}
	}

	return false;
}

bool SearchStudent(List *student_list, char *id, LibStudent &student) {
	for (int i = 1; i <= student_list->size(); i++) {
		LibStudent temp;
		student_list->get(i, temp);
		
		if (strcmp(temp.id, id) == 0) {
			student = temp;
			return true;
		}
	}

	return false;
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

void menu() {
	int menu_choice = 0;
	List student_list;
	bool should_exit = false;

	while (!should_exit) {
		cout << "******************************************************" << endl;
		cout << "MENU\n";
		cout << "----" << endl;
		cout << "1. Read file" << endl;
		cout << "2. Delete record" << endl;
		cout << "3. Search student" << endl;
		cout << "4. Insert book" << endl;
		cout << "5. Display output" << endl;
		cout << "6. Computer and Display Statistics" << endl;
		cout << "7. Student with Same Book" << endl;
		cout << "8. Display Warned Student" << endl;
		cout << "9. Exit" << endl;
		cout << "\nEnter your choice: ";
		cin >> menu_choice;
		cout << "______________________________________________________" << endl;
		cout << "RESULTS:" << endl;

		switch (menu_choice) {
		case MenuItem::READ_FILE:
			ReadFile("../Assignment-1/sample-text-files/student.txt", &student_list);
			cout << "\nSelected READ FILE" << endl;
			cout << student_list.size() << " records have been successfully read.\n";
			break;
		case MenuItem::DELETE_RECORD:
		{
			char student_id[10];
			cout << "\nSeleted DELETE FILE" << endl;
			cout << "Enter Student ID to delete: ";
			cin >> student_id;

			bool is_deleted = DeleteRecord(&student_list, student_id);
			if (is_deleted) {
				cout << "Record deleted. " << student_list.size() << " records remaining" << endl;
			} else {
				cout << "No records deleted" << endl;
			}

			break;
		}
		case MenuItem::SEARCH_STUDENT:
		{
			LibStudent student;
			char student_id[10];

			cout << "\nEnter Student ID to search: ";
			cin >> student_id;

			if (SearchStudent(&student_list, student_id, student)) {
				student.print(cout);
			}
			else {
				cout << "Student not found!" << endl;
			}
			break;
		}
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
			should_exit = true;
			break;
		}

		cout << "______________________________________________________\n" << endl;
	}
}

void main() {
	menu();
}
