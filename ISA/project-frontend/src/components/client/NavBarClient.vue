<template>

  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="collapse navbar-collapse">
      <a class="navbar-brand">
        <h4 style="color: #0b4025" class="font-italic">Vikendice & avanture</h4>
      </a>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item" style="margin-right: 15px">
          <button type="button" class="btn btn-outline-success" @click="logOut">
            Odjavi se
          </button>
        </li>
      </ul>
    </div>
  </nav>

  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <img
      src="../../assets/logo_for_homePage.jpg"
      style="height: 8%; width: 20%; margin-right: 3%"
    />
  </nav>

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
          >
        <router-link to="/client">Vikendice</router-link>
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          data-toggle="dropdown"
          href="#"
          role="button"
          aria-haspopup="true"
          aria-expanded="false"
          ><router-link to="/clientBoats">Brodovi</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/clientAdventures">Avanture</router-link>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          <router-link to="/clientReservationHistory"
            >Istorija rezervacija</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          <router-link to="/clientScheduledReservations"
          >Zakazane rezervacije</router-link
        >
          </a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          <router-link to="clientSubscription">Pretplate</router-link>
          </a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          <router-link to="/myProfile">Profil</router-link>
          </a
        >
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  methods: {
    logOut() {
      localStorage.setItem("token", "");
      localStorage.setItem("role", "");
      console.log(localStorage.getItem("token"));
      console.log(localStorage.getItem("role"));
      this.$router.push({ name: "BoatsStartPage" });
    },
  },
};
</script>