var user = prompt("Do you choose rock, paper, or scissors?");
var computer = Math.random();
if(computer <= 0.33) {
    computer = "rock";
} else if(computer <= 0.66) {
    computer = "paper";
} else {
    computer = "scissors";
} console.log(user + " vs " + computer);
var compare = function(c1, c2) {
    if(c1 === c2) {
        console.log("Tie!");
    } else if(c1 === "rock") {
        if(c2 === "paper") {
            console.log("Computer wins!");
        } else {
            console.log("User wins!")
        }
    } else if(c1 === "paper") {
        if(c2 === "scissors") {
            console.log("Computer wins!");
        } else {
            console.log("User wins!")
        }
    } else if(c1 === "scissors") {
        if(c2 === "rock") {
            console.log("Computer wins!");
        } else {
            console.log("User wins!")
        }
    }
};
compare(user, computer);
