document.addEventListener('DOMContentLoaded', () =>{

    let data = fetch_data('utilisateur',1);

    afficher_table('utilisateur',data)
});