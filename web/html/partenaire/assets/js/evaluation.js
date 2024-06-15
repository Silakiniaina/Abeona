// Gestion du compteur 
const reviews = document.querySelector('.evaluation .num_eval')
const avis = document.querySelector('.evaluation')
let speed = 200
function updateReviews(){
    const numReview = reviews.dataset.target;
    const initialCounter = Number(reviews.innerText);
    const incPerCount = speed/numReview
    if(initialCounter<numReview){
        reviews.innerText = Math.ceil(initialCounter+incPerCount)
        setTimeout(updateReviews,40)
    }
}

let counterObserver = new IntersectionObserver((entries,observe)=>{
    let [entry] = entries
    if(entry.isIntersecting){
        updateReviews()
        observe.unobserve(entry.target)
    }else{
        return
    }
},{
    root:null,
    threshold:0.4,
})

counterObserver.observe(avis)

// Gestion des étoiles

let stars = document.querySelectorAll('.stars')

stars.forEach((element,index)=>{
    const max_stars = element.dataset.max
    const stars_partenaire = element.dataset.target
    const reste_stars = max_stars - stars_partenaire

    for(let i=0;i<stars_partenaire;i++){
        const img = document.createElement('img')
        img.src = 'assets/Icons/etoiles_figma.svg'
        img.alt = 'étoiles jaunes'
        element.appendChild(img)
    }  

    for(let i=0;i<reste_stars;i++){
        const img = document.createElement('img')
        img.src = 'assets/Icons/etoiles_vide.svg'
        img.alt = 'étoiles vides'
        element.appendChild(img) 
    }
})

// Gestion des checked

const profil = document.querySelectorAll('.profil_container .info_avis .nom')

profil.forEach((element)=>{
    const checked = document.createElement('img')
    checked.src='assets/Icons/check.svg'
    checked.alt='checked'
    element.appendChild(checked)
})

// Gestion de la longueur des avis

const button_voir = document.querySelector('.voir-plus')
const container_3 = document.querySelector('.descr_3')
const avis_container = container_3.querySelector('.avis')

button_voir.addEventListener('click',()=>{
    avis_container.scrollTop = 0
    container_3.classList.toggle('active')
    button_voir.innerText = container_3.classList.contains('active') ? "Voir moins" : "Voir plus"
})