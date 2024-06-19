<%@page import="model.shared.URL" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/admin/sidebar.css">
    <link rel="stylesheet" href="assets/css/admin/style.css">
    <link rel="stylesheet" href="assets/css/shared/modal.css">
    <link rel="stylesheet" href="assets/icons/fontawesome/css/all.css">
    <title>Gestion partenaire</title>
</head>
<body>

    <!-- Sidebar -->
    <div class="aside">
        <div class="aside__header">
            <div class="aside__header--logo">
                <i class="fa fa-parachute-box"aria-hidden="true"></i>
            </div>
            <div class="aside__header--brand">
                <p>Abeona Travel</p>
            </div>
        </div>
        <div class="aside__list">
            <a class="aside__list__item" href="#">
                <div class="aside__list__item-img">
                    <i class="fa fa-chart-line"></i>
                </div>
                <div class="aside__list__item-label">Statistiques</div>
            </a>
            <a class="aside__list__item active" href="partenaire">
                <div class="aside__list__item-img">
                    <i class="fa fa-hotel"></i>
                </div>
                <div class="aside__list__item-label">Partenaires</div>
            </a>
            <a class="aside__list__item" href="#">
                <div class="aside__list__item-img">
                    <i class="fa fa-users"></i>
                </div>
                <div class="aside__list__item-label">Utilisateur</div>
            </a>
            <a class="aside__list__item" href="#">
                <div class="aside__list__item-img">
                    <i class="fa fa-piggy-bank"></i>
                </div>
                <div class="aside__list__item-label">Revenu</div>
            </a>
            <a class="aside__list__item" href="evenement?mode=r">
                <div class="aside__list__item-img">
                    <i class="fa fa-calendar-check"></i>
                </div>
                <div class="aside__list__item-label">Evenement</div>
            </a>
        </div>
        <div class="aside__profile">
            <div class="aside__profile-img">
                <img src="assets/images/img.jpg" alt="Sanda">
                <span></span>
            </div>
            <div class="aside__profile__label">
                <div class="aside__profile__label-name">Sanda Silakiniaina</div>
                <div class="aside__profile__label-function">Administrator</div>
            </div>
        </div>
    </div>

<!-- Main content -->
    <div class="main">