document.addEventListener("DOMContentLoaded", () => {
    const $prodLgu = $('[ name="prodLgu"]');
    const $prodBuyer = $('[ name="prodBuyer"]');
    // console.log($prodLgu);
    // console.log(document.querySelector('[ name="prodLgu"]'));
    // console.log($prodLgu[0])
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
})