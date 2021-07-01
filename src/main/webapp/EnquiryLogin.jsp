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
			<div class="container" align="center">
				<div>
					<nav class="navbar navbar-expand-md navbar-dark bg-dark">
						<h3
							style="color: white; font-weight: 900; width: 100%; align-items: center;">
							<b>X-WorkZ Enquiry And Call Management</b>
						</h3>
					</nav>
				</div>
				<div class="row"
					style="color: white; text-align: center; margin-top: 2%">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h3 style="color: red;">
							<b>${LoginMsg}${Failmsg}${loginfaildbypasswod}</b>
						</h3>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
			<div class="container container_border ">
				<div class="LOGIN">
					<h2 align="center"
						style="margin-top: 5%; color: white; padding-top: 3%">LOGIN</h2>
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<div class="row mt-3 mb-3">
								<div class="col-sm-4"></div>
								<div class="col-sm-4" align="center">
									<form action="enquiryLogin.do" method="post">
										<div class="form-group input-group">
											<input name="userName" type="email" class="form-control"
												placeholder="Enter Email Id"
												<%-- value="${dto.userName}" --%> name="userName" />

											<div class="input-group-prepend">
												<span class="input-group-text"> <i class="fa fa-user"
													style="font-size: 24px"></i>
												</span>
											</div>

										</div>
										<div class="form-group input-group">

											<input name="password" class="form-control"
												placeholder="Enter Password" type="password" name="password" />

											<div class="input-group-prepend">
												<span class="input-group-text"> <i class="fa fa-lock"
													style="font-size: 24px"></i>
												</span>
											</div>
										</div>
										<div class="form-group input-group">
											<button type="submit" class="btn btn-success btn-block">
												Login</button>
										</div>
									</form>
									<br> <br>
									<div style="align-content: flex-start;">
										<button onclick="location.href='EnquiryPasswordReset.jsp'"
											type="button" class="btn btn-danger btn-block">Forgot
											Password?</button>
									</div>
								</div>
							</div>
						</div>
						<!-- Modal footer -->
					</div>
				</div>
			</div>
		</div>

		<div>
			<nav class="navbar navbar-expand-md navbar-dark bg-dark"></nav>
		</div>
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