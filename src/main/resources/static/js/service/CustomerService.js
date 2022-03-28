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
            window.location.reload();
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
        },
        error: data => {
            $("#signupMessage").text(data.responseJSON.message);
        }
    });
}
