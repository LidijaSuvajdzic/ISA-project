<template>
  <div class="containerInfo">
    <div class="tab-pane container active">
      <div class="row-boats">
        <div class="col-info">
          <label style="width: 600px" class="text">Naziv: </label>
          <label style="width: 600px" class="text">Adresa:</label>
          <label style="width: 600px" class="text">Promotivni opis: </label>
          <label style="width: 600px" class="text"
            >Biografija instruktora:
          </label>
          <label style="width: 600px" class="text"
            >Maksimalni broj osoba:</label
          >
          <label style="width: 600px" class="text">Slobodni termini:</label>
          <label style="width: 600px" class="text">Pravila ponasanja:</label>
          <label style="width: 600px" class="text">Pecaroska oprema:</label>
          <label style="width: 600px" class="text"
            >Cenovnik i info o dodatnim uslugama:</label
          >
          <label style="width: 600px" class="text"
            >Uslovi otkaza rezervacije:</label
          >
          <div class="col-with-picture">
            <label style="width: 600px" class="text"
              >Slike ambijenta prethodnih druzenja:</label
            >
            <div>
              <img
                src="../../assets/userImages/fishing3.jpg"
                style="height: 250px !important; width: 300px !important"
              />
            </div>
          </div>
          <!--<div>
            <button type="button" class="btn btn-success">
              <router-link to="/clientAdventures">Vrati se nazad</router-link>
            </button>
          </div>-->
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.row-boats {
  display: flex;
}

.text {
  text-align: left;
  font-size: 15px;
  font-weight: 600;
  margin-top: 1%;
  margin-bottom: 1%;
}

.col-info {
  margin-left: 30px;
  margin-top: 10px;
}

.row {
  width: 660%;
  padding-left: 60px;
  height: 10%;
  margin-top: 100px;
  margin-left: 5000px;
  margin-right: 500px;
  opacity: 1.2;
  border-radius: 10px;
  align-content: center;
  background-color: rgba(255, 255, 255, 0.288);
  box-shadow: 0 5px 4px rgb(0 0 0 / 30%), 0 1px 1px rgb(0 0 0 / 22%);
}
/* kartica u okviru stavke menija  */
.containerInfo {
  width: 97%;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 10px;
  opacity: 1;
  border-radius: 10px;
  align-content: center;
  position: relative;
  background-color: fff;
  box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
}
.col-with-picture {
  position: absolute;
  top: 0px;
  right: 0px;
  margin-top: 10px;
}
</style>
