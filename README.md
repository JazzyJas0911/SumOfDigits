# SumOfDigits

Write Java, C++, Python, and JavaScript programs (without external li- brary) that print all combinations 
that sum up to ‘sum’ by inserting the operators between digits in ‘number’.

i. The file names of the source codes should be Assn5.java, Assn5.cpp, Assn5.py and Assn5.js.

ii. The programs should read the command-line arguments passed to the program.

iii. The first argument is ‘number’ and the second argument is ‘sum’.

iv. Using integer arithmetic operators ‘+’ and ‘-’, print all combinations that sum up to ‘sum’ by inserting 
the operators between digits in ‘number’.

v. Execution commands should be:
        javac Assn5.java; java Assn5 123456789 0
        g++ Assn5.cpp -o Assn5; ./Assn5 123456789 0
        python Assn5.py 123456789 0
        node Assn5.js 123456789 0
        
vi. Expected output for the ‘number=123456789’ and ‘sum=0’. There are 22 matched ones among 2 × 38 = 13, 
122 combinations.
        1 : 1+2-34-56+78+9=0
        2 : 1-2-34+5+6+7+8+9=0
        3 : 1-23-4-56-7+89=0
        ...
        12 : -1+2+34-5-6-7-8-9=0
        13 : -1+23+4+56+7-89=0
        14 : -1-2+34+56-78-9=0
        ...
        22 : -12-34+56+7-8-9=0
