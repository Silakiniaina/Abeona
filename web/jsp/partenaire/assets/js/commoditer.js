function getChecked(checkbox,element){
    if(checkbox.checked){
        element.classList.add('checked')
    }else{
        element.classList.remove('checked')
    }
}

const input_commoditer = document.querySelectorAll('.commoditer .input-section')

input_commoditer.forEach(element => {
    const checkbox = element.querySelector('input[type="checkbox"]')

    element.addEventListener('click',()=>{
        checkbox.checked = !checkbox.checked
        getChecked(checkbox,element)
    })

    checkbox.addEventListener('change',()=>{
        getChecked(checkbox,element)
    })
});