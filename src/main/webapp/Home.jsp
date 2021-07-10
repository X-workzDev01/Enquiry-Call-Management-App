<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<link rel="shortcut icon" href="./img/Logo.png">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./css/index.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


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
<style type="text/css">
ul {
	list-style-type: none;
}

li {
	float: left;
}

li button {
	display: block;
	padding: 16px;
	margin: 5px;
}
}
</style>
</head>

<body onload="loadData()"> <!-- loadCourses() --> 

	<div class="backgroundImage">
		<div class="header">
			<a href="" class="logo"><img src="./img/Logo.png" height="90px"></a>
			<div class="header-right">
				<h1>Om's Development Center</h1>
			</div>
			<div style="margin-left: auto; margin-right: 5%; width: 10%;">
				<button onclick="location.href='EnquiryLogin.jsp'" type="button"
					class="btn btn-danger btn-block">
					<i class="fa fa-sign-out" aria-hidden="true"></i> Log Out
				</button>
			</div>
		</div>

		<div class="container">
			<div>
				<nav class="navbar navbar-expand-md navbar-dark bg-dark">
					<div class="collapse navbar-collapse" id="navbarsExample04">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item"><a class="nav-link" href="#"
								onclick="clickFunc('enquiryManagement')">Enquiry Management</a></li>

							<li class="nav-item"><a class="nav-link" href="#"
								onclick="clickFunc('callManagement')">Call Management</a></li>
						</ul>
					</div>
				</nav>
			</div>
			<div class = "loginMessages">
				<div class="container" align="center">
					<div class="row" style="color: white; text-align: center; margin-top: 2%">
						<div class="col align-self-center">
							<h3 style="color: red;">
								<b>${LoginMsg}${success}${faild}${msg}</b>
							</h3>
						</div>
						
					</div>
				</div>
			</div>

			<div class="container container_border " >
				<div class="enquiryManagement" style="margin-bottom: 10%;" >
					<h2 align="center"
						style="margin-top: 5%; color: white; padding-top: 3%">Enquiry
						Management</h2>
					<div class="panel panel-default row">
						<div class="panel-body" align="center" style="margin-left: 20%;">
							<ul>
								<li><button type="button" class="btn btn-primary"
										onclick="openkFunc('enquiryManagement','newEnquiry')">
										<i class="fa fa-file-text-o" aria-hidden="true"></i> New
										Enquiry
									</button></li>
								<li><button type="button" class="btn btn-success"
										onclick="openkFunc('enquiryManagement','uploadEnquiry')">
										<i class="fa fa-cloud-upload" aria-hidden="true"></i> Upload
										Enquires
									</button></li>
								<li><form action="getLatestEnquiries.do" method="post">
										<button type="submit" class="btn btn-info">
											<i class="fa fa-eye" aria-hidden="true"> </i> View Enquires
										</button></li>
								</form>

								<li><button type="button" class="btn btn-light"
										onclick="openkFunc('enquiryManagement','getCloudEnquiries')">
										<i class="fa fa-refresh" aria-hidden="true"></i> Sync Enquires
									</button></li>
							</ul>
						</div>
					</div>
					<div class = "validateExcelfile">
						<div class ="row justify-content-center" style="color :red; margin-top: 2%; ">
							<h3 id="validateFieldmsg"></h3>
						</div>
					</div>
				
				</div>
				
				<div class="callManagement">
					<h2 align="center"
						style="margin-top: 5%; color: white; padding-top: 3%">Call
						Management</h2>
					<div class="panel panel-default">
						<div class="panel-body" align="center"
							style="margin-top: 2%; margin-left: 20%;">
							<ul>
								<li><button type="button" class="btn btn-primary"
										onclick="openkFunc('callManagement','newEnquiry')">
										<i class="fa fa-file-text-o" aria-hidden="true"></i> New Calls
									</button></li>
								<li><button type="button" class="btn btn-success"
										onclick="openkFunc('callManagement','uploadEnquiry')">
										<i class="fa fa-clock-o" aria-hidden="true"></i> Pending Calls
									</button></li>
								<li><button type="button" class="btn btn-info"
										onclick="openkFunc('callManagement','getlatestEnquiries')">
										<i class="fa fa-eye" aria-hidden="true"></i> View All Calls
									</button></li>
								<li><button type="button" class="btn btn-light"
										onclick="openkFunc('callManagement','getCloudEnquiries')">
										<i class="fa fa-upload" aria-hidden="true"></i> Upload Calls
									</button></li>
							</ul>
						</div>
					</div>
				</div>

				<form name="newenq" class="newEnquiry" method="POST"
					action="newEnquiry.do" style="margin-top: 8%;">
					<table class="col-md-6 table table-bordered table-dark" border="1"
						border-color="white" align="center" style="color: white">
						<tr>
							<td colspan="2" align="center"><h3>New Enquiry</h3></td>
						</tr>

						<tr>
							<td><h5>
									Full Name<sup>*</sup>:
								</h5></td>
							<td><input type="text" class="form-control" name="fullName"
								required placeholder="Enter full name" onblur="checkNameExist()" /><b><span
									id="isName" style="color: red;"></span></b></td>
						</tr>

						<tr>
							<td><h5>
									MOBILE NO<sup>*</sup>:
								</h5></td>
							<td><input type="number" class="form-control"
								name="mobileNo" required placeholder="Enter valid mobile number"
								onblur="validateMobileNo()" /><b><span id="isMobile"
									style="color: red;"></span></b></td>
						</tr>

						<tr>
							<td><h5>
									ALTERNATE MOBILE<sup></sup>:
								</h5></td>
							<td><input type="number" class="form-control"
								name="alternateMobileNo"
								placeholder="Enter alternate mobile number" /></td>
						</tr>

						<tr>
							<td><h5>
									EMAIL ID<sup>*</sup>:
								</h5></td>
							<td><input type="email" class="form-control" name="emailId"
								required placeholder="Enter valid email id"
								onblur="checkEmailExist()" /><b><span id="isE"
									style="color: red;"></span></b></td>
						</tr>

						<tr>
							<td><h5>
									COURSE<sup>*</sup>:
								</h5></td>
							<td><select class="form-control" id="courses" name="course"></select>
						</tr>

						<tr>
							<td><h5>
									BATCH TYPE<sup>*</sup>:
								</h5></td>
							<td><select name="batchType"
								class="custom-select custom-select-lg sm-3">
									<option value="OnlineWeekend" label="OnlineWeekend" />
									<option value="OnlineWeekDay" label="OnlineWeekDay" />
									<option value="OfflineWeekend" label="OfflineWeekend" />
									<option value="OfflineWeekDay" label="OfflineWeekDay" />
							</select></td>
						</tr>

						<tr>
							<td><h5>
									SOURCE<sup>*</sup>:
								</h5></td>
							<td><select name="source"
								class="custom-select custom-select-lg sm-3">
									<option value="Facebook" label="Facebook" />
									<option value="LinkedIn" label="LinkedIn" />
									<option value="Instagram" label="Instagram" />
									<option value="X-workZ Website" label="X-workZ Website" />
									<option value="Friends Refrence" label="Friends Refrence" />
							</select></td>
						</tr>

						<tr>
							<td><h5>
									REFRENCE<sup></sup>:
								</h5></td>
							<td><input type="text" class="form-control"
								name="refrenceName" placeholder="Enter refrence name" /></td>
						</tr>

						<tr>
							<td><h5>
									REFRENCE MOBILE NO<sup></sup>:
								</h5></td>
							<td><input type="number" class="form-control"
								name="refrenceMobileNo"
								placeholder="Enter refrence mobile number" /></td>
						</tr>

						<tr>
							<td><h5>
									BRANCH TYPE<sup>*</sup>:
								</h5></td>
							<td><select name="branch" required
								class="custom-select custom-select-lg sm-3">
									<option value="Rajajinagar" label="Rajajinagar" />
									<option value="BTM" label="BTM" />
							</select></td>
						</tr>

						<tr>
							<td><h5>
									COUNSELOR<sup>*</sup>:
								</h5></td>
							<td><input type="text" class="form-control" name="counselor"
								required placeholder="Enter refrence name" /></td>
						</tr>

						<tr>
							<td><h5>
									COMMENTS<sup>*</sup>:
								</h5></td>
							<td><textarea rows="10" cols="10"
									style="overflow: auto; resize: none" maxlength="300"
									class="form-control" name="comments"
									placeholder="Enter comments" required></textarea></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input type="submit"
								class="col-sm-5 btn btn-secondary" value="Submit"></td>
						</tr>

					</table>
				</form>

				<form action="uploadEnquiry.do" class="uploadEnquiry" method="post"
					enctype="multipart/form-data">
					<table class="col-md-6 table table-bordered table-dark" border="1"
						border-color="white" align="center" style="color: white">
						<tr>
							<td colspan="2" align="center"><h3>
									<b>Upload Enquires</b>
								</h3></td>
						</tr>
						<tr>
							<td>
								<h5>Upload Excel File:</h5>
							</td>
							<td><input type="file" class="form-control"
								name="uploadFile"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input
								class="col-sm-5 btn btn-secondary" type="submit" value="Upload"></td>
						</tr>

					</table>
				</form>

				<form class="getlatestEnquiries" action="getLatestEnquiries.do"
					method="post">
					<table class="col-md-6 table table-bordered table-dark" border="1"
						border-color="white" align="center" style="color: white">
						<tr>
							<td colspan="2" align="center"><h3>
									<b>View Enquiries</b>
								</h3></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input
								class="col-sm-5 btn btn-secondary" type="submit"
								value="View Enquiries"></td>
						</tr>
					</table>
				</form>

				<form class="getCloudEnquiries" action="downloadEnquirySchedule.do"
					method="get">
					<table class="col-md-6 table table-bordered table-dark" border="1"
						border-color="white" align="center" style="color: white">
						<tr>
							<td colspan="2" align="center"><h3>
									<b>Sync Enquiries</b>
								</h3></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input
								class="col-sm-5 btn btn-secondary" type="submit" value="Sync"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div>
			<nav class="navbar navbar-expand-md navbar-dark bg-dark"></nav>
		</div>
		<!-- Latest compiled JavaScript -->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
			integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
			integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
			crossorigin="anonymous"></script>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
		<script type="text/javascript" src="./js/index.js"></script>
</body>
</html>