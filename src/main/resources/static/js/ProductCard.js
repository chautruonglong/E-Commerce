$(document).ready(() => {
    $("div[name=productCard]").click(function() {
        const id = $(this).attr("value");
        fetchDetailProduct(id);
    });
});
