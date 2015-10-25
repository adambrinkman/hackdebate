<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HackDebate: Login</title>
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="nback.css" rel="stylesheet">
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="home.js"></script>
    <!--<script src="switchPanel.js"></script>-->

    <script>
    /*
        $(document).ready(function(){
                $("#two").hide();
        });
        $(document).ready(function(){
            $("#radio1").click(function(){
                 //$("#two").hide();
                 //$("#one").show();
                 $("#panel_heading").text("Student Login");
            });
            $("#radio2").click(function(){
               // $("#one").hide();
                //$("#two").show();
                $("#panel_heading").text("Professor Login");
            });
        });
        */
    </script>
</head>
<body>
    <div class="container" id="comments">
        <!-- Main jumbotron for site header -->
        <div class="jumbotron" style="padding-left:20px; padding-top: 0; padding-bottom:3px;  margin-top: 10px; ">
            <h1><span style="color:#04B486;">HackDebate</span>
                <span class="lead text-right" style="color:gray;"><em>Interactive-Based Learning</em><span>
            </h1>
        </div>
        <!--
        <form role="form">
            <div class="panel text-center">
                <div class="panel-body center-block">
                    <label class="radio-inline">
                        <input type="radio" name="optradio" id="radio1" checked>Student
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="optradio" id="radio2">Professor
                    </label>
                </div>
            </div>
        </form>
        -->
        <!-- Simple Student Login - START -->
        <div class="panel panel-success center-block" style="width: 50%" id="one">
            <div class="panel-heading" id="panel_heading">Login</div>
            <div class="panel-body">
                <form class="col-md-12" method="post" action="/login">
                    <div class="form-group">
                        <input type="email" class="form-control input-lg" placeholder="Email" name="login_email" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" placeholder="Password" name="login_password" required>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-lg btn-block" />Sign In
                        <span><a href="#">Need help?</a></span>
                        <span class="pull-right"><a href="#">New Registration</a></span>
                    </div>
                </form>
            </div>
        </div>
        <!-- Simple College Student Login - END -->

        <!-- Simple Professor Login - START -->

        <!--
        <div class="panel panel-success center-block" style="width: 50%" id="two">
            <div class="panel-heading">Professor Login</div>
            <div class="panel-body">
                <form class="col-md-12" action="/login" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="Email" name="pf_login_email" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" placeholder="Password" name="pf_login_password" required>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-lg btn-block">Sign In
                        <span><a href="#">Need help?</a></span>
                        <span class="pull-right"><a href="#">New Registration</a></span>
                    </div>
                </form>
            </div>
        </div>
        -->
        <!-- Simple High School Student Login - END -->


<!-- In Progres Coding. Hidden for now. Simple High School Student Registration - START
        <div class="panel panel-success" style="width: 50%">
            <div class="panel-heading">Student Registration</div>
            <div class="panel-body">
                <form class="col-md-12" action="/register" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="First Name" name="reg_firstname" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="Last Name" name="reg_lastname" required>
                    </div>
                    <div class="form-group">
                        <input type="radio" class="form-control input-lg" name="type" value="College Student" required> College Student
                        <input type="radio" class="form-control input-lg" name="type" value="High School Student" required> High School Student
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="Student ID" name="reg_sid" required>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control input-lg" placeholder="Email" name="reg_email" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="School Name" name="reg_schoolname" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" placeholder="Password" name="reg_password" required>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-lg btn-block">Sign Up
                    </div>
                </form>
            </div>
        </div>
Simple High School Student Registration - END //-->


    </div>


</body>
</html>
