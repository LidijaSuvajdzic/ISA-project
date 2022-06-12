<template>
  <form role="form">
    <div class="form-group">
      <label for="name">Ime</label>
      <input type="text" class="form-control" />
      <label for="name">Prezime</label>
      <input type="text" class="form-control" />
      <label for="name">Email </label>
      <input type="text" class="form-control" />

      <label for="text">Adresa:</label>
      <div class="form-group">
        <table>
          <tr>
            <td><input type="text" class="form-control" /></td>

            <td><input type="text" class="form-control" /></td>
          </tr>
          <tr>
            <td><input type="text" class="form-control" /></td>
            <td><input type="text" class="form-control" /></td>
          </tr>
        </table>
      </div>
      <label for="name">Broj telefona:</label>
      <input type="text" class="form-control" />
    </div>
    <button type="submit" class="btn btn-success"><span></span>Izmeni</button>
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
