#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include "BST.h"


using namespace std;


BST::BST() {
	root = NULL;
	count = 0;
}


bool BST::empty() {
	if (count == 0) return true;
	return false;
}


int BST::size() {
	return count;
}


void BST::preOrderPrint() {
	if (root == NULL) return;// handle special case
	else preOrderPrint2(root);// do normal process
	cout << endl;
}


void BST::preOrderPrint2(BTNode *cur) {
	if (cur == NULL) return;
	cur->item.print(cout);
	preOrderPrint2(cur->left);
	preOrderPrint2(cur->right);
}


void BST::inOrderPrint() {
	if (root == NULL) return;// handle special case
	else inOrderPrint2(root);// do normal process
	cout << endl;
}


void BST::inOrderPrint2(BTNode *cur) {
	if (cur == NULL) return;
	inOrderPrint2(cur->left);
	cur->item.print(cout);
	inOrderPrint2(cur->right);
}


void BST::postOrderPrint() {
	if (root == NULL) return;// handle special case
	else postOrderPrint2(root);// do normal process
	cout << endl;
}


void BST::postOrderPrint2(BTNode *cur) {
	if (cur == NULL) return;
	postOrderPrint2(cur->left);
	postOrderPrint2(cur->right);
	cur->item.print(cout);
}



int BST::countNode() {
	int	counter = 0;
	if (root == NULL) return 0;
	countNode2(root, counter);
	return counter;
}


void BST::countNode2(BTNode *cur, int &count) {
	if (cur == NULL) return;
	countNode2(cur->left, count);
	countNode2(cur->right, count);
	count++;
}


bool BST::findGrandsons(type grandFather) {
	if (root == NULL) return false;
	return (fGS2(grandFather, root));
}


bool BST::fGS2(type grandFather, BTNode *cur) {
	if (cur == NULL) return false;
	//if (cur->item == grandFather) {
	if (cur->item.compare2(grandFather)){

		fGS3(cur, 0);// do another TT to find grandsons
		return true;
	}
	if (fGS2(grandFather, cur->left)) return true;
	return fGS2(grandFather, cur->right);
}


void BST::fGS3(BTNode *cur, int level) {
	if (cur == NULL) return;
	if (level == 2) {
		cur->item.print(cout);
		return;  // No need to search downward
	}
	fGS3(cur->left, level + 1);
	fGS3(cur->right, level + 1);
}



void BST::topDownLevelTraversal() {
	BTNode			*cur;
	Queue		    q;


	if (empty()) return; 	// special case
	q.enqueue(root);	// Step 1: enqueue the first node
	while (!q.empty()) { 	// Step 2: do 2 operations inside
		q.dequeue(cur);
		if (cur != NULL) {
			cur->item.print(cout);

			if (cur->left != NULL)
				q.enqueue(cur->left);

			if (cur->right != NULL)
				q.enqueue(cur->right);
		}
	}
}

//insert for BST
bool BST::insert(type newItem) {
	BTNode	*cur = new BTNode(newItem);
	if (!cur) return false;		// special case 1
	if (root == NULL) {
		root = cur;
		count++;
		return true; 			// special case 2
	}
	insert2(root, cur);			// normal
	count++;
	return true;
}


void BST::insert2(BTNode *cur, BTNode *newNode) {
	//if (cur->item > newNode->item) {
	if (cur->item.compare1(newNode->item)){
		if (cur->left == NULL)
			cur->left = newNode;
		else
			insert2(cur->left, newNode);
	}
	else {
		if (cur->right == NULL)
			cur->right = newNode;
		else
			insert2(cur->right, newNode);
	}
}



bool BST::remove(type item) {
	if (root == NULL) return false; 		// special case 1: tree is empty
	return remove2(root, root, item); 		// normal case
}

bool BST::remove2(BTNode *pre, BTNode *cur, type item) {

	// Turn back when the search reaches the end of an external path
	if (cur == NULL) return false;

	// normal case: manage to find the item to be removed
	//if (cur->item == item) {
	if (cur->item.compare2(item)){
		if (cur->left == NULL || cur->right == NULL)
			case2(pre, cur);	// case 2 and case 1: cur has less than 2 sons
		else
			case3(cur);		// case 3, cur has 2 sons
		count--;				// update the counter
		return true;
	}

	// Current node does NOT store the current item -> ask left sub-tree to check
	//if (cur->item > item)
	if (cur->item.compare1(item))
		return remove2(cur, cur->left, item);

	// Item is not in the left subtree, try the right sub-tree instead
	return remove2(cur, cur->right, item);
}


void BST::case2(BTNode *pre, BTNode *cur) {

	// special case: delete root node
	if (pre == cur) {
		if (cur->left != NULL)	// has left son?
			root = cur->left;
		else
			root = cur->right;

		free(cur);
		return;
	}

	if (pre->right == cur) {		// father is right son of grandfather? 
		if (cur->left == NULL)			// father has no left son?
			pre->right = cur->right;			// connect gfather/gson
		else
			pre->right = cur->left;
	}
	else {						// father is left son of grandfather?
		if (cur->left == NULL)			// father has no left son? 
			pre->left = cur->right;				// connect gfather/gson
		else
			pre->left = cur->left;
	}

	free(cur);					// remove item
}


