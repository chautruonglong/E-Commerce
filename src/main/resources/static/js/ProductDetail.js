const addEventsToProductDetail = (data) => {
    $.tmpl("productDetailTemplate", { product: data }).appendTo("body");

    $("#productDetailCloseButton").click(() => {
        $("#productDetail").remove();
    });

    $("#deleteProductButton").click(() => {
        deleteProduct(data.id);
    });

    $("#editProductButton").click(() => {
        $("#productDetail").remove();
        $("#productUpdate").show();

        $("#productUpdate").attr("value", data.id);
        $("#productFormUpdate input[name=name]").attr("value", data.name);
        $("#productFormUpdate input[name=category]").attr("value", data.category);
        $("#productFormUpdate input[name=price]").attr("value", Number(data.price.replace(/[^0-9.-]+/g, "")));
        $("#productFormUpdate input[name=discount]").attr("value", Number(data.discount.replace(/[^0-9.-]+/g, "")));
        $("#productFormUpdate textarea[name=description]").val(data.description);
    });

    $("#orderProductButton").click(() => {
        if($("#userHeaderButton").is(":visible")) {
            const order = {
                productId: data.id,
            }

            createOrder(order);
        }
        else {
            $("#productDetail").remove();
            $("#loginModal").show();
        }
    });
}
