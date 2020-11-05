
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;


/**
 * @author bacir
 *
 */
public class TestConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			
			String url = "jdbc:postgresql://localhost:5432/PubHub";
			String user = "postgres";
			String pass = "P0sT-T3n3Br@s-Lux";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			DatabaseMetaData meta = conn.getMetaData();
			
			System.out.println(meta.getDatabaseProductName());
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