void BST::case3(BTNode *cur) {
	BTNode		*is, *isFather;

	// get the IS and IS_parent of current node
	is = isFather = cur->right;
	while (is->left != NULL) {
		isFather = is;
		is = is->left;
	}

	// copy IS node into current node
	cur->item = is->item;

	// Point IS_Father (grandfather) to IS_Child (grandson)
	if (is == isFather)
		cur->right = is->right;		// case 1: There is no IS_Father    
	else
		isFather->left = is->right;	// case 2: There is IS_Father

	// remove IS Node
	free(is);
}

void BST::preOrderSearch(BTNode * current, int current_level, vector<Queue> * nodes_by_row) {
	if (current == NULL) return;
	if (current_level >= nodes_by_row->size()) {
		nodes_by_row->resize(nodes_by_row->size() + 1);
	}

	nodes_by_row->at(current_level).enqueue(current);

	current_level += 1;
	preOrderSearch(current->left, current_level, nodes_by_row);
	preOrderSearch(current->right, current_level, nodes_by_row);
}

bool BST::deepestNodes() {
	if (this->empty()) {
		return false;
	}

	vector<Queue> nodes_by_row;

	this->preOrderSearch(this->root, 0, &nodes_by_row);

	Queue deepest_row = nodes_by_row.at(nodes_by_row.size() - 1);
	for (int i = 0; i < deepest_row.size(); i++) {
		BTNode * node;
		deepest_row.dequeue(node);
		node->item.print(cout);
	}

	return true;
}

void printIncreasingOrder(BTNode *cur, ostream &output_mode) {
	if (cur == NULL) return;
	printIncreasingOrder(cur->left, output_mode);
	cur->item.print(output_mode);
	printIncreasingOrder(cur->right, output_mode);
}

void printDecreasingOrder(BTNode *cur, ostream &output_mode) {
	if (cur == NULL) return;
	printDecreasingOrder(cur->right, output_mode);
	cur->item.print(output_mode);
	printDecreasingOrder(cur->left, output_mode);
}

bool BST::display(int order, int source) {
	if (this->empty()) {
		return false;
	}

	ofstream outFile;
	outFile.open("student-info.txt");
	ostream &output_mode = (source == 1) ? cout : outFile;

	if (order == 1) {
		printIncreasingOrder(root, output_mode);
	}
	else {
		printDecreasingOrder(root, output_mode);
	}

	return true;
}

void BST::traverseAndClone(BTNode *cur, type item) {
	if (cur == NULL) return;
	if (item.compare2(cur->item)) {
		root = cur;
	}

	traverseAndClone(cur->right, item);
	traverseAndClone(cur->left, item);
}

bool BST::CloneSubtree(BST t1, type item) {
	if (t1.empty()) {
		return false;
	}
	if (!this->empty()) {
		return false;
	}

	traverseAndClone(t1.root, item);
	return true;
}

void traverseTreeAndRecordPath(BTNode *cur, type item, vector<BTNode> * ancestor_nodes) {
	if (cur == NULL) return;
	if (item.compare2(cur->item)) {
		for (int i = ancestor_nodes->size() -1; i >= 0; i--) {
			BTNode n = ancestor_nodes->at(i);
			cout << n.item.id << " ";
		}
		cout << endl;
		return;
	}

	ancestor_nodes->push_back(*cur);
	traverseTreeAndRecordPath(cur->left, item, ancestor_nodes);
	traverseTreeAndRecordPath(cur->right, item, ancestor_nodes);
	ancestor_nodes->pop_back();
}

bool BST::printAncestor(type item) {
	if (this->empty()) return false;

	if (root->item.compare2(item)) {
		cout << "There is no ancestor for this item." << endl;
		return true;
	}

	vector<BTNode> ancestor_nodes;
	traverseTreeAndRecordPath(root, item, &ancestor_nodes);

	return true;
}

void traverseTreeAndSaveNodeByRow(BTNode * cur, vector<vector<BTNode>> * nodes_by_row, int current_level) {
	if (cur == NULL) return;
	if (current_level >= nodes_by_row->size()) {
		nodes_by_row->resize(nodes_by_row->size() + 1);
	}
	nodes_by_row->at(current_level).push_back(*cur);

	current_level += 1;
	traverseTreeAndSaveNodeByRow(cur->left, nodes_by_row, current_level);
	traverseTreeAndSaveNodeByRow(cur->right, nodes_by_row, current_level);
}

bool BST::printSpiral() {
	if (this->empty()) return false;

	vector<vector<BTNode>> nodes_by_row;
	nodes_by_row.resize(nodes_by_row.size() + 1);
	traverseTreeAndSaveNodeByRow(root, &nodes_by_row, 0);

	bool toLeft = false;
	for (auto row : nodes_by_row) {

		if (toLeft) {
			for (int i = 0; i < row.size(); i++) {
				BTNode node = row.at(i);

				cout << node.item.id << " ";
			}

			toLeft = false;
		}
		else {
			for (int i = row.size() - 1; i >= 0; i--) {
				BTNode node = row.at(i);
				cout << node.item.id << " ";
			}

			toLeft = true;
		}

	}
	cout << endl;

	return true;
}