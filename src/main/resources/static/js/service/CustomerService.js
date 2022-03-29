const login = (email, password) => {
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        processData: false,
        url: "/api/v1/customers/login",
        data: JSON.stringify({
            email,
            password
        }),
        success: () => {
            window.location.reload();
        },
        error: data => {
            $("#loginMessage").text(data.responseJSON.message);
        }
    });
}

const logout = () => {
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        processData: false,
        url: "/api/v1/customers/logout",
        success: () => {
            window.location.replace("/home");
        }
    });
}

const signup = (customer) => {
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        processData: false,
        data: JSON.stringify(customer),
        url: "/api/v1/customers",
        success: () => {
            $("#loginForm").show();
            $("#signupForm").hide();

            $("#signinTabButton").hide();
            $("#signupTabButton").show();

            $("#titleLoginForm").text("Sign in to your account");
            $("#loginMessage").html('<span class="text-emerald-600 text-xs italic">Sign up successfully</span>')

            clearTimeout(timeout);
            $("div[id$=Snackbar]").hide();
            $("#successSnackbar").toggle("slide", {
                direction: "right",
                complete: () => {
                    timeout = setTimeout(() => {
                        $("#successSnackbar").hide();
                    }, 3000);
                }
            }, 300);
        },
        error: data => {
            $("#signupMessage").text(data.responseJSON.message);
        }
    });
}

const updateCustomer = (customer, id) => {
    $.ajax({
        type: "PUT",
        contentType: 'application/json',
        processData: false,
        data: JSON.stringify(customer),
        url: `/api/v1/customers/${id}`,
        success: () => {
            $("#editMessage").html('<span class="text-emerald-600 text-xs italic">Update successfully</span>');

            clearTimeout(timeout);
            $("div[id$=Snackbar]").hide();
            $("#successSnackbar").toggle("slide", {
                direction: "right",
                complete: () => {
                    timeout = setTimeout(() => {
                        $("#successSnackbar").hide();
                    }, 3000);
                }
            }, 300);
        },
        error: data => {
            $("#editMessage").text(data.responseJSON.message);
        }
    });
}
