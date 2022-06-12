<template>
  <form role="form">
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Slobodan od:</label
      >
      <input type="text" class="form-control" id="psw" placeholder="Datum" />
      <input type="text" class="form-control" id="psw" placeholder="Vreme" />
    </div>
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Slobodan do:</label
      >
      <input type="text" class="form-control" id="psw" placeholder="Datum" />
      <input type="text" class="form-control" id="psw" placeholder="Vreme" />
    </div>
    <button type="submit" class="btn btn-success"><span></span>Potvrdi</button>
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
