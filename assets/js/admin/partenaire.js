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
        let name = document.createElement('span');
        name.classList.add('main__inscription__content__name');
        name.textContent = data.email;

        let email = document.createElement('span');
        email.classList.add('main__inscription__content__contact');
        email.textContent = data.email;

        let contact = document.createElement('span');
        contact.classList.add('main__inscription__content__contact');
        contact.textContent = data.contact;

        let action_div = document.createElement('div');
        action_div.classList.add('main__inscription__content__action');

        let btn_decline = document.createElement('button');
        btn_decline.classList.add('main__inscription__content__action-decline');
        btn_decline.classList.add('delete-inscription');
        btn_decline.textContent = 'Decliner'

        let btn_accept = document.createElement('button');
        btn_accept.classList.add('main__inscription__content__action-accept');
        btn_accept.textContent = 'Accepter'

        action_div.appendChild(btn_decline);
        action_div.appendChild(btn_accept);

        div.appendChild(name);
        div.appendChild(email);
        div.appendChild(contact);
        div.appendChild(action_div);
        return div;
    }

    function afficher_inscription() {
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
    
    function scroll_dernier_element_visible() {
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
        afficher_inscription();
        scroll_dernier_element_visible();
    });

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
    
    afficher_inscription();

    /* Gestion des tables et tabs */
    var tabs = document.getElementById('list_tabs');
    activer_switch_tab(tabs);
});  