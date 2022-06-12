<template>
<div>
  <div v-if="this.role === 'ROLE_COTTAGE_OWNER'">
    <NavBarLogOutCO />
    <NavBarHomePage />
  </div>
  <div v-if="this.role === 'ROLE_BOAT_OWNER'">
    <NavBarLogOutCO />
    <NavBarBoatOwner />
  </div>
  <!--KLIJENT-->
  <div v-if="this.role === 'ROLE_CLIENT'">
    <NavBarClient />
  </div>
    <div v-if="this.role === 'ROLE_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator/>
  </div>
  <div v-if="this.role === 'ROLE_PREDEF_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarPredefAdministrator />
  </div>
      <div v-if="this.role === 'ROLE_INSTRUCTOR'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
  </div>

</div>
    <div class="tab-pane active containerInfo">

      <div class="row-boats" v-if="this.role === 'ROLE_CLIENT' || this.role === 'ROLE_COTTAGE_OWNER' || this.role === 'ROLE_BOAT_OWNER'  || this.role === 'ROLE_ADMIN' || this.role === 'ROLE_PREDEF_ADMIN' || this.role === 'ROLE_INSTRUCTOR'">
        <div class="col-with-picture" style="margin-right: 5%; margin-top: 1%">
          <div>
            <img
              src="../../assets/userImages/alba.jpg"
              class="rounded img-thumbnail img-fluid"
              alt="Responsive image"
              style="max-height: 250px !important; max-width: 300px !important"/>
          </div>
        </div>
        <div class="col-info" style="margin-top: 2%">
          <h5 style="width: 600px" class="text">Ime:</h5>
          <input type="text" class="form-control" v-model="this.user.firstname"/>
          <h5 style="width: 600px" class="text">Prezime:</h5>
          <input type="text" class="form-control" v-model="this.user.lastname"/>
          <h5 style="width: 600px" class="text">Broj telefona:</h5>
          <input type="text" class="form-control" v-model="this.user.telephone"/>
          <h5 style="width: 600px" class="text">Ulica:</h5>
          <input type="text" class="form-control" v-model="this.user.address.street"/> 
           <h5 style="width: 600px" class="text">Broj:</h5>
          <input type="text" class="form-control" v-model="this.user.address.number"/>
          <h5 style="width: 600px" class="text">Grad:</h5>
          <input type="text" class="form-control"  v-model="this.user.address.city"/>
          <h5 style="width: 600px" class="text">Drzava:</h5>
          <input type="text" class="form-control" v-model="this.user.address.country"/>
          <button class="btn btn-secondary"  @click="edit()" style="margin-top:40px">Potvrdi</button>
          <button class="btn btn-secondary" @click="cancel()" style="margin-top:40px; margin-left:430px">Odustani</button>
        </div>
      </div>
    </div>

</template>

<script>
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import NavBarLogOutCO from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import NavBarClient from "../../components/client/NavBarClient.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue"
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
export default {
  name: "EditProfile",
  components: {
    NavBarLogOut,
  NavBarHomePage,
    NavBarClient,
    HeaderStartPage,
    NavBarAdministrator,
    NavBarPredefAdministrator,
    NavBarLogOutCO,
    NavBarBoatOwner,
NavBarFishingInstructor
   
  },
  data(){
    return {
      role: "",
      user: { id: 0, firstname: "", lastname: "", address: { id: "", street:"", number: 0, city: "", country: ""}, telephone: ""}
    }
  },
  methods: {
    async edit() {
      await fetch("http://localhost:8081/api/user/update", {
        method: "POST",
        headers: {
           Authorization: "Bearer " + localStorage.getItem("token"),
           "Content-type": "application/json"
        },
        body: JSON.stringify({ id: parseInt(this.user.id), firstname: this.user.firstname, lastname: this.user.lastname, address: { id: this.user.address.id, street:this.user.address.street, number: this.user.address.number, city: this.user.address.city, country: this.user.address.country }, telephone: this.user.telephone}),
      });
      this.$router.push({ name: "MyProfile" })
    },
    cancel(){ 
      this.$router.push({ name: "MyProfile" });
    },
    async getUser() {
      console.log("USAO SAM U GET USER")
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        "Access-Control-Allow-Origin": "*"
      };
      const res = await fetch("http://localhost:8081/api/user/userInfo", {headers});
      const data = await res.json();
      console.log(data)
      return data;
    }
  },
  async created() {
    this.role = localStorage.getItem("role")
    console.log(this.role);
    this.user = await this.getUser();
  },
};
</script>

<style scoped>
.containerInfo {
  margin-left: 5%;
  margin-top: 2%;
}
.row-boats {
  display: flex;
}
.buttonMargin {
  margin-left: 5%;
}
</style>
