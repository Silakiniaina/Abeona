// Gestion du focus des inputs
const input_container = document.querySelectorAll(".input-section");

input_container.forEach((input) => {
  if (input.querySelector(".input")) {
    var label = input.querySelector(".label-input");
    var input_value = input.querySelector(".input");
    input_value.addEventListener("focus", () => {
      label.classList.add("label-input_up");
    });

    input_value.addEventListener("blur", () => {
      if (input_value.value.replace(/\s+/g, "") === "") {
        label.classList.remove("label-input_up");
      }
    });
  }
});

// Gestion du boutons suivant
const button = document.querySelectorAll('.abeona-custom_3')

button.forEach(element =>{
    element.addEventListener('mouseenter',()=>{
        element.querySelector('img').src='assets/Icons/second_arrow.png'
    })

    element.addEventListener('mouseleave',()=>{
        element.querySelector('img').src='assets/Icons/icons8-arrow-50.png'
    })
})