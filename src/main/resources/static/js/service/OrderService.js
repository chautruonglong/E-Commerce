let timeout;

const createOrder = (order) => {
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        processData: false,
        data: JSON.stringify(order),
        url: "/api/v1/orders",
        success: () => {
            clearTimeout(timeout);
            $("#snackbar").hide();

            $("#snackbar").toggle("slide", {
                direction: "right",
                complete: () => {
                    timeout = setTimeout(() => {
                        $("#snackbar").hide();
                    }, 3000);
                }
            }, 300);

            $("#productDetail").remove();
        }
    });
}
