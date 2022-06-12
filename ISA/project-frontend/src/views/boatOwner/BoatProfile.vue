<template>
  <div v-if="this.role === 'ROLE_BOAT_OWNER'">
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>

  <!--OBICNI KORISNIK-->
  <div v-if="this.role === ''">
    <HeaderLogAndRegister />
    <HeaderStartPage />
    <NavBarStartPage />
  </div>
  
  <!--KLIJENT-->
  <div v-if="this.role === 'ROLE_CLIENT'">
    <NavBarClient/>
  </div>
  
  <div>
    <div class="tab-pane active containerInfo">
      <div class="row" >
        <div class="column" style="width: 19rem; height: 3rem">
          <h4>{{this.boat.name}}</h4>
          
        </div>
        <div class="column" style="width: 35rem; height: 3rem">
          <h4>{{boat.address.street}} {{boat.address.number}}, {{boat.address.city}}, {{boat.address.country}}</h4>
        </div>
        <div class="column" style="width: 18rem; height: 3rem;">
        
            <button class="btn btn-success" v-if="this.role === 'ROLE_BOAT_OWNER'" style=" height: 3rem; margin-left: 3rem " data-target="#akcija" data-toggle="modal" >Dodaj akciju</button>
        </div>
           <div class="column" style="width: 18rem; height: 3rem;">
         
         <button class="btn btn-success" v-if="this.role === 'ROLE_BOAT_OWNER'" style=" height: 3rem; " @click="editBoat()">Izmeni brod</button> 
       <button class="btn btn-outline-success"  v-if="this.role === 'ROLE_BOAT_OWNER'"  style=" height: 3rem; margin-left:2.5rem  " data-target="#rezervacija" data-toggle="modal" @click="findCurrentClient()">Rezervisi</button>
           
             
        </div>
        <div class="column" style="width: 28rem; height: 3rem" >
        
          <button class="btn btn-success" data-target="#pretplata" data-toggle="modal" v-if="this.role === 'ROLE_CLIENT'" @click="subscribeModal()">Pretplati se</button>
          <button class="btn btn-success" v-if="this.role === 'ROLE_CLIENT'" style="margin-left:40px" @click="showActions()">Akcije</button>
        </div>
      </div>

       <div class="row">
        <div class="columnPic" v-for="im in boat.images" :key="im"  >
          <img :src="im" style="height: 70%; width: 80%" />
        </div>
