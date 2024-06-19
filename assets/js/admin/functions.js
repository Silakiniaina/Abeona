const ITEMS_PER_PAGE = 20;

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
    result.id = 'act'
    Object.keys(actions).forEach(key =>{
        let btn = document.createElement('button');
        let icon = document.createElement("i");
        icon.classList.add('fa');
        icon.classList.add(`fa-${actions[key]}`);
        btn.appendChild(icon);
        btn.classList.add(key);
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
        span.textContent = 'Active';
        div.appendChild(span);
        td.appendChild(div);
    }else if(value == 'false'){
        var div = document.createElement("div");
        div.classList.add('status');
        var span = document.createElement('span');
        span.textContent = 'Not Active';
        div.appendChild(span);
        td.appendChild(div);
    }else{
        td.textContent = value;
    }
}

function processString(str) {
    if(str.includes('_')){
        const parts = str.split('_');
        if (parts.length === 2) {
            return parts[0];
        } else if (parts.length === 3 && parts[0].includes('id')) {
            return parts[1];
        } else if (parts.some(part => part.includes('date'))) {
            return parts[0] + ' ' + parts[1];
        }
    }else{
        return str;
    }

}

function enlever_colonne(array, keyToExclude) {
    return array.map(obj => {
        const result = {};

        // Copy all key-value pairs except the one to exclude
        Object.keys(obj).forEach(key => {
            if (key !== keyToExclude) {
                result[key] = obj[key];
            }
        });

        return result;
    });
}

/* Fonction qui permet de generer un tableau a partir d'un json */
function creer_table_from_json(jsonData) {
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');

    jsonData = enlever_colonne(jsonData, "description");

    Object.keys(jsonData[0]).forEach(key => {
        const th = document.createElement('th');
        th.textContent = processString(key);
        th.id = key;
        headerRow.appendChild(th);
    });

    const actionHeader = document.createElement('th');
    actionHeader.textContent = 'Action';
    headerRow.appendChild(actionHeader);
    thead.appendChild(headerRow);
    table.appendChild(thead);

    const tbody = document.createElement('tbody');
    jsonData.forEach(item => {
        const row = document.createElement('tr');
        Object.entries(item).forEach(([key, value]) => {
            const td = document.createElement('td');
            generer_td_values(td, value); 
            row.appendChild(td);
        });
        const actionCell = document.createElement('td');
        const actions = { 'delete': 'trash', 'edit': 'pen' };
        actionCell.appendChild(generer_action_buttons(actions)); 
        row.appendChild(actionCell);
        tbody.appendChild(row);
    });

    table.appendChild(tbody);
    return table;
}

async function recuperer_data_puis_afficher(tabId) {
    try {
        const data = await fetch_data(tabId);
        afficher_table(tabId, data, 1);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}


/* Display data in the table */
function afficher_table(tab, data, page=1) {
    const tableContainer = document.querySelector(`.table-container-${tab}`);
    console.log(tableContainer);
    tableContainer.innerHTML = '';
    const table = creer_table_from_json(data);
    tableContainer.appendChild(table);
}
const ajax = (method, link, formData) =>{
    return new Promise((resolve, reject) =>{
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () =>{
            if(xhr.readyState == 4){
                if(xhr.status == 200){
                    if(xhr.responseText){
                        resolve(xhr.responseText);
                    }else{
                        reject(xhr.responseText);
                    }
                }else{
                    reject(xhr.responseText);
                }
            }else{

            }
        }

        xhr.open(method, link);
        xhr.send(formData);
    });
};

/* Function pour fetcher les datas d'un tab correspondant au pagination 
    ici tu appel des ajax pour les recuperer
*/
function fetch_data(tabid) {
    var tab = document.getElementById("tab");
    var type = document.getElementById("type");  
    if(tabid === 'hotel' || tabid === 'transport' || tabid === 'guide'){
        tab.value = "p";
        if (tabid === 'hotel') type.value = "h";
        else if(tabid === 'transport')type.value = "t";
        else if (tabid === 'guide') type.value = "g";
    }else if (tabid === 'utilisateur')tab.value = "u";
    else if (tabid === 'evenement-calendrier' || tabid === 'evenement-hotel'){
        tab.value = "e";
        if(tabid === 'evenement-calendrier')type.value = "c";
        else if(tabid === 'evenement-hotel')type.value = "h";
    }
    let btn = document.getElementById("submit_filter");
    btn.onclick = (e) => {
        e.preventDefault();
        console.log("clicked");
        var formData = new FormData(document.getElementById("filter-container"));

        for (var pair of formData.entries()) {
            console.log(pair[0] + ": " + pair[1]);
        }

        ajax('POST', 'recherche', formData)
        .then((data) => {
            console.log(data);
        })
        .catch((error) => {
            console.log(error);
        });
    };
}

/* Recuperer les valeurs des inputs */
function get_form_data(tabid){
    let result = new FormData();
    if(tabid === 'e'){
        let input_nom = document.getElementById("input_nom");
        console.log(nom.value);
        result.append("nom",input_nom.value);
        // result.append("categorie",document.getElementById("input_categorie").value);
        // result.append("ville",document.getElementById("input_ville").value);
        // result.append("date_debut",document.getElementById("input_date_d").value);
        // result.append("date_fin",document.getElementById("input_date_f").value);
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
            recuperer_data_puis_afficher(tabId);
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

    if (tabs.length > 0) {
        switch_tab(tabs[0]);
    }
}
