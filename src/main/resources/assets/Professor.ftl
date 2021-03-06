<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>HackDebate: Welcome Professor!, Throw your Posts</title>

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
                <a class="navbar-brand" href="index.html">Professor's Board</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <!--dropdown for message-->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>John Smith</strong></h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading">
                                            <strong>John Smith</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading">
                                            <strong>Professor</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div> <!-- /.media-->
                            </a>
                        </li><!-- /.message-preview-->
                        <li class="message-footer">
                            <a href="#">Read All New Replies</a>
                        </li>
                    </ul><!-- /.dropdown-menu -->
                </li><!-- /.dropdown-->
                <li class="dropdown">
                    <!-- dropdown for alert-->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu alert-dropdown">
                        <li>
                            <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">View All</a>
                        </li>
                    </ul><!-- /.dropdown-menu -->
                </li><!-- /.dropdown-->
                <li class="dropdown">
                    <!-- dropdown for user account-->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <!-- dropdown for user account-->
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul><!-- /.dropdown-menu -->
                </li><!-- /.dropdown -->
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
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <form class="col-md-12" method="post" action="/insertTopic">
                                            <div class="form-group">
                                                <strong>Create a new Post:</strong>
                                            </div>
                                            <div class="form-group">
                                                <input type="text" class="form-control input-lg" name="topic" required />
                                            </div>
                                            <div class="form-group">
                                                <input type="datetime-local" class="form-control" name="begindate" required/>
                                            </div>
                                            <div class="form-group">
                                                <input type="datetime-local" class="form-control" name="enddate" required/>
                                            </div>
                                            <div class="form-group">
                                                <strong>Want to provide a reference? (Optional)</strong>
                                            </div>
                                            <div class="form-group">
                                                    <input list="books" name="book" >
                                                    <datalist id="books">
                                                        <option value="Java 6">
                                                        <option value="Java 8">
                                                        <option value="C++">
                                                        <option value="C">
                                                        <option value="C#">
                                                    </datalist>
                                                </form>
                                            </div>
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-primary btn-lg btn-block"  />
                                            </div>
                                        </form>
                                    </div>
                                    <!--
                                    <div class="col-sm-2">
                                        <div class="panel panel-danger text-center">
                                            <div class="panel-heading"><small><h3 class="panel-title">End Time: </h3>10/25/2015 10:00 AM</small></div>
                                        </div>
                                    </div>
                                    -->
                                    <!-- /.col-sm-3 and /.panel-->
                                    <!-- /.row -->


                                </div>
                            </div>
                        </div><!-- /.panel -->
                    </div>
                </div><!-- /#topicHeading -->
                <!-- Debate post display board-->
                <div class="row">
                    <div class="input-group">
                        <input type="text" class="form-control" id="debate-text" placeholder="Enter your opinion" />
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" id="send-post">SEND</button>
                        </span>
                    </div>
                </div>
                <!-- /.row-->

            </div><!-- /.row vdivide-->

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/js/bootstrap.min.js"></script>
    <script src="lib/js/debate.js"></script>

    <script>
        $( "input[name='book']" ).keyup(function() {
            var value = $( "input[name='book']" ).val();
            console.log( value );

            $.ajax({
              url: '/books',
              type: "GET",
              data: value,
              success: function (data) {
                 console.log(data);
              }
            });
        });
    </script>
</body>

</html>