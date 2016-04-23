/**
 * 
 */

function getPreference(){
	$.ajax({
        type: "GET",
        url:  "getprefernceurl",
        contentType: 'application/json',
        success: function(response){
        	console.log(response);
        	var sts = JSON.parse(response).successmsg.split(",");
        	console.log(sts);
        	
        	for(var i = 0; i<sts.length; i++){
        		console.log(sts[i]);
        		$('input:checkbox[value="'+ sts[i] +'"]').attr("checked", true);
        	}
        },
        error: function(error){
        	console.log(error);
        }
	});
}

function savePreference(){
	var cat = [];
	$('input:checkbox[name=pref]').each(function() 
	{    
		if($(this).is(':checked'))
			cat.push($(this).val());
	});
	
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
        error: function(error){
        	console.log(error);
        }
    });
}

function myFavoriteEvent(){
	$.ajax({
        type: "GET",
        url:  "getprefernceurl",
        contentType: 'application/json',
        success: function(response){
        	console.log(response);
        	var sts = JSON.parse(response).successmsg;
        	console.log(sts.slice(0,-1));
        	searchMyFavoriteEvent(sts.slice(0,-1));
        	
        },
        error: function(error){
        	console.log(error);
        }
	});
}

function searchMyFavoriteEvent(preferenceList){
	console.log(preferenceList);
	$.ajax({
		type: 'GET',
		url:'https://www.eventbriteapi.com/v3/events/search/?expand=venue&subcategories='+preferenceList+'&token=E7QFPNMJDMVX6YV5S3KS',
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
			$('#map').hide();
			$('#eventsList').append(objul); 
		}  
	});

}

function deleteEvent(eventid){
	console.log(eventid);

	var eventId = {
			id: eventid			
	}
	
	$.ajax({
        type: "POST",
        url:  "deleteevent",
        data: eventId,
        success: function(response){
        	console.log("Success");
        	console.log(response);
        	document.open();
            document.write(response);
            document.close();
        },
        error: function(error){
        	console.log(error);
        }
    });

}
