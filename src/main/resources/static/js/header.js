$(document).ready(() => {
    const lgTabActivate = "bg-gray-900 text-white block px-3 py-2 rounded-md text-base font-medium";
    const lgTabDeactivate = "text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium";

    const smTabActivate = "bg-gray-900 text-white block px-3 py-2 rounded-md text-base font-medium";
    const smTabDeactivate = "text-gray-300 hover:bg-gray-700 hover:text-white block px-3 py-2 rounded-md text-base font-medium";

    const lgTabs = $("#lgTabCategory button");
    const smTabs = $("#smTabCategory button");

    lgTabs.click(function() {
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
    });

    smTabs.click(function() {
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
    });

    $("#logoHeader").click(() => {
        indexState = {
            ...indexState,
            page: 0,
            category: null,
            isSearching: false
        };

        fetchMoreProducts(true);
    });

    const searching = $("#searching");
    searching.keypress(e => {
        const keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode === 13) {
            const q = searching.val();
            if(q) {
                indexState = {
                    ...indexState,
                    page: 0,
                    category: q,
                    isSearching: true
                };

                searchProducts(true);
            }
        }
    });

    $("#searchingButton").click(() => {
        const q = searching.val();
        if(q) {
            indexState = {
                ...indexState,
                page: 0,
                category: q,
                isSearching: true
            };

            searchProducts(true);
        }
    })
});
