#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <ctime>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm> 
#include <locale>

#include "List.h"
#include "LibStudent.h"
#include "LibBook.h"
#include "Date.h"

using namespace std;

#define FINE_RATE_PER_DAY 0.5

bool IsOnlyTabOrSpace(string text) {
	return (text.find_first_not_of(' ') == std::string::npos || text.find_first_not_of('\t') == std::string::npos);
}

// trim from start (in place)
static inline void ltrim(string &s) {
	s.erase(s.begin(), find_if(s.begin(), s.end(), [](unsigned char ch) {
		return !isspace(ch) || !ch == '\t';
	}));
}

// trim from end (in place)
static inline void rtrim(std::string &s) {
	s.erase(std::find_if(s.rbegin(), s.rend(), [](unsigned char ch) {
		return !isspace(ch) || !ch == '\t';
	}).base(), s.end());
}

// trim from both ends (in place)
static inline void trim(std::string &s) {
	ltrim(s);
	rtrim(s);
}

// trim from both ends (copying)
static inline std::string trim_copy(std::string s) {
	trim(s);
	return s;
}

vector<string> SplitString(string to_split, char seperator) {
	stringstream streamData(to_split);
	string value;
	vector<string> outputArray;

	while (getline(streamData, value, seperator)) {
		if (IsOnlyTabOrSpace(value)) {
			continue;
		}
		outputArray.push_back(trim_copy(value));
	}

	return outputArray;
}

tm make_tm(int year, int month, int day) {
	tm tm = { 0 };
	tm.tm_year = year - 1900;
	tm.tm_mon = month - 1;
	tm.tm_mday = day;
	return tm;
}

bool InsertBook(string filename, List *student_list) {
	ifstream file;
	file.open(filename, ios::in);
	if (!file.is_open()) {
		return false;
	}
	
	string line;
	while (!file.eof()) {
		getline(file, line);
		if (IsOnlyTabOrSpace(line)) {
			continue;
		}

		vector<string> output = SplitString(line, ' ');
		string book_record_student_id = output[0];

		for (int i = 1; i < student_list->size(); i++) {
			LibStudent student;
			student_list->get(i, student);
			if (strcmp(book_record_student_id.c_str(), student.id)) {
				continue;
			}

			for (int j = 0; j < 15; j++) {
				// If not empty, move to next one.
				if (strlen(student.book[j].title) > 1 && student.book[j].title[0] == ' ') {
					continue;
				}

				strcpy(student.book[j].title, output[2].c_str());
				strcpy(student.book[j].publisher, output[3].c_str());
				strcpy(student.book[j].ISBN, output[4].c_str());
				student.book[j].yearPublished = stoi(output[5]);
				strcpy(student.book[j].callNum, output[6].c_str());

				vector<string> borrow_date = SplitString(output[7], '/');
				student.book[j].borrow.day = stoi(borrow_date[0]);
				student.book[j].borrow.month = stoi(borrow_date[1]);
				student.book[j].borrow.year = stoi(borrow_date[2]);

				vector<string> due_date = SplitString(output[8], '/');
				student.book[j].due.day = stoi(due_date[0]);
				student.book[j].due.month = stoi(due_date[1]);
				student.book[j].due.year = stoi(due_date[2]);

				// Calculating fine
				time_t current_date_time = mktime(&make_tm(2020, 3, 29));
				time_t due_date_time = mktime(&make_tm(student.book[j].due.year, student.book[j].due.month, student.book[j].due.day));

				const int seconds_per_day = 60 * 60 * 24;
				if (current_date_time > due_date_time) {
					time_t overdue_duration = difftime(current_date_time, due_date_time) / seconds_per_day;
					student.book[j].fine = overdue_duration * FINE_RATE_PER_DAY;
				}

				student.totalbook++;
				break;
			}

			student.calculateTotalFine();
			student.print(cout);
		}
	}

	file.close();
	return true;
}

bool Display(List, int, int);
bool computeAndDisplayStatistics(List *);
bool printStuWithSameBook(List *, char *);
bool displayWarnedStudent(List *, List *, List *);

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
			if (IsOnlyTabOrSpace(line)) { // Skips lines with only whitespace
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
			}
			else {
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
		{
			InsertBook("../Assignment-1/sample-text-files/book.txt", &student_list);
			break;
		}
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
