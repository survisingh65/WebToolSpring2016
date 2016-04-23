<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- SCROLLS -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<style>
<%@ include file ="styles.css"%>
</style>
<script src="<c:url value="/resources/homeController.js"/>"></script>
<script src="<c:url value="/resources/userController.js"/>"></script>
<!-- <link rel="stylesheet" type="text/css" href="<c:url value="styles.css" />"/>-->
<link href='https://fonts.googleapis.com/css?family=Raleway:400,800,600'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Shadows+Into+Light'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Pacifico'
	rel='stylesheet' type='text/css'>
<script src="http://mymaplist.com/js/vendor/TweenLite.min.js"></script>
<!-- SPELLS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.10.1/lodash.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<!--<script src="scripts/dirPagination.js"></script>-->
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-route.js"></script>
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDhcAb4DpiNAdtBhBT3NFegFtT2UjX-aDg"
	async defer></script>
<script
	src="//rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Base64/0.3.0/base64.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/spin.js/2.3.2/spin.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-spinner/0.8.1/angular-spinner.js"></script>
</head>

<!-- define angular controller -->
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="col-md-3">
			<span class="app-logo">Events Around Me</span>
		</div>
		<div class="col-md-3">
			<div class="input-group search-controls" id="adv-search"
				style="width: 345px;">
				<input type="text" class="form-control" placeholder="Enter Keywords" />
				<div class="input-group-btn">
					<div class="btn-group" role="group">
						<button class="btn btn-primary" type="submit"
							onclick="searchEventBrite()">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><i class="fa fa-sign-out"></i> Log Out</a></li>
				<li><a href="uploadnewevent"><i class="fa fa-upload"></i>
						Upload Event</a></li>
			    <li><a href="viewcreatedevents"><i class="fa fa-diamond"></i>
						View Event</a></li>
				<li><a href="newadminform"><i class="fa fa-history"></i></i>
						Create User</a></li>
			</ul>
		</div>
	</div>
	</nav>



		<div class="container">
				<ul class="list-group">
					<c:forEach items="${requestScope.al}" var="u">
						<li class="list-group-item">
						
							<div class="row">
						
								<div class="col-md-12" style="padding: 10px; color:#3B5998">
                                    <p>
                                        <b>	${u.eventName}</b>
                                    </p>
                                    <p>
                                        <i class="fa fa-calendar-times-o"></i>&nbsp;${u.date} / ${u.time}
                                    </p>
                                    <p>	${u.venue}</p>
                                 <button type="submit" id="${u.eventId}" onclick="deleteEvent(${u.eventId})" class="btn btn-danger btn-sm">Delete Event</button>
                                </div>
                             </div>
                             
					 </li>
					</c:forEach>
				</ul>
			</div>
	

</body>
</html>