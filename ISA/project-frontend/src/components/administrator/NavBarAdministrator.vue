<template>
  <nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/administrator">Zahtevi za registraciju</router-link>
        </button>
      </li>
            <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/deleteAccountRequests">Zahtevi za brisanje naloga</router-link>
        </button>
      </li>
                        <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/evaluate">Revizije(ocenjivanje)</router-link>
        </button>
      </li>
                  <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/complaints">Zalbe</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/SeeAndDeleteEntities">Entiteti</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/percentage">Definisanje procenta</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/incomeRecords">Evidencija o prihodima </router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/buisinessReport">Izvestaj o poslovanju</router-link>
        </button>
      </li>
            <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/Requests">Zahtevi za izvestaj/komentar</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/myProfile">Profil</router-link>
        </button>
      </li>
    </ul>
  </nav>
</template>
<style scoped>
button {
  background-color: #4caf50; /* Green */
}
</style>
