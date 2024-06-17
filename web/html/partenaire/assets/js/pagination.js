const pagination = document.querySelector('.pagination')

const liste = pagination.querySelectorAll('li')

let active = 1

liste.forEach((element,index)=>{
    active == index + 1 ?  element.classList.toggle('active') : null

    element.addEventListener('click',()=>{
        liste[active-1].classList.toggle('active')
        active = index + 1
        liste[active-1].classList.toggle('active')
        element.querySelector('a').click()
    })
})