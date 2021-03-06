<!DOCTYPE html>
<!-- For data purposes: #userName for the name at the top navigation bar, #textbook for the textbook references under post topic -->
<!-- For textbook, the classes are .txtName, .txtAuthor, .txtIsbn -->
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>HackDebate: Debate Board of John Smith</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="lib/css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="lib/js/reconnecting-websocket.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
      var PORT = "${PORT}";
      var USER_NAME = "${userName}";
      function getPort() {
          return PORT;
      }
      function getUserName() {
        return USER_NAME;
      }
    </script>
</head>

<body>
    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">${userName}'s Board</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li id="userName" class="navbar-brand"><small><i class="fa fa-user"></i>${userName}</small></li>
            </ul><!-- /.nav navbar-right top-nav-->
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="debate.ftl"></i> Biology</a>
                    </li>
                    <li>
                        <a href="literature.ftl"></i> Literature</a>
                    </li>
                    <li>
                        <a href="history.ftl"></i> History</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">
            <div class="container-fluid">

                <!-- Heading Panel with debate topic -->
                <div id="topicHeading" class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading"><div class="row">
                                <div id="topic" class="col-sm-10">
                                    <p class="lead topicText"><strong>Topic: </strong><small><em>Should there be more strict regulation of antibiotics/ should patients look elsewhere like herbals, diet, lifestyle for treatments?</em></small></p>
                                </div><!-- /#topic.col-sm-10 for Topic-->
                                <div id="endTime" class="col-sm-2">
                                    <div class="panel panel-danger text-center">
                                        <div class="panel-heading"><small><h3 class="panel-title">End Time: </h3>10/25/2015 10:00 AM</small></div>
                                    </div><!-- /.panel panel-danger text-center-->
                                </div><!-- /#endTime.col-sm-2 -->
                                <div id="textbook" class="col-sm-12" style="margin-top: -5px; color: grey;">
                                    <h5>Recommended Sources:</h5>
                                    <ul>
                                        <li>
                                            <base><em><span class="txtName">Biology (Hardcover)</span>, <span class="txtAuthor"> Neil A. Campbell</span>, ISBN: <span class="txtIsbn"> 978-0805371468</span></em></base>
                                        </li>
                                        <li>
                                            <base><em><span class="txtName">Molecular Cell Biology (Hardcover)</span>, <span class="txtAuthor"> Harvey Lodish</span>, ISBN: <span class="txtIsbn">978-0716743668</span></em></base>
                                        </li>
                                    </ul>
                                </div><!-- /#textbook.col-sm-12-->
                            </div></div><!-- /.panel-heading and /.row -->
                        </div><!-- /.panel panel-default -->
                    </div><!-- /.col-lg-12 -->
                </div><!-- /#topicHeading.row -->

                <!-- Debate post display board-->
                <div class="row vdivide text-center">
                    <div class="col-lg-12">
                        <h3>In Favor</h3>
                        <div class="table-responsive">
                            <table id="prosColumn" class="table table-hover table-striped">
                              <thead>
                                <tr>
                                  <th class="debatorName">Name</th>
                                  <th class="debatorArg">Argument</th>
                                </tr>
                              </thead>
                              <tbody id="prosColumnBody">
                              </tbody>
                            </table>
                        </div><!-- /.table-responsive -->
                    </div><!-- /#prosColumn.col-lg-6 -->
                </div>
                <!-- /.row -->
                            </div>
            <!-- /.container-fluid -->
                <div class="col-lg-12">
                  <div class="input-group">
                    <input type="text" class="form-control" id="discuss-text" placeholder="Enter your opinion" />
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" id="send-post">SEND</button>
                    </span>
                  </div>
                </div><!-- /.col-lg-12-->


        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/js/bootstrap.min.js"></script>
    <script src="lib/js/gd.js"></script>
</body>

</html>
