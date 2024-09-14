import javax.management.Query;
import java.sql.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";
        String pass = "1234";
//        String queryById = "select name from student where sid = 1";
        String queryGetAll = "select * from student";
//        String queryPost = "insert into student values (7, 'Jhon', 48)";
//        String queryPut = "update student set name='MAX' where sid=7";
//        String queryDelete = "delete from student where sid=7";
//        String queryPut = "update student set name='MAX' where sid=7";


        //now lets do the queryPost with preparedStatement
        Random random = new Random();
        int sid = random.nextInt(10);
        String name = "jaffer";
        int marks = random.nextInt(10);
        String queryPostPre = "insert into student values (?,?,?)";


        //this is optional
//        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url, user , pass);
        System.out.println("Connection Established");

        //execution;
        //creating referebce for Statement
        Statement st = con.createStatement();



        //also there is callableStatement for the stored Procedure to safe our query in db


        //execute
        //executeQuery return type ResultSet
        ResultSet rs = st.executeQuery(queryGetAll);
        //..next() if it finds the data it returns true if not false also check if do we have the next data

        //now i want to return the data
        //getString return the data of perticular column
        //to get one data
//        rs.next();// this checks if your pointer is before the first column it will point it towards it
//        String name = rs.getString("name");
//        System.out.println("Name of the student is" + name);
//        System.out.println(rs.next());

//        now we want to get all data
        while(rs.next()){
            System.out.print(rs.getInt(1) + "-");
            System.out.print(rs.getString(2) + "-");
            System.out.println(rs.getInt(3));
        }
        /* out put
        1-Bilal-20
        2-Ammar-20
        3-jhahnzaib-50
        4-Qurutalain-50
        */

        //now lets insert the data
        //if execute returns the ResultData like getAll or Get but when we update the data it can return anything
//        boolean postStatus = st.execute(queryPostPre);
//        System.out.print(postStatus);




        //now lets update the data
        //if execute returns the ResultData like getAll or Get but when we update the data it can return anything
//        boolean updateStatus = st.execute(queryPut);
//        System.out.print(updateStatus);


        //now lets delete record 7
//        boolean postStatus = st.execute(queryDelete);
//        System.out.print(postStatus);


        //for preparedStatement we use prepared statement to pass the parameters
        PreparedStatement prSt = con.prepareStatement(queryPostPre);
        prSt.setInt(1, sid);
        prSt.setString(2, name);
        prSt.setInt(3, marks);

        //now lets insert the data
        //if execute returns the ResultData like getAll or Get but when we update the data it can return anything
        boolean postStatus = prSt.execute();
        System.out.print(postStatus);

        con.close();
        System.out.println("Connection Closed");


    }
}