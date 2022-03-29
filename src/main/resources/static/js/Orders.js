$(document).ready(() => {
    $(window).scroll(function() {
        if($(window).scrollTop() == $(document).height() - $(window).height()) {
            orderState = {
                ...orderState,
                page: orderState.page + 1
            };

            fetchMoreOrders();
        }
    });

    $("button[name=removeOrderItemButton]").click(function() {
        const id = $(this).parent().parent().parent().parent().attr("value");
        deleteOrder(id);
    });
});
