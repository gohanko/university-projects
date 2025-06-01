#include <iostream>
#include <cstring>
#include <string>
#include <fstream>

using namespace std;

struct Person {				  // define a person type
    char name[20], phone[20]; // user name and phone
    double money;			  // money in bank
};

int main() {
	char f[10] = {'0', 'b', '2', 'd', '4', 'e', '6', 'f', '8', 'g'};
	float b[34];
	double ar[2][2];
	ifstream in;
	ofstream out;
	char s[256];
	Person p;
	int i, j;

	cout << "--------------------------------(a)-------------------------------------\n";
	cout << f[6] << endl;

	cout << "--------------------------------(b)-------------------------------------\n";
	cout << "Please input something: ";
	cin >> b[7];

	cout << "--------------------------------(c)-------------------------------------\n";
	out.open("output.txt");
	
	if (!out) {
		cout << "Cannot open file!" << endl;
	}
	else {
		cin.clear();
		out << f[6];
		out.close();
	}

	cout << "--------------------------------(d)-------------------------------------\n";
	string filename;
	cout << "Please input a file name! ";
	cin >> filename;
	out.open(filename);

	if (!out) {
		cout << "Cannot open file!" << endl;
	}
	else {
		out.close();
	}

	cout << "--------------------------------(e)-------------------------------------\n";
	in.open("InputFiles/input.txt");

	if (!in) {
		cout << "Cannot open file" << endl;
	}
	else {
		for (i = 0; in >> b[i]; i++);

		cout << "Displaying the content of the array: " << endl;
		for (j = 0; j < i; j++) {
			cout << b[j] << endl;
		}

	}

	cout << "--------------------------------(f)-------------------------------------\n";
	in.open("InputFiles/matrix1.txt");
	if (!in) {
		cout << "Cannot open file!" << endl;
	}
	else {
		for (int i = 0; i <= 6; i++) {
			in >> s;
		}

		for (int a = 0; a < 2; a++) {
			for (int b = 0; b < 2; b++) {
				int data;
				in >> data;
				ar[a][b] = data;
				cout << data << endl;
			}
		}
	}

	cout << "--------------------------------(h)-------------------------------------\n";
	for (i = 0; cin >> b[i]; i++);

	cout << "Content in array b :" << endl;
	for (j = 0; j < i; j++) {
		cout << b[j] << endl;
	}

	cout << "--------------------------------(i)-------------------------------------\n";
	in.open("InputFiles/customers.txt");

	if (!in) {
		cout << "Not opened successfully" << endl;
	}
	else {
		for (int i = 0; i <= 5; i++) {
			in >> s;
		}

		in.getline(p.name, 20);

		cout << "Name: " << p.name << endl;
		in.close();
	}

	system("pause");
	return 0;	
}