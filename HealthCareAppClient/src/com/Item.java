package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {

	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharma_db", "root", "3edc$RFV");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 
	

	// reading an items -------------------------
	public String readItems()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table border='1'>"
					+ "<tr><th>Item Code</th> "
					+ "<th>Pack Size</th>"
					+ "<th>Item Name</th>"
					+ "<th>Supplier Price</th>"
					+ "<th>Supplier Discount</th>"
					+"<th>Cost Price</th>"
					+"<th>Selling Price</th>"
					+"<th>Whole Sale Price</th>"
					+"<th>Date</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";
			String query = "select * from stock";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				
				String itemID = Integer.toString(rs.getInt("itemID"));
				String itemCode = rs.getString("itemCode");
				String packSize = rs.getString("pSize");
				String itemName = rs.getString("itemName");
				String SupPrice = Double.toString(rs.getDouble("itemPrice"));
				String supDiscount = Double.toString(rs.getDouble("itemDiscount"));
				String CostPrice = rs.getString("costPrice");
				String selling = Double.toString(rs.getDouble("sellPrice"));
				String WholeSalePrice = rs.getString("WhsellPrice");
				String idate = rs.getString("iDate"); 
				
				
				
				 

				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate' type='hidden'value='" + itemID + "'>" + itemCode + "</td>";output += "<td>" + itemName + "</td>";output += "<td>" + SupPrice + "</td>";output += "<td>" + selling + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button'"+ "value='Update'"+"class='btnUpdate btn btn-secondary'></td>"+"<td><input name='btnRemove' type='button'"+" value='Remove'"+"class='btnRemove btn btn-danger' data-itemid='"+ itemID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//inserting---------------------
	public String insertItem(String string,String PackSize, 
			String Iname,String SupPrice,String SupDiscount,String CostPrice,String Selling, 
			String WholeSalePrice,String  idate)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into stock(`Icode`, `PackSize`, `Iname`, `SupPrice`, `SupDiscount`, `CostPrice`, `Selling`, `WholeSalePrice`, `Date`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, string);
			preparedStmt.setString(2, PackSize);
			preparedStmt.setString(3, Iname);
			preparedStmt.setDouble(4, Double.parseDouble(SupPrice));
			preparedStmt.setDouble(5, Double.parseDouble(SupDiscount));
			preparedStmt.setDouble(6, Double.parseDouble(CostPrice));
			preparedStmt.setDouble(7, Double.parseDouble(Selling));
			preparedStmt.setDouble(7, Double.parseDouble(WholeSalePrice));
			preparedStmt.setString(8, idate);
			// execute the statement
			preparedStmt.execute();
			
			 System.out.print("successfuly inserted");
			 
			con.close();
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//update items
	public String updateItem(String string,String PackSize, 
			String Iname,String SupPrice,String SupDiscount,String CostPrice,String Selling, 
			String WholeSalePrice,String  idate)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE stock SET Icode = ?, "
					+ "PackSize = ?, Iname = ?, SupPrice = ?, "
					+ "SupDiscount = ?, CostPrice = ?, Selling = ?, "
					+ "WholeSalePrice = ?, Date = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, string);
			preparedStmt.setString(2, PackSize);
			preparedStmt.setString(3, Iname);
			preparedStmt.setDouble(4, Double.parseDouble(SupPrice));
			preparedStmt.setDouble(5, Double.parseDouble(SupDiscount));
			preparedStmt.setDouble(6, Double.parseDouble(CostPrice));
			preparedStmt.setDouble(7, Double.parseDouble(Selling));
			preparedStmt.setDouble(7, Double.parseDouble(WholeSalePrice));
			preparedStmt.setString(8, idate);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//delete items------------------------
	public String deleteItem(String Icode) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from stock where Icode=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Icode));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			//output = "Error while deleting the item.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			
			System.err.println(e.getMessage());
		}
		return output;
	}
}
