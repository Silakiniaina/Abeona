/* Fonction pour enlever tout focus d'une liste */
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

/* Fonction pour activer une node dans une liste */
function active_onglet(list_onglet, onglet_active){
    for(let i=0; i<list_onglet.length; i++){
        if(list_onglet[i] == onglet_active){
            list_onglet[i].classList.add("active");
            break;
        }
    }
}

/* recuperer tous les datas des tabs */
async function fetch_all_data() {
    const hotelData = await fetch_data('hotel');
    const guideData = await fetch_data('guide');
    const transportData = await fetch_data('transport');
    return {
        hotel: hotelData,
        guide: guideData,
        transport: transportData
    };
}


/* Fonction pour creer des buttons dont les  labels sont des icons fontawsome */
function generer_action_buttons(actions){
    var result = document.createElement("div");
    result.classList.add('actions');
    Object.keys(actions).forEach(key =>{
        let btn = document.createElement('button');
        btn.classList.add(key);
        let icon = document.createElement("i");
        icon.classList.add('fa');
        icon.classList.add(`fa-${actions[key]}`);
        btn.appendChild(icon);

        /* Écouter les clics sur les boutons de suppression et d'édition */
        document.addEventListener('click', function(e) {
            if (e.target.classList.contains('delete')) {
                const row = e.target.closest('tr');
                row.remove();
            } else if (e.target.classList.contains('edit')) {
                console.log('Édition');
            }
        });
        result.appendChild(btn);

    });

    return result;
}

/* Fonction qui permet de generer un tableau a partir d'un json */
function creer_table_from_json(jsonData) {
    const data = typeof jsonData === 'string' ? JSON.parse(jsonData) : jsonData;
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');

    Object.keys(data[0]).forEach(key => {
        const th = document.createElement('th');
        th.textContent = key;
        headerRow.appendChild(th);
    });

    const actionHeader = document.createElement('th');
    actionHeader.textContent = 'Action';
    headerRow.appendChild(actionHeader);
    thead.appendChild(headerRow);
    table.appendChild(thead);
    
    // Création du corps du tableau
    const tbody = document.createElement('tbody');
    data.forEach(item => {
        const row = document.createElement('tr');
        // Remplissage des cellules de données
        Object.entries(item).forEach(([key, value]) => {
            const td = document.createElement('td');
            td.textContent = value;
            row.appendChild(td);
        });
        const actionCell = document.createElement('td');
        var actions = {'delete':'trash','edit':'pen'};
        actionCell.appendChild(generer_action_buttons(actions));
        row.appendChild(actionCell);
        tbody.appendChild(row);
    });

    table.appendChild(tbody);
    return table;
}

/* Fonction pour activer une pagination */
function active_pagination(list){
    Array.from(list.children).forEach(pag =>{
        pag.addEventListener('click', () =>{
            remove_focus(list);
            active_onglet(list,pag);
        })
    })
}

const ITEMS_PER_PAGE = 10;

/* Fetch data for a specific tab and page */
async function fetch_data(tab, page) {
    // Implémentez ici votre logique pour récupérer les données
    // Par exemple, en ajoutant des paramètres de pagination à votre requête API
    // Retourne un objet avec { items: [], totalItems: number }
    return { items: [], totalItems: 0 };
}

/* Display data in the table */
function afficher_table(tab, data, page) {
    const tableContainer = document.querySelector(`.table-container-${tab}`);
    tableContainer.innerHTML = '';
    const table = creer_table_from_json(data);
    tableContainer.appendChild(table);
}

/* Function pour fetcher les datas d'un tab correspondant au pagination */
function fetch_data(tabid,page_num){
    var result = [
        {
            nom:'Sanda',
            prenom:'Silakiniaina'
        }
    ]

    return result;
}

/* Initialize tab switching */
async function activer_switch_tab(list_tab){
    const tabs = list_tab.children;

    const switch_tab = async (button) => {
        remove_focus(tabs);
        button.classList.add('active');
        const tabId = button.getAttribute('data-tab');
        document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));
        document.getElementById(tabId).classList.add('active');
        const data = fetch_data(tabId,1);
        afficher_table(tabId, data,1);
    };

    Array.from(tabs).forEach(button => {
        button.addEventListener('click', () => switch_tab(button));
    });

    if (tabs.length > 0) {
        switch_tab(tabs[0]);
    }
}
