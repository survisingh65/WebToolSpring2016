<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- SCROLLS -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <style>
     		<%@ include file="styles.css"%>
		</style>
		<script src="<c:url value="/resources/controller.js" />"></script>
        <!-- <link rel="stylesheet" type="text/css" href="<c:url value="styles.css" />"/>-->
        <link href='https://fonts.googleapis.com/css?family=Raleway:400,800,600' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
        <script src="http://mymaplist.com/js/vendor/TweenLite.min.js"></script>
        <!-- SPELLS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.10.1/lodash.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
        <!--<script src="scripts/dirPagination.js"></script>-->
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-route.js"></script>
        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDhcAb4DpiNAdtBhBT3NFegFtT2UjX-aDg" async defer></script>
        <script src="//rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Base64/0.3.0/base64.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/spin.js/2.3.2/spin.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-spinner/0.8.1/angular-spinner.js"></script>
    </head>

     <body>
        <nav class="navbar navbar-default" >
        	<div class="container-fluid" >
	        <div class="col-md-3"><span class="app-logo">Events Around Me</span></div>
	        <div class="col-md-3">
	            <div class="input-group search-controls" id="adv-search" style="width: 345px;">
	                <input type="text" class="form-control" placeholder="Enter Keywords" />
	                <div class="input-group-btn">
	                    <div class="btn-group" role="group">
	                        <button class="btn btn-primary" type="submit" disabled onclick="searchEventBrite()">
	                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	                        </button>
	                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="register"><i
                        class="fa fa-sign-in"></i> Register</a></li>
                 <li><a href="contact2"><i class="fa fa-comment"></i>
                    Contact</a></li>
            </ul>
        </div>
    </div>
        </nav>



        <div id="main">
            <div class="container">
			    <div class="row">
			        <div class="col-md-offset-3 col-md-6">
			            <div class="panel panel-default">
			                <div class="panel-heading"> <strong class="">Login</strong>
			
			                </div>
			                <div class="panel-body">
			                    <form:form class="form-horizontal" role="form" modelAttribute="user" action="login.html" method="POST">
			                        <div class="form-group">
			                            <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
			                            <div class="col-sm-9">
			                                <form:input type="email" class="form-control" id="inputEmail3" placeholder="Email"
			                                      path = "email" required=""/>
			                                      <form:errors path="email"/>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label for="inputPassword3" class="col-sm-3 control-label">Password</label>
			                            <div class="col-sm-9">
			                                <form:input type="password" class="form-control" id="inputPassword3" placeholder="Password"
			                                       path ="password" required=""/>
			                                       <form:errors path="password"/>
			                                       
			                                <form:input type="hidden" class="form-control" id="inputPassword4" path ="status"/>
			                                       <form:errors path="password"/>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <div class="col-sm-offset-3 col-sm-9">
			                                <div class="checkbox">
			                                    <label class="">
			                                        <input type="checkbox" class="">Remember me</label>
			                                </div>
			                            </div>
			                        </div>
			                        <div class="form-group last">
			                            <div class="col-sm-offset-3 col-sm-9">
			                                <button type="submit" class="btn btn-success btn-sm">Sign In</button>
			                                <button type="reset" class="btn btn-default btn-sm">Reset</button>
			                            </div>
			                        </div>
			                    </form:form>
			                </div>
			                <div class="panel-footer">Not Registered? <a href="register" class="">Register here</a>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
        </div>
    </body>
</html>