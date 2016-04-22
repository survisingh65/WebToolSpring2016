/**
 * 
 */

console.log("This is js");

function initializeMap() {
	  var mapProp = {
	    center:new google.maps.LatLng(42.3601,-71.0589),
	    zoom:12,
	    mapTypeId:google.maps.MapTypeId.ROADMAP
	  };
	  var map=new google.maps.Map(document.getElementById("eventsList"),mapProp);
	  
	  popularEvents(map);
}

function popularEvents(map){
	$.ajax({
		type: 'GET',
		url:'https://www.eventbriteapi.com/v3/events/search/?expand=venue&location.latitude=42.35&location.longitude=-71.06&location.within=10mi&token=E7QFPNMJDMVX6YV5S3KS',
		success: function(response){
			console.log("Got response");
			console.log(response.events);
			for (i = 0; i < response.events.length; i++) {
                createMarker(map, response.events[i], i, response);
          }
		}
	});
}

var createMarker = function (map, info, i, response) {
    var marker = new google.maps.Marker({
        map: map,
        position: new google.maps.LatLng(info.venue.latitude, info.venue.longitude),
        title: info.name.text,
        animation: "Animation.BOUNCE",
        icon: "https://upload.wikimedia.org/wikipedia/commons/9/92/Map_marker_icon_%E2%80%93_Nicolas_Mollet_%E2%80%93_Home_%E2%80%93_People_%E2%80%93_Default.png"
    });
    marker.content = '<div class="infoWindowContent">' + info.venue.name + '</div>';

    var et = info.id;
    var infoWindow = new google.maps.InfoWindow();
    google.maps.event.addListener(marker, 'click', function () {
        infoWindow.setContent('<a id="'+ i +'" href="'+ eventDetailsMapInfo(i, response) +'"><h4 style="color:#3B5998">' + marker.title + '</h4></a>' + marker.content);
        infoWindow.open(map, marker);
    });
}



function searchEventBrite(el){
	var search = $('#searchid').val();
	console.log("This is eb");
	$.ajax({
		type: 'GET',
		url:'https://www.eventbriteapi.com/v3/events/search/?expand=venue&q='+search+'&location.latitude=42.35&location.longitude=-71.06&location.within=10mi&token=E7QFPNMJDMVX6YV5S3KS',
		success: function(response){
			console.log(response);
			var objul = $('<ul></ul>');
			objul.addClass('list-group');
			for(var i=0; i< response.events.length; i++){
				var objli = $('<li></li>');
				objli.addClass('list-group-item');
				
				var objdivhidden = $('<div id="hid'+i+'" hidden></div>');
				objdivhidden.text(response.events[i].description.text);
				
				objli.append(objdivhidden);
				
				var objhref = $('<a id="'+ i +'" href="#"></a>');
				objhref.click(function (g){
					eventDetails(event, response);
				});
				objli.append(objhref);
				
				var objdiv = $('<div></div>');
				objdiv.addClass('row');
				
				var objdivleft = $('<div></div>');
				objdivleft.addClass('col-md-4');
				
				var objimg = $('<img style="width: 60%; margin-left: 30px; margin-top: 10px;" alt ="no photo available"></img>');
				if(response.events[i].logo == null){
					objimg.attr('src', '');
				}else{
					objimg.attr('src',response.events[i].logo.url);	
				}
				objdivleft.append(objimg);
				objdiv.append(objdivleft);
				
				
				var objdivright = $('<div></div>');			
				objdivright.addClass('col-md-8');
				objdiv.append(objdivright);
				
				var objpara1 = $('<p style ="color:#3B5998;font-weight:bold;"></p>');
				objpara1.text(response.events[i].name.text);
				objdivright.append(objpara1);
				
				var objpara2 = $('<p style ="color:#3B5998;font-style:italic;"></p>');
				objpara2.text("Venue: " + response.events[i].venue.name);
				objdivright.append(objpara2);
				
				var objpara3 = $('<p style ="color:#3B5998;font-style:italic;"></p>');
				objpara3.text(response.events[i].venue.address.address_1 + " " + response.events[i].venue.address.city);
				objdivright.append(objpara3);
				
				var objpara4 = $('<p style ="color:#3B5998;font-style:italic;"></p>');
				objpara4.text("Date & Time: " + response.events[i].start.local);
				objdivright.append(objpara4);
				
				objhref.append(objdiv);
				objul.append(objli);
				}
			$('#eventsList').empty();
			$('#eventsList').append(objul); 
		}  
	});
	
	
}

