<template>
  <!--Vlasnik vikendice-->
  <div v-if="this.role === 'ROLE_COTTAGE_OWNER'">
    <NavBarLogOutCO />
    <NavBarHomePage />
  </div>
    <div v-if="this.role === 'ROLE_BOAT_OWNER'">
    <NavBarLogOutCO />
    <NavBarBoatOwner />
  </div>
  <div v-if="this.role === 'ROLE_INSTRUCTOR'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
  </div>
  <!--KLIJENT-->
  <div v-if="this.role === 'ROLE_CLIENT'">
    <NavBarClient />
  </div>
   <div v-if="this.role === 'ROLE_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
     <div v-if="this.role === 'ROLE_PREDEF_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarPredefAdministrator />
  </div>
  <div v-if="this.normal">
      <div style="margin-top:80px;margin-left:50px">
        <div class="tab-pane active containerInfo">
          <div class="row-boats">
            <div class="col-with-picture" style="margin-right: 5%; margin-top: 1%">
              <div>
                <img
                  src="../../assets/userImages/alba.jpg"
                  class="rounded img-thumbnail img-fluid"
                  alt="Responsive image"
                  style="max-height: 250px !important; max-width: 300px !important"
                />
              </div>
            </div>

            <div>
              <!--KLIJENT-->
              <div class="col-info" style="margin-top: 2%" v-if="this.role === 'ROLE_CLIENT'">
                <h5 style="width: 600px" class="text">Ime: {{client.firstname}}</h5>
                <h5 style="width: 600px" class="text">Prezime: {{client.lastname}}</h5>
                <h5 style="width: 600px" class="text">Adresa: {{client.address.street}} {{client.address.number}}, {{client.address.city}}, {{client.address.country}}</h5>
                <h5 style="width: 600px" class="text">Email: {{client.email}}</h5>
                <h5 style="width: 600px" class="text">Broj telefona: {{client.telephone}}</h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: {{client.loyalityType}}</h5>
                <h5 style="width: 600px" class="text">Poeni korisnika: {{client.poents}}</h5>
                <h5 style="width: 600px" class="text">Penali korisnika: {{client.penals}}</h5>
              </div>

              <!--VLASNIK VIKENDICE-->
              <div class="col-info" style="margin-top: 2%" v-if="this.role === 'ROLE_COTTAGE_OWNER'">
                <h5 style="width: 600px" class="text">Ime:  {{cottageOwner.name}}</h5>
                <h5 style="width: 600px" class="text">Prezime: {{cottageOwner.surname}} </h5>
                <h5 style="width: 600px" class="text">Adresa: {{cottageOwner.address.street}}  {{cottageOwner.address.number}}, {{cottageOwner.address.city}}, {{cottageOwner.address.country}}</h5>
                <h5 style="width: 600px" class="text">Email: {{cottageOwner.email}}</h5>
                <h5 style="width: 600px" class="text">Broj telefona: {{cottageOwner.telephone}}</h5>
        
              </div>

              <div class="col-info" style="margin-top: 2%" v-if="this.role === 'ROLE_INSTRUCTOR'">
                <h5 style="width: 600px" class="text">Ime:  {{instructor.firstname}}</h5>
                <h5 style="width: 600px" class="text">Prezime: {{instructor.lastname}} </h5>
                <h5 style="width: 600px" class="text">Adresa: {{instructor.address.street}}  {{instructor.address.number}}, {{instructor.address.city}}, {{instructor.address.country}}</h5>
                <h5 style="width: 600px" class="text">Email: {{instructor.email}}</h5>
                <h5 style="width: 600px" class="text">Broj telefona: {{instructor.telephone}}</h5>
        
              </div>

                <!--VLASNIK broda-->
              <div class="col-info" style="margin-top: 2%" v-if="this.role === 'ROLE_BOAT_OWNER'">
                <h5 style="width: 600px" class="text">Ime:  {{boatOwner.name}}</h5>
                <h5 style="width: 600px" class="text">Prezime: {{boatOwner.surname}} </h5>
                 <h5 style="width: 600px" class="text">Adresa: {{boatOwner.address.street}}  {{boatOwner.address.number}}, {{boatOwner.address.city}}, {{boatOwner.address.country}}</h5>
                <h5 style="width: 600px" class="text">Email: {{boatOwner.email}}</h5>
                <h5 style="width: 600px" class="text">Broj telefona: {{boatOwner.telephone}}</h5>
        
              </div>

              <div style="margin-top:40px">
                <button class="btn btn-secondary" v-on:click="editProfile">
                  Izmena profila
                </button>
                <button
                  class="btn btn-secondary buttonMargin"
                  @click="changePass()"
                >
                  Promeni lozinku
                </button>
                <button
                  class="btn btn-secondary buttonMargin"
                  data-target="#deleteAccount"
                  data-toggle="modal"
                >
                  Obrisi nalog
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
     </div>

    <div v-if="normal == false" style="margin-top:40px;margin-left:40px">
      <table>
        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Stara
                  lozinka</label
                ></td>
                <td> <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Stara lozinka" 
                  v-model="oldPassword"
                /></td></tr>
        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Nova
                  lozinka</label
                ></td>
                <td> <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Nova lozinka" 
                  v-model="this.newPassword"
                /></td></tr>
                 <tr><td><label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Ponovite
                  unos lozinke</label
                ></td>
                <td><input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Potvrdite lozinku"  v-model="this.repeatPassword"
                /></td></tr>
              <tr><td><button type="submit" class="btn btn-success btn-block" @click="changePassword()" style="width:100px;margin-top:20px">
                <span></span> Potvrdi
              </button></td>
              <td><button
                type="button" style="width:100px;margin-top:20px;margin-left:200px"
                class="btn btn-outline-secondary pull-left" @click="backToNormal()">
                Odustani
              </button></td>
          </tr>
          </table>
              
    </div>

    <!-- Modal za izmenu sifre
    <div class="modal fade" id="password" role="dialog">
      <div class="modal-dialog">
      
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Izmena lozinke
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Trenutna
                  lozinka</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Unesite lozinku"
                />
              </div>
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Nova
                  lozinka</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Nova lozinka" v-model="this.newPassword"
                />
              </div>
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Ponovite
                  unos lozinke</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Potvrdite lozinku"  v-model="this.repeatPassword"
                />
              </div>
              <button type="submit" class="btn btn-success btn-block" @click="changePassword()">
                <span></span> Potvrdi
              </button>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Odustani
            </button>
          </div>
        </div>
      </div>
    </div>-->

    <!-- Modal za brisanje naloga-->
    <div class="modal fade" id="deleteAccount" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Brisanje naloga
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" >
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Da li ste
                  sigurni da zelite da obrisete svoj nalog? Ukoliko jeste,
                  navedite razlog brisanja Vaseg naloga:</label
                >
              </div>

              <div class="form-group">
                <textarea type="text" style="width: 100%; height: 50%" v-model="explanation">
                </textarea>
              </div>
              <button type="submit" class="btn btn-success btn-block" @click="sendDeleteRequest()">
                 Potvrdi
              </button>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Odustani
            </button>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarLogOutCO from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import NavBarClient from "../../components/client/NavBarClient.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import Swal from 'sweetalert2';
