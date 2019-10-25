
var loadFile = function(event) {
    var output = document.getElementById('previewImg');
    output.src = URL.createObjectURL(event.target.files[0]);
};
