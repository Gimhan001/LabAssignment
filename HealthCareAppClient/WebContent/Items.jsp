<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.Item"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/items.js"></script>
	<meta charset="ISO-8859-1">

	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				
				<h1>Item Management v10-new</h1>
				<form id="formItem" name="formItem" method="post" action="Item.jsp">
					Item code: 
					<input id="itemCode" name="itemCode" type="text"class="form-control form-control-sm">
					 <br> Pack Size:
					<input id="pSize" name="pSize" type="text"class="form-control form-control-sm"> 
					<br>Item name:
					<input id="itemName" name="itemName" type="text"class="form-control form-control-sm"> 
					<br> Item Price: 
					<input id="itemPrice" name="itemPrice" type="text"class="form-control form-control-sm"> 
					<br> Item Supplier Discount: 
					<input id="itemDiscount" name="itemDiscount" type="text"class="form-control form-control-sm">
					 <br>Item CostPrice: 
					<input id="costPrice" name="costPrice" type="text"class="form-control form-control-sm">
					 <br>Item Selling Price: 
					<input id="sellPrice" name="sellPrice" type="text"class="form-control form-control-sm">
					 <br>Item Whole Sale Price: 
					<input id="WhsellPrice" name="WhsellPrice" type="text"class="form-control form-control-sm">
					 <br> Date : 
					<input id="idate" name="iDate" type="Date"class="form-control form-control-sm">
					 <br> 
					<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>


				<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
  
   <br>
   <div id="divItemsGrid">
   
   
   </div>

			</div>
		</div>
	</div>

</body>
</html>