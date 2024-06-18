// Gestion des formulaires
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

if(document.getElementById('form_commoditer')){
    const form = document.getElementById('form_commoditer')
    const input_check = form.querySelectorAll('input[type="checkbox"]')
    const select = form.querySelector('.options')

    form.addEventListener('submit', (e) => {
        e.preventDefault()
        let checked = false
        input_check.forEach(element =>{
            if(element.checked){
                checked = true
            }
        })
        if(!checked){
            setError(select,"*Choisissez une commoditer.")
        }else{
            form.submit()
        }
    })
}else if(document.getElementById('form_transport') || document.getElementById("form_guide") || document.getElementById("form_hotel")){
    let form = null
    let nom = null
    let descr = null
    let prix = null


    if(document.getElementById('form_transport')){
        form = document.getElementById('form_transport')
        nom = form.querySelector('input[type="text"].name_transport')
        descr = form.querySelector('textarea.description')
        prix = form.querySelector('input[type="number"].prix')
    }else if(document.getElementById("form_guide")){
        form = document.getElementById("form_guide")
        nom = form.querySelector('input[type="text"].nom_guide')
        descr = form.querySelector('textarea.description')
    }else{
        form = document.getElementById("form_hotel")
        nom = form.querySelector('input[type="text"].nom_hotel')
        descr = form.querySelector('textarea.description')
    }

    form.addEventListener('submit',(event)=>{
        event.preventDefault()
        let haveError = false
        if(nom.value.replace(/\s+/g, "") === ""){
            haveError = true
            setError(nom,"*Nom obligatoire.")
        }else{
            setError(nom,"",false)
        }

        if(descr.value.replace(/\s+/g,"")===""){
            setError(descr,"*Description vide.")
            haveError = true
        }else{
            setError(descr,"",false)
        }

        if(document.getElementById('form_transport')){        
        if(Number(prix.value) === 0 || prix.value ===""){
                setError(prix,"*Tarif obligatoire.")
                haveError = true
            }else{
                setError(prix,"",false)
            }
        }

        !haveError ? form.submit() : null
        })
}else if(document.getElementById("form_chambre")){
    const form =document.getElementById("form_chambre")
    const prix = form.querySelector('input[type="number"].prix')
    const capacite = form.querySelector('input[type="number"].capacite')
    const nombres = form.querySelector('input[type="number"].nombres')

    form.addEventListener('submit',(event)=>{
        event.preventDefault()
        let haveError = false

        if(Number(prix.value) === 0 || prix.value ===""){
            setError(prix,"*Tarif obligatoire.")
            haveError = true
        }else{
            setError(prix,"",false)
        }

        if(Number(capacite.value) === 0 || capacite.value ===""){
            setError(capacite,"*Capacité obligatoire.")
            haveError = true
        }else{
            setError(capacite,"",false)
        }

        if(Number(nombres.value) === 0 || nombres.value ===""){
            setError(nombres,"*Nombre obligatoire.")
            haveError = true
        }else{
            setError(nombres,"",false)
        }

        !haveError ? form.submit() : null
    })
}