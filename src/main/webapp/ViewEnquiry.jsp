<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.10.23/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/rowreorder/1.2.7/css/rowReorder.dataTables.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/responsive/2.2.7/css/responsive.dataTables.min.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./css/index.css">
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<style>
h3 {
	color: white;
}

label {
	color: white;
}
</style>
</head>

<body>
	<div class="backgroundImage">
		<div class="header">
			<a href="" class="logo"><img src="./img/Logo.png" height="90px"></a>
			<div class="header-right">
				<h1>Om's Development Center</h1>
			</div>
			<div style="align-content:flex-basis; width: 6%; margin-bottom: 0.5%;">
				<button onclick="location.href='Home.jsp'" type="button"
					class="btn btn-success btn-block"><i class="fa fa-home" aria-hidden="true"></i> Home</button>
			</div>
			
			<div style="align-items: flex-end; width: 6%;">
				<button onclick="location.href='EnquiryLogin.jsp'" type="button"
					class="btn btn-danger btn-block"><i class="fa fa-sign-out" aria-hidden="true"></i> Log Out</button>
			</div>
		</div>
		<div class="container">
			<div class="container" align="center">
				<div>
					<nav class="navbar navbar navbar-dark bg-dark">
						<h3
							style="color: white; font-weight: 900; width: 100%; align-items: center;">
							<b>View Enquiries</b>
						</h3>
					</nav>
				</div>
				<div class="row"
					style="color: white; text-align: center; margin-top: 2%">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h3 style="color: red;">
							<b>${msg}</b>
						</h3>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
			<div class="container container_border ">
				<div class="container" style="align-content: flex-start;">
					
					<form action="getCustomEnquiries.do" id ="viewEnquiry" class="getCustomEnquiries"
						method="post">
						<h3>Select Custom Dates Enquiries</h3>
						<label> Select From Date: <input type="date"
							name="fromDate" value="20-9-2018">
						</label> <label> Select To Date: <input type="date" name="toDate"
							id="today">
						</label> <span><button class="btn btn-primary">Search</button></span>
					</form>					
			
                    <form action="getLatestEnquiries.do" method="post" style="margin-bottom: 0.5%;">
					   <button class="btn btn-primary">Latest 30 Enquiries</button>
                     </form>
					
                     
					<table id="example" class="table table-striped table-bordered"
						style="width: 100%">
						<thead style="color: black; background-color: brown;">
							<tr>
								<th>Enquiry ID</th>
								<th>TIME STAMP</th>
								<th>FULL NAME</th>
								<th>MOBILE</th>
								<th>ALTERNATE MOBILE</th>
								<th>EMAIL ID</th>
								<th>COURSE</th>
								<th>BATCH TYPE</th>
								<th>SOURCE</th>
								<th>REFRENCE</th>
								<th>REFRENCE MOBILE</th>
								<th>BRANCH</th>
								<th>COUNSELOR NAME</th>
								<th>COMMENTS</th>
								<th>ACTION</th>
							</tr>
						</thead>

						<tbody style="color: white;">
							<c:forEach var="enquiryList" items="${enquiryList}">
								<tr>
									<td>${enquiryList.enquiryId}</td>
									<td><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${enquiryList.dateTime}"/></td>
									<td>${enquiryList.fullName}</td>
									<td>${enquiryList.mobileNo}</td>
									<td>${enquiryList.alternateMobileNo}</td>
									<td>${enquiryList.emailId}</td>
									<td>${enquiryList.course}</td>
									<td>${enquiryList.batchType}</td>
									<td>${enquiryList.source}</td>
									<td>${enquiryList.refrenceName}</td>
									<td>${enquiryList.refrenceMobileNo}</td>
									<td>${enquiryList.branch}</td>
									<td>${enquiryList.counselor}</td>
									<td>${enquiryList.comments}</td>
									<td><form method="post" action="getEnquiryById.do">
									    <input type="hidden" type="number" value="${enquiryList.enquiryId}" name="enquiryId"> 
									    <button class="btn btn-success">Update</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						<tfoot style="color: black; background-color: brown;">
							<tr>
								<th>Enquiry ID</th>
								<th>TIME STAMP</th>
								<th>FULL NAME</th>
								<th>MOBILE</th>
								<th>ALTERNATE MOBILE</th>
								<th>EMAIL ID</th>
								<th>COURSE</th>
								<th>BATCH TYPE</th>
								<th>SOURCE</th>
								<th>REFRENCE</th>
								<th>REFRENCE MOBILE</th>
								<th>BRANCH</th>
								<th>COUNSELOR NAME</th>
								<th>COMMENTS</th>
								<th>ACTION</th>
							</tr>
						</tfoot>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div>
			<nav class="navbar navbar-expand-md navbar-dark bg-dark"></nav>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.23/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://cdn.datatables.net/rowreorder/1.2.7/js/dataTables.rowReorder.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.7/js/dataTables.responsive.min.js"></script>

	<script type="text/javascript" src="./js/index.js"></script>
</body>
</html>