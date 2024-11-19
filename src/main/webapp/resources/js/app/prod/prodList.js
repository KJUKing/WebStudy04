/**
 *
 */
document.addEventListener("DOMContentLoaded", () => {
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