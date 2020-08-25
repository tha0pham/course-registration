$(function () {
    $("#student").validate({
        rules: {
            code: {
                required: true,
                studentID: true,
            },
            pid: {
                required: true,
                digits: true,
                minlength: 9,
                maxlength: 12,
            },
            bdate: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 2,
            },
            byear: {
                required: true,
                digits: true,
                minlength: 4,
                maxlength: 4,
            },
            gender: {
                required: true,
            },
            image: {
                required: true,
            },
            lname: {
                required: true,
                minlength: 2,
                maxlength: 50,
            },
            fname: {
                required: true,
                minlength: 2,
                maxlength: 30,
            },
            phone: {
                required: true,
                digits: true,
                minlength: 9,
                maxlength: 12,
            },
            email: {
                required: true,
                email: true,
            },
            address: {
                required: true,
                minlength: 4,
                maxlength: 100,
            },
        },
        messages: {
            code: {
                required: "Student ID is required.",
            },
            pid: {
                required: "Personal ID is required.",
                digits: "Invalid personal ID.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            bdate: {
                required: "Birth date is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            byear: {
                required: "Birth year is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            image: {
                required: "Image is required.",
            },
            gender: {
                required: "Select a gender.",
            },
            lname: {
                required: "Last name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            fname: {
                required: "First name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            phone: {
                required: "Phone number is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            email: {
                required: "Email is required.",
                email: "Wrong format.",
            },
            address: {
                required: "Address is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
        }
    });

    $("#instructor").validate({
        rules: {
            code: {
                required: true,
                digits: true,
                minlength: 9,
                maxlength: 12,
            },
            bdate: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 2,
            },
            byear: {
                required: true,
                digits: true,
                minlength: 4,
                maxlength: 4,
            },
            image: {
                required: true,
            },
            gender: {
                required: true,
            },
            lname: {
                required: true,
                minlength: 2,
                maxlength: 50,
            },
            fname: {
                required: true,
                minlength: 2,
                maxlength: 30,
            },
            phone: {
                required: true,
                digits: true,
                minlength: 9,
                maxlength: 12,
            },
            email: {
                required: true,
                email: true,
            },
            address: {
                required: true,
                minlength: 4,
                maxlength: 100,
            },
        },
        messages: {
            code: {
                required: "Personal ID is required.",
                digits: "Wrong format.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            bdate: {
                required: "Birth date is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            byear: {
                required: "Birth year is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            image: {
                required: "Image is required.",
            },
            gender: {
                required: "Select a gender.",
            },
            lname: {
                required: "Last name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            fname: {
                required: "First name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            phone: {
                required: "Phone number is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            email: {
                required: "Email is required.",
                email: "Wrong format.",
            },
            address: {
                required: "Address is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
        }
    });

    $("#user").validate({
        rules: {
            username: {
                required: true,
                minlength: 4,
                maxlength: 12,
            },
            cdate: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 2,
            },
            cyear: {
                required: true,
                digits: true,
                minlength: 4,
                maxlength: 4,
            },
            lname: {
                required: true,
                minlength: 2,
                maxlength: 50,
            },
            fname: {
                required: true,
                minlength: 2,
                maxlength: 30,
            },
            gender: {
                required: true,
            },
            phone: {
                required: true,
                digits: true,
                minlength: 9,
                maxlength: 12,
            },
            email: {
                required: true,
                email: true,
            },
            address: {
                required: true,
                minlength: 4,
                maxlength: 100,
            },
        },
        messages: {
            username: {
                required: "Username is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            cdate: {
                required: "Created date is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            cyear: {
                required: "Created year is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            lname: {
                required: "Last name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            fname: {
                required: "First name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            gender: {
                required: "Select a gender.",
            },
            phone: {
                required: "Phone number is required.",
                minlength: "Too short.",
                maxlength: "Too long",
                digits: "Wrong format.",
            },
            email: {
                required: "Email is required.",
                email: "Wrong format.",
            },
            address: {
                required: "Address is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
        }
    });

    $("#faculty").validate({
        rules: {
            name: {
                required: true,
                minlength: 4,
                maxlength: 50,
            },
            description: {
                required: true,
                minlength: 4,
                maxlength: 200,
            },
        },
        messages: {
            name: {
                required: "Name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            description: {
                required: "Description is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
        }
    });

    $("#major").validate({
        rules: {
            name: {
                required: true,
                minlength: 4,
                maxlength: 50,
            },
            description: {
                required: true,
                minlength: 4,
                maxlength: 200,
            },
        },
        messages: {
            name: {
                required: "Name is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
            description: {
                required: "Description is required.",
                minlength: "Too short.",
                maxlength: "Too long",
            },
        }
    });

    $("#course").validate({
        rules: {
            code: {
                required: true,
                minlength: 4,
                maxlength: 20,
            },
            name: {
                required: true,
                minlength: 4,
                maxlength: 100,
            },
            description: {
                required: true,
                minlength: 4,
                maxlength: 400,
            },
            credit: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 2,
            },
            cost: {
                required: true,
                number: true,
                minlength: 1,
                maxlength: 3,
            },
            hour: {
                required: true,
                number: true,
                minlength: 1,
                maxlength: 3,
            },
            passingScore: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 2,
            },
        },
        messages: {
            code: {
                required: "Required.",
                minlength: "Minimum length is 4",
                maxlength: "Maximum length is 20",
            },
            name: {
                required: "Required.",
                minlength: "Minimum length is 4",
                maxlength: "Maximum length is 100",
            },
            description: {
                required: "Required.",
                minlength: "Minimum length is 4",
                maxlength: "Maximum length is 400",
            },
            credit: {
                required: "Required.",
                digits: "Should be digits.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            cost: {
                required: "Required.",
                number: "Should be numbers.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            hour: {
                required: "Required.",
                number: "Should be numbers.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            passingScore: {
                required: "Required.",
                digits: "Should be digits.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
        }
    });

    $("#clazz").validate({
        rules: {
            name: {
                required: true,
                minlength: 3,
                maxlength: 20,
            },
            week: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 2,
            },
            capacity: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 3,
            },
            maxabsent: {
                required: true,
                number: true,
                minlength: 1,
                maxlength: 4,
            },
            midterm: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 3,
            },
            final: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 3,
            },
            assignment: {
                required: true,
                digits: true,
                minlength: 1,
                maxlength: 3,
            },
        },
        messages: {
            name: {
                required: "Required.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            week: {
                required: "Required.",
                digits: "Should be digits.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            capacity: {
                required: "Required.",
                digits: "Should be digits.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            maxabsent: {
                required: "Required.",
                number: "Should be numbers.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            midterm: {
                required: "Required.",
                digits: "Should be digits.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            final: {
                required: "Required.",
                digits: "Should be digits.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
            assignment: {
                required: "Required.",
                digits: "Should be digits.",
                minlength: "Too short.",
                maxlength: "Too long.",
            },
        }
    });

    $("#contacts-form").validate({
        rules: {
            name: {
                required: true,
                minlength: 4,
                maxlength: 50,
            },
            email: {
                required: true,
                email: true,
            },
            website: {
                required: true,
                minlength: 4,
                maxlength: 100,
            },
            message: {
                required: true,
                minlength: 4,
                maxlength: 500,
            },
        },
        messages: {
            name: {
                required: "",
                minlength: "",
                maxlength: "",
            },
            email: {
                required: "",
                email: "",
            },
            website: {
                required: "",
                minlength: "",
                maxlength: "",
            },
            message: {
                required: "",
                minlength: "",
                maxlength: "",
            },
        }
    });

    $.validator.addMethod("studentID", function (value, element) {
        return /\d{2}-\d{2}-[0,1]-[1,2,3]\d{4}/.test(value);
    }, "Invalid Student ID.");
});