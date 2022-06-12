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
          <table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>

            <th>Sadrzaj zalbe za entitet</th>
            <th>Sadrzaj zalbe za osobu</th>
            <th>Ocena za entitet</th>
            <th>Ocena za osobu</th>
            <th>Korisnik koji salje reviziju</th>
            <th>Korisnik za koga je revizija</th>
            <th></th>
            <th></th>
            <th></th>    
        </tr>
    </thead>
    <tbody>
          <tr v-for="(evaluate, index) in evaluates" :key="index">
                  <td>{{evaluate.id }}</td>
                  <td> {{evaluate.contentEntity}}</td>
                  <td> {{evaluate.contentUser}}</td>
                  <td> {{evaluate.gradeForUser}}</td>
                  <td> {{evaluate.gradeForEntity}}</td> 
                  <td>{{evaluate.nameOfuserWhoSendsComplaint}} {{evaluate.lastnameOfuserWhoSendsComplaint}}</td>
                  <td>{{evaluate.name}} {{evaluate.lastname}}</td>
                  <td v-if="evaluate.accepted === null"><button class="btn btn-success btn-block" @click="Accept(evaluate.id,evaluate.mail)">Odobri</button></td>
                  <td v-if="evaluate.accepted === null"><button class="btn btn-success btn-block" @click="Reject(evaluate.id,evaluate.mail)">Odbij</button></td>
                  <td ></td>
           </tr> 

    </tbody>
</table>
    </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
import Swal from 'sweetalert2';
import axios from 'axios';

export default ({
    name: "Evaluate",
    components: {
        NavBarLogOut,
        HeaderStartPage,
        NavBarPredefAdministrator,
        NavBarAdministrator
    },
    data() {
        return {
            id:0,
            email:"",
            emailWhoSent:"",
            emailWhoReceive: "",
            userRole: "",
            counter: 0,
            content1: "",
            content2: "",
            evaluates: "",
            evaluate: {id:0,mailOfuserWhoSendsComplaint:"", nameOfuserWhoSendsComplaint:"",lastnameOfuserWhoSendsComplaint:"",mail:"",name:"",lastname:"", accepted: null, contentEntity:"",contentUser:"", gradeForUser: 0, gradeForEntity:0,      
            user: { id: 0, name: "", surname: "",email:"", address: { id: "", street:"", number: 0, city: "", country: ""}, reasonForRegistration:"", telephone: ""},
            userWhoSendsComplaint: { id: 0, name: "",email:"", surname: "", address: { id: "", street:"", number: 0, city: "", country: ""}, reasonForRegistration:"", telephone: ""}}
        }
    },
    methods: {
        async getRevision() {
            const res = await fetch("http://localhost:8081/api/evaluate/getAll");
            const data = await res.json();
            return data;
        },
        async Accept(id,email) {
            this.id = id;
            this.email = email;
        const headers = {
                Authorization: "Bearer " + localStorage.getItem("token"),
            };
            axios.post("http://localhost:8081/api/evaluate/accept/"+this.id+"/"+this.email,{headers})
            .then (response => { 
                console.log(response);
                this.$router.push({ name: "Evaluate" });
                this.$router.go(0)
            })  
                  return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Odobrili ste reviziju!'
           });
            
        },
                async Reject(id,email) {
            this.id = id;
            this.email = email;
        const headers = {
                Authorization: "Bearer " + localStorage.getItem("token"),
            };
            axios.post("http://localhost:8081/api/evaluate/reject/"+this.id+"/"+this.email,{headers})
            .then (response => { 
                console.log(response);
                this.$router.push({ name: "Evaluate" });
                this.$router.go(0)
            })  
                  return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Odbili ste reviziju!'
           });

        }
    },
      async created() {
      this.userRole = localStorage.getItem("role");
      this.evaluates =  await this.getRevision();
  }

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
