const onUpdateButtonClicked = (descri, feeling, character, duration, url) => {
    document.getElementById("update_form").action = url;
    CKEDITOR.instances.editor2.setData(descri);
    document.getElementById("duration").value = duration;
    const characters = document.getElementById('charactersUpdate');
    for (let i = 0; i < characters.options.length; i++) {
        if (characters.options[i].value === character) {
            characters.options[i].selected = true;
            characters.dispatchEvent(new Event('change'));
            break;
        }
    }
    const feelings = document.getElementById('feelingsUpdate');
    for (let i = 0; i < feelings.options.length; i++) {
        if (feelings.options[i].value === feeling) {
            feelings.options[i].selected = true;
            feelings.dispatchEvent(new Event('change'));
            break;
        }
    }

}