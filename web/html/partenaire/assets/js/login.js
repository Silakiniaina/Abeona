// Initialisation de la derniere formulaire lu

var index_validation = 0;
if (localStorage.getItem("form_valid")) {
  index_validation = localStorage.getItem("form_valid");
} else {
  index_validation = 1;
  localStorage.setItem("form_valid", 1);
}

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

// Gestion du slide des formulaires

const container = document.querySelectorAll(".container");


// Fonction pour slide
function nextOrPreced(container,next,indice_now){
      container.classList.toggle("container_next");
      container.classList.toggle("container_appear");
      var index_next = next ? (indice_now+1) + 1 : indice_now;
      var name_next_container = ".container_" + index_next;
      const next_container = document.querySelector(".container_" + index_next);

      localStorage.setItem("form_valid", index_next);
      next_container.classList.remove("container_next");
      next_container.classList.toggle("container_appear");
}


// boucle des forms containers
container.forEach((container, index) => {
  if (Number(index_validation )=== index + 1) {
    container.classList.remove("container_previous");
    container.classList.add("container_appear");
  }

  if (container.querySelector(".precedent")) {
        const precedent = container.querySelector(".precedent")
        precedent.addEventListener('click',(event)=>{
            event.preventDefault()
            nextOrPreced(container,false,index)
        })
  }

  if (container.querySelector(".validation")) {
    const validation = container.querySelector(".validation");
    validation.addEventListener("click", (event) => {
      event.preventDefault();
      nextOrPreced(container,true,index)
    });
  }
});
