<template>
  <div v-if="role==='ROLE_COTTAGE_OWNER'">
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div v-if="role==='ROLE_BOAT_OWNER'">
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>
  <div class="">
    <div class="tab-pane active containerInfo">
     
     
<div class="containerInfo" >
   <div class="containerInfo" >
  <div v-if="reservations.length == 0"> <h5> Nemate rezervacije za protekli period  </h5> </div>
      <div class="card-group">
         
       <div v-for="reservation in this.reservations" :key="reservation" >  
  <div class="card"  v-if="reservation.term.dateEnd < d">
    <!-- <img class="card-img-top"  alt="Card image cap"> -->
     <div class="card-header bg-transparent border-success" > {{reservation.entity.name}}</div>
    <div class="card-body" >
      <h5 class="card-title" >{{reservation.entity.name}}</h5>
      <p class="card-text" > Termin vazenja: {{ dateTime(reservation.term.dateStart) }}  do {{ dateTime(reservation.term.dateEnd) }}  </p>
       <p class="card-text" > Adresa: {{reservation.entity.address.street}}   {{reservation.entity.address.number}}, {{reservation.entity.address.city}}, {{reservation.entity.address.country}}  </p>
      <p class="card-text" > Cena: {{reservation.price}} din</p>
       <p class="card-text" > Klijent: {{reservation.user.name}}  {{reservation.user.surname }} </p>
          <p class="card-text">Email:   {{reservation.user.email }} </p>
      <!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
       <div class="card-footer bg-transparent border-success"><button class="btn btn-success"  >
            Napisi zalbu
            </button></div>
    </div>
    </div>
  </div>

</div>
</div>
</div>
    </div>

  </div>
</template>

<script>
// import Search from "../../components/cottageOwner/Search.vue";
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
// import axios from 'axios'
import moment from "moment";

export default {
  name: "LastReservations",
  components: {
    // Search,
    NavBarLogOut,
    NavBarHomePage,
        NavBarBoatOwner
  },
  data() {
    return {
       allReservations: "",
        reservations: "",
        boatOwner: "",
        cottageOwner: "",
        role: "",
       d: new Date(),
    }
  },

  methods: {
    async fetchOwner(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/cottageOwner/profileCottageOwner", {headers});
      const data = await res.json();

      return data;
    },
      async fetchBoatOwner(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/boatOwner/profileBoatOwner", {headers});
      const data = await res.json();

      return data;
    },
      async getAllReservationsBO(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reservation/allReservationsBoatOwner", {headers});
      const data = await res.json();
 this.reservations = data;
      return data;
     
    },
       async getAllReservations(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reservation/allReservationsCottageOwner", {headers});
      const data = await res.json();
 this.reservations = data;
      return data;
     
    },
    async getMyCottages(){
   
    fetch("http://localhost:8081/api/cottages/myCottages/"+ this.cottageOwner.id).then( response => response.json()).then(data => this.cottages = data );
 
   },
         
    dateTime(value) {
      return moment(value).format("DD-MM-YYYY");
  
  },
  },
   async created() {
        this.role = localStorage.getItem("role");
 if(this.role =="ROLE_COTTAGE_OWNER"){
    this.cottageOwner = this.fetchOwner();
    this.reservations = this.getAllReservations();
 }else{
         this.boatOwner = this.fetchBoatOwner();
      this.reservations= this.getAllReservationsBO();
 }
    console.log(this.reservations);
    
  },

};


</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 2%;
    margin-right: 2%;
}
.row-boats {
  display: flex;
}
</style>
