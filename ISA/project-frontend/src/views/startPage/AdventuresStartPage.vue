<template>
  <div style="">
    <div>
      <HeaderLogAndRegister />
      <HeaderStartPage />
      <NavBarStartPage />
    </div>
    <div style="width: 1000px" v-if="adventures.length != 0">
      <nav class="navbar navbar-expand-sm navbar-dark">
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Naziv"
          v-model="name"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Ulica"
          v-model="street"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Grad"
          v-model="city"
        />
        <button class="btn btn-success" type="submit" @click="search()">
          Pretrazi
        </button>
      </nav>
    </div>
    <div class="containerInfo">
      <div class="tab-pane container active b">
        <div
          class="row-boats"
          v-for="(adventure, index) in adventures"
          :key="index"
        >
          <div class="col-with-picture">
            <div v-if="adventure.images.length != 0">
              <img
                :src="getImgUrl(adventure.images[0].filePath)"
                style="height: 250px !important; width: 300px !important" 
              />
            </div>
          </div>
          <div class="col-info">
            <h4 style="width: 600px" class="text">
              Promotivni opis: {{ adventure.promoDescription }}
            </h4>
            <h4 style="width: 600px" class="text">
              Naziv: {{ adventure.name }}
            </h4>
            <h4 style="width: 600px" class="text">
              Adresa: {{ adventure.address.street }}
              {{ adventure.address.number }}, {{ adventure.address.city }},
              {{ adventure.address.country }}
            </h4>
            <h4 style="width: 600px" class="text">
              Prosecna ocena: {{ adventure.grade }}
            </h4>
            <h4 style="width: 600px" class="text">
              Biografija instruktora: {{ adventure.instructorBiografy }}
            </h4>
             <button  @click="goToAdventure(adventure.name)">Prikazi profil</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarStartPage from "../../components/startPage/NavBarStartPage.vue";
import HeaderLogAndRegister from "../../components/startPage/HeaderLogAndRegister.vue";

export default {
  name: "AdventuresStartPage",
  components: {
    HeaderStartPage,
    NavBarStartPage,
    HeaderLogAndRegister,
  },
  data() {
    return {
      adventures: [],
      name: "",
      street: "",
      city: "",
    };
  },

  methods: {
    async fetchAdventures() {
      const res = await fetch("http://localhost:8081/api/adventures");
      const data = await res.json();
      return data;
    },
    async search() {
      const res = await fetch("http://localhost:8081/api/adventures/search", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify({
          name: this.name,
          street: this.street,
          city: this.city,
        }),
      });
      const data = await res.json();
      this.adventures = data;
    },
    getImgUrl(img) {
      var images = require.context('../../assets/adventureImages/', false, /.jpg$/)
    return images('./' + img + ".jpg")
    },
    goToAdventure(name){
      this.name=name;
      console.log(name)
      localStorage.setItem("nameOfAdventure",this.name)
      this.$router.push({ name: "AdventureProfile" });
    }
  },
  async created() {
    this.adventures = await this.fetchAdventures();
  },
};
</script>

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

button {
  background-color: #4caf50; /* Green */
  width: 50%;
  height: 20%;
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
  background-color: #fdfbec;
  box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
}
.col-with-picture {
  margin-top: 1%;
  margin-bottom: 1%;
}
.b {
  background-color: #fdfbec;
}
</style>
