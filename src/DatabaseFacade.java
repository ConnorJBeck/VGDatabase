import java.sql.*;

public class DatabaseFacade {

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1522:ug", "ora_s8h0b", "a57723158");

            Statement stmt = con.createStatement();

            // stmt is a statement object
            int created = stmt.executeUpdate("CREATE TABLE branch2 (" +
                    "branch_id integer not null primary key, " +
                    "branch_name varchar(20) not null, " +
                    "branch_addr varchar(50), " +
                    "branch_city varchar(20) not null, " +
                    "branch_phone integer )");

            // stmt is a statement object
            int rowCount = stmt.executeUpdate("INSERT INTO branch VALUES (20, 'Richmond Main', " +
                    "'18122 No.5 Road', 'Richmond', 5252738)");


            int branchID;
            String branchName;
            String branchAddr;
            String branchCity;
            int branchPhone;

            ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
            while(rs.next())
            {
                branchID = rs.getInt(1);
                branchName = rs.getString("branch_name");
                branchAddr = rs.getString(3);
                if (rs.wasNull()) {
                    branchAddr = "";
                }
                branchCity = rs.getString("branch_city");
                branchPhone = rs.getInt(5);
                if (rs.wasNull()) {
                    branchPhone = 0;
                }

                System.out.println(branchID + " - " + branchName + ": " + branchAddr + " " + branchCity + ", " + branchPhone);
            }

            stmt.executeUpdate("DROP TABLE branch2");

            con.close();
        } catch (SQLException err) {
            System.out.println("Error: " + err.getMessage());
            System.out.println("Error: " + err.toString());
            for (int i = 0; i < err.getStackTrace().length; i++) {
                System.out.println("Error: " + err.getStackTrace()[i]);
            }
            System.out.println("Error: " + err.getStackTrace().length);
        }


    }

}
