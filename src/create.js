const readline = require("readline");var a = [1,3,5,7,9];var b = [2,4,6,8];function pickList(string){let array = (string == "1"? a : b); console.log("Here are your numbers!");for( var i =0; i < array.length; i++){console.log("number: " + array[i])}}; function start(){const reader = readline.createInterface({input: process.stdin, output: process.stdout,}); reader.question("Do you want to see list 1 or 2? ", answer => {pickList(answer);reader.close();});} start()