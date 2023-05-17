function showLoader() {
    const loadingEl = document.createElement("div");
    loadingEl.id = "loader";
    document.body.prepend(loadingEl);
    loadingEl.classList.add("page-loader");
    loadingEl.classList.add("flex-column");
    loadingEl.classList.add("bg-dark");
    loadingEl.classList.add("bg-opacity-25");
    loadingEl.innerHTML = `
        <span class="spinner-border text-primary" role="status"></span>
        <span class="text-gray-800 fs-6 fw-semibold mt-5">Loading...</span>
    `;

    // Show page loading
    KTApp.showPageLoading();
}

function hideLoader() {
    const loader = document.getElementById("loader");
    if (loader) {
        document.body.removeChild(loader);
    }
}

function send(formData, urlSend, urlSuccess){

    const request = new XMLHttpRequest();
    request.open("POST", urlSend);
    // Afficher le loader
    showLoader();
    request.onreadystatechange = () => {
        if (request.readyState === XMLHttpRequest.DONE) {
            hideLoader();
            const status = request.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                // The request has been completed successfully
                if (urlSuccess === undefined || urlSuccess === null) {
                    Swal.fire({
                        text: request.responseText,
                        icon: 'success',
                        confirmButtonText: 'OK'
                    })
                } else {
                    window.location.href = urlSuccess;
                }

            } else {
                const error = request.responseText
                Swal.fire({
                    text: error,
                    icon: 'error',
                    confirmButtonText: 'OK'
                })
            }
        }
    }
    request.send(formData);
}

function sendGet(urlSend, urlSuccess){

    const request = new XMLHttpRequest();
    request.open("GET", urlSend);
    // Afficher le loader
    showLoader();
    request.onreadystatechange = () => {
        if (request.readyState === XMLHttpRequest.DONE) {
            hideLoader();
            const status = request.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                // The request has been completed successfully
                if (urlSuccess === undefined || urlSuccess === null) {
                    location.reload();
                    // Swal.fire({
                    //     text: request.responseText,
                    //     icon: 'success',
                    //     confirmButtonText: 'OK'
                    // })

                } else {
                    window.location.href = urlSuccess;
                }

            } else {
                const error = request.responseText
                Swal.fire({
                    text: error,
                    icon: 'error',
                    confirmButtonText: 'OK'
                })
            }
        }
    }
    request.send();
}