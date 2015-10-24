$(document).ready(function() {
	var socket = new ReconnectingWebSocket("ws://localhost:3000/debatechat");
  socket.debug = true;
  socket.timeoutInterval = 5400;
});