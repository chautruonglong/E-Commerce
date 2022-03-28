$(document).ready(() => {
    $("#editCustomerForm").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        errorPlacement: function(label, element) {
            label.addClass('text-red-500 text-xs italic');
            label.insertAfter(element);
        },
        wrapper: 'span',
        rules: {
            password: {
                required: true
            },
            name: {
                required: true
            }
        }
    });

    $("#saveSubmitButton").click(() => {
        if($("#editCustomerForm").valid()) {
            const customer = {
                password: $("#editCustomerForm input[name=password]").val(),
                name: $("#editCustomerForm input[name=name]").val(),
                phone: $("#editCustomerForm input[name=phone]").val(),
                address: $("#editCustomerForm input[name=address]").val()
            };

            const id = $("#editCustomerForm").attr("value");

            updateCustomer(customer, id);
        }
    });
});