function eventDetails(e, response){
	console.log("details");
	var x = e.currentTarget.id;
	var y = response.events[x];
	
	var objul = $('<ul></ul>');
	objul.addClass('list-group');
	$('#eventsList').empty();
	$('#eventsList').append(objul);
	
	var objli = $('<li></li>');
	objli.addClass('list-group-item');
	objul.append(objli);
	
	var objdiv = $('<div></div>');
	objdiv.addClass('row');
	objli.append(objdiv);
	
	var objdivleft = $('<div></div>');
	objdivleft.addClass('col-md-4');
	
	var objimg = $('<img style="width: 60%; margin-left: 30px; margin-top: 10px;" alt ="no photo available"></img>');
	if(y.logo == null){
		objimg.attr('src', '');
	}else{
		objimg.attr('src',y.logo.url);	
	}
	objdivleft.append(objimg);
	objdiv.append(objdivleft);
	
	var submitBtn = $('<button/>')
    					.text("I'm Going")
    					.addClass('btn btn-primary')
    					.css({'display' : 'block', 'text-align' : 'center', 'margin-left':'30px', 'margin-top':'10px'})
    					.click(function () { 
    						saveEvent(); 
    						});
	
	objdivleft.append(submitBtn);
	
	var objdivright = $('<div></div>');			
	objdivright.addClass('col-md-8');
	objdiv.append(objdivright);
	
	var objpara1 = $('<p id="eventName" style ="color:#3B5998;font-weight:bold;"></p>');
	objpara1.text(y.name.text);
	objdivright.append(objpara1);
	
	var objpara2 = $('<p id="eventVenue" style ="color:#3B5998;font-style:italic;"></p>');
	objpara2.text("Venue: " + y.venue.name);
	objdivright.append(objpara2);
	
	var objpara3 = $('<p id="eventAddress" style="color:#3B5998;font-style:italic;"></p>');
	objpara3.text(y.venue.address.address_1 + " " + y.venue.address.city);
	objdivright.append(objpara3);
	
	var objpara4 = $('<p id="eventTime" style ="color:#3B5998;font-style:italic;"></p>');
	objpara4.text("Date & Time: " + y.start.local);
	objdivright.append(objpara4);
	
	var objpara5 = $('<p id="eventDetails" style ="color:#3B5998;font-style:italic;"></p>');
	objpara5.html(y.description.html);
	objdivright.append(objpara5);
	
	var objpara6 = $('<p id="eventId" hidden style ="color:#3B5998;font-style:italic;"></p>');
	objpara6.html(y.id);
	objdivright.append(objpara6);
	
	
}


function eventDetailsMapInfo(e, response){
	console.log("details");
	//var x = e.currentTarget.id;
	var y = response.events[e];
	
	var objul = $('<ul></ul>');
	objul.addClass('list-group');
	$('#eventsList').empty();
	$('#eventsList').append(objul);
	
	var objli = $('<li></li>');
	objli.addClass('list-group-item');
	objul.append(objli);
	
	var objdiv = $('<div></div>');
	objdiv.addClass('row');
	objli.append(objdiv);
	
	var objdivleft = $('<div></div>');
	objdivleft.addClass('col-md-4');
	
	var objimg = $('<img style="width: 60%; margin-left: 30px; margin-top: 10px;" alt ="no photo available"></img>');
	if(y.logo == null){
		objimg.attr('src', '');
	}else{
		objimg.attr('src',y.logo.url);	
	}
	objdivleft.append(objimg);
	objdiv.append(objdivleft);
	
	var submitBtn = $('<button/>')
    					.text("I'm Going")
    					.addClass('btn btn-primary')
    					.css({'display' : 'block', 'text-align' : 'center', 'margin-left':'30px', 'margin-top':'10px'})
    					.click(function () { 
    						saveEvent(); 
    						});
	
	objdivleft.append(submitBtn);
	
	var objdivright = $('<div></div>');			
	objdivright.addClass('col-md-8');
	objdiv.append(objdivright);
	
	var objpara1 = $('<p id="eventName" style ="color:#3B5998;font-weight:bold;"></p>');
	objpara1.text(y.name.text);
	objdivright.append(objpara1);
	
	var objpara2 = $('<p id="eventVenue" style ="color:#3B5998;font-style:italic;"></p>');
	objpara2.text("Venue: " + y.venue.name);
	objdivright.append(objpara2);
	
	var objpara3 = $('<p id="eventAddress" style="color:#3B5998;font-style:italic;"></p>');
	objpara3.text(y.venue.address.address_1 + " " + y.venue.address.city);
	objdivright.append(objpara3);
	
	var objpara4 = $('<p id="eventTime" style ="color:#3B5998;font-style:italic;"></p>');
	objpara4.text("Date & Time: " + y.start.local);
	objdivright.append(objpara4);
	
	var objpara5 = $('<p id="eventDetails" style ="color:#3B5998;font-style:italic;"></p>');
	objpara5.html(y.description.html);
	objdivright.append(objpara5);
	
	var objpara6 = $('<p id="eventId" hidden style ="color:#3B5998;font-style:italic;"></p>');
	objpara6.html(y.id);
	objdivright.append(objpara6);
	
	
}



function saveEvent(){

	
	var id = $('#eventId').text();
	var venue = $('#eventVenue').text();
	var address = $('#eventAddress').text();
	var details = $('#eventDetails').text();
	var time = $('#eventTime').text();
	var name = $('#eventName').text();

	var userEvent = { 
		       eventId : id,
		       venue : venue,
		       address : address,
		       details : details,
		       time : time,
		       name : name
		}
	
	console.log("hi");
	 $.ajax({
	        type: "POST",
	        url: "saveevent",
	        data: userEvent,
	        success: function(response){
	        	console.log("Success");
	        	console.log(response);
	        	document.open();
	            document.write(response);
	            document.close();
	        },
	        error: function(){
	        	console.log("error");
	        }
	    });
}


function getPreference(){
	$.ajax({
        type: "POST",
        url:  "getprefernceurl",
        success: function(response){
        	console.log(response);
        }
	});
}

function savePreference(){
	var cat = [];
	for(var i=0; i < document.preferenceform.pref.length; i++){
		if(document.preferenceform.pref[i].checked){
		   cat.push(document.preferenceform.pref[i].value);		   
		}	
	}
	var userpref = {
			up: cat.toString()			
	}
	
	console.log(cat);
	$.ajax({
        type: "POST",
        url:  "saveprefernceurl",
        data: userpref,
        success: function(response){
        	console.log("Success");
        	console.log(response);
        	document.open();
            document.write(response);
            document.close();
        },
        error: function(){
        	console.log("error");
        }
    });
}
