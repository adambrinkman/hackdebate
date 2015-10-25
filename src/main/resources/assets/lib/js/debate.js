$(document).ready(function() {
	var socket = new ReconnectingWebSocket("ws://localhost:3000/debatechat");
  socket.debug = true;
  socket.timeoutInterval = 5400;

	$('#send-post').click(function(e) {
		e.preventDefault();
		var userId = '';
		var timestamp = new Date().getTime();
		var text = $('#debate-text').val();
		var post = createPost(userId, timestamp, text);
		console.log("post: " + JSON.stringify(post));
		socket.send(post);
	});

	socket.onmessage = function(data) {
		console.log("response: " + JSON.stringify(data));
	};
});

function createPost(userId, timestamp, text) {
	return {
		"userId": userId,
		"timestamp": timestamp,
		"text": text
	};
}
