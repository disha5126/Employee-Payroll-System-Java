import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main 
    {
        public static void main(String[] args) 
            {
                Main m1=new Main();
                System.out.println("\t\t\t\t\t\t\t==================================");
                System.out.println("\t\t\t\t\t\t\tWelcome to Employee Payroll System");
                System.out.println("\t\t\t\t\t\t\t==================================");

                System.out.println("\nSelect who is accesing the application:-\nEnter:\n\t1 - Admin\n\t2 - Employee\n\t3 - Exit");

                Scanner sc=new Scanner(System.in);
                boolean running=true;
                System.out.print("\nYour choice: ");//check who is using the employee payroll system
                while(running)
                    {
                        int choice=sc.nextInt();
                        switch (choice) {
                            case 1:
                                m1.AdminLogin();
                                break;
                            case 2:
                                Employee.startEmployee();;
                                break;
                            case 3:
                                running=false;
                                break;
                            default:
                                System.out.println("Invalid Choice, enter again");
                                break;
                        }
                    }
            }  
        void AdminLogin()//if admin is using the employee payroll system, they are required to login
            {
                String adminUsername="Admin-1234";
                String adminPassword="Admin@1234";
                Main m2=new Main();

                boolean running=true;
                while(running)
                    {
                        System.out.println("\t\t\t\t\t\t\t\t=====================");
                        System.out.println("\t\t\t\t\t\t\t\tAdministration Portal");
                        System.out.println("\t\t\t\t\t\t\t\t=====================");
                        Scanner sc=new Scanner(System.in);
                        System.out.print("\n\nEnter following details to login:\n\tUsername: ");
                        String getUsername=sc.next();
                        System.out.print("\tPassword: ");
                        String getPassword=sc.next();

                        if(adminUsername.equals(getUsername) & adminPassword.equals(getPassword))//asking what functions to conduct from CRUD(create,update,read,delete) if login is successful
                            {
                                System.out.println("\t\t\t\t\t\t\t\tLogin Successful!!");
                                System.out.println("\t\t\t\t\t\t\t\t******************");
                                Scanner sc1=new Scanner(System.in);
                                boolean running2=true;

                                System.out.println("\nEnter what you would like to do with employee data:\n1 - Add Employee\n2 - Update Employee data\n3 - Get Employee data\n4 - Delete Employee\n5 - Save and exit");
                                System.out.print("\nYour choice: ");
                                while(running2)
                                    {
                                        int choice2=sc.nextInt();
                                        switch (choice2) {
                                            case 1:
                                                m2.AddEmployee();
                                                break;
                                            case 2:
                                                m2.UpdateEmployeeData();
                                                break;
                                            case 3:
                                                m2.GetEmployeeData();
                                                break;
                                            case 4: 
                                                m2.DeleteEmployee();
                                                break;
                                            case 5:
                                                System.out.println("Employee functions done successfully!!!🥳🥳🥳");
                                                running2=false;
                                                break;
                                            default:
                                                System.out.println("Invalid Choice, enter again");
                                                break;
                                        }
                                    }
                                    running=false;
                            }
                        else
                            {
                                System.out.println("Invalid username or password, enter again");
                            }
                    }
            }
        void AddEmployee()// adds new employee
            {
                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");

                int employeeId;
                String employeeName;
                String employeeEmail;
                String employeePassword;
                String employeeDesignation;
                String employeeDepartment;
                String employeeJoiningDate;
                int employeeBankAccountNumber;
                double employeeSalary;

                Scanner sc=new Scanner(System.in);
                System.out.println("\nEnter new employee's following details:\n\tId: ");
                employeeId=sc.nextInt();
                System.out.println("\tName: ");
                employeeName=sc.next();
                System.out.println("\tSalary: ");
                employeeSalary=sc.nextDouble();
                System.out.println("\tDesignation: ");
                employeeDesignation=sc.next();
                System.out.println("\tE-mail: ");
                employeeEmail=sc.next();
                System.out.println("\tPassword: ");
                employeePassword=sc.next();
                System.out.println("\tBank account number: ");
                employeeBankAccountNumber=sc.nextInt();
                System.out.println("\tJoining Date(YYYY-MM-DD):");
                employeeJoiningDate=sc.next();
                System.out.println("\tDepartment: ");
                employeeDepartment=sc.next();

                String sqlInsert="INSERT INTO employee (emp_name, emp_salary, emp_desg, emp_email, emp_pass, emp_bankacc, joining_date, emp_dept) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                try
                    {//connect to database
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_payroll_system", "root", "root");

                        PreparedStatement ps=cn.prepareStatement(sqlInsert);
                        ps.setString(1, employeeName);
                        ps.setDouble(2, employeeSalary);
                        ps.setString(3, employeeDesignation);
                        ps.setString(4, employeeEmail);
                        ps.setString(5, employeePassword);
                        ps.setInt(6, employeeBankAccountNumber);
                        ps.setString(7, employeeJoiningDate);
                        ps.setString(8, employeeDepartment);
                    
                        ps.executeUpdate();

                        System.out.println("Employee added successfully!!!🥳🥳🥳");
                    }
                catch(SQLException | ClassNotFoundException e)
                    {
                        System.out.println(e);
                    } 
                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");
            }
        void UpdateEmployeeData()//updates employee data only if authorised by employee, currently restricted
            {
                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");
                System.out.println("\n\t\t\t\tEmployee Data cannot be changed without respective employee authorisation.");
                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");

            }
        void GetEmployeeData()//reads employee details of a single employee at a time
            {
                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");

                int employeeId;
                Scanner sc=new Scanner(System.in);
                System.out.println("\nEnter employee ID of the respective employee to get data: ");
                employeeId=sc.nextInt();

                String sqlFetch="SELECT * FROM employee where emp_id=?;";

                try 
                    {//connect to database
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_payroll_system", "root", "root");

                        PreparedStatement ps=cn.prepareStatement(sqlFetch);
                        ps.setInt(1, employeeId);
                        ResultSet rs=ps.executeQuery();

                        if (rs.next()) 
                            {
                                System.out.println("Employee details are as follows:");
                                System.out.println("\tId: " + rs.getInt("emp_id"));
                                System.out.println("\tName: " + rs.getString("emp_name"));
                                System.out.println("\tSalary: " + rs.getDouble("emp_salary"));
                                System.out.println("\tDesignation: " + rs.getString("emp_desg"));
                                System.out.println("\tEmail: " + rs.getString("emp_email"));
                                System.out.println("\tPassword: " + rs.getString("emp_pass"));
                                System.out.println("\tBank account number: " + rs.getInt("emp_bankacc"));
                                System.out.println("\tJoining Date: " + rs.getString("joining_date"));
                                System.out.println("\tDepartment: " + rs.getString("emp_dept"));
                            } 
                        else 
                            {
                                System.out.println("No employee found with ID: " + employeeId);
                            }
                    } 
                catch (Exception e) 
                    {
                        System.out.println(e);
                    }
                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");
            }
        void DeleteEmployee()//removes employee
            {

                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");

                int employeeId;
                Scanner sc=new Scanner(System.in);
                System.out.println("\nEnter employee ID of the respective employee to delete: ");
                employeeId=sc.nextInt();


                String sqlDelete="DELETE FROM employee where emp_id="+employeeId+";";

                try 
                    {//connect to database
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_payroll_system", "root", "root");

                        PreparedStatement ps=cn.prepareStatement(sqlDelete);
                        ps.executeUpdate();
                    } 
                catch (Exception e) 
                    {
                        System.out.println(e);
                    }
                System.out.println("\n\t\t\t---------------------------------------------------------------------------------------\n");
            }
    }
