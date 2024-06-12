const menuhamburger = document.querySelector(".menu-hamburger");
const navlinks = document.querySelector(".nav-links");

// Gestion du menu
menuhamburger.addEventListener("click", () => {
  navlinks.classList.toggle("mobile-menu");
  menuhamburger.classList.toggle("close");
  if (navlinks.classList.contains("mobile-menu")) {
    menuhamburger.src = "assets/Icons/icons8-croix-64.png";
  } else {
    menuhamburger.src = "assets/Icons/icons8-menu-64.png";
  }
});