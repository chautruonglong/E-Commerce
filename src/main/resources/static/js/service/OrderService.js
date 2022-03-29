$.get("/tmpl/OrderItem.html", markup => {
    $.template("orderItemTemplate", markup);
});

const createOrder = (order) => {
    $.ajax({
        type: "POST",
        contentType: 'application/json',
        processData: false,
        data: JSON.stringify(order),
        url: "/api/v1/orders",
        success: () => {
            clearTimeout(timeout);
            $("div[id$=Snackbar]").hide();
            $("#newSnackbar").toggle("slide", {
                direction: "right",
                complete: () => {
                    timeout = setTimeout(() => {
                        $("#newSnackbar").hide();
                    }, 3000);
                }
            }, 300);

            $("#productDetail").remove();
        }
    });
}

const fetchMoreOrders = () => {
    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: 'json',
        url: "/api/v1/orders",
        data: {
            page: orderState.page,
            limit: orderState.limit,
        },
        success: data => {
            $.each(data, (key, item) => {
                $.tmpl("orderItemTemplate", { product: item }).appendTo("#orderLists");
            });

            $(document).ready(() => {
                const buttons = $("button[name=removeOrderItemButton]");
                buttons.unbind("click");
                buttons.click(function() {
                    const id = $(this).parent().parent().parent().parent().attr("value");
                    deleteOrder(id);
                });
            });
        }
    });
}

const deleteOrder = (id) => {
    $.ajax({
        type: "DELETE",
        dataType: "json",
        contentType: 'json',
        url: `/api/v1/orders/${id}`,
        success: data => {
            $(`li[name=orderItem][value=${id}]`).remove();

            clearTimeout(timeout);
            $("div[id$=Snackbar]").hide();
            $("#deleteSnackbar").toggle("slide", {
                direction: "right",
                complete: () => {
                    timeout = setTimeout(() => {
                        $("#deleteSnackbar").hide();
                    }, 3000);
                }
            }, 300);
        }
    });
}