</div>

      <div class="row aboutCottage">
        <div class="column columnAbout">
          <h4>Opis smestaja</h4>
          <label>
           {{boat.promoDescription}}
          </label>
          <h4> Pravila ponasanja</h4>
          <label> {{boat.rules}} </label>
        </div>

        <div class="column" style="padding-left: 10rem">
          <h4>Informacije</h4>
          <div class="row" style="height: 25rem; background: whitesmoke">
            <div class="column" style="width: 20rem">
              <p>Cena: {{boat.price}}</p>
              <p>Ocena broda: {{boat.grade}}</p>
              <p v-if="this.role === 'ROLE_CLIENT'">Ocena vlasnika broda: {{this.ownerGrade}}</p>
              <p>Maksimalan broj osoba: {{capacity}}</p>
              <p>Tip broda: {{boat.typeBoat}}</p>
              <p>Navigaciona oprema: {{boat.navigationEquipment}}</p>
              <p>Max brzina: {{boat.maxSpeed}}</p>
              <p>Broj motora: {{boat.motorNumber}}</p>
              <p>Snaga: {{boat.power}}</p>
              <p>Duzina: {{boat.lenght}}</p>
            </div>

            <div 
              class="column"
              style="width: 13rem; height: 18rem; background: white"
            > 
            <p class="pStyle">dodatne usluge:</p>
             <div class="row" style="height: 2rem; margin-left: 2rem; margin-right: 2rem; background: white" v-for="service in boat.additionalServices" :key="service">
           
            
              <p>{{ service.name}} : {{service.price}}</p> 
            </div>
            </div>

             
         
          </div>
        </div>
      </div>
    </div>
  </div>


  <!-- Modal za pretplatu -->
  <div class="modal fade" id="pretplata" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
              Rezervacija
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="subscribe">
              <div class="form-group">
                <label for="name">Da li ste sigurni da zelite da se pretplatite ?</label>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" @click="subscribe()" style="width:80px; margin-bottom:20px">
                       Potvrdi
                    </button>
                  </td>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px; margin-left:230px; margin-bottom:20px">
                      Otkazi
                    </button>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>


    <!-- Modal za akciju -->
  <div class="modal fade" id="akcija" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
              Akcija
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="saveAction()">
              <div class="form-group">
                 <div class="row"> 
                  <div class="col">
                     <label style="margin-top: 1rem;"> Datum pocetka </label>
                   </div>
                     
                   <div class="col">
                      <input class="form-control mr-sm-2" type="date" placeholder="Datum" v-model="action.dateStart" min="this.today" />
                    </div>  
                 </div>
               
               <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;"> Vreme pocetka </label>
                </div>
                  <div class="col">
                      <input 
                                class="form-control mr-sm-2"
                                type="time"
                                placeholder="Vreme"
                                v-model="action.timeStart"
                              />
                    </div>  
               
                 </div>
 
             <div class="row">  
                 <div class="col">
                     <label style="margin-top: 1rem;"> Broj dana </label>
                 </div>  
                <div class="col">
                   
        <input class="form-control mr-sm-2" type="number" placeholder="Broj dana" v-model="action.duration"/>
                 </div> 
                 </div>

                 <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;"> Ponuda vazi do </label>
                </div>
                  <div class="col">
                       <input class="form-control mr-sm-2" type="date" placeholder="Datum" v-model="action.dateEndAction" min="this.today" />
                    </div>  
               
                 </div>

                  <div class="row">  
                 <div class="col">
                     <label style="margin-top: 1rem;"> Promo cena </label>
                 </div>  
                <div class="col">
                <div class="input-group-append">
                   <input  class="form-control mr-sm-2" type="number" placeholder=" cena" v-model="action.price"  />
                    <span class="input-group-text">RSD</span>
                  </div> 
                  </div> 
                 </div>

                   <div class="row">  
                 <div class="col">
                     <label style="margin-top: 1rem;"> Dodatne usluge </label>
                 </div>  
                <div class="col">
                <div class="input-group-append">
                  <table>
                    <tr v-for="(adS, index) in this.boat.additionalServices" :key="index">
                      <td  style="width: 6rem;" >{{adS.name}}  </td> 
                       <td  style="width: 6rem;" >{{adS.price}}  </td> 
                       <td style="width: 2.3rem;" > </td> 
                      <td><button type="button" class="btn btn-outline-secondary" @click="remove(this.boat.additionalServices, index)">x </button> </td> 
                       </tr> 
                     </table>
                  </div> 
                  </div> 
                 </div>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block"  style="width:80px; margin-bottom:20px">
                      Potvrdi
                    </button>
                  </td>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px; margin-left:230px; margin-bottom:20px">
                      Otkazi
                    </button>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>

        <!-- Modal za rezervaciju -->
  <div class="modal fade" id="rezervacija" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
              Kreiraj rezervaciju za korisnika 
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="makeReservation()">
              <div class="form-group">
                 <div class="row"> 
                  <div class="col">
                     <label style="margin-top: 1rem;"> Datum pocetka </label>
                   </div>
                     
                   <div class="col">
                      <input class="form-control mr-sm-2" type="date" placeholder="Datum" v-model="reservation.dateStart" min="this.today" />
                    </div>  
                 </div>
               
               <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;"> Vreme pocetka </label>
                </div>
                  <div class="col">
                      <input 
                                class="form-control mr-sm-2"
                                type="time"
                                placeholder="Vreme"
                                v-model="reservation.timeStart"
                              />
                    </div>  
               
                 </div>
 
             <div class="row">  
                 <div class="col">
                     <label style="margin-top: 1rem;"> Broj dana </label>
                 </div>  
                <div class="col">
                   
        <input class="form-control mr-sm-2" type="number" placeholder="Broj dana" v-model="reservation.duration"/>
                 </div> 
                 </div>

                 <div class="row">  
                 <div class="col">
                  <label style="margin-top: 1rem;"> Klijent:  </label>
                </div>
                  <div class="col">
                        <label>{{ this.client.name }}  {{ this.client.surname}}</label>
                    </div>  
               
                 </div>

                  <div class="row">  
                 <div class="col">
                     <label style="margin-top: 1rem;"> Cena </label>
                 </div>  
                <div class="col">
                <div class="input-group-append">
                   <input  class="form-control mr-sm-2" type="number" placeholder=" cena" v-model="reservation.price"  />
                    <span class="input-group-text">RSD</span>
                  </div> 
                  </div> 
                 </div>

                   <div class="row">  
                 <div class="col">
                     <label style="margin-top: 1rem;"> Dodatne usluge </label>
                 </div>  
                <div class="col">
                <div class="input-group-append">
                  <table>
                    <tr v-for="(adS, index) in this.boat.additionalServices" :key="index">
                      <td  style="width: 6rem;" >{{adS.name}}  </td> 
                       <td  style="width: 6rem;" >{{adS.price}}  </td> 
                       <td style="width: 2.3rem;" > </td> 
                      <td><button type="button" class="btn btn-outline-secondary" @click="remove(this.boat.additionalServices, index)">x </button> </td> 
                       </tr> 
                     </table>
                  </div> 
                  </div> 
                 </div>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block"  style="width:80px; margin-bottom:20px">
                      Potvrdi
                    </button>
                  </td>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px; margin-left:230px; margin-bottom:20px">
                      Otkazi
                    </button>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarClient from "../../components/client/NavBarClient.vue"
