var post;
$(document).ready(function() {
	var socket = new ReconnectingWebSocket("ws://localhost:" + getPort() + "/debatechat");
  socket.debug = true;
  socket.timeoutInterval = 5400;

	console.log("cookie" + getCookie('userId'));
	$('#send-post').click(function(e) {
		e.preventDefault();
		var userId = document.cookie.userId;
		var timestamp = new Date().getTime();
		var opinion = $('#discuss-text').val();
		post = createPost(userId, timestamp, opinion);
		console.log("post: " + JSON.stringify(post));
		socket.send(JSON.stringify(post));
	});

	socket.onmessage = function(res) {
		addOpinionColumn($.parseJSON(res.data));
	};
});

function createPost(userId, timestamp, opinion) {
	return {
		"user_id": userId,
		"timestamp": timestamp,
		"opinion": opinion,
		"user_name": getUserName(),
		"side": "in_favor"
	};
}

function addOpinionColumn(p) {
	console.log("post: " + JSON.stringify(p));
	var column = p.side === 'in_favor' ? $('tbody#prosColumnBody') : $('tbody#consColumnBody');
	var textElement = $('<td></td>').text(p.opinion);
	var nameElement = $('<td></td>').text(p.user_name);
	var row = $('<tr></tr>');
	row.append(nameElement);
	row.append(textElement);
	column.append(row);
}

function getCookie(name) {
	var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
