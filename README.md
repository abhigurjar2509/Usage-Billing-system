# Usage-Billing-system
Usage Billing System is a lightweight REST API built using Java's built-in HttpServer
It allows you to:

Manage gym members, 
Track equipment usage, 
Deduct free hours, 
Calculate billing amount, 
Generate JSON response

Data Structures & Algorithms (DSA) Used
HashMap (Core Data Structure)

The system uses three HashMap objects:

static Map<Integer, Member> members;
static Map<Integer, Equipment> equipments;
static Map<Integer, Usage> usages;

Why HashMap?
Provides O(1) average time complexity,
Fast data retrieval using IDs,
Efficient in-memory storage

Billing Algorithm (Logic-Based Calculation)

The billing logic:

Calculate total hours used, 
Deduct available free hours, 
Charge remaining hours based on rate, 
Generate final payable amount.

How to Run
Step 1: Compile
Open terminal inside the project folder:
javac *.java

Step 2: Run
java GymBilling

How to Test
Option 1: Browser
http://localhost:8080/bill?usageId=1

Option 2: Postman
Method: GET
URL:
http://localhost:8080/bill?usageId=1

