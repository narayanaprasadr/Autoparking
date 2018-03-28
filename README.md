## Automatic Parking system Problem
The purpose of this exercise is to demonstrate TDD and come up with a solution to the problem
given below. Working unit tests will be sufficient as a solution, but you may provide a simple user
interface to demonstrate your codes capability if you desire.

Cars are placed on a 15 by 15 grid at particular co-ordinates heading north, and the simple
commands Left, right and forward are transmitted to them. The commands must be executed and
the final position calculated.

The following examples should be used to demonstrate your code:

For the input "5,5:RFLFRFLF"
We should get the position  "7,7"

For the input "6,6:FFLFFLFFLFF"
We should get the position  "6,6"

For the input "5,5:FLFLFFRFFF"
We should get the position  "4,1"

### How to execute
This is developed using Spring Boot. So clone it in eclipse build a jar and execute.

Open a brawser and type the following URL

http://localhost:8080/automatedParking/park/{x,y:sequence}

example

http://localhost:8080/automatedParking/park/5,5:FLFLFFRFFF
