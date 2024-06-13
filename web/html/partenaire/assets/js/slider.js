const list_image = document.querySelectorAll('.visualisation')

const slider = document.querySelector('.img-slider')

let current_indice = 0
list_image.forEach((img,index)=>{
    const div = document.createElement('div')
    div.classList.add('slide')
    div.classList.add('slide'+index)
    if(index === current_indice){
        div.classList.add('active')
        img.classList.add('active')
    }

    const image = document.createElement('img')
    image.src = img.src
    image.alt = 'slider '+index

    div.appendChild(image)
    slider.appendChild(div)

    img.addEventListener('click',()=>{
        const previous= document.querySelector('.slide'+current_indice)
        previous.classList.remove('active')
        list_image[current_indice].classList.remove('active')
        img.classList.add('active')
        div.classList.add('active')
        current_indice = index
    })
})

const container = document.querySelectorAll('.slide')

const repeat = () =>{
    var repeater = ()=>{setTimeout(()=>{
        container[current_indice].classList.remove('active')
        list_image[current_indice].classList.remove('active')

        current_indice = current_indice+1===list_image.length ? 0 : current_indice +1

        container[current_indice].classList.add('active')
        list_image[current_indice].classList.add('active')
        repeater()
    },4500)}
    list_image.forEach((element)=>{
        element.addEventListener('click',()=>{
            clearTimeout(repeater)
        })
    })
    repeater()
}

repeat()

