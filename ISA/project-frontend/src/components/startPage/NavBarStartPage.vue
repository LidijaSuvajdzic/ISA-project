<template>
  <nav
    class="navbar navbar-expand-sm bg-light navbar-dark justify-content-center"
  >
    <ul class="navbar-nav">
      <li class="nav-item">
        <button
          type="button"
          class="btn btn-success"
          style="margin-left: 20px; width: 120px"
        >
          <router-link to="/">Boats</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button
          type="button"
          class="btn btn-success"
          style="margin-left: 20px; width: 120px"
        >
          <router-link to="/cottages">Cottages</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button
          type="button"
          class="btn btn-success"
          style="margin-left: 20px; width: 120px"
        >
          <router-link to="/adventures">Adventures</router-link>
        </button>
      </li>
    </ul>
  </nav>
</template>

<script></script>

<style scoped>
.li {
  margin-left: 15px;
}
</style>
