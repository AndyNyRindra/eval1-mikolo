const onDeleteButtonClicked = (id, name, url, type) => {
    document.getElementById("element-type-name").textContent = type;
    document.getElementById("element-id").textContent = id;
    document.getElementById("element-url").addEventListener('click', function() {
        sendGet(url, null);
    });
    document.getElementById("element-name").textContent = name;
}