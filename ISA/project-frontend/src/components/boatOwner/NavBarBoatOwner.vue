<template>
  <div style="color: whitesmoke">
    <ul class="nav nav-tabs navbar-light bg-light">
      
      <li class="nav-item">
        <a
          class="nav-link"
          data-toggle="dropdown"
          href="#"
          role="button"
          aria-haspopup="true"
          aria-expanded="false"
          ><router-link to="/myProfile">Profil</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/myBoats">Moji brodovi</router-link>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          <router-link to="/statistic">Statistika poslovanja</router-link></a
        >
      </li>
      <li class="nav-item dropdown">
        <a
          class="nav-link dropdown-toggle"
          data-toggle="dropdown"
          href="#"
          role="button"
          aria-haspopup="true"
          aria-expanded="false"
          >Rezervacije</a
        >
        <div class="dropdown-menu">
          <a class="dropdown-item" ><router-link to="/lastReservations">  Prosle</router-link></a>
          <a class="dropdown-item"><router-link to="/nextReservations"> Predstojece</router-link></a>
           <a class="dropdown-item" href="#"><router-link to="/actions"> Akcije</router-link></a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" ><router-link to="/reservations"> Sve rezervacije</router-link></a>
        </div>
      </li>
    </ul>
  </div>
</template>
<style>
a {
  font-size: 1.1em;
  color: #0b4025;
}
</style>
