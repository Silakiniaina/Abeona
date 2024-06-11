function createTableFromJson(jsonData) {
    const data = typeof jsonData === 'string' ? JSON.parse(jsonData) : jsonData;

    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');

    // Création des en-têtes de colonnes
    Object.keys(data[0]).forEach(key => {
        const th = document.createElement('th');
        th.textContent = key;
        headerRow.appendChild(th);
    });
    // Ajout de l'en-tête pour les actions
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
        // Ajout de la colonne des actions
        const actionCell = document.createElement('td');
        const actionsDiv = document.createElement('div');
        actionsDiv.classList.add('actions');
        const deleteButton = document.createElement('button');
        deleteButton.classList.add('delete');
        deleteButton.innerHTML = '<i class="fa fa-trash"></i>';
        const editButton = document.createElement('button');
        editButton.classList.add('edit');
        editButton.innerHTML = '<i class="fa fa-pen"></i>';

        actionsDiv.appendChild(deleteButton);
        actionsDiv.appendChild(editButton);
        actionCell.appendChild(actionsDiv);
        row.appendChild(actionCell);

        tbody.appendChild(row);
    });
    table.appendChild(tbody);

    return table;
}

// Écouter les clics sur les boutons de suppression et d'édition
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('delete')) {
        const row = e.target.closest('tr');
        row.remove();
    } else if (e.target.classList.contains('edit')) {
        console.log('Édition');
    }
});

// Fonction pour afficher le tableau dans l'onglet spécifié
function displayTable(tabId, data) {
    const containerName = ".table-container-"+tabId;
    const tableContainer = document.querySelector(containerName);
    tableContainer.innerHTML = '';

    const table = createTableFromJson(data);
    tableContainer.appendChild(table);
}

function get_json_by_tab_id(tabId){
    var jsonData = 12;
    if(tabId == 'hotel'){ 
        jsonData = [
            { "Nom": "Alice", "Email": "alice@example.com", "Contact": "123-456-7890" },
            { "Nom": "Bob", "Email": "bob@example.com", "Contact": "234-567-8901" },
            { "Nom": "Charlie", "Email": "charlie@example.com", "Contact": "345-678-9012" },
            { "Nom": "Charlie", "Email": "charlie@example.com", "Contact": "345-678-9012" },
        ];
    }else if(tabId == 'transport'){
        jsonData = [
            { "Nom": "Taxi 1", "Type": "Taxi", "Ville": "Antananarivo", "Evaluation": "4.9", "Date": "2024-07-12" },
            { "Nom": "Bus 1", "Type": "Bus", "Ville": "Paris", "Evaluation": "4.7", "Date": "2024-08-05" },
            { "Nom": "Métro 1", "Type": "Métro", "Ville": "New York", "Evaluation": "4.8", "Date": "2024-09-10" }
        ]
    }else if(tabId == 'guide'){
        jsonData = [
            { "Nom": "Guide 1", "Categorie": "Tourisme", "Ville": "Paris", "Evaluation": "4.8", "Date": "2024-08-01" },
            { "Nom": "Guide 2", "Categorie": "Aventure", "Ville": "Londres", "Evaluation": "4.6", "Date": "2024-09-15" },
            { "Nom": "Guide 3", "Categorie": "Histoire", "Ville": "Rome", "Evaluation": "4.9", "Date": "2024-10-20" }
        ]
        
    }
    return jsonData;
}

function get_table(){
    const tabButtons = document.querySelectorAll('.main__list__tabs__header__content');
    const tabContents = document.querySelectorAll('.main__list__tabs__body__content');

    
    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            tabButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
            tabContents.forEach(content => content.classList.remove('active'));
            const tab = button.getAttribute('data-tab');
            document.getElementById(tab).classList.add('active');
            const jsonData = get_json_by_tab_id(tab);
            displayTable(tab,jsonData)
            console.log("displaying table "+tab)
        });
    });

    const jsonData = get_json_by_tab_id('hotel');
    displayTable('hotel', jsonData);
}

document.addEventListener('DOMContentLoaded', function() {
    const totalElements = 10;
    const increment = 2;
    let currentCount = 0;
    let lastSeenElementIndex = 0; 
    let lastVisibleElementTop = 0; 
    
    const elements = Array.from({ length: totalElements }, (_, i) => ({
        name: `Partenaire ${i + 1}`,
        email: `partenaire${i + 1}@gmail.com`,
        contact: `(+261) 38 34 038 ${String(i + 1).padStart(2, '0')}`
    }));
    
    const listElement = document.querySelector('.main__inscription');
    const voirPlusButton = document.getElementById('see-more');

    function createElement(data) {
        const div = document.createElement('div');
        div.className = 'main__inscription__content';
        div.innerHTML = `
        <span class="main__inscription__content__name">${data.name}</span>
        <span class="main__inscription__content__email">${data.email}</span>
            <span class="main__inscription__content__contact">${data.contact}</span>
            <div class="main__inscription__content__action">
                <button class="main__inscription__content__action-decline">Decliner</button>
                <button class="main__inscription__content__action-accept">Accepter</button>
            </div>
        `;
        return div;
    }

    function displayElements() {
        const startIndex = lastSeenElementIndex; // Utiliser lastSeenElementIndex comme point de départ
        const endIndex = Math.min(startIndex + increment, totalElements);

        for (let i = startIndex; i < endIndex; i++) {
            const element = createElement(elements[i]);
            listElement.insertBefore(element, voirPlusButton);
        }

        lastSeenElementIndex = endIndex; // Mettre à jour lastSeenElementIndex

        if (lastSeenElementIndex >= totalElements) {
            voirPlusButton.style.display = 'none';
        }
    }
    
    function scrollToLastSeenElement() {
        const lastSeenElement = document.querySelector(`.main__inscription__content:nth-child(${lastSeenElementIndex + 1})`);
        if (lastSeenElement) {
            lastSeenElement.scrollIntoView({ behavior: 'smooth' });
        } else {
            console.log("Not scrolling");
        }
    }

    voirPlusButton.addEventListener('click', () => {
        lastVisibleElementTop = listElement.getBoundingClientRect().top; // Mémoriser la position actuelle
        displayElements();
        scrollToLastSeenElement();
    });

    displayElements();
    get_table();
});    

