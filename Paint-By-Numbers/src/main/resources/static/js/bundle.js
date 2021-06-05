function fileUpload(form, input, spinner) {
    var fileForm = document.getElementById(form);
    var fileInput = document.getElementById(input);
    var loading = document.getElementById(spinner);
    fileInput.onchange = function (){
        var formData = new FormData(fileForm);
        formData.append('file', fileInput.files[0]);
        var xhr = new XMLHttpRequest();
        xhr.addEventListener('loadstart', function () {
            loading.classList.remove("d-none");
        });
        xhr.addEventListener("loadend", function () {
            loading.classList.add("d-none");
            location.reload();
        });
        xhr.open('POST', fileForm.getAttribute('action'), true);
        xhr.send(formData);
        return false;
    }
}