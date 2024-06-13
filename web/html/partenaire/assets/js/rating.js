const rating = document.querySelectorAll('.rating')

rating.forEach((element)=>{
    const rating = element.dataset.rates
    const img = document.createElement('img')
    const span = document.createElement('span')

    img.src = element.classList.contains('yellow') ? 'assets/Icons/etoiles_figma.svg' : 'assets/Icons/etoiles_black.svg'
    img.alt = 'etoiles'

    span.textContent=rating
    element.appendChild(img)
    element.appendChild(span)
})