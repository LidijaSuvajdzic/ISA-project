<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
<h2>Rezervacije za moje avanture:</h2>
<table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Datum pocetka</th>
            <th>Datum kraja</th>
            <th>Entitet</th>
            <th>Cena</th>
            <th></th>       
            <th></th>   
        </tr>
    </thead>
    <tbody>
          <tr v-for="(reservation, index) in ret" :key="index">
                  <td>{{reservation.id}}</td>
                  <td>{{dateTime(reservation.dateStart1)}}</td>
                  <td>{{dateTime(reservation.dateEnd1)}}</td>
                  <td>{{reservation.entity.name}}</td>
                  <td>{{reservation.price}} €</td>
                  <td><button class="btn btn-success btn-block" data-target="#prikazKlijena" data-toggle="modal" @click="findClient(reservation.clientID)">Prikazi profil klijenta</button></td>
                  <td><button class="btn btn-success btn-block" v-if="reservation.dateEnd1 < d"  data-target="#izvestajaa" data-toggle="modal" @click="findClientAndOwner(reservation.clientID,reservation.id)">Dodaj izvestaj</button></td>
           </tr>

    </tbody>
</table>

<h2>Moje brze rezervacije:</h2>
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
          <tr v-for="(reservation, index) in quickRet" :key="index">
                  <td>{{reservation.id}}</td>
                  <td>{{dateTime(reservation.dateStart1)}}</td>
                  <td>{{dateTime(reservation.dateEnd1)}}</td>
                  <td>{{reservation.entity.name}}</td>
                  <td>{{reservation.price}} €</td>
                  <td><button class="btn btn-success btn-block" data-target="#izvestajaa" data-toggle="modal" @click="findClient(reservation.clientID)">Prikazi profil klijenta</button></td>
<td><button class="btn btn-success btn-block" v-if="reservation.dateEnd1 < d" data-target="#izvestajaa" data-toggle="modal" @click="findClientAndOwner(reservation.clientID,reservation.id)">Dodaj izvestaj</button></td>
           </tr>

    </tbody>
</table>

  
     <div class="modal fade" id="izvestajaa" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Napisi izvestaj</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         <form role="form"  @submit.prevent="createReport()">
           <!-- <form role="form" > -->
            <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;" > Komentar </label>
                </div>
                  <div class="col">
                      <textarea 
                                class="form-control mr-sm-2"
                                type="text"
                                placeholder="Vas komentar"
                                rows="4"
                                v-model="report.comment"
                              />
                    </div>  
               
                 </div>

                    <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;"> Izaberite tip restrikcije:  </label>
                </div>
                  <div class="col">
                  <select  class="form-select" v-model="report.type" style="height:2rem; width: 13rem; margin-bottom: 0.8rem; margin-top: 1rem; background-color: rgb(241, 241, 241); " aria-label="Default select example" >
                   
                    <option value="BEZ_PENALA">Bez restrikcije</option>
                    <option value="NEGATIVAN_KOMENTAR">Negativan penal</option>
                    <option value="NIJE_SE_POJAVIO">Nije se pojavio</option>

                    </select>
                    </div>  
               
                 </div>
         </form>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-secondary" data-dismiss="modal">Odustani</button>
        <button type="button" class="btn btn-success" @click="createReport()">Kreiraj izvestaj</button>
      </div>
    </div>
      </div>
    </div>


   <div class="modal fade" id="prikazKlijena" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Profil klijenta
            </h5>
          </div>
              <div class="col-info" style="margin-top: 2%">
                <h5 style="width: 600px" class="text">Ime: {{client.firstname}}</h5>
                <h5 style="width: 600px" class="text">Prezime: {{client.lastname}}</h5>
                <h5 style="width: 600px" class="text">Adresa: {{client.address.street}} {{client.address.number}}, {{client.address.city}}, {{client.address.country}}</h5>
                <h5 style="width: 600px" class="text">Email: {{client.email}}</h5>
                <h5 style="width: 600px" class="text">Broj telefona: {{client.telephone}}</h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: {{client.loyalityType}}</h5>
                <h5 style="width: 600px" class="text">Poeni korisnika: {{client.poents}}</h5>
                <h5 style="width: 600px" class="text">Penali korisnika: {{client.penals}}</h5>
              </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Vrati se
            </button>
          </div>
        </div>
      </div>
    </div>


</template>

<script>
import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import moment from "moment";
export default ({
    name: "MyAdventureReservations",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
           d : new Date(),
          quickRet:"",
          namee:"",
          counter:0,
          inputText:"",
          userRole:"",
          id:"",
          ownerId:0,
          report:{comment:"", type:"",idOwner:0,idClient:0},
             ret: "",
      reservation: { id:0, dateStart1: null, dateEnd1: null, entity: { id:0, name: ""}, price: 0,clientID:""},
      client: {id:0,firstname:"", lastname:"", email:"", role:"", penals:0, poents:0, address: { country: "",  city: "", street: "",number:"" }, telephone:""},
        owner: {id:0,firstname:"", lastname:"", email:"", role:"", address: { country: "",  city: "", street: "",number:"" }, telephone:""}
            }
    },
    methods: {

        async getReservations() {
                const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reservation/myRegularReservation", {headers});
      const data = await res.json();
      return data;
        },


        async getMyQuickReservations() {
                const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reservation/myActionReservation", {headers});
      const data = await res.json();
      return data;
        } ,
    dateTime(value) {
      return moment(value).format("DD-MM-YYYY HH:mm:ss");
  
  },

        async findClient(clientID) {
            this.id = clientID;
            console.log(this.id);
                            const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
       const res = await fetch("http://localhost:8081/api/client/getClient/"+this.id, {headers});
      this.client = await res.json();        
      console.log(this.client.firstname);
        },

        async findOwneer(clientID) {
            this.id = clientID;
            console.log(this.id);
                            const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
       const res = await fetch("http://localhost:8081/api/user/getInstructor/"+this.id, {headers});
      this.owner = await res.json();        
        },

        async findClientAndOwner(clientID,id) {
          this.id = clientID;
          await this.findClient(this.id);
          await this.findOwneer(id);

          console.log( this.client.firstname);
          console.log( this.owner.firstname);
        },

        async createReport() {
 
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };

      console.log("Id usera je"+ this.client.id);
      console.log("Id usera je"+ this.owner.id);

   axios.post("http://localhost:8081/api/reportOwner/reportAdventure",{
      idClient : this.client.id,
      idOwner : this.owner.id,
      type: this.report.type,
      comment: this.report.comment

   }, {headers})
   }

    },
      async created() {
      this.userRole = localStorage.getItem("role");
      this.ret =  await this.getReservations();
      this.quickRet = await this.getMyQuickReservations();
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
h5 {
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

