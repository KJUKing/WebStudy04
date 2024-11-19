document.addEventListener("DOMContentLoaded", () => {
    const myOffcanvas = document.getElementById('offcanvasExample');
    const lastCreated = document.getElementById("lastCreated");

    if (myOffcanvas) {
        myOffcanvas.addEventListener('show.bs.offcanvas', event => {
            const url = event.relatedTarget.getAttribute('data-href');
            const offcanvasBody = myOffcanvas.querySelector('.offcanvas-body');

            fetch(url)
                .then(resp => resp.text())
                .then(html => {
                    offcanvasBody.innerHTML = html;
                })
                .catch(error => console.error('Error fetching data:', error));
        });
    }

    if (lastCreated) {
        lastCreated.click();
    }
});
