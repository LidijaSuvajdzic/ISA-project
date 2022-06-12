<template>
<div v-if="role==='ROLE_BOAT_OWNER'">
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>
  <div v-if="role==='ROLE_COTTAGE_OWNER'">
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div class="">
    <div class="tab-pane active containerInfo">
     
     
<div class="containerInfo" >
   <div class="containerInfo" >
   <div v-if="reservations.length == 0"> <h5> Nemate rezervacije </h5> </div>
      <div class="card-group">
  <div class="card"  v-for="reservation in this.reservations" :key="reservation" >
    <!-- <img class="card-img-top"  alt="Card image cap"> -->
     <div class="card-header bg-transparent border-success"> {{reservation.entity.name}}</div>
    <div class="card-body">
      <h5 class="card-title">{{reservation.entity.name}}</h5>
      <p class="card-text"> Termin vazenja: {{ dateTime(reservation.term.dateStart) }}  do {{ dateTime(reservation.term.dateEnd) }}  </p>
       <p class="card-text"> Adresa: {{reservation.entity.address.street}}  {{reservation.entity.address.number}}, {{reservation.entity.address.city}}, {{reservation.entity.address.country}}  </p>
      <p class="card-text"> Cena: {{reservation.price}} din</p>
       <p class="card-text"> Klijent: {{reservation.user.name}}  {{reservation.user.surname }} </p>
          <p class="card-text">Email:   {{reservation.user.email }} </p>
      <!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
       <div class="card-footer bg-transparent border-success" ><button class="btn btn-success" v-if="reservation.term.dateEnd < d" type="button" data-target="#report" data-toggle="modal" @click="getSelected(reservation)">
            Napisi izvestaj
            </button>
      </div>
    </div>
  </div>

</div>
</div>
</div>
    </div>

<!-- Modal za report-->
<div class="modal fade" id="report" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Napisi izvestaj</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         <form role="form" @submit.prevent="makeReport()">
           <!-- <form role="form" > -->
            <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;"> Komentar </label>
                </div>
                  <div class="col">
                      <textarea 
                                class="form-control mr-sm-2"
                                type="text"
                                placeholder="Vas komentar"
                                v-model="report.comment"
                                rows="4"
                              />
                    </div>  
               
                 </div>

                    <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;"> Izaberite tip restrikcije:  </label>
                </div>
                  <div class="col">
                  <select  class="form-select" v-model="restType" style="height:2rem; width: 13rem; margin-bottom: 0.8rem; margin-top: 1rem; background-color: rgb(241, 241, 241); " aria-label="Default select example" >
                   
                    <option value="0">Bez restrikcije</option>
                    <option value="1">Negativan penal</option>
                    <option value="2">Nije se pojavio</option>
                      
                    </select>
                    </div>  
               
                 </div>
         </form>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-secondary" data-dismiss="modal">Odustani</button>
        <button type="button" class="btn btn-success" @click="makeReport()">Kreiraj izvestaj</button>
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
// import Swal from 'sweetalert2';
import axios from 'axios';

export default {
  name: "MyCottages",
  components: {
    // Search,
    NavBarLogOut,
    NavBarHomePage,
    NavBarBoatOwner
  },
  data() {
    return {
        reservations: "",
        actionReservations: "",
        cottageOwner: "",
        boatOwner: "",
        role: "",
          d : new Date(),
        report: { idClient: 0, idOwner: 0, comment: "", restrictionType: 0},

        restType: "",
        selectedId: 0,
        
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
       async getAllReservations(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reservation/allReservationsCottageOwner", {headers});
      const data = await res.json();
 this.reservations = data;
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
    async getMyCottages(){
   
    fetch("http://localhost:8081/api/cottages/myCottages/"+ this.cottageOwner.id).then( response => response.json()).then(data => this.cottages = data );
 
   },
         
    dateTime(value) {
      return moment(value).format("DD-MM-YYYY");
  
  },
    makeReport(){
 
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };

       this.report.idClient = this.selectedId;
       //this.report.idOwner = this.cottageOwner.id; //promeni za boat ownera!!
  this.report.restrictionType = parseInt(this.restType);
  console.log(  this.report.idClient);

  console.log(  this.report.idOwner); //ovo je undefined

console.log(this.report)

   axios.post("http://localhost:8081/api/reportOwner/reportCottage", this.report, {headers}).then( response => response.json());




   },
     getSelected(reservation){
      this.selectedId = reservation.user.id;
   
    },
  },
   async created() {
   this.role = localStorage.getItem("role");

   if(this.role =="ROLE_COTTAGE_OWNER"){
     this.cottageOwner = this.fetchOwner();
    this.reservations = this.getAllReservations();
   }
    else{
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
