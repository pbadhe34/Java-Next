import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RunJoinBlock {

	//create database data;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/appdata","root", "MyRootPass123");
		 
		
		String joinQuery= "select \r\n" + 
				"  r.rep_name,\r\n" + 
				"  sum(case when p.prod_name = 'Shoes' then s.quantity else 0 end) as Shoes,\r\n" + 
				"  sum(case when p.prod_name = 'Pants' then s.quantity else 0 end) as Pants,\r\n" + 
				"  sum(case when p.prod_name = 'Shirt' then s.quantity else 0 end) as Shirt\r\n" + 
				"from reps r\r\n" + 
				"inner join sales s\r\n" + 
				"  on r.rep_id = s.rep_id\r\n" + 
				"inner join products p\r\n" + 
				"  on s.prod_id = p.prod_id\r\n" + 
				"group by r.rep_name;\r\n" + 
				"";
		 
		Statement stmt = connDB.createStatement();
		ResultSet results = stmt.executeQuery(joinQuery);
		
		while(results.next())
		{
			String name = results.getString(1);
			int shoeQty = results.getInt(2);
			int pantsQty = results.getInt(3);
			int shirtQty = results.getInt(4);
			System.out.println("The record found with "+name+" "+shoeQty + " "+pantsQty+"  "+shirtQty);
		}
		
		stmt.close();
		results.close();
		connDB.close();
		
	}

}
