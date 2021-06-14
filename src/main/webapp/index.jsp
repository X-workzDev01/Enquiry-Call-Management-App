<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="./img/Logo.png">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./css/index.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>

	<div class="backgroundImage">
		<div class="header">
			<a href="" class="logo"><img src="./img/Logo.png" height="90px"></a>
			<div class="header-right">
				<h1>Om's Development Center</h1>
			</div>
		</div>
		<div class="container">
			<div>
				<nav class="navbar navbar-expand-md navbar-dark bg-dark">

				<div class="collapse navbar-collapse" id="navbarsExample04">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="clickFunc('bulkMail')">Send Mail</a></li>
				
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="clickFunc('sendSMS')">Send SMS</a></li>
					</ul>
					
				</div>
				</nav>
			</div>

			<div class="container" align="center">
				<div class="row"
					style="color: white; text-align: center; margin-top: 3%">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h3 style="color: red;">
							<b>${msg}</b> <b>${loginsuccess}</b> <b>${loginfaildbypasswod}</b>
						</h3>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>

			<div class="container container_border ">
				<div class="sendSMS">
					<h2 align="center"
						style="margin-top: 5%; color: white; padding-top: 3%">Send
						SMS</h2>
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<div class="row mt-3 mb-3">
								<div class="col-sm-4"></div>
								<div class="col-sm-4" align="center">
									<select class="custom-select custom-select-lg sm-3"
										onchange="getSMSType()" id="smsOerations"
										style="font-size: 20px; text-align: center;">
										<option value="0">Select SMS Operation</option>
										<option value="1">Bulk SMS</option>
										<option value="2">Single SMS</option>
										<option value="3">Delivery Report</option>
										<option value="4">Check Balance</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="bulkMail">
					<h2 align="center"
						style="margin-top: 5%; color: white; padding-top: 3%">Bulk
						Mails</h2>
					<div class="panel panel-default">
						<div class="panel-heading" align="center">
							<h4 style="margin-top: 5%; color: white;">
								<br> <br> <b>Select MailId To Check The Lists</b>
							</h4>
						</div>
						<div class="panel-body" align="center" style="margin-top: 2%">
							<div class="row mt-3 mb-3">
								<div class="col-sm-4"></div>
								<div class="col-sm-4" align="center">
									<select class="custom-select custom-select-lg sm-3"
										onchange="changeMsgType()" id="mailType"
										style="font-size: 20px; text-align: center;">
										<option value="0">X-workz.in</option>
										<option value="1">Gmail.com</option>
									</select>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-4"></div>
								<div class="col-sm-4" align="center">
									<select class="custom-select custom-select-lg sm-3"
										onchange="getMessageType()" id="messageDetail"
										style="font-size: 20px; text-align: center;">
										<option value="0">Message Type</option>
										<option value="1">News Feed</option>
										<option value="2">New Joiners</option>
										<option value="3">Fees Paid</option>
										<option value="4">Birthday</option>
										<option value="5">Course Details</option>
									</select>
								</div>
								<div class="col-sm-2" style="margin-top: 1%">
									<a href="" data-toggle="modal" data-target="#listModal"
										onclick="getListDetails();">List Data</a>
								</div>
							</div>

							<form class="newsFeed" action="newsfeed.do" method="post"
								style="margin-top: 8%;">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>
												<b>News Feed</b>
											</h3></td>
									</tr>
									<tr>
										<td style=""><h5>
												Mail ID<sup>*</sup>:
											</h5></td>
										<td><select class="custom-select custom-select-lg sm-3"
											name="msgType">
												<option value="0">X-workz.in</option>
												<option value="1">Gmail.com</option>
										</select></td>
									</tr>
									<tr>
										<td><h5>
												Subject Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="subName"><input type="hidden" name="fileName"
											value="News_Feed.html"> <input type="hidden"
											name="msgType" value="0" id="msgTypeChng"></td>

									</tr>
									<tr>
										<td style=""><h5>
												List Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="listName"></td>
									</tr>

									<tr>
										<td style=""><h5>Dynamic News:</h5></td>
										<td><input type="text" class="form-control"
											name="dynamicNews"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Image Github Url<sup>*</sup>:
											</h5></td>
										<td class="imgSrc"><img src="" id="newsImg" width="220"
											height="220"><input type="hidden" id="imgURL" value=""
											name="imageURL"></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input
											class="col-sm-5 btn btn-light" type="submit"
											value="Send Mail"></td>
									</tr>

								</table>
							</form>

							<form class="newJoiner" action="newJoiner.do" method="post"
								style="margin-top: 8%;">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>New Joiner</h3></td>
									</tr>
									<tr>
									<tr>
										<td style=""><h5>
												Mail ID<sup>*</sup>:
											</h5></td>
										<td><select class="custom-select custom-select-lg sm-3"
											name="msgType">
												<option value="0">X-workz.in</option>
												<option value="1">Gmail.com</option>
										</select></td>
									</tr>
									<td style=""><h5>
											Subject Name<sup>*</sup>:
										</h5></td>
									<td><input type="text" class="form-control" name="subName"><input
										type="hidden" name="fileName" value="New_Joiners.html"></td>
									</tr>
									<tr>
										<td style=""><h5>
												List Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="listName"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Course Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="courseName"></td>
									</tr>


									<tr>
										<td colspan="2" align="center"><input type="submit"
											class="col-sm-5 btn btn-light" value="Send Mail"></td>
									</tr>

								</table>
							</form>


							<form class="feesPayment" action="feesPaid.do" method="post"
								style="margin-top: 8%;">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>Fees Payment</h3></td>
									</tr>
									<tr>
										<td style=""><h5>
												Mail ID<sup>*</sup>:
											</h5></td>
										<td><select class="custom-select custom-select-lg sm-3"
											name="msgType">
												<option value="0">X-workz.in</option>
												<option value="1">Gmail.com</option>
										</select></td>
									</tr>
									<tr>
										<td style=""><h5>
												Subject Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="subName"><input type="hidden" name="fileName"
											value="payment.html"></td>
									</tr>
									<tr>
										<td style=""><h5>
												List Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="listName"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Course Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="courseName"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Batch Code<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="batchCode"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Date With Year<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="dateYear"></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input type="submit"
											class="col-sm-5 btn btn-light" value="Send Mail"></td>
									</tr>

								</table>
							</form>

							<form class="birthday" action="birthdayGreeting.do" method="post"
								style="margin-top: 8%;">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>Birthday Greeting</h3></td>
									</tr>
									<tr>
										<td style=""><h5>
												Mail ID<sup>*</sup>:
											</h5></td>
										<td><select class="custom-select custom-select-lg sm-3"
											name="msgType">
												<option value="0">X-workz.in</option>
												<option value="1">Gmail.com</option>
										</select></td>
									</tr>
									<tr>
										<td style=""><h5>
												Subject Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="subName"><input type="hidden" name="fileName"
											value="birthday.html"></td>
									</tr>
									<tr>
										<td style=""><h5>
												List Name<sup>*</sup>:
											</h5></td>
										<td><input type="text" class="form-control"
											name="listName"></td>
									</tr>

									<tr>
										<td style=""><h5>
												Image Github Url<sup>*</sup>:
											</h5></td>
										<td><img src="" id="newsImg1" width="220" height="220"><input
											type="hidden" id="imgURL1" value="" name="imageURL"></td>
									</tr>

									<tr>
										<td style=""><h5>
												Quotes<sup>*</sup>:
											</h5></td>
										<td><textarea cols="40" rows="5" class="form-control"
												name="birthdayQuotes" id="bithdayQt" required="required"></textarea></td>
									</tr>

									<tr>
										<td colspan="2" align="center"><input type="submit"
											class="col-sm-5 btn btn-light" value="Send Mail"></td>
									</tr>

								</table>
							</form>

							<form class="courseContent" method="post"
								action="courseContain.do" style="margin-top: 8%;">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>Course Content</h3></td>
									</tr>
									<tr>
										<td style=""><h5>
												Mail ID<sup>*</sup>:
											</h5></td>
										<td><select class="custom-select custom-select-lg sm-3"
											name="msgType">
												<option value="0">X-workz.in</option>
												<option value="1">Gmail.com</option>
										</select></td>
									</tr>
									<tr>
										<td style=""><h5>
												Subject Name<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text"
											name="subName"><input type="hidden" name="fileName"
											value="Course_Details.html"></td>
									</tr>
									<tr>
										<td style=""><h5>
												List Name<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text"
											name="listName"></td>
									</tr>

									<tr>
										<td style=""><h5>
												Course Name<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text"
											name="courseName"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Start Date<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text"
											name="startDate"></td>
									</tr>

									<tr>
										<td style=""><h5>
												Time<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text" name="time"></td>
									</tr>

									<tr>
										<td style=""><h5>
												Trainer Name<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text"
											name="trainerName"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Class Mode<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text"
											name="classMode"></td>
									</tr>
									<tr>
										<td style=""><h5>
												Fee<sup>*</sup>:
											</h5></td>
										<td><input class="form-control" type="text" name="fees"></td>
									</tr>

									<tr>
										<td colspan="2" align="center"><input type="submit"
											class="col-sm-5 btn btn-light" value="Send Mail"></td>
									</tr>

								</table>
							</form>
						</div>
					</div>
				</div>

				<div class="sendBulkSMS">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="sendSMS.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>
												<b>Send Bulk SMS</b>
											</h3></td>
									</tr>
									<tr>
										<td><h5>
												Enter Message<sup>*</sup>:
											</h5>
										</td>
										<td><textarea rows="4" cols="30" class="form-control"
												id="txtMsg" name="msg"></textarea>
											<h6 id="count_message"></h6></td>
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
											class="col-sm-5 btn btn-light" type="submit" value="Send SMS"></td>
									</tr>

								</table>
							</form>
						</div>
					</div>
				</div>

				<div class="sendSingleSMS">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="sendSingleSMS.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>
												<b>Send Single SMS</b>
											</h3></td>
									</tr>
									<tr>
										<td>
											<h5>
												Enter Mobile Number<sup>*</sup>:
											</h5>
										</td>
										<td><input type="text" class="form-control" name="mobile"></td>
									</tr>
									<tr>
										<td><h5>
												Enter Message<sup>*</sup>:
											</h5></td>
										<td><textarea rows="4" cols="30" class="form-control"
												id="txtMsg" name="message"></textarea>
											<h6 id="count_message"></h6></td>
									</tr>

									<tr>
										<td colspan="2" align="center"><input
											class="col-sm-5 btn btn-light" type="submit" value="Send SMS"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>


				<div class="reports">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="reports.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>
												<b>Check SMS Report</b>
											</h3></td>
									</tr>
									<tr>
										<td>
											<h5>
												Enter Message Id<sup>*</sup>:
											</h5>
										</td>
										<td><input type="text" class="form-control"
											name="messageId"></td>
									</tr>

									<tr>
										<td colspan="2" align="center"><input
											class="col-sm-5 btn btn-light" type="submit"
											value="Get Report"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>

				<div class="checkSMSBalance">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="checkSMSBalance.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center">
											<h3>
												<b>Check SMS Balance</b>
											</h3>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input
											class="col-sm-5 btn btn-light" type="submit"
											value="Check Balance"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>

			<!-- The Modal -->
			<div class="modal" id="listModal">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">MailChimp List</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col-2">List Name</th>
										<th scope="col">Total Subscriber</th>
										<th scope="col">Description</th>
									</tr>
								</thead>
								<tbody class="listBody">

								</tbody>
							</table>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
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
	</div>
</body>
</html>