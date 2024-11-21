function paging(page) {
    console.log(page);
    searchForm.page.value = page;
    searchForm.requestSubmit();
}

document.addEventListener("DOMContentLoaded", ()  => {



    let $searchForm = $("#searchForm");
    let $searchBtn = $(".search-btn");
    $searchBtn.on("click", function (){
        let $parent = $(this).parents(".search-area");
        $parent.find(":input[name]").each(function (index, input) {
            console.log(input.name, input.value)
            // $searchForm.find(`[name="${input.name}"]`).val(input.value);
            if (searchForm[input.name]) {
                searchForm[input.name].value = input.value
            }
            // searchForm.requestSubmit();
            $searchForm.submit();
        });
    })
})