import HeaderLogAndRegister from "../../components/startPage/HeaderLogAndRegister.vue";
import NavBarStartPage from '../../components/startPage/NavBarStartPage.vue';
import axios from "axios"
import Swal from "sweetalert2"


export default {
  name: "BoatProfile",
  components: {
    NavBarLogOut,
    NavBarBoatOwner,
    HeaderStartPage,
    NavBarClient,
    HeaderLogAndRegister,
    NavBarStartPage
  },
  data() {
    return {
      role:"",
      id: "",
      boat: "",
     
      capacity: 0,
      action: { dateStart: "", timeStart: "", price: 0, duration: 0, entityId: 0, dateEndAction: "", additionalServices:[]},
      owner: {},
      ownerGrade: 0,
          client: "",
            reservation: { dateStart: "", timeStart: "", price: 0, duration: 0, entityId: 0, additionalServices:[], clientId: 0, type: 0 },
    };
  },
  async created() {
    this.role = localStorage.getItem("role");
    this.id = this.$route.params.id;
    this.boat = this.getBoat(this.id);  
    this.owner = this.getUser(this.id);
  },
  methods: {
     showActions(){
       this.$router.push({ name: 'CottageActions', params: { id: this.id}})
     },
    async getBoat(id) {
      const res = await fetch("http://localhost:8081/api/boats/boat/" + id);
      const data = await res.json();  
       console.log(data) ;
       this.boat= data;  //samo u created nije radilo
       this.capacity = this.boat.quantity;
      return data;
    },
       async editBoat(){
                axios.get("http://localhost:8081/api/reservation/check/"+ this.boat.id).then( 
           response => { 
             console.log(response)
       this.$router.push({ name: 'EditBoat', params: { id: this.id}})
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce izmeniti brod jer imate rezervacije u narednom periodu!'
           });
         }
         )
      
   },
  async saveAction(){
     this.action.entityId =this.boat.id;
      console.log(this.action.entityId);
     this.action.additionalServices = this.boat.additionalServices;
       console.log(this.action.additionalServices);

      axios.post("http://localhost:8081/api/reservation/checkAvailability", this.action).then( 
           response => { 
             console.log(response)
              axios.post("http://localhost:8081/api/reservation/actionBoat", this.action).then(
                 response => { 
                   console.log(response);
                 return new Swal({
             title:"Akcija kreirana !",
             type: "success",
             text:'Uspesno ste kreirali akciju!'
           });
           }).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Greska prilikom kreiranja akcije!'
           });
         }
         );
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce kreirati akciju u navedenom periodu jer je brod zauzet!'
           });
         }
         )
   },
     remove(services,index){

      console.log(this.services)
      console.log(services)
      this.services.splice(index, 1);
    },
   
    /*async editBoat(){
          this.$router.push({ name: 'EditBoat', params: { id: this.id}})
    },*/
    async getUser(id){
     const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/boatOwner/boatOwnerUser/" + id,
        { headers }
      );
      const data = await res.json();
      this.owner = data;
      console.log(this.owner)
      this.ownerGrade = this.owner.grade;
      return data;
   },
   subscribe(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.get( "http://localhost:8081/api/client/" + this.id ,
        { headers }).then(
          response => {
            console.log(response);
            alert("Uspesno ste se pretplatili!")
            this.$router.go(0);
          }
        )
    },

       makeReservation(){
     this.reservation.clientId = this.client.id;
     this.reservation.entityId = this.id;
     this.reservation.additionalServices = this.boat.additionalServices;

     if(this.client.name== null){
        return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce kreirati rezervaciju jer nemate klijenta!'
           });
     } else {

      axios.post("http://localhost:8081/api/reservation/checkAvailability",  
      { dateStart: this.reservation.dateStart, timeStart: this.reservation.timeStart, price: this.reservation.price, duration: this.reservation.duration, entityId: this.id, dateEndAction: "", additionalServices:[] }).then( 
           response => { 
             console.log(response)
              axios.post("http://localhost:8081/api/reservation/makeReservationOwner", this.reservation).then(
                res=> {
                  console.log(res);
               return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Uspesno ste rezervisali brod!'
           });
                }
              )
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce kreirati rezervaciju u navedenom periodu jer je brod zauzet!'
           });
         }
         )}
   }, 
   findCurrentClient(){
      axios.get("http://localhost:8081/api/reservation/currentClient/"+ this.boat.id).then( 
           response => { 
             console.log(response)
             this.client = response.data;
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nemate klijenta kome mozete napraviti rezervaciju'
           });
         }
         )
   }
  },
  
};
</script>

