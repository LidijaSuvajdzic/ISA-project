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
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Email</th>
            <th>Broj telefona</th>
            <th>Razlog registracije</th>
            <th>Tip registracije</th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>            
        </tr>
    </thead>
    <tbody>
          <tr v-for="(user, index) in users" :key="index">
                  <td>{{user.id}}</td>
                  <td>{{user.firstname}} </td>
                  <td> {{user.lastname}}</td>
                  <td>{{user.email}}</td>
                  <td>{{user.telephone}}</td>
                  <td>{{user.reasonForRegistration}}</td>
                  <td>{{user.role}}</td>
                  <td><button class="btn btn-success btn-block" @click="confirmRequest(user.id) " >Prihvati zahtev</button></td>
                  <td><button class="btn btn-success btn-block" name="odbij" @click="SaveIdAndEmail(user.id,user.email) ">Odbij zahtev</button></td>
           </tr> 

    </tbody>
</table>
 <div> <div class="form-group" v-if='this.counter== 1'>
                    
                      
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
                @click="RejectRequest(reason)"

              >
                <span></span> Posalji
              </button>
            </form>


                    
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
export default {
  name: "AllRegistrationRequests",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },
  data() {
    return {
      counter : 0,
      users: "",
      selectID: 0,
      deleteID:"",
      userEmail:"",
      role: "",
      userRole: "",
      name:"",
      reasonn:"",
      user: { id: 0, firstname: "", lastname: "", address: { id: "", street:"", number: 0, city: "", country: ""}, reasonForRegistration:"", telephone: ""}
    
    }
  },

  methods: {
    async getUsers() {
      const res = await fetch("http://localhost:8081/api/user/getAllRequestUser");
      const data = await res.json();
      return data;
    },
    async confirmRequest(id) {
       this.selectID = id;
       const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/user/confirm/"+this.selectID,{headers})
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "AllRegistrationRequests" });
      })  
            new Swal({
             title:"Uspesno",
             type: "success",
             text:'Prihvatili ste zahtev za registraciju!',
           });
      
      this.$router.go(0);
    },
    RejectRequest(reason) {
      this.counter = 0;
      this.reasonn = reason;
      console.log(this.deleteID);
      console.log(this.userEmail);
      console.log(this.reasonn);
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.delete("http://localhost:8081/api/user/reject/" + this.deleteID+"/"+this.reasonn+"/"+this.userEmail ,{headers})
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "AllRegistrationRequests" });
      })  
      new Swal({
             title:"Uspesno",
             type: "success",
             text:'Odbili  ste zahtev za registraciju!',
           });
      this.$router.go(0);     
      
   },
   async SaveIdAndEmail(id,email) {
     this.counter = 1;
     this.deleteID=id;
     this.userEmail=email;
     console.log(this.counter)
   },

  },
    async created() {
      this.users = await this.getUsers();
      this.userRole = localStorage.getItem("role");
  },
};
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
