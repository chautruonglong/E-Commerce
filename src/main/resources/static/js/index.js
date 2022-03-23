$(document).ready(() => {
    $(window).scroll(function() {
        if($(window).scrollTop() == $(document).height() - $(window).height()) {
            indexState = {
                ...indexState,
                page: indexState.page + 1
            };

            if(indexState.isSearching) {
                searchProducts();
            }
            else {
                fetchMoreProducts();
            }
        }
    });
});
