$(document).ready(function() {
	var socket = new ReconnectingWebSocket("ws://localhost:3000/debatechat");
  socket.debug = true;
  socket.timeoutInterval = 5400;

	$('#send-post').click(function() {
		var userId = '';
		var timestamp = new Date().getTime();
		var text = $('#debate-text');
		var post = createPost();
		socket.send(post);
	});
});

function createPost(userId, timestamp, text) {
	var post = {
		"userId": userId,
		"timestamp": timestamp,
		"text": text
	};
	return post;
}
