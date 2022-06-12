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
