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
    <table>
        <tr><h1>Izvestaj o prihodima:</h1></tr>
        <tr><td><p>Izaberi datum od:</p></td> <td><input type="date" v-model="this.dateFrom"/></td></tr>
        <tr><td><p>Izaberi datum do:</p></td> <td><input type="date" v-model="this.dateTo"/></td> <td><button @click="getSomeReservation()">Pronadji</button></td><td><button @click="AllReservations()">Prikazi sve</button></td></tr>
    </table>      
<table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Datum pocetka</th>
            <th>Datum kraja</th>
            <th>Entitet</th>
            <th>Cena</th>
            <th></th>          
        </tr>
    </thead>
    <tbody>
          <tr v-for="(reservation, index) in reservations" :key="index">
                  <td>{{reservation.id}}</td>
                  <td>{{reservation.dateStart}}</td>
                  <td>{{reservation.dateEnd}}</td>
                  <td>{{reservation.entity.name}}</td>
                  <td>{{reservation.price}} €</td>
                  <td></td>
           </tr> 
    </tbody>
</table>
    <h2>-------------------------------------------------</h2>
<table class="styled-table1">
  <tr>
<td>Ukupni prihodi od uspesnih rezervacija su:</td>
<td>{{this.totalEarnings}} €</td>
  </tr>
</table>
<h2>-------------------------------------------------</h2>
  </div>
</template>

<script>
import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
export default {
  name: "BuisinessReport",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },

    data() {
    return {
        dateFrom:null,
        dateTo:null,
      userRole: "",
        totalEarnings: 0.0,
        reservations: "",
      reservation: { id:0, dateStart: "", dateEnd: "", entity: { id:0, name: ""}, price: 0}
    }
  },
  methods: {
    async getReservation() {
      const res = await fetch("http://localhost:8081/api/reservation/getSuccessfulBooking");
      const data = await res.json();
      return data;
    },
        async findReservation() {
     const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.get( "http://localhost:8081/api/reservation/getSomeReservations/"+this.dateFrom+"/"+this.dateTo ,
        { headers }).then(
          response => {
            this.reservations = response.data
          }
        )    
    },
            async findEarnings() {
     const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.get( "http://localhost:8081/api/reservation/getEarningsByDate/"+this.dateFrom+"/"+this.dateTo ,{
        reservations: this.reservations,
      },
        { headers }).then(
          response => {
            this.totalEarnings = response.data
          }
        )      
    },
        async getTotalEarnings() {
      const res1 = await fetch("http://localhost:8081/api/reservation/getTotalEarnings");
      const data1 = await res1.json();
      return data1;
    },
    async AllReservations() {
      this.reservations = await this.getReservation();
      this.totalEarnings = await this.getTotalEarnings();
  },
  async getSomeReservation() {
    this.reservations = await this.findReservation();
    this.totalEarnings = await this.findEarnings(); 

       
  }
  },
    async created() {
      this.userRole = localStorage.getItem("role");
  },
}
</script>
<style scoped>
h2,h5 {
  margin: 25px 20px;
}
.styled-table1 td {
  padding: 25px 20px;
}
.styled-table1 {
    border-collapse: collapse;
    margin: 25px 20px;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
} 

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