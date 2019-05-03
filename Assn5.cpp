#include <iostream>
#include "string"
#include <vector> 
#include <array>
#include <queue> 
#include <algorithm>
#include <iterator>
using namespace std;

vector<string> arrList;
vector<string> evalExpr;


//generates the combinations of the character array from the length of the input number
void generatePermutations(vector<char> set, string prefix, int n, int k)
{
	if (k == 0) {
		if (prefix.at(0) != '@') 
		{
			arrList.insert(arrList.end(), prefix);
		}
		return;
	}

	for (int i = 0; i < n; ++i) 
	{
		string newPrefix = prefix + set[i];
		generatePermutations(set, newPrefix, n, k - 1);
	}
}


//helper function to allow the filtering of the @ sign in the string containing both numbers and operators
void replaceHelper(string & targetString, char keyChar)
{
	targetString.erase(remove(targetString.begin(), targetString.end(), '@'), targetString.end());
}

//inserts the numbers inbetween the list of operators: from ++-@+@@++ to 1+2+3-45+67+8+9
void insertNumbers(vector<string> arr, string digits)
{
	for (size_t i = 0; i < arr.size(); i++) {
		string str = "";
		for (size_t j = 0; j < digits.length(); j++) {
			if (j == 0) {
				str = str + "0";
			}

			str = str + (arr[i]).at(j);
			str = str + (digits).at(j);
		}
		replaceHelper(str, '@');
		evalExpr.insert(evalExpr.end(), str);
	}
}

//evaluate method that takes in a string math expression and return an integer number
int evaluate(string s)
{
	int expTotal = 0; //total of the passed in string that will be returned
	bool adding = true; //bool used to determine if the evaluator is currently adding or subtracting from the total
	for (int i = 0; i < s.length(); i = i + 0){ //looping through the expression in the form 0+1+2+3-45+67+8+9
		string tempNum = ""; //temporary string used to build numbers more than one digit such as 45
		if (s.at(i) == '+'){ //checking to see if the next number to be evaluated will be added or subtracted
			adding = true;
			i++; //iterating to the next value in the list
		}
		else if(s.at(i) == '-'){ //checking if there is a minus
			adding = false;
			i++;
		}
		while (i < s.length() && s.at(i) != '+' && s.at(i) != '-'){ //beggining to build the number string until it either hits an operator or reaches the end of the string
			tempNum = tempNum + s.at(i); //building the string of numbers
			i++;
		}
		if (adding == true){ //once the string of numbers has been made such as 45, the number is either added or subtracted from the grand total depending on the bool flag
			expTotal = expTotal + stoi(tempNum);
		}
		else{
			expTotal = expTotal - stoi(tempNum);
		}
	}

	return expTotal; //once all of the numbers have been added or subtracted the total is returned and evaluated in the main
}

int main(int argc, char * argv[])
{
	//taking in the arguments from the command line 
	string nums = argv[1];
	string sum = argv[2];
	

	vector<char> opSet;
	opSet.push_back('+');
	opSet.push_back('-');
	opSet.push_back('@');


	size_t k = nums.length();
	int count = 0;

	generatePermutations(opSet, "", opSet.size(), k);
	insertNumbers(arrList, nums);

	// Evaluates a String into an Expression
	for (size_t i = 0; i < evalExpr.size(); i++)
	{
		int total = evaluate(evalExpr[i]);// gets the expression total
		if (total == stoi(sum))
		{// checks if total is equal to the sum

			if(evalExpr[i].at(1) == '+')
				cout << evalExpr[i].substr(2) << " = " << sum << endl;
			else
				cout << evalExpr[i].substr(1) << " = " << sum << endl; // output expresstion and total
			count++;// increments the number of found solutions
		}
	}
	cout << "There are " << count << " possible solutions." << endl;

}
