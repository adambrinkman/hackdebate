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
</head>
<body>
    <div class="container" id="comments">
        <div class="page-header">
            <h1>Welcome!</h1>
        </div>
        
        <form role="form">
            <div class="panel">
                <div class="panel-body">
                    <label class="radio-inline" "><!-- style=" width 200px; height 100px;" -->
                        <input type="radio" name="optradio">
                        College Student
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="optradio">High School Student
                    </label>
                </div>
            </div>
        </form>

        <!-- Simple College Student Login - START -->
        <div class="panel panel-success" style="width: 50%">
            <div class="panel-heading">College Student Login</div>
            <div class="panel-body">
                <form class="col-md-12" method="post" action="/login">
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="Student ID" name="cs_login_sid" required>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control input-lg" placeholder="Email" name="cs_login_email" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" placeholder="Password" name="cs_login_password" required>
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
        <!-- Simple High School Student Login - START -->
        <div class="panel panel-success" style="width: 50%">
            <div class="panel-heading">High School Student Login</div>
            <div class="panel-body">
                <form class="col-md-12" action="/login" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="Name" name="hs_login_name" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" placeholder="School Name" name="hs_login_schoolname" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" placeholder="Password" name="hs_login_password" required>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-lg btn-block">Sign In
                        <span><a href="#">Need help?</a></span>
                        <span class="pull-right"><a href="#">New Registration</a></span>
                    </div>
                </form>
            </div>
        </div>
        <!-- Simple High School Student Login - END -->
        <!-- Simple High School Student Registration - START -->
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
        <!-- Simple High School Student Registration - END -->


    </div>

    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="home.js"></script>
</body>
</html>