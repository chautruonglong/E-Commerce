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

            $("#productDetailCloseButton").click(() => {
                $("#productDetail").remove();
            });

            $("#deleteProduct").click(() => {
                deleteProduct(data.id);
            });

            $("#orderProduct").click(() => {
                console.log("order");
            });
        }
    });
}

const deleteProduct = (id) => {
    $.ajax({
        type: "DELETE",
        dataType: "json",
        contentType: 'json',
        url: `/api/v1/products/${id}`,
        success: data => {
            $(`div[name=productCard][value=${id}]`).remove();
            $("#productDetail").remove();
        }
    });
}

const postProduct = (data) => {
    $.ajax({
        type: "POST",
        processData: false,
        contentType: false,
        url: `/api/v1/products`,
        data,
        success: data => {
            $.tmpl("productCardTemplate", { product: data }).prependTo("#listProducts");

            $("#productCreation").hide();

            $("#productForm").each(function() {
                this.reset();
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
