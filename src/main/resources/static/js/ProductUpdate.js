$(document).ready(() => {
    $("#productUpdateCloseButton").click(() => {
        $("#productUpdate").hide();
    });

    $("#productFormUpdate").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        errorPlacement: function(label, element) {
            label.addClass('text-red-500 text-xs italic');
            label.insertAfter(element);
        },
        wrapper: 'span',
        rules: {
            name: {
                required: true
            },
            category: {
                required: true
            },
            price: {
                required: true,
                number: true,
                min: 0
            },
            discount: {
                required: true,
                number: true,
                min: 0,
                max: 100
            }
        }
    });

    $("#updateProductButton").click(function() {
        if($("#productFormUpdate").valid()) {
            const formData = new FormData();

            $("#productFormUpdate").serializeArray().forEach(item => {
                formData.append(item.name, item.value);
            });

            const file = $("#productFormUpdate input[name=thumbnailImage][type=file]")[0].files[0];
            if(file) {
                formData.append("thumbnailImage", file);
            }

            updateProduct($("#productUpdate").attr("value"), formData);
        }
    });
});
