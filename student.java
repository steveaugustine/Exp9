import java.sql.*;
import java.util.*;
public class student {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data?autoReconnect=true&useSSL=false", "root", "root");
            Statement stmt = con.createStatement();
            System.out.print("Enter How Many Student : ");
            int n = sc.nextInt();
            int rollno;
            String name;
            float CGPA;
            PreparedStatement ps = con.prepareStatement("insert into studentCGPA values(?,?,?)");
            for (int i = 0; i < n; i++) {
                System.out.println("\n\tDetails of Student "+(i+1));
                System.out.print("\nEnter Roll No: ");
                rollno = sc.nextInt();
                System.out.print("Enter Name: ");
                name = sc.next();
                System.out.print("Enter CGPA: ");
                CGPA = sc.nextFloat();
                ps.setInt(1, rollno);
                ps.setString(2, name);
                ps.setFloat(3, CGPA);
                ps.executeUpdate();
            }



            ResultSet rs = stmt.executeQuery("select * from studentCGPA where CGPA > 7;");
            System.out.println("\n \t Students who have CGPA greater than 7 ");
            while (rs.next())
                System.out.println("Roll no : "+rs.getInt(1) + " \t Name : " + rs.getString(2) + "\t CGPA : " + rs.getFloat(3));
            System.out.println("Click 1 to clear the table or else click 0 to continue ");
            int ch = sc.nextInt();
            if(ch == 1)
                stmt.executeUpdate("truncate studentCGPA");
            sc.close();

        } catch (Exception e) {
            System.out.println(e);
        } 
      
    }

} 
/*  
Enter How Many Student : 2

        Details of Student 1

Enter Roll No: 7
Enter Name: jon
Enter CGPA: 9

        Details of Student 2

Enter Roll No: 19
Enter Name: Sahoo
Enter CGPA: 6

         Students who have CGPA greater than 7
Roll no : 101    Name : Akku     CGPA : 9.0
Click 1 to clear the table or else click 0 to continue
0
*/
