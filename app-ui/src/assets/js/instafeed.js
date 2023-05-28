let sliderIndex = 1;
let timer;
let start;

let slider = document.getElementsByClassName("mySlides");
let dots = document.getElementsByClassName('dot');
carousel();

function plusSlides(toIndex) {
    clearTimeout(timer);
    toIndex > 0 ? sliderIndex++ : sliderIndex--;
    showSlides(sliderIndex);
}

function showSlides(n) {
    if (n > slider.length) sliderIndex = 1;
    if (n < 1) sliderIndex = slider.length;
    for (let i = 0; i < slider.length; i++) {
        slider[i].style.display = "none";
        dots[i].style.backgroundColor = '#bbb';
    }
    slider[sliderIndex - 1].style.display = "block";
    dots[sliderIndex - 1].style.backgroundColor = '#4f4d4d';
    timer = setTimeout(carousel, 4000);
}

function carousel() {
    clearTimeout(start);
    for (let i = 0; i < slider.length; i++) {
        slider[i].style.display = "none";
        dots[i].style.backgroundColor = '#bbb';
    }
    sliderIndex++;
    if (sliderIndex > slider.length) sliderIndex = 1;
    slider[sliderIndex - 1].style.display = "block";
    dots[sliderIndex - 1].style.backgroundColor = '#4f4d4d';
    timer = setTimeout(carousel, 4000);
}

function sliderToogler(dotNumber) {
    clearTimeout(timer);
    for (let i = 0; i < slider.length; i++) {
        slider[i].style.display = "none";
        dots[i].style.backgroundColor = '#bbb';
    }
    sliderIndex = dotNumber;
    if (sliderIndex > slider.length) sliderIndex = 1;
    slider[sliderIndex - 1].style.display = "block";
    dots[sliderIndex - 1].style.backgroundColor = '#4f4d4d';
    timer = setTimeout(carousel, 4000);
}
