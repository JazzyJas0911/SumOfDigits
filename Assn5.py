import sys


def generatePermutations(opSet, prefix, n, k):
    global arrList
    if k == 0:
        if(prefix[0] != '@'):
            arrList.append(prefix)
        return 

    i = 0
    while i < n:
        newPrefix = prefix + opSet[i]
        generatePermutations(opSet, newPrefix, n, k - 1)
        i = i + 1

    return arrList


    

def insertNumbers(arr, digits):
    global evalExpr
    i = 0

    while i < len(arr):
        str = ""
        j = 0
        while j < len(digits):
            if j == 0:
                str = str + "0"
            str = str + (arr[i])[j]
            str = str + (digits)[j]
            j = j + 1
        str = str.replace("@", "")
        evalExpr.append(str)
        i = i + 1
    return evalExpr

    


def evaluate(s):
    total = 0

    if ('+' not in s) and ('-' not in s): 
        return int(s)

    i = len(s) - 1
    while i >= 0:
        if (s)[i] == '+' or (s)[i] == '-':
            break
        i = i - 1
    
    x = s[0: i]
    y = s[i + 1: len(s)]

    if (s)[i] == '+':
        total = evaluate(x) + evaluate(y)
    elif (s)[i] == '-':
        total = evaluate(x) - evaluate(y)
    
    return total




####### MAIN #######
opSet = ['+', '-', '@']
nums = sys.argv[1]
sum = int(sys.argv[2])
count = 0
k = len(nums)
arrList = []
evalExpr = []
        
arrList = generatePermutations(opSet, "", len(opSet), k) 
evalExpr = insertNumbers(arrList, nums)

i = 0
while i < len(evalExpr):
    total = evaluate(evalExpr[i])
    if total == sum:
        if (evalExpr[i])[1] == "+":
            print ((evalExpr[i])[2:len(evalExpr[i])], " = ", sum)
        else:
            print ((evalExpr[i])[1:len(evalExpr[i])], " = ", sum)
        count = count + 1
    i = i + 1
print ("There are ", count, " possible solutions.")