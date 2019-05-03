var arrList = [];
var evalExpr = [];

class Assn5 {
  static generatePermutations(set, prefix, n, k) {
    if (k === 0) {
      if (prefix.charAt(0) !== "@") {
        arrList.push(prefix); //add to list
      }
      return;
    }
    for (var i = 0; i < n; ++i) {
      var newPrefix = prefix + set[i];
      Assn5.generatePermutations(set, newPrefix, n, k - 1);
    }
  }
  static insertNumbers(arr, digits) {
    for (var i = 0; i < arr.length; i++) {
      var str = "";
      for (var j = 0; j < digits.length; j++) {
        if (j === 0) {
          str = str + "0";
        }
        str = str + arr[i].charAt(j);
        str = str + digits.charAt(j);
      }
      var newChar = "";
      str = str.split("@").join(newChar); //replace @ with ""

      evalExpr.push(str); //add to list
    }
  }
  static evaluate(s) {
    var i;
    var total = 0;
    if (!s.includes("+") && !s.includes("-")) {
      return parseInt(s, 10);
    }
    for (var i = s.length - 1; i >= 0; i--) {
      if (s.charAt(i) === "+" || s.charAt(i) === "-") {
        break;
      }
    }
    var x = s.substring(0, i);
    var y = s.substring(i + 1, s.length);

    switch (s.charAt(i)) {
      case "+":
        total = Assn5.evaluate(x) + Assn5.evaluate(y);
        break;
      case "-":
        total = Assn5.evaluate(x) - Assn5.evaluate(y);
        break;
    }

    return total;
  }
}

process.argv.forEach(function(val, index, array) {
  if (index === 2) {
    nums = val;
  } else if (index === 3) {
    //assign variables to the numbers entered from the terminal
    sum = val;
  }
});
var count = 0;

var k = nums.length;
var opSet = ["+", "-", "@"];

Assn5.generatePermutations(opSet, "", opSet.length, k);
Assn5.insertNumbers(arrList, nums);

for (var i = 0; i < evalExpr.length; i++) {
  var total = Assn5.evaluate(evalExpr[i]);

  if (total == sum) {
    if (evalExpr[i].charAt(1) === "+") {
      //formatting
      console.log(evalExpr[i].substring(2) + " = " + sum);
      count++;
    } else {
      console.log(evalExpr[i].substring(1) + " = " + sum);
      count++;
    }
  }
}
console.log("There are " + count + " possible solutions.");
