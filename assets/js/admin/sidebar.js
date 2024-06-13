var onglet = document.getElementsByClassName("aside__list__item");

for(let i=0; i<onglet.length; i++){
    onglet[i].addEventListener('click',() =>{
        remove_focus(onglet);
        active_onglet(onglet,onglet[i])
    });
}
