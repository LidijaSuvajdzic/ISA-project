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
   <div id="#example-2">
        <table class="styled-table">
    <thead>
        <tr>
            <th>ID zahteva</th>
            <th>Ime </th>
            <th>Prezime </th>
            <th>Email</th>
            <th>Obrazlozenje</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
          <tr v-for="(request, index) in requests" :key="index" >
                  <td>{{request.id}}</td>
                  <td>{{request.name}}</td>
                  <td>{{request.surname}}</td>
                  <td>{{request.mail}}</td>
                  <td>{{request.explanation}}</td>
                  <td><button class="btn btn-success btn-block" data-target="#prihvatanje" data-toggle="modal" @click="SaveIDAndEmail(request.id,request.mail)">Prihvati zahtev</button></td>
                  <td><button class="btn btn-success btn-block" data-target="#odbijanje" data-toggle="modal" @click="SaveIDAndEmail(request.id,request.mail)">Odbij zahtev</button></td> 
                  </tr>
    </tbody>
</table>
    </div>

    <div class="modal fade" id="odbijanje" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Razlog odbijanja
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="name">Obrazlozenje:</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="reasonn"
                />
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                @click="Reject(reasonn)"
              >
                <span></span> Posalji
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="prihvatanje" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Razlog prihvatanja
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="name">Obrazlozenje:</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="reason"
                />
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="Accept(reason)"
              >
                <span></span> Posalji
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
import axios from 'axios'
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
import Swal from 'sweetalert2';
export default({
name: "DeleteAccountRequests",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },
  data() {
    return {
      userRole: "",
      requests: "",
      selectID: 0,
      userEmail: "",
      reason:"",
      reasonn: "",
      request: { explanation: "",name:"",surname:"",mail:"", id: 0},
   
    }
  },
  methods: {
    async getRequests() {
      const res = await fetch("http://localhost:8081/api/userDeleteReq/getRequests");
      const data = await res.json();
      return data;
    },
     async SaveIDAndEmail(identificator,email) {
      this.selectID = identificator;
      this.userEmail = email;
    },
      Accept(reason) {
      this.reason = reason;
      console.log(this.selectID);
      console.log(this.reason);
      console.log(this.userEmail);
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/userDeleteReq/confirm/" + this.selectID+"/"+this.reason+"/"+this.userEmail ,{headers})
              .then (response => { 
        console.log(response);
        this.$router.push({ name: "DeleteAccountRequests" });
         this.$router.go(0); 
      }).catch(function (error) {
        console.log(error)
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Ovaj entitet se koristi u sistemu! Nemoguce ga je obrisati!',
           });
                 
        }); 
   },
      Reject(reason) {
      this.reasonn = reason;
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.delete("http://localhost:8081/api/userDeleteReq/reject/" + this.selectID+"/"+this.reasonn+"/"+this.userEmail ,{headers})
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "DeleteAccountRequests" });
      })  
      return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Odbili ste zahtev za brisanje profila!'
           });      
   }
  },
      async created() {
      this.userRole = localStorage.getItem("role");
      this.requests = await this.getRequests();
  },
})
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