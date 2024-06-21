<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/styles.css">
    <script src="assets/js/input.js" defer></script>
    <script src="assets/js/form.js" defer></script>
    <title>Inscription Partenaire</title>
</head>
<body>
        <header>
        <nav class="navbar">
        <a href="" class="logo">
            LOGO
        </a>
        <div class="nav-links">
                <ul class="list-container">
                    <li>
                        <a href="">
                            Accueil
                        </a>
                    </li>
                    <li><a href="">Partenaires</a></li>
                    <li><a href="">Services</a></li>
                    <li><a href="">Historique</a></li>
                    <li><a href="">A propos</a></li>
                    <li><a href="">Aide</a></li>
                </ul>
                <ul class="list-container">
                    <li class="btn abeona-custom_2">
                        <a>
                            Se connecter
                        </a>
                    </li>
                </ul>
        </div>
        <img src="assets/Icons/icons8-menu-64.png" alt="menu-icons" class="menu-hamburger">
    </nav>
    </header>
    <div class="inscription_section">
        <div class="inscription_container">
            <div class="img-desc semi-ecran">
                <div class="">
                    <div class="rectangle_1"></div>
                    <div class="rectangle_2"></div>
                    <p class="desc_form">
                        Soyez le bienvenue parmis nous!!
                    </p>
                    <div class="rectangle_3"></div>
                    <div class="rectangle_4"></div>
                </div>
            </div>
            <div class="form_container semi-ecran">
                <h1>S'inscrire</h1>
                <p class="desc_inscr">
                </p>
                <form action="">
                <div class="input-section">
                    <input name="partenaire" id="partenaire" class="input" type="text"/>
                    <label for="partenaire" class="label-input">Nom partenaire</label>
                    <!-- <p class="error-form"></p> -->
                </div>

                <div class="input-section">
                    <input name="email" id="email" class="input" type="email"/>
                    <label for="email" class="label-input">Email</label>
                    <!-- <p class="error-form"></p> -->
                </div>

                <div class="input-section">
                    <input name="mdp" id="mdp" class="input" type="password"/>
                    <label for="mdp" class="label-input">Mot de passe</label>
                    <!-- <p class="error-form"></p> -->
                </div>

                <div class="input-section">
                    <input name="confirmation" id="confirmation" class="input" type="password"/>
                    <label for="confirmation" class="label-input">Confirmation</label>
                </div>
                <div class="input-section" style="display: flex;justify-content:space-evenly;">
                    <input type="checkbox" name="conditions" id="conditions"> 
                    <label for="conditions">J'accepte les <a href="">Termes</a> et <a href="">Conditions d'utilisations</a></label>
                </div>
                <button class="btn abeona-custom_3">S'inscrire <img src="assets/Icons/icons8-arrow-50.png" alt=""></button>
                <p style="text-align: center; margin-top: 25px;">Vous avez déjà un compte ? <a href="">Se connecter</a></p>
            </form>
            </div>
        </div>
    </div>
</body>
</html>