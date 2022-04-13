#include <iostream>
#include <fstream>
#include <sstream>
#include <cstdlib>
#include <cstdio>
#include <ctime>
#include <string>
#include <vector>
#include <algorithm>
#include "BST.h"
#include "Student.h"

using namespace std;

static inline string trim(string s) {
	s.erase(s.begin(), find_if(s.begin(), s.end(), [](unsigned char ch) { return !isspace(ch) || !ch == '\t'; }));
	s.erase(find_if(s.rbegin(), s.rend(), [](unsigned char ch) { return !isspace(ch) || !ch == '\t'; }).base(), s.end());
	return s;
}

bool isLineOnlyTabOrSpace(string line) {
	return (line.find_first_not_of(' ') == string::npos || line.find_first_not_of('\t') == string::npos);
};

vector<string> splitString(string to_split, char seperator) {
	stringstream streamData(to_split);
	string value;
	vector<string> outputArray;

	while (getline(streamData, value, seperator)) {
		if (isLineOnlyTabOrSpace(value)) {
			continue;
		}

		outputArray.push_back(trim(value));
	}

	return outputArray;
}

bool readFile(const char *filename, BST *student_bst) {
	ifstream inFile;
	inFile.open(filename, ios::in);
	if (!inFile.is_open()) {
		cout << "File can't be opened!" << endl;
		return false;
	}

	while (!inFile.eof()) {
		Student student;
		string current_line;
		int current_line_count = 0;

		while (current_line_count < 7) {
			getline(inFile, current_line);
			if (isLineOnlyTabOrSpace(current_line)) {
				continue;
			}

			current_line_count++;
			vector<string> output = splitString(current_line, '=');

			if (output[0] == "StudentId") {
				student.id = stoi(output[1]);
			} else if (output[0] == "Name") {
				strcpy(student.name, output[1].c_str());
			} else if (output[0] == "Address") {
				strcpy(student.address, output[1].c_str());
			} else if (output[0] == "DOB") {
				strcpy(student.DOB, output[1].c_str());
			} else if (output[0] == "PhoneNumber") {
				strcpy(student.phone_no, output[1].c_str());
			} else if (output[0] == "Course") {
				strcpy(student.course, output[1].c_str());
			} else if (output[0] == "CGPA") {
				student.cgpa = atof(output[1].c_str());
			}
		}

		student_bst->insert(student);
	}

	cout << "Successfully read " << student_bst->size() << " record" << endl;
	return true;
}

enum MenuItem : int {
	READ_DATA_TO_BST = 1,
	PRINT_DEEPEST_NODES,
	DISPLAY_STUDENT,
	CLONE_SUBTREE,
	PRINT_ANCESTOR,
	PRINT_SPIRAL,
	EXIT
};

int menu() {
	bool isExit = false;

	BST student_bst;
	while (!isExit) {
		cout << "(1) Read data to BST" << endl;
		cout << "(2) Print deepest nodes" << endl;
		cout << "(3) Display student" << endl;
		cout << "(4) Clone Subtree" << endl;
		cout << "(5) Print Ancestor" << endl;
		cout << "(6) Print Spiral" << endl;
		cout << "(7) Exit" << endl;
		cout << "Enter your choice: ";

		int choice = 0;
		cin >> choice;

		switch (choice) {
		case MenuItem::READ_DATA_TO_BST: {
			readFile("./student.txt", &student_bst);
			break;
		}
		case MenuItem::PRINT_DEEPEST_NODES: {
			student_bst.deepestNodes();
			break;
		}
		case MenuItem::DISPLAY_STUDENT: {
			int order = 0;
			int source = 0;

			cout << "Ascending (1), Descending (2): ";
			cin >> order;

			cout << "\nScreen (1), File (2): ";
			cin >> source;

			student_bst.display(order, source);
			break;
		}
		case MenuItem::CLONE_SUBTREE: {
			BST t2;
			Student student;

			cout << "Enter the student.id from which to clone a subtree from: ";
			cin >> student.id;

			t2.CloneSubtree(student_bst, student);

			cout << "***************************************" << endl;
			cout << "Original Tree: " << endl;
			student_bst.preOrderPrint();

			cout << "***************************************" << endl;
			cout << "Clone Subtree: " << endl;
			t2.preOrderPrint();
			break;
		}
		case MenuItem::PRINT_ANCESTOR: {
			Student student;

			cout << "Enter the student.id that you want to find the ancestor of: ";
			cin >> student.id;

			student_bst.printAncestor(student);
			break;
		}
		case MenuItem::PRINT_SPIRAL: {
			student_bst.printSpiral();
			break;
		}
		case MenuItem::EXIT: {
			isExit = true;
			break;
		}
		default:
			break;
		}
		
		cout << endl;
		cout << "***********************************************************************" << endl;
	}

	return 0;
}

int main() {
	menu();
	return 0;
}
