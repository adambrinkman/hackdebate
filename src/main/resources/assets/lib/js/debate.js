var post;
$(document).ready(function() {
	// var socket = new ReconnectingWebSocket("ws://localhost:" + getPort() + "/debatechat");
	var socket = new ReconnectingWebSocket();
  socket.debug = true;
  socket.timeoutInterval = 5400;

	$('#send-post').click(function(e) {
		e.preventDefault();
		var userId = '123';
		var timestamp = new Date().getTime();
		var text = $('#debate-text').val();
		post = createPost(userId, timestamp, text);
		console.log("post: " + JSON.stringify(post));
		socket.send(JSON.stringify(post));
	});

	socket.onmessage = function(res) {
		addOpinionColumn($.parseJSON(res.data));
	};
});

function createPost(userId, timestamp, text) {
	return {
		"user_id": userId,
		"timestamp": timestamp,
		"text": text
	};
}

function addOpinionColumn(p) {
	console.log("text: " + p.text);
	var column = $('#prosColumnBody');
	var textElement = $('<td></td>').text(p.text);
	var nameElement = $('<td></td>').text(p.user_id);
	var row = $('<tr>/<tr>');
	row.append(nameElement);
	row.append(textElement);
	column.append(row);
	console.log("added!");
}
