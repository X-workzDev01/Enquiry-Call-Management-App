$(document).ready(function () {
    allHide();
	$('.newEnquiry').hide();
	$('.getCloudEnquiries').hide();
	$('.getlatestEnquiries').hide();
	$('.uploadEnquiry').hide();
	$('.viewEnquiries').hide();
	$('.enquiryManagement').hide();
	$('.callManagement').hide();
});

let getDropdown  = () => fetch('https://raw.githubusercontent.com/xworkzodc/newsfeed/master/mailSender.json').then(data => data.json());

function hideMainTab(){	
	$('.getlatestEnquiries').hide();
	$('.getCloudEnquiries').hide();
	$('.enquiryManagement').hide();
	$('.callManagement').hide();
}

function handleSelect(page) {
	window.location = page.value + ".jsp";
}

$(document).ready(function() {
	var table = $('#example').DataTable({
		responsive : true,
		scrollY : 500,
		deferRender : true,
		scroller : true
	});

	new $.fn.dataTable.FixedHeader(table);
});

document.querySelector("#today").valueAsDate = new Date();

function getEnquiryType() {
	
    if ($('#enquiryOperations').val() == '0') {
        allHide();
    }
    if ($('#enquiryOperations').val() == '1') {
        allHide();
		$('.newEnquiry').show();
    }
    if ($('#enquiryOperations').val() == '2') {
        allHide();
        $('.uploadEnquiry').show();
    }
    if ($('#enquiryOperations').val() == '3') {
        allHide();
    	$('.getCloudEnquiries').show();
    }
    if ($('#enquiryOperations').val() == '4') {
        allHide(); 
        $('.getlatestEnquiries').show(); 
    }
}

function allHide() {
	$('.newEnquiry').hide();
	$('.getCloudEnquiries').hide();
	$('.getlatestEnquiries').hide();
	$('.uploadEnquiry').hide(); 
}

function clickFunc(className){
	console.log("called clickFunc="+className);
	hideMainTab();
	allHide();
	$('.'+className).show();
}

function openkFunc(mainclass,subclass){
	console.log("called clickFunc="+mainclass+" sub:"+subclass);
	hideMainTab();
	allHide();
	$('.'+mainclass).show();
	$('.'+subclass).show();
}

$(document).ready(function() {
	var contextPath = $("meta[name='contextPath']").attr("content");
	$.ajax({  
    	type: "GET",  
		url: contextPath + "/validateExcelFields.do",  
		success: function (data) {
			$("#validateFieldmsg").html(data);  
		}  
	});  
});

function checkEmailExist() {
	var contextPath = $("meta[name='contextPath']").attr("content");
	var EnquiryDTO = {};
	EnquiryDTO["emailId"] = document.forms["newenq"]["emailId"].value; //document.forms["newenq"]["emailId"].value;
		
	 $.ajax( {
		type : "POST",
		contentType : "application/json",
		url :contextPath + "/getEnquiryByEmail.do",
		data : JSON.stringify(EnquiryDTO),
		dataType : 'json',
		success : function(data) {
			if (data.emailId != null) {
				$('#isE').text('Email Id Already Exist!');
			 }
			else {
				$('#isE').text('');
			}
		  },
		 error : function(e) {
                console.log("ERROR: ", e);
         }
	});
}

function loadCourses() {
	var contextPath = $("meta[name='contextPath']").attr("content");
	console.log("contextpath : "+contextPath);
	$.ajax({  
    	type: "GET",  
		url: contextPath + "/getAllCourses.do",  
		success: function (data) {  
			var course = '<option value="-1">Please Select a Course</option>';  
			for (var i = 0; i < data.length; i++) {  
				course += '<option value="' + data[i] + '">' + data[i] + '</option>';  
			}  
			$("#courses").html(course);  
		}  
	});  
}

function checkNameExist() {
	var contextPath = $("meta[name='contextPath']").attr("content");
	var EnquiryDTO = {};
	EnquiryDTO["fullName"] = document.forms["newenq"]["fullName"].value; //document.forms["newenq"]["emailId"].value;
		
	 $.ajax( {
		type : "POST",
		contentType : "application/json",
		url :contextPath + "/getEnquiryByName.do",
		data : JSON.stringify(EnquiryDTO), 
		dataType : 'json',
		success : function(data) {
			if (data.fullName != null) {
				$('#isName').text('FullName Exist!,Check Once');
			 }
			else {
				$('#isName').text('');
			}
		  },
		 error : function(e) {
                console.log("ERROR: ", e);
         }
	});
}


function validateMobileNo() {
    var contextPath = $("meta[name='contextPath']").attr("content");
	var EnquiryDTO = {};
	var number = document.forms["newenq"]["mobileNo"].value;
	EnquiryDTO["mobileNo"] =number;  
	if (number.length == 10){
		$.ajax( {
			type : "POST",
			contentType : "application/json",
			url :contextPath + "/getEnquiryByMobile.do",
			data : JSON.stringify(EnquiryDTO), 
			dataType : 'json',
			success : function(data) {
				if (data.mobileNo != null) {
					$('#isMobile').text('Mobile Number Exist!,Check Once');
				 }
				else {
					$('#isMobile').text('');
				}
			},
		 	error : function(e) {
                console.log("ERROR: ", e);
        	}
		});
	}else if (number.length != 10){
		$('#isMobile').text('Enter only 10 digit mobile number');
	}
}