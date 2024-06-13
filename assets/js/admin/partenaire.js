var active_tab = 'hotel';

/* Paginations */
var paginations = document.getElementById(`${active_tab}-pagination`);
active_pagination(paginations);


document.addEventListener('DOMContentLoaded', function() {

    /* Gestion des listes d'inscription partenaire */
    const total_elements = 10;
    const ajout = 2;
    let id_dernier_element = 0; 
    let dernier_element_vu = 0; 
    
    const elements = Array.from({ length: total_elements }, (_, i) => ({
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
        /* Commencer au dernier element vu */
        const startIndex = id_dernier_element;
        const endIndex = Math.min(startIndex + ajout, total_elements);

        for (let i = startIndex; i < endIndex; i++) {
            const element = createElement(elements[i]);
            listElement.insertBefore(element, voirPlusButton);
        }

        id_dernier_element = endIndex; 

        if (id_dernier_element >= total_elements) {
            voirPlusButton.style.display = 'none';
        }
    }
    
    function scrollToLastSeenElement() {
        const lastSeenElement = document.querySelector(`.main__inscription__content:nth-child(${id_dernier_element + 1})`);
        if (lastSeenElement) {
            lastSeenElement.scrollIntoView({ behavior: 'smooth' });
        } else {
            console.log("Not scrolling");
        }
    }

    voirPlusButton.addEventListener('click', () => {
        /* Memorisation du dernier element vu */
        dernier_element_vu = listElement.getBoundingClientRect().top;
        displayElements();
        scrollToLastSeenElement();
    });

    displayElements();

    /* Gestion des tables et tabs */
    var tabs = document.getElementById('list_tabs');
    activer_switch_tab(tabs);
});  