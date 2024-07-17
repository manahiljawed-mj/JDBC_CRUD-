import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabseDemo {
    public static void main(String[] a) throws Exception {
        /*
        1-Import package
        2-Load and Register Driver
        3-Create Connection
        4-Create Statement
        5-Execute Statement
        6-Close
        */
        Class.forName("org.postgresql.Driver");
        Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5433/databaseDemo","postgres","1804");
        System.out.println("Connected to PostgreSQL database");
        Statement st=conn.createStatement();
       ResultSet rs= st.executeQuery("SELECT * FROM Student");

        List<List<String>> result=new ArrayList<>();

        while(rs.next()){
             List row=new ArrayList();

             row.add(rs.getString("student_id"));
             row.add(rs.getString("student_name"));
             row.add(rs.getString("student_department"));

             result.add(row);
        }

        for (List<String> row : result) {

                System.out.println(row);

        }

        String query="Insert into Student Values(8,'Insia','CS')";

        boolean insertResult=st.execute(query);

        if(insertResult){
            System.out.println("Insertion Failed");
        }
        else
        {
            System.out.println("Inserted Successfully");

        }

        String query2="Update Student set student_name='Insia Farhan' where student_id=7";

        int rows=st.executeUpdate(query2);

        if(rows>0){
            System.out.println("Updated Successfully");
        }
        else {
            System.out.println("Updated Failed");
        }

        String query3="Delete * from Student where student_id=8";
        boolean deleteresult=st.execute(query3);

        if(deleteresult){
            System.out.println("Failed to delete student");
        }
        else
        {
            System.out.println("Deleted student");


        }

        PreparedStatement ps=conn.prepareStatement("Insert into Student values(?,?,?)");
        ps.setInt(1,8);
        ps.setString(2,"Jawed");
        ps.setString(3,"Cs");

        boolean preparedResult=ps.execute();

        if(preparedResult){

            System.out.println("Insertion Failed");

        }
        else {
            System.out.println("Inserted Successfully");

        }
        conn.close();



    }
}