function updateFilter(tabId) {
    const filterContainer = document.getElementById('filter-container');
    
    filterContainer.innerHTML = '';

    if (tabId === 'hotel') {
        filterContainer.innerHTML = `
            <form action="">
                <div class="form-content input_name">
                    <label for="input_nom">Nom</label>
                    <input type="text" name="nom" id="input_nom">
                </div>
                <div class="form-content input_email">
                    <label for="input_email">Email</label>
                    <input type="text" name="email" id="input_email">
                </div>
                <div class="form-content input_contact">
                    <label for="input_contact">Contact</label>
                    <input type="text" name="contact" id="input_contact">
                </div>
                <button type="submit">Filtrer</button>
            </form>
        `;
    } else if (tabId === 'transport') {
        filterContainer.innerHTML = `
            <form action="">
                <div class="form-content input_name">
                    <label for="input_nom">Nom</label>
                    <input type="text" name="nom" id="input_nom">
                </div>
                <div class="form-content input_type">
                    <label for="input_type">Type</label>
                    <select name="type" id="input_type">
                        <option value="">Tous</option>
                        <option value="Taxi">Taxi</option>
                        <option value="Bus">Bus</option>
                        <option value="Métro">Métro</option>
                    </select>
                </div>
                <div class="form-content input_city">
                    <label for="input_ville">Ville</label>
                    <select name="ville" id="input_ville">
                        <option value="">Tous</option>
                        <option value="Antananarivo">Antananarivo</option>
                        <option value="Paris">Paris</option>
                        <option value="New York">New York</option>
                    </select>
                </div>
                <div class="form-content input_eval">
                    <label for="input_evaluation">Evaluation</label>
                    <input type="number" name="evaluation" id="input_evaluation">
                </div>
                <div class="form-content input_date">
                    <label for="input_date">Date</label>
                    <input type="date" name="date" id="input_date">
                </div>
                <button type="submit">Filtrer</button>
            </form>
        `;
    } else if (tabId === 'guide') {
        filterContainer.innerHTML = `
            <form action="">
                <div class="form-content input_name">
                    <label for="input_nom">Nom</label>
                    <input type="text" name="nom" id="input_nom">
                </div>
                <div class="form-content input_category">
                    <label for="input_category">Categorie</label>
                    <select name="categorie" id="input_category">
                        <option value="">Tous</option>
                        <option value="Tourisme">Tourisme</option>
                        <option value="Aventure">Aventure</option>
                        <option value="Histoire">Histoire</option>
                    </select>
                </div>
                <div class="form-content input_city">
                    <label for="input_ville">Ville</label>
                    <select name="ville" id="input_ville">
                        <option value="">Tous</option>
                        <option value="Paris">Paris</option>
                        <option value="Londres">Londres</option>
                        <option value="Rome">Rome</option>
                    </select>
                </div>
                <div class="form-content input_eval">
                    <label for="input_evaluation">Evaluation</label>
                    <input type="number" name="evaluation" id="input_evaluation">
                </div>
                <div class="form-content input_date">
                    <label for="input_date">Date</label>
                    <input type="date" name="date" id="input_date">
                </div>
                <button type="submit">Filtrer</button>
            </form>
        `;
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const tabButtons = document.querySelectorAll('.main__list__tabs__header__content');
    const tabContents = document.querySelectorAll('.main__list__tabs__body__content');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            tabButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
            tabContents.forEach(content => content.classList.remove('active'));
            const tabId = button.getAttribute('data-tab');
            document.getElementById(tabId).classList.add('active');
            const jsonData = get_json_by_tab_id(tabId);
            displayTable(tabId, jsonData);
            updateFilter(tabId); // Mettre à jour les filtres en fonction de l'onglet cliqué
            console.log("displaying table " + tabId);
        });
    });

    // Initialisation du tableau et des filtres dans l'onglet "hotel"
    const jsonData = get_json_by_tab_id('hotel');
    displayTable('hotel', jsonData);
    updateFilter('hotel'); // Mettre à jour les filtres pour l'onglet initial
});