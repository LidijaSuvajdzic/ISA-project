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
            <th>Tip</th>
            <th>Sadrzaj zalbe za entitet</th>
            <th>Sadrzaj zalbe za osobu</th>
            <th>Korisnik koji salje prijavu</th>
            <th>Korisnik za koga je prijava</th>
            <th></th>
            <th></th>    
                        <th></th>
            <th></th>          
        </tr>
    </thead>
    <tbody>
          <tr v-for="(complaint, index) in complaints" :key="index">
                  <td>{{complaint.id }}</td>
                  <td>{{complaint.complaintType}} </td>
                  <td> {{complaint.contentEntity}}</td>
                  <td> {{complaint.contentUser}}</td>
                  <td>{{complaint.nameOfuserWhoSendsComplaint}} {{complaint.lastnameOfuserWhoSendsComplaint}}</td>
                  <td>{{complaint.name}} {{complaint.surname}}</td>
                  <td  v-if="complaint.isAnswered === false"><button class="btn btn-success btn-block" @click="SaveEmails(complaint.mailOfuserWhoSendsComplaint,complaint.mail,complaint.id)">Odgovori na zalbu</button></td>
                  <td ></td>
           </tr> 

    </tbody>
</table>
 <div> <div class="form-group" v-if='this.counter== 1'>                  
             <form role="form">
              <div class="form-group">
                <label for="name">Odgovor za korisnika koji je poslao zalbu:</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="content1"
                />
              </div>
              <div class="form-group">
                <label for="name">Odgovor za korisnika za kog je poslata zalba:</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="content2"
                />
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="sendMails(content1,content2)"
              >
                <span></span> Posalji
              </button>
            </form>               
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
export default ({
    name: "Complaints",
    components: {
        NavBarLogOut,
        HeaderStartPage,
        NavBarPredefAdministrator,
        NavBarAdministrator
    },
    data() {
        return {
            id:0,
            emailWhoSent:"",
            emailWhoReceive: "",
            userRole: "",
            counter: 0,
            content1: "",
            content2: "",
            complaints: "",
            complaint: {id:0,mailOfuserWhoSendsComplaint:"", nameOfuserWhoSendsComplaint:"",lastnameOfuserWhoSendsComplaint:"",mail:"",name:"",lastname:"",isAnswered: true, contentEntity:"",contentUser:"", complaintType: "",      
            user: { id: 0, name: "", surname: "",email:"", address: { id: "", street:"", number: 0, city: "", country: ""}, reasonForRegistration:"", telephone: ""},
            userWhoSendsComplaint: { id: 0, name: "",email:"", surname: "", address: { id: "", street:"", number: 0, city: "", country: ""}, reasonForRegistration:"", telephone: ""}}
    }
    },
    methods: {
        async getComplaints() {
            const res = await fetch("http://localhost:8081/api/complaint/getAllComplaints");
            const data = await res.json();
            return data;
        },
        async sendMails(content1,content2) {
            this.counter = 0;
            this.content1 = content1;
            this.content2 = content2;
            const headers = {
                Authorization: "Bearer " + localStorage.getItem("token"),
            };
            axios.post("http://localhost:8081/api/complaint/sendMails/"+this.emailWhoSent+"/"+this.emailWhoReceive + "/"+ this.content1 + "/"+this.content2+ "/" +this.id,{headers})
            .then (response => { 
                console.log(response);
                this.$router.push({ name: "Complaints" });
            })  
                  return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Odgovori na zalbu su uspesno poslati!'
           });
            },

            SaveEmails(email1,email2,id) {
            this.id = id;  
            this.counter = 1;
            this.emailWhoSent = email1;
            this.emailWhoReceive = email2;
   },

    },
      async created() {
      this.userRole = localStorage.getItem("role");
      this.complaints =  await this.getComplaints();
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
