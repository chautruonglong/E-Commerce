$.get("/tmpl/ProductCard.html", markup => {
    $.template("productCardTemplate", markup);
});

$.get("/tmpl/ProductDetail.html", markup => {
    $.template("productDetailTemplate", markup);
});

const fetchMoreProducts = (reset=false) => {
    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: 'json',
        url: "/api/v1/products",
        data: {
            page: indexState.page,
            limit: indexState.limit,
            category: indexState.category
        },
        success: data => {
            if(reset) {
                $("#listProducts").html("");
            }

            $.each(data, (key, item) => {
                $.tmpl("productCardTemplate", {
                    product: item,
                    handleClick: function() {
                        const id = $(this).attr("value");
                        fetchDetailProduct(id);
                    }
                }).appendTo("#listProducts");
            });
            $(document).ready(() => {
                const productCards = $("div[name=productCard]");
                productCards.unbind("click");
                productCards.click(function() {
                    const id = $(this).attr("value");
                    fetchDetailProduct(id);
                });
            });
        }
    });
}

const searchProducts = (reset=false) => {
    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: 'json',
        url: "/api/v1/products/q",
        data: {
            page: indexState.page,
            limit: indexState.limit,
            category: indexState.category
        },
        success: data => {
            if(reset) {
                $("#listProducts").html("");
            }
            $.each(data, (key, item) => {
                $.tmpl("productCardTemplate", { product: item }).appendTo("#listProducts");
            });
            $(document).ready(() => {
                const productCards = $("div[name=productCard]");
                productCards.unbind("click");
                productCards.click(function() {
                    const id = $(this).attr("value");
                    fetchDetailProduct(id);
                });
            });
        }
    });
}

const fetchDetailProduct = (id) => {
    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: 'json',
        url: `/api/v1/products/${id}`,
        success: data => {
            $.tmpl("productDetailTemplate", { product: data }).appendTo("body");

            $("#closedModalButton").click(() => {
                $("#productModal").remove();
            });
        }
    });
}
