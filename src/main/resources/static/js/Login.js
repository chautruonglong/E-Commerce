$(document).ready(() => {
    $("#loginForm").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        errorPlacement: function(label, element) {
            label.addClass('text-red-500 text-xs italic');
            label.insertAfter(element);
        },
        wrapper: 'span',
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true
            }
        }
    });

    $("#signupForm").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        errorPlacement: function(label, element) {
            label.addClass('text-red-500 text-xs italic');
            label.insertAfter(element);
        },
        wrapper: 'span',
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true
            },
            name: {
                required: true
            }
        }
    });

    $("#loginModalCloseButton").click(() => {
        $("#loginModal").hide();
    });

    $("#loginButton").click(() => {
        if($("#loginForm").valid()) {
            const email = $("#loginForm input[name=email]").val();
            const password = $("#loginForm input[name=password]").val();

            login(email, password);
        }
    });

    $("#loginForm").keydown(e => {
        if(e.keyCode === 13){
            if($("#loginForm").valid()) {
                const email = $("#loginForm input[name=email]").val();
                const password = $("#loginForm input[name=password]").val();

                login(email, password);
            }
        }
    });

    $("#signupTabButton").click(() => {
        $("#loginForm").hide();
        $("#signupForm").show();

        $("#signinTabButton").show();
        $("#signupTabButton").hide();

        $("#titleLoginForm").text("Sign up your account");
    });

    $("#signinTabButton").click(() => {
        $("#loginForm").show();
        $("#signupForm").hide();

        $("#signinTabButton").hide();
        $("#signupTabButton").show();

        $("#titleLoginForm").text("Sign in to your account");
    });

    $("#signupSubmitButton").click(() => {
        if($("#signupForm").valid()) {
            const customer = {
                email: $("#signupForm input[name=email]").val(),
                password: $("#signupForm input[name=password]").val(),
                name: $("#signupForm input[name=name]").val(),
                phone: $("#signupForm input[name=phone]").val(),
                address: $("#signupForm input[name=address]").val()
            };

            signup(customer);
        }
    });
});
