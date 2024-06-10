// Gestion du formulaire d'inscription
const button_submit = document.querySelector('form .abeona-custom_3')
const formulaire = document.querySelector('.form_container form')
const input = document.querySelectorAll('.input-section')


let name_partenaire = formulaire.querySelector('input[type="text"][name="partenaire"]')
let email = formulaire.querySelector('input[type="email"][name="email"]')
let mdp = formulaire.querySelector('input[type="password"][name="mdp"]')
let confirmation = formulaire.querySelector('input[type="password"][name="confirmation"]')

function setError(element , texte,isError=true){
    const parent = element.parentNode
    if(parent.querySelector('.error-form')){
        const error = parent.querySelector('.error-form')
        isError ? error.textContent =texte : parent.removeChild(error)
    }else{
        if(isError){
            const paragraphe = document.createElement('p')
            paragraphe.classList.add('error-form')
            paragraphe.textContent = texte
            parent.appendChild(paragraphe)
        }
    }
}

confirmation.addEventListener('change',()=>{
    if(mdp.value!==confirmation.value){
        confirmation.textContent = ''
        setError(confirmation,"*Correspondance erroné")
    }else{
        setError(confirmation,"",false)
    }
})

confirmation.addEventListener('input',()=>{
    if(mdp.value.replace(/\s+/g, "") === ""){
        confirmation.value = ''
        setError(confirmation,"*Mot de passe vide.")
    }else{
        setError(confirmation,"",false)
    }
})



formulaire.addEventListener('submit',(event)=>{
    event.preventDefault()
    let error = false
    if(mdp.value.replace(/\s+/g, "") === ""){
        setError(mdp,"*Mot de passe vide.")
        error = true
    }else{
        setError(mdp,"",false)
    }
    
    if(confirmation.value.replace(/\s+/g, "") === ""){
        setError(confirmation,"*Verification inachevée.")
        error = true
    }else if(mdp.value!==confirmation.value){
        error = true
    }else{
        setError(confirmation,"",false)
    }

    if(email.value.replace(/\s+/g, "") === ""){
        setError(email,"*Email obligatoire.")
        error = true
    }else{
        setError(email,"",false)
    }

    if(name_partenaire.value.replace(/\s+/g, "") === ""){
        setError(name_partenaire,"*Nom obligatoire.")
        error = true
    }else{
        setError(name_partenaire,"",false)
    }

    !error ? event.target.submit() : null

})


