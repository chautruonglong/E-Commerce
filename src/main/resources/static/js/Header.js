$(document).ready(() => {
    const lgTabActivate = "bg-gray-900 text-white block px-3 py-2 rounded-md text-base font-medium";
    const lgTabDeactivate = "text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium";

    const smTabActivate = "bg-gray-900 text-white block px-3 py-2 rounded-md text-base font-medium";
    const smTabDeactivate = "text-gray-300 hover:bg-gray-700 hover:text-white block px-3 py-2 rounded-md text-base font-medium";

    const lgTabs = $("#lgTabCategory button");
    const smTabs = $("#smTabCategory button");

    lgTabs.click(function() {
        if(window.location.pathname.match(/^(\/home|\/index|\/)$/)) {
            lgTabs.attr("class", lgTabDeactivate);
            smTabs.attr("class", smTabDeactivate);

            $(this).attr("class", lgTabActivate);
            $(`button[name=${"sm" + $(this).attr("name").substring(2)}]`).attr("class", smTabActivate);

            indexState = {
                ...indexState,
                page: 0,
                category: $(this).text().toLowerCase(),
                isSearching: false
            };

            fetchMoreProducts(true);
        }
        else {
            window.location.href = `/home?page=0&limit=40&category=${$(this).text().toLowerCase()}`;
        }
    });

    smTabs.click(function() {
        if(window.location.pathname.match(/^(\/home|\/index|\/)$/)) {
            lgTabs.attr("class", lgTabDeactivate);
            smTabs.attr("class", smTabDeactivate);

            $(this).attr("class", smTabActivate);
            $(`button[name=${"lg" + $(this).attr("name").substring(2)}]`).attr("class", lgTabActivate);

            indexState = {
                ...indexState,
                page: 0,
                category: $(this).text().toLowerCase(),
                isSearching: false
            };

            fetchMoreProducts(true);
        }
        else {
            window.location.href = `/home?page=0&limit=40&category=${$(this).text().toLowerCase()}`;
        }
    });

    $("#logoHeader").click(() => {
        if(window.location.pathname.match(/^(\/home|\/index|\/)$/)) {
            indexState = {
                ...indexState,
                page: 0,
                category: null,
                isSearching: false
            };

            fetchMoreProducts(true);
        }
        else {
            window.location.href = "/home";
        }
    });

    const searching = $("#searching");
    searching.keypress(e => {
        if(e.keyCode === 13) {
            const q = searching.val();
            if(q) {
                if(window.location.pathname.match(/^(\/home|\/index|\/)$/)) {
                    indexState = {
                        ...indexState,
                        page: 0,
                        category: q,
                        isSearching: true
                    };

                    searchProducts(true);
                }
                else {
                    window.location.href = `/home?page=0&limit=40&q=${q}`;
                }
            }
        }
    });

    $("#searchingButton").click(() => {
        const q = searching.val();
        if(q) {
            if(window.location.pathname.match(/^(\/home|\/index|\/)$/)) {
                indexState = {
                    ...indexState,
                    page: 0,
                    category: q,
                    isSearching: true
                };

                searchProducts(true);
            }
            else {
                window.location.href = `/home?page=0&limit=40&q=${q}`;
            }
        }
    });

    $('html').click(function() {
        $("#userPopupMenu").hide();
    });

    $('#userIcon').click((e) => {
        event.stopPropagation();
        $("#userPopupMenu").show();
    });

    $("#buggerMenu").click(() => {
       const mobileMenu = $("#mobileMenu");
       if(mobileMenu.is(":visible")) {
           mobileMenu.slideUp("fast");
       }
       else {
           mobileMenu.slideToggle("fast");
       }
    });

    $("#loginHeaderButton").click(() => {
        $("#loginModal").show();
    });

    $("#profileButton").click(() => {
        window.location.href = "/profile";
    });

    $("#ordersButton").click(() => {
        window.location.href = "/orders";
    });

    $("#logoutButton").click(() => {
        logout();
    });
});
