<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- SCROLLS -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDhcAb4DpiNAdtBhBT3NFegFtT2UjX-aDg" async defer></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<style>
<%@ include file="styles.css"%>
</style>
<script src="<c:url value="/resources/homeController.js" />"></script>
<script src="<c:url value="/resources/userController.js" />"></script>
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
<body onload="getPreference()">
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="col-md-3">
			<span class="app-logo">Events Around Me</span>
		</div>
		<div class="col-md-3">
			<div class="input-group search-controls" id="adv-search"
				style="width: 345px;">
				<input id = "searchid" type="text" class="form-control" placeholder="Enter Keywords" />
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
				<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
				<li><a href="favoriteevent"><i class="fa fa-child"></i> My
						Events</a></li>
			    <li><a href="favorite"><i class="fa fa-diamond"></i>
						Favorites</a></li>
				<li><a href="history"><i class="fa fa-history"></i></i>
						History</a></li>
				<li><a href="contact"><i class="fa fa-comment"></i>
						Contact</a></li>
			    
			</ul>
		</div>
	</div>
	</nav>
    
	<div class="container">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<h2>Ticked are my favourite sub-categories...!!</h2>
			<div name = "preferenceform" class="larger-form">
			    </br>
				<label><i class="fa fa-music">&nbsp;Music</i></label></br>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="3007"/>Pop</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="3011"/> Rock</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="3012"/>Metal</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="3013"/> Folk</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="3017"/>Opera</label>
			    </br>
			    </br>
			    <label><i class="fa fa-car">&nbsp;Outdoor</i></label></br>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="9001"/>Hiking</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="9002"/> Rafting</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="9003"/>Kayaking</label>
			    <label class="checkbox-inline"><input type="checkbox" name="pref" value="9004"/> Canoeing</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="9005"/>Climbing</label>
			    </br>
			    </br>
			    <label><i class="fa fa-glass">&nbsp;Drink</i></label></br>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="10001"/>Beer</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="10002"/>Wine</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="10003"/>Food</label>
			    <label class="checkbox-inline"><input type="checkbox" name="pref" value="10004"/>Spirits</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="10099"/>Others</label>
			    </br>
			    </br>
			    <label><i class="fa fa-book">&nbsp;Technology</i></label></br>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="2001"/>Medicine</label>  
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="2003"/>Biotech</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="2005"/>Mobile</label>
			    <label class="checkbox-inline"><input type="checkbox" name="pref" value="2007"/>Robotics</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="2002"/>Science</label>
			    </br>
			    </br>
			    <label><i class="fa fa-users">&nbsp;Holiday</i></label></br>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="16002"/>Easter</label>  
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="16004"/>Halloween/Haunt</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="16005"/>Thanksgiving</label>
			    <label class="checkbox-inline"><input type="checkbox" name="pref" value="16006"/>Christmas</label>
				<label class="checkbox-inline"><input type="checkbox" name="pref" value="16007"/> Channukah</label>
			    </br>
			    </br>
			    <button type="submit" onclick="savePreference()" class="btn btn-success">Save</button>
			</div>
		</div>
	</div>

	
</body>
</html>