/**
 *
 */
function paging(page) {
    console.log(page);
    searchForm.page.value = page;
    searchForm.requestSubmit();
}

document.addEventListener("DOMContentLoaded", () => {

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


    const $prodLgu = $('[ name="prodLgu"]');
    const $prodBuyer = $('[ name="prodBuyer"]');

    $prodLgu.on("change", e => {
        let lgu = e.target.value;
        if (lgu) {
            $prodBuyer.find("option").hide();
            $prodBuyer.find("option:first").show();
            $prodBuyer.find(`.${lgu}`).show();
        } else {
            $prodBuyer.find("option").show();
        }
    })

    $prodLgu.trigger("change")



    const myOffcanvas = document.getElementById('offcanvasExample')
    const lastCreated = document.getElementById("lastCreated");


    myOffcanvas.addEventListener('show.bs.offcanvas', event => {
        const url = event.relatedTarget.getAttribute('data-href');
        const offcanvasBody = document.querySelector('.offcanvas-body');
        // let aTag = event.relatedTarget;
        // let url = aTage.dataset.href;
        fetch(url)
            .then(resp => resp.text())
            .then(html => {
                offcanvasBody.innerHTML = html;
            })
            .catch(error => console.error('Error fetching data:', error));
    });

    if (lastCreated) {
        lastCreated.click();
    }
})