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
    result = {
        'hotel':hotelData,
        'transport':transportData,
        'guide':guideData
    }
    return result;
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
        result.appendChild(btn);

    });

    return result;
}

/* Function generer status */
function generer_td_values(td, value){
    if(value == 'true'){
        var div = document.createElement("div");
        div.classList.add('status');
        var span = document.createElement('span');
        span.classList.add('active');
        var txt = document.createElement('p');
        txt.textContent = 'Active';
        div.appendChild(span);
        span.appendChild(txt);
        td.appendChild(div);
    }else if(value == 'false'){
        var div = document.createElement("div");
        div.classList.add('status');
        var span = document.createElement('span');
        var txt = document.createElement('p');
        txt.textContent = 'Not Active';
        div.appendChild(span);
        span.appendChild(txt);
        td.appendChild(div);
    }else{
        td.textContent = value;
    }
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
            generer_td_values(td, value);
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

const ITEMS_PER_PAGE = 20;

/* Display data in the table */
function afficher_table(tab, data, page=1) {
    const tableContainer = document.querySelector(`.table-container-${tab}`);
    console.log(tableContainer);
    tableContainer.innerHTML = '';
    const table = creer_table_from_json(data);
    tableContainer.appendChild(table);
}

/* Function pour fetcher les datas d'un tab correspondant au pagination 
    ici tu appel des ajax pour les recuperer
*/
function fetch_data(tabid,page_num){
    var result = []
    if(tabid === 'hotel'){
        result = [
            {
                "id_hotel": "H1",
                "nom_hotel": "Hotel Sunshine",
                "description": "A cozy hotel with a beautiful view of the city.",
                "adresse_hotel": "123 Sunshine St, Pleasantville",
                "date_insertion": "2024-06-15T10:00:00Z",
            }
        ]

    }else if(tabid === 'transport'){
        result = [
            {
                "id_transport": "T1",
                "nom_transport": "City Bus",
                "description": "Public bus service within the city.",
                "tarif": 2.50,
                "date_insertion": "2024-06-15T10:00:00Z"
            }
        ]
            

    }else if(tabid === 'guide'){
        result = [
            {
                "id_guide": "G1",
                "nom_guide": "Beloha",
                "description": "Man with lorem ipsum",
                "tarif": 2000,
                "date_insertion": "2024-06-15T10:00:00Z"
            }
        ]
    }else if(tabid === 'utilisateur'){
        result = [
            {
                "id_user":"USR1",
                "nom":"Sanda",
                "email":"sandasilakiniaina4@gmail.com",
                "date_de_naissance":"12-07-2005",
                "date_insertion":"11-06-2024",
                "status":"false",
            }
        ]
    }
    return result;
}

/* Revenir a la page 1 de la pagination */
function reset_pagination(tabId){
    var element = document.getElementById(`${tabId}-pagination`);
    var paginations = element.children;
    console.log(paginations);

    remove_focus(paginations);
    active_onglet(paginations,paginations[0])
}

/* Enlever une pagination */
function clear_pagination(tabId){
    var existent_pagination = document.getElementById(`${tabId}-pagination`);
    if(existent_pagination != null){
        existent_pagination.parentNode.removeChild(existent_pagination);
    }
}

/* Fonction pour afficher les paginations : 
    total : nombre de ligne total
    to_show : nombre de ligne a afficher
*/
function generer_pagination(tabId, total, to_show) {
    clear_pagination(tabId);
    const page_number = Math.floor(total / to_show) + 1;
    var container = document.createElement('div');
    container.id = `${tabId}-pagination`;
    container.classList.add('pagination');
    
    const fragment = document.createDocumentFragment();
    for (let i = 1; i <= page_number; i++) {
        let span = document.createElement('span');
        if (i === 1) {
            span.classList.add('active');
        }
        span.textContent = i;
        span.addEventListener('click',() =>{
            remove_focus(span.parentElement.children);
            active_onglet(span.parentElement.children, span)
        });
        fragment.appendChild(span);
    }
    container.appendChild(fragment);
    return container;
}

/* Initialize tab switching */
async function activer_switch_tab(list_tab){
    const tabs = list_tab.children;
    const switch_tab = async (button) => {
        remove_focus(tabs);
        button.classList.add('active');
        const tabId = button.getAttribute('data-tab');
        let list_container = document.getElementsByClassName('main__list__tabs__body__content');
        Array.from(list_container).forEach(content => content.classList.remove('active'));
        document.getElementById(`${tabId}_container`).classList.add('active');
        try{
            const data = fetch_data(tabId,1);
            afficher_table(tabId, data,1);
            var pagination = generer_pagination(tabId,120,ITEMS_PER_PAGE);
            var container = document.getElementById(tabId+"_container");
            container.appendChild(pagination);
        }catch(error){
            console.log(error);
        }
    };

    Array.from(tabs).forEach(button => {
        button.addEventListener('click', () => switch_tab(button));
    });

    // if (tabs.length > 0) {
    //     switch_tab(tabs[0]);
    // }
}
