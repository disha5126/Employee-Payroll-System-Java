# 💼 Employee Payroll System – Java

![Status](https://img.shields.io/badge/status-ongoing-yellow)
![Type](https://img.shields.io/badge/type-java%20application-blue)
![Tech](https://img.shields.io/badge/tech-Java%20%7C%20MySQL-orange)
![Database](https://img.shields.io/badge/database-MySQL-blueviolet)

---

## 📌 Overview

The Employee Payroll System is a **Java-based console application** designed to manage employee records and automate payroll processing.

It features role-based access for administrators and employees, with integration to a MySQL database for persistent data storage.

---

## ✨ Features

### 🔐 Admin Portal

* Secure login using predefined credentials
* Add new employee records
* View employee details by ID
* Update employee information
* Delete employee records

### 👤 Employee Portal

* Login using employee ID, email, and password
* View personal and job details
* Calculate detailed salary components:

  * Base Salary
  * HRA (House Rent Allowance)
  * Conveyance Allowance
  * Medical Allowance
  * Other Allowances
  * PF (Provident Fund)
  * PT (Professional Tax)
  * IT (Income Tax)
* Generate and print salary slips
* Save salary slip as a `.txt` file

---

## 🧩 My Contribution

* Designed and implemented role-based system (Admin & Employee)
* Developed payroll calculation logic with multiple salary components
* Integrated Java application with MySQL database using JDBC
* Built structured flow for employee data management
* Implemented file handling for salary slip generation

---

## 🛠️ Tech Stack

* Java
* MySQL
* JDBC (MySQL Connector/J)
* OOP (Object-Oriented Programming)

---

## 🗄️ Database Setup

1. Create database:

```sql
CREATE DATABASE employee_payroll_system;
```

2. Create table:

```sql
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
```

3. Update MySQL credentials in the Java code if needed.

---

## 🚀 How to Run

1. Compile the program:

```bash
javac Main.java
```

2. Run the application:

```bash
java Main
```

3. Follow on-screen instructions to access:

* Admin Portal
* Employee Portal

---

## ⚠️ Requirements

* Java JDK 8 or above
* MySQL Server
* MySQL Connector/J (JDBC Driver)

---

## 📝 Notes

* Ensure MySQL server is running before execution
* Salary slips are saved as:
  `EmployeeName.txt` in the working directory

---
