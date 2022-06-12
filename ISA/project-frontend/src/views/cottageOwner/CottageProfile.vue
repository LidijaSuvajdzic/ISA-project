<template>
  <div v-if="this.role === 'ROLE_COTTAGE_OWNER'">
    <NavBarLogOut />
    <NavBarHomePage />
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
  
  <div  v-if="!showForm">
    <div class="tab-pane active containerInfo">
      <div class="row">
        <div class="column" style="width: 22rem; height: 3rem">
          <h4>{{this.cottage.name}}</h4>
          
        </div>
        <div class="column" style="width: 35rem; height: 3rem">
          <h4>{{cottage.address.street}} {{cottage.address.number}}, {{cottage.address.city}}, {{cottage.address.country}},</h4>
        </div>
        <div class="column" style="width: 18rem; height: 3rem">
          <button type="button" class="btn btn-success" v-if="this.role === 'ROLE_COTTAGE_OWNER'" @click="editCottage(cottage)">
        Izmeni vikendicu
          </button>
        </div>
        <div class="column" style="width: 28rem; height: 3rem" >
          <!-- <button class="btn btn-success" v-if="this.role === 'ROLE_COTTAGE_OWNER'"  @click="createAction()" >Dodaj akciju</button> -->
          <button type="button" class="btn btn-success" v-if="this.role === 'ROLE_COTTAGE_OWNER'" data-target="#akcija" data-toggle="modal">
       Dodaj akciju
          </button>
          <button class="btn btn-success" data-target="#pretplata" data-toggle="modal" v-if="this.role === 'ROLE_CLIENT'" @click="subscribeModal()">Pretplati se</button>
          <button class="btn btn-success" v-if="this.role === 'ROLE_CLIENT'" style="margin-left:40px" @click="showActions()">Akcije</button>
       <button type="button" class="btn btn-outline-success"  style="margin-left:40px" v-if="this.role === 'ROLE_COTTAGE_OWNER'" data-target="#rezervacija" data-toggle="modal" @click="findCurrentClient()">
       Rezervisi
          </button> </div>
      
      </div>
 
      <div class="row">
        <div class="columnPic" v-for="im in cottage.images" :key="im"  >
          <img :src="im" style="height: 70%; width: 80%" />
        </div>



 

<!-- 
        <div class="column" style="width: 50rem">
          <div class="row">
           
            <div class="column columnSmall" style="width:60rem; height: 14em">
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="clearfix"></div>
            </div>
          </div>
          
          <div class="row" style="height: 14em">
            <div class="column columnSmall2" style="width: 60rem; height: 18em">
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="clearfix"></div>
            </div>
          </div>
        </div> -->
      </div>

      <div class="row aboutCottage">
        <div class="column columnAbout">
          <h4>Opis smestaja</h4>
          <label>
           {{cottage.promoDescription}}
          </label>
        </div>

        <div class="column" style="padding-left: 10rem">
          <h4>Informacije</h4>
          <div class="row" style="height: 20rem; background: whitesmoke">
            <div class="column" style="width: 20rem">
              <p>Cena: {{cottage.price}}</p>
              <p>Ocena vikednice: {{cottage.grade}}</p>
              <p v-if="this.role === 'ROLE_CLIENT'">Ocena vlasnika vikendice: {{this.ownerGrade}}</p>
              <p>Maksimalan broj osoba: {{capacity}}</p>
              <p>Broj soba: {{cottage.roomsNumber}}</p>
              <p>Broj kreveta po sobi: {{cottage.bedsByRoom}}</p>
              <p>Pravila ponasanja: {{cottage.rules}}</p>
            </div>
            <div 
              class="column"
              style="width: 12rem; height: 18rem; background: white"> 
            <p class="pStyle">dodatne usluge:</p>
             <div class="row" style="height: 2rem; background: white" v-for="service in cottage.additionalServices" :key="service">
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
            <form role="form" @submit.prevent="Cancel">
              <div class="form-group">
                <label for="name">Da li ste sigurni da zelite da se pretplatite ?</label>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" @click="subscribe()" style="width:80px; margin-bottom:20px">
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
                    <tr v-for="(adS, index) in this.cottage.additionalServices" :key="index">
                      <td  style="width: 6rem;" >{{adS.name}}  </td> 
                       <td  style="width: 6rem;" >{{adS.price}}  </td> 
                       <td style="width: 2.3rem;" > </td> 
                      <td><button type="button" class="btn btn-outline-secondary" @click="remove(this.cottage.additionalServices, index)">x </button> </td> 
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
                    <tr v-for="(adS, index) in this.cottage.additionalServices" :key="index">
                      <td  style="width: 6rem;" >{{adS.name}}  </td> 
                       <td  style="width: 6rem;" >{{adS.price}}  </td> 
                       <td style="width: 2.3rem;" > </td> 
                      <td><button type="button" class="btn btn-outline-secondary" @click="remove(this.cottage.additionalServices, index)">x </button> </td> 
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
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarClient from "../../components/client/NavBarClient.vue"
import HeaderLogAndRegister from "../../components/startPage/HeaderLogAndRegister.vue";
import NavBarStartPage from '../../components/startPage/NavBarStartPage.vue';
import axios from "axios"
import Swal from "sweetalert2"


