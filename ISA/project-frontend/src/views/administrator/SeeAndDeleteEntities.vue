<template>
  <div>
  <div v-if="this.userRole === 'ROLE_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
    <div v-if="this.userRole === 'ROLE_PREDEF_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarPredefAdministrator />
  </div>
  </div>
  <div>
    <h1>Entiteti:</h1>
<table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Naziv</th>
            <th>Ocena</th>
            <th>Cena</th>
            <th>Promo opis</th>
            <th>Pravila</th> 
            <th>Adresa</th>  
            <th></th>         
        </tr>
    </thead>
    <tbody>
          <tr v-for="(entity, index) in entities" :key="index">
                  <td>{{entity.id}}</td>
                  <td>{{entity.name}}</td>
                  <td>{{entity.grade}}</td>
                  <td>{{entity.price}} €</td>
                  <td>{{entity.promoDescription}}</td>
                  <td>{{entity.rules}}</td>
                  <td>{{entity.address.street}} {{entity.address.number}} {{entity.address.city}}</td>
                  <td><button class="btn btn-success btn-block" data-target="#brisanje" data-toggle="modal" @click="saveId(entity.id)">Obrisi entitet</button></td>
           </tr> 
    </tbody>
</table>
  </div>
<div>
     <h1>Korisnici:</h1>
<table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Uloga</th>
            <th>Email</th>
            <th>Adresa</th>  
            <th></th>         
        </tr>
    </thead>
    <tbody>
          <tr v-for="(user, index) in users"  :key="index">
                  <td v-if='user.role != "ROLE_PREDEF_ADMIN" && user.role != "ROLE_ADMIN"'>{{user.id}}</td>
                  <td v-if='user.role != "ROLE_PREDEF_ADMIN" && user.role != "ROLE_ADMIN"'>{{user.firstname}}</td>
                  <td v-if='user.role != "ROLE_PREDEF_ADMIN" && user.role != "ROLE_ADMIN"'>{{user.lastname}}</td>
                  <td v-if='user.role != "ROLE_PREDEF_ADMIN" && user.role != "ROLE_ADMIN"'> {{user.role }}</td>
                  <td v-if='user.role != "ROLE_PREDEF_ADMIN" && user.role != "ROLE_ADMIN"'>{{user.email}}</td>
                  <td v-if='user.role != "ROLE_PREDEF_ADMIN" && user.role != "ROLE_ADMIN"'>{{user.address.street}} {{user.address.number}} {{user.address.city}}</td>
                  <td v-if='user.role != "ROLE_PREDEF_ADMIN" && user.role != "ROLE_ADMIN"'><button class="btn btn-success btn-block" data-target="#brisanjeUsera" data-toggle="modal"  @click="saveId(user.id)">Obrisi</button></td>
           </tr> 
    </tbody>
</table>
  </div>

  <div class="modal fade" id="brisanjeUsera" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Da li ste sigurni da zelite da obrisete korisnika?
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
             <form role="form">
              <div class="form-group">
                <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="deleteUser()"
              >Da</button>
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="refreshPage()"
              >
                <span></span> Ne
              </button>
            </form>
          </div>
        </div>
      </div>
      </div>

  <div class="modal fade" id="brisanje" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Da li ste sigurni da zelite da obrisete entitet?
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
             <form role="form">
              <div class="form-group">
                <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="deleteEntity()"
              >Da</button>
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="refreshPage()"
              >
                <span></span> Ne
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
import Swal from 'sweetalert2';

export default {
  name: "SeeAndDeleteEntities",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },
    data() {
    return {
      userRole: "",
        deleteId: 0,
        entities: "",
        entity: { id:0, name: "", address: { id: "", street:"", number: 0, city: ""}, promoDescription: "", rules:"", price:0, grade: 0},
        user: { id: 0, firstname: "", lastname: "",role:"", address: { id: "", street:"", number: 0, city: "", country: ""}, reasonForRegistration:"", telephone: ""},
        users:""
    }
  },
  methods: {
    async getEntities() {
      const res = await fetch("http://localhost:8081/api/entities/getAllEntities");
      const data = await res.json();
      return data;
    },
    async getUsers() {
      const res = await fetch("http://localhost:8081/api/user");
      const data = await res.json();
      return data;
    },
    async refreshPage() {
        this.deleteId = 0;
        this.$router.push({ name: "SeeAndDeleteEntities" });
        this.$router.go(0);
    },
    async saveId(id) {
        this.deleteId = id;
    },
    async deleteEntity() {
        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
        axios.delete("http://localhost:8081/api/entities/deleteEntity/"+this.deleteId,{headers})
        .then (response => { 
        console.log(response);
        this.$router.push({ name: "SeeAndDeleteEntities" });
         this.$router.go(0); 
      }).catch(function (error) {
        if(error.response.status == 500) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Ovaj entitet se koristi u sistemu! Nemoguce ga je obrisati!',
           });
                 }
        }); 
     
    },
        async deleteUser() {
        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
        axios.delete("http://localhost:8081/api/user/deleteUser/"+this.deleteId,{headers})
        .then (response => { 
        console.log(response);
        this.$router.push({ name: "SeeAndDeleteEntities" });
        this.$router.go(0); 
      }).catch(function (error) {
        if(error.response.status == 500) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Ovaj user se koristi u sistemu! Nemoguce ga je obrisati!',
           });
                 }else if (error.response.status == 400) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Ovaj user se koristi u sistemu! Nemoguce ga je obrisati!',
           });

                 }
        });   
      // return new Swal({
      //        title:"Uspesno",
      //        type: "success",
      //        text:'Obrisali ste usera!!'
      //      }); 
    }
  },
    async created() {
      this.userRole = localStorage.getItem("role");
      this.entities = await this.getEntities();
      this.users = await this.getUsers();
  },
}

</script>
<style scoped>
.styled-table {
    border-collapse: collapse;
    margin: 25px 20px;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

h1 {
    margin: 25px 20px;
    font-family: sans-serif;
}
.styled-table thead tr {
    background-color: #4caf50;
    color: #ffffff;
    text-align: left;
}
button {
  background-color: #4caf50; /* Green */
}
.styled-table th,
.styled-table td {
    padding: 12px 15px;
}
</style>