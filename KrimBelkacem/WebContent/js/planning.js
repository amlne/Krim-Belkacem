function ouvrir(x) {
    var y = x.parentElement.parentElement.parentElement;
    if (y.className == 'planning-header col-lg-12') {
        y.nextElementSibling.style.display = 'block';
        y.className = 'planning-header col-lg-12 active';
    } else {
        y.nextElementSibling.style.display = 'none';
        y.className = 'planning-header col-lg-12';
    }
}
