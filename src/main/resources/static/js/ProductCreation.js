$(document).ready(() => {
    $("#addProductButton").click(function() {
        $("#productCreation").show();
    });

    $("#productCreationCloseButton").click(() => {
        $("#productCreation").hide();
    });

    $("#productForm").validate({
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
            },
            thumbnailImage: {
                required: true
            }
        }
    });

    $("#postProductButton").click(function() {
        if($("#productForm").valid()) {
            const formData = new FormData();

            $("#productForm").serializeArray().forEach(item => {
                formData.append(item.name, item.value);
            });

            formData.append("thumbnailImage", $("input[name=thumbnailImage][type=file]")[0].files[0]);

            postProduct(formData);
        }
    });
});
