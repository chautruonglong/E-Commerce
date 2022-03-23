let indexStates = {
    page: 0,
    limit: 40,
    category: null
};
$(document).ready(() => {
    $(window).scroll(function() {
        if($(window).scrollTop() == $(document).height() - $(window).height()) {
            indexStates.page += 1;

            $.ajax({
                type: "GET",
                dataType: "json",
                contentType: 'json',
                url: "/api/v1/products",
                data: indexStates,
                success: data => {
                    let html = "";
                    $.each(data, (key, item) => {
                        html += `
                        <div class="group relative hover:cursor-pointer">
                            <div class="w-full min-h-80 bg-gray-200 aspect-w-1 aspect-h-1 rounded-md overflow-hidden group-hover:opacity-75 lg:h-80 lg:aspect-none">
                                <img src="${item.thumbnailImage}" alt="${item.name}" class="w-full h-full object-center object-cover lg:w-full lg:h-full">
                            </div>
                            <div class="mt-4 flex flex-col justify-between">
                                <p class="text-base font-extrabold text-gray-900">${item.price + ' VNĐ'}</p>
                                <div>
                                    <span class="mt-1 pl-1 text-sm text-gray-500 border-solid border-2 border-indigo-100 bg-slate-200">${'-' + item.discount + '%'}</span>
                                </div>
                                <h3 class="text-sm text-gray-700">
                                    <a href="#">
                                      <span aria-hidden="true" class="absolute inset-0"></span>
                                      ${item.name}
                                    </a>
                                </h3>
                            </div>
                        </div>
                    `
                    });
                    $("#listProducts").append(html);
                }
            });
        }
    });
});
