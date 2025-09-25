Employee Payroll System

Overview
The Employee Payroll System is a Java-based console application that allows administrators to manage employee records and employees to view and generate their salary slips. The application connects to a MySQL database to store and retrieve employee data.

Features

Admin Portal
- Login using pre-defined credentials:
  - Username: Admin-1234
  - Password: Admin@1234
- Add new employee records
- View employee details by ID
- Delete employee records
- Update employee records (currently displays authorization message)

Employee Portal
- Login using employee ID, email, and password
- View employee details
- Calculate salary components:
  - Base Salary  
  - House Rent Allowance (HRA)  
  - Conveyance Allowance  
  - Medical Allowance  
  - Other Allowance  
  - Provident Fund (PF)  
  - Professional Tax (PT)  
  - Income Tax (IT)  
- Generate and print salary slip
- Save salary slip to a text file

Requirements
- Java JDK 8 or above
- MySQL Database
- MySQL Connector/J (JDBC driver)
- Console/Terminal to run the program

Database Setup

1. Create a database named employee_payroll_system.
2. Create an employee table with the following structure:

CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(100),
    emp_salary DOUBLE,
    emp_desg VARCHAR(50),
    emp_email VARCHAR(100),
    emp_pass VARCHAR(50),
    emp_bankacc INT,
    joining_date DATE,
    emp_dept VARCHAR(50)
);

3. Update MySQL username and password in the Java code if different from "root".

How to Run

1. Compile all Java files:
javac Main.java

2. Run the main program:
java Main

3. Follow the on-screen instructions to navigate between Admin and Employee portals.

Notes
- Ensure MySQL server is running before executing the program.
- Salary slip files are saved as EmployeeName.txt in the working directory.
- Employee updates require proper authorization (currently restricted).

Authors
- Disha Joshi