export default {
  name: "CottageProfile",
  components: {
    NavBarLogOut,
    NavBarHomePage,
    HeaderStartPage,
    NavBarClient,
    HeaderLogAndRegister,
    NavBarStartPage
  },
  data() {
    return {
      role:"",
      id: "",
      cottage: "",
      capacity: 0,
      services: [],
      showForm: false, 
      action: { dateStart: "", timeStart: "", price: 0, duration: 0, entityId: 0, dateEndAction: "", additionalServices:[] },
     reservation: { dateStart: "", timeStart: "", price: 0, duration: 0, entityId: 0, additionalServices:[], clientId: 0, type: 1 },
      owner: {},
      ownerGrade: 0,
      client: ""

    };
  },
  async created() {
    this.role = localStorage.getItem("role");
    this.id = this.$route.params.id;
    this.cottage = this.getCottage(this.id); 
    this.owner = this.getUser(this.id);
    this.ownerGrade = this.owner.grade; 
    console.log(this.owner);
  
  },
  methods: {
    showActions(){
      this.$router.push({ name: 'CottageActions', params: { id: this.id}})
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
    async getCottage(id) {
      const res = await fetch("http://localhost:8081/api/cottages/cottage/" + id);
      const data = await res.json();  
       console.log(data) ;
       this.cottage= data;  //samo u created nije radilo
       this.capacity = this.cottage.bedsByRoom * this.cottage.roomsNumber;
       
       this.services = this.cottage.additionalServices
      return data;
    },
    
      async editCottage(cottage){
                axios.get("http://localhost:8081/api/reservation/check/"+ this.cottage.id).then( 
           response => { 
             console.log(response)
        this.$router.push({ name: 'EditCottage', params: { id: cottage.id}})
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce izmeniti vikendicu jer imate rezervacije u narednom periodu!'
           });
         }
         )
      
   },
  async saveAction(){
     this.action.entityId =this.cottage.id;
      console.log(this.action.entityId);
     this.action.additionalServices = this.cottage.additionalServices;
       console.log(this.action.additionalServices);

      axios.post("http://localhost:8081/api/reservation/checkAvailability", this.action).then( 
           response => { 
             console.log(response)
              axios.post("http://localhost:8081/api/reservation/actionCottage", this.action)
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce kreirati akciju u navedenom periodu jer je vikendica zauzeta!'
           });
         }
         )
   },
  

   createAction(){

     this.showForm = true;
   },
    remove(services,index){

      console.log(this.services)
      console.log(services)
      this.services.splice(index, 1);
    },
   async getUser(id){
     const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/cottageOwner/cottageOwnerUser/" + id,
        { headers }
      );
      const data = await res.json();
      this.owner = data;
      this.ownerGrade = this.owner.grade;
      return data;
   }, 
   makeReservation(){

      if(this.client.name== null){
        return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce kreirati rezervaciju jer nemate klijenta!'
           });
     } else {
     this.reservation.clientId = this.client.id;
     this.reservation.entityId = this.id;
     this.reservation.additionalServices = this.cottage.additionalServices;

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
             text:'Uspesno ste rezervisali vikendicu!'
           });
                }
              )
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce kreirati rezervaciju u navedenom periodu jer je vikendica zauzeta!'
           });
         }
         )}
   }, 
   findCurrentClient(){
      axios.get("http://localhost:8081/api/reservation/currentClient/"+ this.cottage.id).then( 
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
  margin-top: 1.5rem;
  height: 35em;
}
.column {
  float: left;
  width: 43em;
  padding: 9px;
  height: 28em;
}
.columnPic {
  float: left;
  width: 30rem;
  padding: 4px;
  height: 25em;
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

.clearfix {
  clear: both;
}
</style>
