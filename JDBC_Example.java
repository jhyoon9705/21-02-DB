import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Example {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/parking_lot_sample?useUnicode=true&useJDBCCompliantTimezoneShift=true&"
					+ "useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1676!@#$");
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select * from parkingLot");

			// Print the result set of "select * from parkingLot"
			System.out.println("===== Before dynamic insert =====");
			while (rset.next()) {
				System.out.println(rset.getInt(1) + "  " + rset.getString(2) + "  " + rset.getString(3) + "  " + rset.getInt(4) + "  " + rset.getString(5) + "  " + rset.getString(6));
			}
			
			System.out.println("\n");
			
			// dynamic insert
			String sql= "insert into parkingLot values (?,?,?,?,?,?)";
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, 7);
			pstmt.setString(2, "Happy Building");
			pstmt.setString(3, "daebang-dong");
			pstmt.setInt(4, 3500);
			pstmt.setString(5, "05AM");
			pstmt.setString(6, "00AM");
			pstmt.executeUpdate();
				
			ResultSet rset2 = stmt.executeQuery("select * from parkingLot");

			// Print the result set of "select * from parkingLot" after dynamic insert
			System.out.println("===== After dynamic insert =====");
			while (rset2.next()) {
				System.out.println(rset2.getInt(1) + "  " + rset2.getString(2) + "  " + rset2.getString(3) + "  " + rset2.getInt(4) + "  " + rset2.getString(5) + "  " + rset2.getString(6));
			}
		
			stmt.close();
			conn.close();
			
			
		}
		catch(SQLException sqle) {
			System.out.println("SQLException : "+sqle);
		}
		
		
	}
}
