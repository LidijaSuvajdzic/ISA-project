<template>
  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <img
      src="../../assets/logo_for_homePage.jpg"
      style="height: 8%; width: 20%; margin-right: 3%"
    />
    <div class="collapse navbar-collapse">
      <a class="navbar-brand">
        <h4 style="color: #0b4025" class="font-italic">Vikendice & avanture</h4>
      </a>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item" style="margin-right: 15px">
          <button type="button" class="btn btn-outline-success" @click="logOut">
            <router-link to="/"> </router-link>Odjavi se
          </button>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>


export default ({
  setup() {
    
  },
  methods: {
    logOut(){
     localStorage.removeItem("token");
     localStorage.removeItem("role");
     this.$router.push({ name: "BoatsStartPage"});
    }},
  
})
</script>