export default {
  name: "MyProfile",

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
      client: "",
      newPassword: "",
      oldPassword: "",
      repeatPassword: "",
      explanation: "",
      normal: true, 
      cottageOwner: "",
      boatOwner: "",
      instructor:""
    }
  },
  methods: {
    editProfile() {
      this.$router.push({ name: "EditProfile" });
    },
    async getClient() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/client/profileClient", {headers});
      const data = await res.json();
      return data;
    },
    async getInstructor() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/instructor/profileInstructor", {headers});
      const data = await res.json();
      return data;
    },
    async getCottageOwner() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/cottageOwner/profileCottageOwner", {headers});
      const data = await res.json();
      return data;
    },
      async getBoatOwner() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/boatOwner/profileBoatOwner", {headers});
      const data = await res.json();
      return data;
    },
    changePass(){
      this.normal = false;
    },
    async changePassword(){
      console.log(localStorage.getItem("token"))
      if (this.repeatPassword != this.newPassword){
        console.log("nova sifra: "+this.newPassword)
            console.log("ponovljena sifra: "+this.repeatPassword)
       // alert("");
        return new Swal('Niste dobro uneli ponovljenu sifru!Probajte ponovo!');
      } else {
        const res = await fetch("http://localhost:8081/api/user/changePassword" , {
          method: "POST",
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
            'Content-Type': 'application/json;charset=UTF-8',
            Accept: 'application/json',
          },
          body: JSON.stringify({password: this.newPassword})
        });
        const data = await res.json();
        localStorage.setItem("token", data.accessToken);
       // alert("")
  
            return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Molimo ulogujte se ponovo sa novom sifrom!'
           }).then(this.$router.push({ name: "BoatsStartPage" }));
        
        }
    },
    async sendDeleteRequest(){
      const res = await fetch("http://localhost:8081/api/userDeleteReq" , {
        method: "POST",
        headers: {
          "Content-type": "application/json",
           Authorization: "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({explanation: this.explanation})
      });''
     const ret = await res.data
     console.log(ret)
    },
    backToNormal(){
      this.normal = true;
    }
  },
  async created() {
    this.role = localStorage.getItem("role")
  
  if(this.role == "ROLE_COTTAGE_OWNER") {
    this.cottageOwner = await this.getCottageOwner();
  } else if(this.role == "ROLE_CLIENT"){
     this.client = await this.getClient();
  } else if(this.role =="ROLE_BOAT_OWNER"){
    this.boatOwner = await this.getBoatOwner();
  }else if(this.role =="ROLE_INSTRUCTOR"){
    this.instructor = await this.getInstructor();
  }
 
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
