document.addEventListener('DOMContentLoaded', () =>{

    let data = fetch_data('evenement-calendrier',1);
    let list_tab = document.getElementById('list_tabs');

    /* Écouter les clics sur les boutons de suppression et d'édition */
    document.addEventListener('click', function(e) {
        if (e.target.classList.contains('delete')) {
            const row = e.target.closest('tr');
            row.remove();
        }else if(e.target.classList.contains('delete-inscription')){
            const row = e.target.closest('.main__inscription__content');
            row.remove();
        } else if (e.target.classList.contains('edit')) {
            console.log('Édition');
        }
    });
    
    activer_switch_tab(list_tab);
});