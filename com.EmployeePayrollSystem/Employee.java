import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Employee//employee class 
    {
        static int employeeId;
        static String employeeName;
        static double employeeSalary;
        static String employeeEmail;
        static String employeePassword;
        static String employeeDesignation;
        static String employeeDepartment;
        static String employeeJoiningDate;
        static int employeeBankAccountNumber;

        Scanner sc=new Scanner(System.in);

        void getInput()//gets input from user for login
            {
                System.out.print("\nEnter the following employee details:\n\tId: ");
                employeeId=sc.nextInt();
                System.out.print("\tE-mail: ");
                employeeEmail=sc.next();
                System.out.print("\tPassword: ");
                employeePassword=sc.next();
            }
        double[] calc(double salary,int id)//calculates salary by adding allowances and deducting provident fund,professional tax and income tax
            {
                double houseRentAllowance=0.4*salary;
                double conveyanceAllowance=0.12*salary;
                double medicalAllowance=0.08*salary;
                double otherAllowance=0.2*salary;

                double grossSalary=salary+houseRentAllowance+conveyanceAllowance+medicalAllowance+otherAllowance;

                double providentFund=0.12*salary;
                double professionalTax=0.008*salary;
                double incomeTax=0.08*salary;

                double netSalary=grossSalary-(providentFund+professionalTax+incomeTax);

                double val[]={salary,houseRentAllowance,conveyanceAllowance,medicalAllowance,otherAllowance,providentFund,professionalTax,incomeTax,grossSalary,netSalary};
                return val;
            }
        
        double[] generateSalarySlip(int id,double[] val1)// generates salary slip on the terminal
            {
                System.out.println("\n\t\t\t\t\t\t\tSalary Slip of Employee");
                System.out.println("\t\t\t\t\t\t\t***********************");             
                
                String sql="SELECT * FROM employee where emp_id=?";

                try 
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_payroll_system", "root", "root");

                        PreparedStatement ps=cn.prepareStatement(sql);
                        ps.setInt(1, id);
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

                double salary,houseRentAllowance,conveyanceAllowance,medicalAllowance,otherAllowance,providentFund,professionalTax,incomeTax,grossSalary,netSalary;
                salary=val1[0];
                houseRentAllowance=val1[1];
                conveyanceAllowance=val1[2];
                medicalAllowance=val1[3];
                otherAllowance=val1[4];
                providentFund=val1[5];
                professionalTax=val1[6];
                incomeTax=val1[7];
                grossSalary=val1[8];
                netSalary=val1[9];
                
                System.out.println("Base salary: "+salary);
                System.out.println("Additions:\n\tHouse Rent Allowance: +"+houseRentAllowance);
                System.out.println("\tConveyance Allowance: +"+conveyanceAllowance);
                System.out.println("\tMedical Allowance: +"+medicalAllowance);
                System.out.println("\tOther Allowance: +"+otherAllowance);
                System.out.println("___________________________________________________________");
                System.out.println("\t Gross Salary: "+grossSalary);
                System.out.println("Deductions:\n\tProvident Fund: -"+providentFund);
                System.out.println("\tProfessional Tax: -"+professionalTax);
                System.out.println("\tIncome Tax: -"+incomeTax);
                System.out.println("___________________________________________________________");
                System.out.println("\t Net Salary: "+netSalary);

                double val[]={houseRentAllowance,conveyanceAllowance,medicalAllowance,otherAllowance,providentFund,professionalTax,incomeTax};
                return val;
            }

        void SaveFile(int id,String name,double salary,String desg,String mail,String pass,int bankacc,String date,String dept,double house_all,double convey_all,double med_all,double other_all,double prov_fund,double prof_tax,double income_tax,double grossSalary,double netSalary)
            {
                    //creates a employeeName.txt file of the salary slip
                try 
                    {
                        String fileName =name+".txt";

                        // Create file and write data into file
                        FileWriter fw = new FileWriter(fileName);

                        fw.write("\n\t\t\t\t\t\t\tSalary Slip of Employee");
                        fw.write("\t\t\t\t\t\t\t***********************");
                        fw.write("Id: " + id);
                        fw.write("Name: " + name);
                        fw.write("Designation: " + desg);
                        fw.write("Email: " + mail);
                        fw.write("-----------------------------------------------------");
                        fw.write("Base salary: " + salary);
                        fw.write("Additions:");
                        fw.write("\tHouse Rent Allowance: +" + house_all);
                        fw.write("\tConveyance Allowance: +" + convey_all);
                        fw.write("\tMedical Allowance: +" + med_all);
                        fw.write("\tOther Allowance: +" + other_all);
                        fw.write("_____________________________________________________");
                        fw.write("\t Gross Salary: " + grossSalary);
                        fw.write("Deductions:");
                        fw.write("\tProvident Fund: -" + prov_fund);
                        fw.write("\tProfessional Tax: -" + prof_tax);
                        fw.write("\tIncome Tax: -" + income_tax);
                        fw.write("_____________________________________________________");
                        fw.write("\t Net Salary: " + netSalary);

                        fw.close();

                        System.out.println("\nSalary slip saved successfully → " + fileName);
                    } 
                catch (IOException e) 
                    {
                        System.out.println("Error while saving file: " + e.getMessage());
                    }
                System.out.println("\n\nSalary slip file generated successfully!!");
            }

        static void startEmployee()
            {
                System.out.println("\t\t\t\t\t\t\t\t\t===============");
                System.out.println("\t\t\t\t\t\t\t\t\tEmployee Portal");
                System.out.println("\t\t\t\t\t\t\t\t\t===============");

                Employee e1=new Employee();
                System.out.println("\n\t\t\t\t\t\t\t\t\t\tLogin/Sign in: ");
                e1.getInput();

                String sql="select * from employee where emp_id = ? and emp_email = ? and emp_pass = ?";
                try
                    {//connect to database
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_payroll_system", "root", "root");

                        PreparedStatement ps=cn.prepareStatement(sql);
                        ps.setInt(1, employeeId);
                        ps.setString(2, employeeEmail);
                        ps.setString(3 , employeePassword);

                        ResultSet rs=ps.executeQuery();

                        if(rs.next())//get employee values if login is successful
                            {
                                System.out.println("Login Successful!!!!");
                                employeeId=rs.getInt("emp_id");
                                employeeName=rs.getString("emp_name");
                                employeeSalary=rs.getDouble("emp_salary");
                                employeeDesignation=rs.getString("emp_desg");
                                employeeEmail=rs.getString("emp_email");
                                employeePassword=rs.getString("emp_pass");
                                employeeBankAccountNumber=rs.getInt("emp_bankacc");
                                employeeJoiningDate=rs.getString("joining_date");
                                employeeDepartment=rs.getString("emp_dept");
                            }
                        else
                            {
                                System.out.println("Invalid Id or password");
                            }
                    }
                catch(SQLException | ClassNotFoundException e)
                    {
                        System.out.println(e);
                    }   
                
                Scanner sc=new Scanner(System.in);
                System.out.println("\n\nChoose what you would like to do:\n\t1 - Generate my salary slip\n\t2 - Check if my salary slip file exists, if not create one\n\t3 - Exit");//gets user input to generate salary slip or access it
                boolean running=true;
                System.out.print("\nYour choice: ");
                double[] val=null;
                while(running)
                    {
                        int choice=sc.nextInt();
                        switch (choice) {
                            case 1:
                                val = e1.calc(employeeSalary, employeeId); // calc only returns values, no print
                                e1.generateSalarySlip(employeeId, val); // separate method prints salary slip
                                break;
                            case 2:
                                if (val == null) 
                                    {
                                        // calculate first if not done yet
                                        val = e1.calc(employeeSalary, employeeId);
                                    }
                                e1.SaveFile(employeeId, employeeName, employeeSalary, employeeDesignation, employeeEmail,
                                            employeePassword, employeeBankAccountNumber, employeeJoiningDate, employeeDepartment,
                                            val[0], val[1], val[2], val[3], val[4], val[5], val[6],val[7],val[8]);
                                break;
                            case 3:
                                System.out.println("Exiting Employee portal..........");
                                running=false;
                                break;
                            default:
                                System.out.println("Invalid Choice, enter again");
                                break;
                        }
                    }
            }
    }