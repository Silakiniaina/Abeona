var onglet = document.getElementsByClassName("aside__list__item");
function remove_focus(list_onglet){
    for(let i=0; i<list_onglet.length; i++){
        ls_class = list_onglet[i].classList;
        for(let j=0; j<ls_class.length; j++){
            if(ls_class[j] == 'active'){
                ls_class.remove(ls_class[j]);
                break;
            }
        }
    }
}

function active_onglet(list_onglet, onglet_active){
    for(let i=0; i<list_onglet.length; i++){
        if(list_onglet[i] == onglet_active){
            list_onglet[i].classList.add("active");
            break;
        }
    }
}

for(let i=0; i<onglet.length; i++){
    onglet[i].addEventListener('click',() =>{
        remove_focus(onglet);
        active_onglet(onglet,onglet[i])
    });
}
