var sliderIndex = 1;
var timer;
var start;
var slider = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName('dot');
// function resetAllPages() {
//     // document.getElementsByClassName('main-page')[0].style.display = 'none';
//     document.getElementsByClassName('cooperation-page')[0].style.display = 'none';
//     document.getElementsByClassName('categories-page')[0].style.display = 'none';
//     document.getElementsByClassName('contacts-page')[0].style.display = 'none';
//     document.getElementsByClassName('guarantee-page')[0].style.display = 'none';
// }
//
// resetAllPages();
carousel();
function plusSlides(toIndex) {
    clearTimeout(timer);
    toIndex > 0 ? sliderIndex++ : sliderIndex--;
    showSlides(sliderIndex);
}
function showSlides(n) {
    if (n > slider.length)
        sliderIndex = 1;
    if (n < 1)
        sliderIndex = slider.length;
    for (var i = 0; i < slider.length; i++) {
        slider[i].style.display = "none";
        dots[i].style.backgroundColor = '#bbb';
    }
    slider[sliderIndex - 1].style.display = "block";
    dots[sliderIndex - 1].style.backgroundColor = '#4f4d4d';
    timer = setTimeout(carousel, 8000);
}
function carousel() {
    clearTimeout(start);
    for (var i = 0; i < slider.length; i++) {
        slider[i].style.display = "none";
        dots[i].style.backgroundColor = '#bbb';
    }
    sliderIndex++;
    if (sliderIndex > slider.length){
        sliderIndex = 1;
    }

    slider[sliderIndex - 1].style.display = "block";
    dots[sliderIndex - 1].style.backgroundColor = '#4f4d4d';
    timer = setTimeout(carousel, 4000);
}
function sliderToogler(dotNumber) {
    clearTimeout(timer);
    for (var i = 0; i < slider.length; i++) {
        slider[i].style.display = "none";
        dots[i].style.backgroundColor = '#bbb';
    }
    sliderIndex = dotNumber;
    if (sliderIndex > slider.length)
        sliderIndex = 2;
    slider[sliderIndex - 1].style.display = "block";
    dots[sliderIndex - 1].style.backgroundColor = '#4f4d4d';
    timer = setTimeout(carousel, 4000);
}
function openNav() {
    document.getElementById("myNav").style.width = "20%";
}
/* Close when someone clicks on the "slider" symbol inside the overlay */
function closeNav() {
    document.getElementById("myNav").style.width = "0%";
    // document.getElementsByTagName('a')
}