<style scoped>
.containerInfo {
  margin-left: 3%;
  margin-top: 2%;
}
.row-boats {
  display: flex;
}
.buttonMargin {
  margin-left: 5%;
}
.mainImage {
  height: 30em;
  width: 43em;
}
.aboutCottage {
  margin-top: 3.5em;
  height: 35em;
}
.column {
  float: left;
  width: 43em;
  padding: 9px;
  height: 28em;
}
.columnAbout {
  float: left;
  width: 50em;
  padding: 10px;
  height: 28em;
}
.columnSmall {
  float: left;
  width: 45em;
  margin-left: 5px;
  margin-top: -12px;
  height: 14em;
}
.columnSmall2 {
  float: left;
  width: 45em;
  margin-left: 5px;
  margin-top: 15px;
  height: 14em;
}
/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
.backgroundInfo {
  background: whitesmoke;
}
.pStyle {
  font-size: 18px;
  font-weight: bold;
  font-style: italic;
}
.smallImages {
  height: 15em;
  width: 18em;
}
.images {
  float: left;
  border-style: solid;
  border-width: 2px;
  margin: 3px;
  height: 15em;
  width: 16em;
  overflow: hidden;
  text-align: center;
}
.columnPic {
  float: left;
  width: 30rem;
  padding: 4px;
  height: 25em;
}

.clearfix {
  clear: both;
}
</style>
