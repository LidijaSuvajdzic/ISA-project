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
    <h5>Sve uspesne rezervacije su prikazane u tabeli:</h5>
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
<td>{{totalEarnings}} €</td>
  </tr>
</table>
<h2>-------------------------------------------------</h2>
  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
export default {
  name: "IncomeRecords",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },
    data() {
    return {
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
        async getTotalEarnings() {
      const res1 = await fetch("http://localhost:8081/api/reservation/getTotalEarnings");
      const data1 = await res1.json();
      return data1;
    },
  },
    async created() {
      this.userRole = localStorage.getItem("role");
      this.reservations = await this.getReservation();
      console.log("Ocitao rezervacije");
      this.totalEarnings = await this.getTotalEarnings();
      console.log("Ocitao zaradu");
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