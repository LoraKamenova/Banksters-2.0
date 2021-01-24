var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]; // Store month names in array
var d = new Date();
d.setDate(d.getDate() - 1);
var dateStr = d.getDate() + ' ' + monthNames[d.getMonth()] + ' ' + d.getFullYear();
let div = document.getElementById("dateDiv");
div.innerText = `Stocks closing prices for ${dateStr}:`;

let div2 = document.getElementById("dateDiv2");
div2.innerText = `Market Indexes closing values for ${dateStr}:`;