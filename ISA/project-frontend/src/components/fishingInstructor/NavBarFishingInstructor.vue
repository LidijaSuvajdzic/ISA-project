<template>
  <nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/fishingInstructor">Avanture</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/AvailablePeriod"
            >Definisanje slobodnih termina</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/MyAdventureReservations">Rezervacije</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/Calendarr">Kalendar</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/myProfile"
            >Azuriraj svoje podatke</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/ChangePasswordFishingInstructor"
            >Promeni lozinku</router-link
          ></a
        >
      </li>
    </ul>
  </nav>
</template>
<style scoped>
a {
  background-color: #4caf50; /* Green */
}
</style>
