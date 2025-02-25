<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form_area {
            background-color: #becbd1;
            border: 2px solid gray;
            border-radius: 20px;
            box-shadow: 0 8px 22px 0 #2b2b2b;
            padding: 20px;
        }

        .title {
            color: #264143;
            font-weight: 900;
            font-size: 1.5em;
            margin-bottom: 20px;
            text-align: center;
        }

        .sub_title {
            font-weight: 600;
            margin: 5px 0;
        }

        .form_style {
            outline: none;
            border: 2px solid gray;
            box-shadow: 0 2px 15px 0 #2b2b2b;
            width: 100%;
            padding: 12px 10px;
            border-radius: 4px;
            font-size: 15px;
        }

        .btnn {
            padding: 15px;
            margin-top: 25px;
            width: 100%;
            font-size: 15px;
            background: #89a0ab;
            border-radius: 10px;
            font-weight: 800;
            box-shadow: 3px 3px 0px 0px #2b2b2b;
            border: none;
        }
    </style>
    <jsp:include page="components/headlinks.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="components/navbar.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="form_area">
                    <p class="title">Student Registration</p>
                    <form>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="sub_title" for="id">Student ID</label>
                                <input type="number" class="form_style" id="id" name="id" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="sub_title" for="name">Name</label>
                                <input type="text" class="form_style" id="name" name="name" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="sub_title" for="mobileNo">Mobile Number</label>
                                <input type="tel" class="form_style" id="mobileNo" name="mobileNo" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="sub_title">Gender</label>
                                <div>
                                    <input type="radio" id="male" name="gender" value="M" required> <label for="male">Male</label>
                                    <input type="radio" id="female" name="gender" value="F" required> <label for="female">Female</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="sub_title" for="gradPerc">Graduation Percentage</label>
                                <input type="number" step="0.01" class="form_style" id="gradPerc" name="gradPerc" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="sub_title" for="salary">Salary</label>
                                <input type="number" step="0.01" class="form_style" id="salary" name="salary" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="sub_title">Membership Status</label>
                                <div>
                                    <input type="checkbox" id="isMember" name="isMember"> <label for="isMember">Member</label>
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="sub_title" for="dob">Date of Birth</label>
                                <input type="date" class="form_style" id="dob" name="dob" required>
                            </div>
                        </div>
                        <button type="submit" class="btnn">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="components/footerlink.jsp"></jsp:include>
</body>
</html>
