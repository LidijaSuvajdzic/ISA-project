<template>
  <form role="form">
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Lozinka</label
      >
      <input
        type="password"
        class="form-control"
        id="psw"
        placeholder="Unesite novu lozinku"
      />
    </div>
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Ponovite unos
        lozinke</label
      >
      <input
        type="password"
        class="form-control"
        id="psw"
        placeholder="Potvrdite novu lozinku"
      />
    </div>
    <button type="submit" class="btn btn-success">
      <span></span>Potvrdi izmenu
    </button>
  </form>
</template>
<style scoped>
.submit {
  background-color: #4caf50; /* Green */
  margin: 0px 20px;
}
form {
  width: 50%;
  margin: 20px 20px;
}
</style>
