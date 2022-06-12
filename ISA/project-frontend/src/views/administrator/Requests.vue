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
    <h1>Zahtevi za izvestaje/komentare/pohvale:</h1>
<table class="styled-table">
    <thead>
        <tr>
          <th>ID</th>
            <th>Naziv klijenta</th>
            <th>Naziv osobe koja je unela izvestaj</th>
            <th>Komentar</th>
            <th>Tip</th> 
            <th></th>
            <th></th>
            <th></th>         
        </tr>
    </thead>
    <tbody>
          <tr v-for="(report, index) in reports" :key="index">
                  <td>{{report.id}}</td>
                  <td>{{report.clientName}} {{report.clientLastname}}</td>
                  <td>{{report.ownerName}} {{report.ownerLastname}}</td>
                  <td>{{report.comment}}</td>
                  <td>{{report.type}}</td>
                  <td><button  v-if="report.type != 'NEGATIVAN_KOMENTAR'" class="btn btn-success btn-block"  @click="acceptRequest(report.id,report.type)">PRIHVATI</button></td>
                  <td><button  v-if="report.type === 'NEGATIVAN_KOMENTAR'"  class="btn btn-success btn-block" data-target="#izaberi" data-toggle="modal" @click="save(report.id,report.type)">PRIHVATI1</button></td>
                  <td><button class="btn btn-success btn-block" data-target="#brisanjeizvestaja" data-toggle="modal" @click="save(report.id,report.type)">ODBIJ</button></td>
           </tr> 
    </tbody>
</table>
  </div>


<div class="modal fade" id="izaberi" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Da li ste sigurni da zelite da obrisete zahtev?
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
             <form role="form">
               <div>
                  <label>Da li klijent dobija penal</label>
                  <select class="form-control" v-model="choise">
                    <option value="DA">DA</option>
                    <option value="NE">NE</option>
                  </select>
                
               </div>
              <div class="form-group">
                <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="acceptRequest1(choise)"
              >Da</button>
              </div>
            </form>
          </div>
        </div>
      </div>
      </div>









<div class="modal fade" id="brisanjeizvestaja" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Da li ste sigurni da zelite da obrisete zahtev?
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
             <form role="form">
              <div class="form-group">
                <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="rejectRequest()"
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
  name: "Requests",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },
    data() {
    return {
      counter:0,
        reports:"",
        userRole:"",
        id:0,
        choise:"",
        typee:"",
                report: {id:0,clientName:"",clientLastname:"",ownerName:"",ownerLastname:"",revise:false,
                     client: {id:0,firstname:"", lastname:"", email:"", role:"", penals:0, poents:0, address: { country: "",  city: "", street: "",number:"" }, telephone:""},
        owner: {id:0,firstname:"", lastname:"", email:"", role:"", address: { country: "",  city: "", street: "",number:"" }, telephone:""},
            idClient:0,
            idOwner:0,
            comment:"",
            type:"",
        },
             
    }
  },
  methods: {
    async getReps() {
                      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reportOwner/getAllReports",{headers});
      const data = await res.json();
      return data;
    },
    async acceptRequest(idd,typee) {
              this.id = idd;
        this.typee = typee;
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/reportOwner/confirm/"+this.id+"/"+this.typee,{headers})
              .then (response => { 
        console.log(response);
        this.$router.push({ name: "Requests" });
         this.$router.go(0); 
      })

    },
        async acceptRequest1(choise) {
          this.choise=choise;
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/reportOwner/confirm1/"+this.id+"/"+this.choise,{headers})
              .then (response => { 
        console.log(response);
        this.$router.push({ name: "Requests" });
         this.$router.go(0); 
      })

    },
    async rejectRequest() {
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
        console.log(this.id);
        console.log(this.typee);
        axios.delete("http://localhost:8081/api/reportOwner/deleteRequest/"+this.id,{headers})
        .then (response => { 
        console.log(response);
        this.$router.push({ name: "Requests" });
        this.$router.go(0); 
      })
      return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Obrisali ste zahtev!!'
           }); 

    },
    async save(idd,typee) {
        this.id = idd;
        this.typee = typee;
    }
  },
  async created() {
      this.userRole = localStorage.getItem("role");
      this.reports = await this.getReps();
      this.counter = 1;
  }
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