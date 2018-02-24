package com.pharmacy.data;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.pharmacy.entities.Pharmacy;

public class PharmacyDataContext extends DataContext {
	
	private static PharmacyDataContext context = new PharmacyDataContext();
	
	public static PharmacyDataContext getInstance(){
		return context;
	}
	
	private PharmacyDataContext() {  	   
	}
	
	public List<Pharmacy> getAllPharmacies() {
		Connection conn = getConnection();
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT * FROM  pharmacy";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_pharmacy");
		        	  String name = rs.getString("pharm_title");
		        	  String address = rs.getString("address");
	        	  pharmacies.add(new Pharmacy(id, name, address));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pharmacies; 
	}
	
	public boolean updatePharmacy(Pharmacy p){
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "UPDATE pharmacy "
					+ "SET pharm_title = ? , address = ? "
					+ "WHERE id_pharmacy = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, p.getTitle());
			st.setString(2, p.getAddress());
			st.setInt(3, p.getId());
			System.out.println(st.executeUpdate());
			conn.commit();
			st.close();
			conn.setAutoCommit(true);
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return res;
		}
        return true;
	}

	public Pharmacy getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addPharmacy(Pharmacy item) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "INSERT INTO pharmacy (pharm_title, address)"
					+ "VALUES ( ? ,  ? )";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, item.getTitle());
			st.setString(2, item.getAddress());
			System.out.println(st.executeUpdate());
			conn.commit();
			st.close();
			conn.setAutoCommit(true);
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return res;
		}
        return true;
	}

	
	
	 public static void JDBCexample(String db, String user, String passwd) {
	    	try {
	           Class.forName("com.mysql.jdbc.Driver"); 	
	     	   Connection c = DriverManager
	                 .getConnection("jdbc:mysql://localhost/"+db+"?useSSL=false",user,passwd);       
	           Statement st = c.createStatement();
	           
	           // create a table
	           String tableName = "myTable" + String.valueOf((int)(Math.random() * 1000.0));
	           String createTable = "CREATE TABLE " + tableName + " (id Integer, name Text(32))";
	           st.execute(createTable); 
	           System.out.println("Create Table " + tableName);
	           
	           System.out.println("Adding Table " );
	           // enter value into table
	           for(int i=0; i<25; i++) {
	             String addRow = "INSERT INTO " + tableName + " VALUES ( " + 
	                       String.valueOf((int) (Math.random() * 100000)) + ", 'Text Value " + 
	                       String.valueOf(Math.random()) + "')";  	  
	             st.execute(addRow);
	             System.out.println("...." + addRow );
	           }
	           
	           // Fetch table
	           String selTable = "SELECT * FROM " + tableName;
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()) {
	             System.out.println(rs.getInt(1) + " : " + rs.getString(2));
	           }
	           
	           // drop the table
	           String dropTable = "DROP TABLE " + tableName;
	           st.execute(dropTable);
	           System.out.println("Drop Table " + tableName);
	           
	           // close and cleanup
	           st.close();
	           c.close();	
	         }
	         catch(Exception sqle){
	             System.out.println("Exception: " + sqle.getMessage());
	         }
	    }
	
	

}
