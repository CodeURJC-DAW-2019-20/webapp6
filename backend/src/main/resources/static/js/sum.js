function sum(){
   var num1 = parseFloat(document.getElementById("total").value);
   var num2 = parseFloat(document.getElementById("tax").value);
   var n = num1 + num2;
   var sum = n.toFixed(2) + "€";

   document.getElementById("sum").innerHTML = sum;
}