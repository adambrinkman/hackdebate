<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nback</title>
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="nback.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="library/socket.io.js"></script>
    <script>
      var socket =  io.connect('http://localhost:9092');
      console.log("socket initilized");
      socket.on('connect', function() {
        console.log("Client has connected to the server!");
      });

      socket.on('chatevent', function(data) {
        console.log("userName: " + data.userName + ", message: " + data.message);
      });

      socket.on('disconnect', function() {
        console.log("the client has disconnected");
      });
    </script>
  </head>
  <body>
    <div class="container">
      <div class="row12">
        <button type="button" class="btn btn-default btn-lg">Hello World</button>
      </div>
    </div>

    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="home.js"></script>
  </body>
</html>
