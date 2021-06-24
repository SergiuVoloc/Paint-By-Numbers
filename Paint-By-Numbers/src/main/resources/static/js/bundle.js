function confirmDelete() {
    if (!(confirm('Are you sure you want to delete this product?'))) return false;
}
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
function size2price(size){
    let price = 0;
    if(size === "40x50"){ price = 0; }
    else if(size === "40x60"){ price = 5; }
    else if(size === "50x50"){ price = 10; }
    else if(size === "50x70"){ price = 15; }
    else if(size === "60x60"){ price = 20; }
    else if(size === "60x80"){ price = 25; }
    else if(size === "60x100"){ price = 30; }
    else if(size === "70x70"){ price = 35; }
    else if(size === "70x100"){ price = 40; }
    else if(size === "70x140"){ price = 45; }
    else if(size === "80x120"){ price = 50; }

    return price;
}
function priceUpdate(){
    document.getElementById('total').value =
        parseFloat(document.getElementById('productPrice').value) +
        parseFloat(document.getElementById('sizePrice').value) +
        parseFloat(document.getElementById('framePrice').value)
}