<template>
  <div>
    <NavBarClient />
  </div>
  <div v-if="this.r === false">
  <!--Pretraga-->
    <div style="width: 1000px;margin-top:20px;margin-bottom:20px" v-if="cottages.length != 0">
      <nav class="navbar navbar-expand-sm navbar-dark">
        <input
          class="form-control mr-sm-2"
          type="date"
          placeholder="Datum"
          v-model="date"
          min="this.today"
        />
        <input
          class="form-control mr-sm-2"
          type="time"
          placeholder="Vreme"
          v-model="time"
        />
        <input
          class="form-control mr-sm-2"
          type="number"
          min="0"
          placeholder="Broj dana"
          v-model="number"
        />
        <input
          class="form-control mr-sm-2"
          type="number"
          min="0"
          placeholder="Broj ljudi"
          v-model="people"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Grad"
          v-model="city"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Drzava"
          v-model="country"
        />
        <button class="btn btn-success" type="submit" @click="search()">
          Pretrazi
        </button>
      </nav>
    </div>

  <!-- Sortiranje -->
  <div class="dropdown" style="margin-bottom:20px; margin-left:20px"  >
    <button
      class="btn btn-secondary dropdown-toggle"
      type="button"
      id="dropdownMenuButton"
      data-toggle="dropdown"
      aria-haspopup="true"
      aria-expanded="false"
      v-if="cottages.length != 0"
    >
      Sortitaj
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
      <a class="dropdown-item" href="#" @click="sort('NazivRastuce')"
        >Naziv - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('NazivOpadajuce')"
        >Naziv - opadajuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('OcenaRastuce')"
        >Ocena - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('OcenaOpadajuce')"
        >Ocena - opadajuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('UlicaRastuce')"
        >Ulica - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('UlicaOpadajuce')"
        >Ulica - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('GradRastuce')"
        >Grad - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('GradOpadajuce')"
        >Grad - rastuce</a
      >
    </div>
  </div>

  <!--Filtriranje-->

  <div class="filter" style="margin-left:20px;margin-top: 20px">
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle drop-btn" ref="btnToggle" id="dropdownMenuButton" data-toggle="dropdown" 
        aria-haspopup="true" aria-expanded="false" v-if="cottages.length != 0">
            Filtriraj - ocena &ensp;
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <a class="dropdown-item" href="#a" @click="filterData(5)" >5</a>
          <a class="dropdown-item" href="#a" @click="filterData(4)" >4</a> 
          <a class="dropdown-item" href="#a" @click="filterData(3)" >3</a>
          <a class="dropdown-item" href="#a" @click="filterData(2)" >2</a>
          <a class="dropdown-item" href="#a" @click="filterData(1)" >1</a>
        </div>
        <button class="btn btn-secondary" style="margin-left:20px" @click="filterData(-1)" v-if="cottages.length != maxLength"><i class="fa fa-close"></i></button>  
    </div>   
  </div>

  <div class="containerInfo">
    <div class="tab-pane container active" v-if="this.cottages.length!=0">
      <div class="row-boats" v-for="(cottage, index) in cottages" :key="index">
        <div class="col-with-picture" >
          <div v-if="cottage.images.length != 0">
            <img
              :src="getImgUrl(cottage.images[0].filePath)"
              style="height: 250px !important; width: 300px !important"  @click="goToCottage()"
            />
          </div>
        </div>
        <div class="col-info">
          <h4 style="width: 600px" class="text">
            Promotivni opis: {{ cottage.promoDescription }}
          </h4>
          <h4 style="width: 600px" class="text">Naziv: {{ cottage.name }}</h4>
          <h4 style="width: 600px" class="text">
            Adresa: {{ cottage.address.street }} {{ cottage.address.number }},
            {{ cottage.address.city }}, {{ cottage.address.country }}
          </h4>
          <h4 style="width: 600px" class="text">Ocena: {{ cottage.grade }}</h4>
          <h4 style="width: 600px" class="text">Cena: {{cottage.price}}</h4>
          <button class="btn btn-success" type="submit" @click="openModal(cottage), getSelected(cottage)" v-if="this.s == true">
            Rezerviši
          </button>
          <button class="btn btn-success" @click="showCottage(cottage)" style="margin-left:40px">Prikazi vikendicu</button>

        </div>
      </div>
    </div>
  </div>

  <!-- MODAL -->
 <!-- <div v-if="modalOpened" class="custom-modal-overlay" @click="closeModal()"></div>
  <div v-if="modalOpened" class="custom-modal">
    <div class="custom-modal-header">
      <h1>Rezervisite vikendicu</h1>
      <button @click="closeModal()">X</button>
    </div>
    <div class="custom-modal-content">
      <h4 style="width: 600px" class="text">Naziv: {{this.selectedEntity.name}}</h4>
      <h4 style="width: 600px" class="text">
      Adresa:   {{ this.selectedEntity.address.street }} {{ this.selectedEntity.address.number }},
            {{ this.selectedEntity.address.city }}, {{ this.selectedEntity.address.country }}
      </h4>
      <h4 style="width: 600px" class="text">Ocena: {{this.selectedEntity.grade}}</h4>
      <h4 style="width: 600px" class="text">Cena: {{this.selectedEntity.price}}</h4>
      <h4 style="width: 600px" class="text">Datum pocetka: {{this.date}}</h4>
      <h4 style="width: 600px" class="text">Broj dana: {{this.number}}</h4>
      <h4 style="width: 600px" class="text" v-if="this.people > 0 ">Broj osoba: {{this.people}}</h4>
      <h4 style="width: 600px" class="text" v-if="this.people <= 0 ">Broj osoba: <input type="number" v-model="this.people"> </h4>
      
      <select width=300 style="width: 350px" 
            size="8" multiple v-model="this.additionalServicesReservation" >
        <option  v-bind:value="a.id" v-for="(a, index) in this.additionalServices" :key="index" @click="getPrice()">{{a.name}}  {{a.price}} rsd</option>
      </select>
      <div> 
        <label style="width: 600px" v-if="this.additionalServices.length != 0">Izaberite zeljene dodatne usluge:</label>
        <table v-if="this.additionalServices.length != 0">
          <tr  v-for="(as, index) in additionalServices" :key="index">
            <td> {{as.name}} </td>
            <td> {{as.price}} </td>
            <td><button class="btn btn-secondary" @click="addService(as, index)">+</button></td>
          </tr>
        </table>
      </div>
      <div v-if="this.additionalServicesReservation.length != 0"> 
        <label style="width: 600px" v-if="this.additionalServicesReservation.length != 0">Izaberali ste:</label>
        <table v-if="this.additionalServicesReservation.length != 0">
          <tr  v-for="(as, index) in additionalServicesReservation" :key="index">
            <td> {{as.name}} </td>
            <td> {{as.price}} </td>
            <td><button class="btn btn-secondary" @click="removeService(as, index)">-</button></td>
          </tr>
        </table>
      </div>
      <h4 style="width: 600px" class="text">Konacna cena: {{this.price}}</h4>
      <button class="btn btn-success" type="submit" style="margin-top:10px" @click="makeReservation()">
        Rezerviši
      </button>
      <button style="margin-left:700px" class="btn" type="submit">
        Odustani
      </button>
    </div>
  </div>-->
  </div>
  <div v-if="this.r === true" style="margin-top:50px; margin-left:50px">
  <h4 style="margin-top:50px; margin-left:10px; margin-bottom:20px"><i> Rezervisi vikendicu:</i> </h4>
   <!-- Nav tabs -->
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <a class="nav-link active" data-toggle="tab" href="#home">Podaci</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#menu1">Dodatne usluge</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#menu2">Potvrda rezervacije</a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div class="tab-pane container active" id="home">
      <div style="margin-top:50px; margin-bottom:50px">
        <table>
          <tr>
            <td><p style="font-family:Helvetica " class="text"> <i>Naziv:</i></p></td>
            <td><p>{{this.selectedEntity.name}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica " class="text"> <i>Adresa:</i> </p></td>
            <td> <p>
              {{ this.selectedEntity.address.street }} {{ this.selectedEntity.address.number }},
              {{ this.selectedEntity.address.city }}, {{ this.selectedEntity.address.country }}
            </p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Ocena:</i></p></td>
            <td><p>{{this.selectedEntity.grade}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Cena:</i></p></td>
            <td><p>{{this.selectedEntity.price}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Datum pocetka:</i></p></td>
            <td><p><input type="date"  v-model="this.date" style="width:120px"></p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Vreme:</i></p></td>
            <td><p><input type="time" v-model="this.time" style="width:120px"></p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Broj dana: </i></p></td>
            <td><p><input type="number" v-model="this.number" min="0"  @change="checkDate()" style="width:120px"></p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Datum kraja:</i></p></td>
            <td><p>{{this.format_date(this.dateEnd)}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Broj osoba: </i></p></td>
            <td><p><input type="number" v-model="this.people" min="0" style="width:120px">  Maksimalan broj ljudi: {{this.maxPeople}} </p></td>
          </tr>
        </table>
      </div>
    </div>
    <div class="tab-pane container fade" id="menu1">
       <div style="margin-left:50px;margin-top:50px;"> 
        <label  style="width: 600px; font-family:Helvetica;width: 600px" v-if="this.additionalServices.length != 0"><i>Izaberite zeljene dodatne usluge:</i></label>
        <table v-if="this.additionalServices.length != 0">
          <tr  v-for="(as, index) in additionalServices" :key="index">
            <td style="width:40%"> {{as.name}} </td>
            <td style="width:40%"> {{as.price}} din.</td>
            <td style="width:20%"><button class="btn btn-secondary" @click="addService(as, index)">+</button></td>
          </tr>
        </table>
      </div>

      <div v-if="this.additionalServicesReservation.length != 0"  style="margin-left:50px;margin-top:50px;"> 
        <label style="width: 600px; font-family:Helvetica;width: 600px " v-if="this.additionalServicesReservation.length != 0"><i>Izabrali ste:</i></label>
        <table v-if="this.additionalServicesReservation.length != 0">
          <tr  v-for="(as, index) in additionalServicesReservation" :key="index">
            <td style="width:40%"> {{as.name}} </td>
            <td style="width:40%"> {{as.price}} din.</td>
            <td style="width:20%"><button class="btn btn-secondary" @click="removeService(as, index)">-</button></td>
          </tr>
        </table>
      </div>
      <div v-if="this.additionalServicesReservation.length == 0" style="margin-left:50px;margin-top:50px;width: 600px;"> 
        Nema dodatnih usluga trenutno u ponudi.
      </div>
      <h4 style="margin-left:50px;margin-top:50px;" class="text" ><i>Konacna cena:</i> {{this.price}} din.</h4>
    </div>
    <div class="tab-pane container fade" id="menu2">
      <div style="margin-top:50px; margin-bottom:50px">
        <table>
          <tr>
            <td><p style="font-family:Helvetica" class="text"> <i>Naziv:</i></p></td>
            <td><p style="margin-top:10px">{{this.selectedEntity.name}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica " class="text"> <i>Adresa:</i> </p></td>
            <td> <p style="margin-top:10px">
              {{ this.selectedEntity.address.street }} {{ this.selectedEntity.address.number }},
              {{ this.selectedEntity.address.city }}, {{ this.selectedEntity.address.country }}
            </p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Ocena:</i></p></td>
            <td><p style="margin-top:10px"> {{this.selectedEntity.grade}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Cena:</i></p></td>
            <td><p style="margin-top:10px">{{this.price}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Datum pocetka:</i></p></td>
            <td><p style="margin-top:12px">{{this.format_date(this.date)}} , {{this.time}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Broj dana: </i></p></td>
            <td><p style="margin-top:10px">{{this.number}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Datum kraja:</i></p></td>
            <td><p style="margin-top:10px">{{this.format_date( this.dateEnd)}}</p></td>
          </tr>
          <tr>
            <td><p style="font-family:Helvetica "  class="text"> <i>Broj osoba: </i></p></td>
            <td><p style="margin-top:10px"> {{this.people}} </p></td>
          </tr>
          <tr>
            <td><button class="btn btn-success" type="submit" style="margin-top:10px" @click="makeReservation()"> Rezerviši</button>
            </td><button  class="btn btn-secondary" style="margin-top:10px" type="submit" @click="goToCottages()">  Odustani </button>
            <td>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
  </div>


</template>

<script>
import NavBarClient from "../../../components/client/NavBarClient.vue";
import axios from 'axios'
import Swal from 'sweetalert2';
import moment from 'moment';

export default {
  name: "ClietAllCottages",
  components: {
    NavBarClient,
  },

  data() {
    return {
      cottages: [],
      date: "",
      time:"",
      country: "",
      city: "",
      number: "",
      maxLength:0,
      modalOpened: false,
      people: "",
      selectedEntity: {},
      price: "",
      s: false,
      today: "",
      additionalServices : [],
      additionalServicesReservation: [],
      maxPeople: "",
      r: false,
      dateEnd: ""
    };
  },

  methods: {
    goToCottages(){
      //this.closeModal();
      this.r = false;
     //this.$router.go(0);
    },
    addService(service, index){
      this.additionalServicesReservation.push(service);
      this.additionalServices.splice(index, 1)
      this.price = this.getPrice();
    },
    removeService(service, index){
      this.additionalServices.push(service);
      this.additionalServicesReservation.splice(index, 1);
      this.price = this.getPrice();
    },
    getDateEnd(){
       const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        };
        axios.post("http://localhost:8081/api/reservation/getDateEnd" ,{
         date: this.date,
         time: this.time,
         number: this.number,
         id: this.selectedEntity.id
        },{headers})
      .then (response => { 
        console.log(response.data);
        this.dateEnd = response.data;
      })
    },
    getNumberPeople(){
       const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        };
        axios.get("http://localhost:8081/api/cottages/getMaxPeople/" + this.selectedEntity.id ,{headers})
        .then (response => { 
          console.log(response.data);
          this.maxPeople = response.data;
        })
    },
    checkDate(){
    this.today = this.dates()
    var t = this.format_date(this.today)
    var dt = this.format_date(this.date)
     if (this.date == "" || this.time == "" || this.number == "") {
        return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Morate uneti datum, vreme i broj dana!'
           });
      } else {
        if(dt < t){
           return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Morate izabrati datum koji je danasnji ili posle danasnjeg!'
           });
        } else {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        };
        axios.post("http://localhost:8081/api/reservation/checkDate" ,{
         date: this.date,
         time: this.time,
         number: this.number,
         id: this.selectedEntity.id
        },{headers})
      .then (response => { 
        console.log(response.data);
        this.price = response.data;
      })
      .catch(
        error => {
        console.log(error)
         return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Morate izabrati drugi datum!Vikendica je zauzeta u ovom periodu!'
           });
      })
        }}
    },
    getPrice(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        };
        axios.post("http://localhost:8081/api/reservation/totalPrice" ,{
          entityPrice: this.selectedEntity.price,
          services: this.additionalServicesReservation,
        },{headers})
      .then (response => { 
        console.log(response.data);
        this.price = response.data;
      });
    },
    getSelected(entity){
      this.selectedEntity = entity;
    },
    async fetchCottages() {
      const res = await fetch("http://localhost:8081/api/cottages");
      const data = await res.json();
      return data;
    },
    async showCottage(cottage){
        this.$router.push({ name: 'CottageProfile', params: { id: cottage.id}})
    },
    async search() { 
    this.today = this.dates()
    var t = this.format_date(this.today)
    var dt = this.format_date(this.date)
     if (this.date == "" || this.time == "" || this.number == "") {
        return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Morate uneti datum, vreme i broj dana!'
           });
      } else {
        if(dt < t){
           return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Morate izabrati datum koji je danasnji ili posle danasnjeg!'
           });
        } else {
        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/cottages/reservationSearch" ,{
          date: this.date,
          time: this.time,
          city: this.city,
          country: this.country,
          number: this.number,
          people: this.people
        },{headers})
      .then (response => { 
        console.log(response.data);
        this.cottages = response.data
        this.s = true;
      }) }
      }
    },
    async makeReservation() {
      console.log(this.date)
      console.log(this.time)
      console.log(this.number)
      if (this.date == "" || this.time == "" || this.number == "") {
        return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Morate uneti datum, vreme i broj dana!'
           });
      } else {
      //  this.checkDate();
        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        };
        axios.post("http://localhost:8081/api/reservation" ,{
          dateStart: this.date,
          timeStart: this.time,
          price: this.price,
          duration: this.number,
          entityId: this.selectedEntity.id,
          additionalServices : this.additionalServicesReservation,
          type: 1
        },{headers})
      .then (response => { 
         if (response.data === "Imate tri ili vise penala!Ne mozete vrsiti rezervisanje do prvog sledeceg u mesecu."){
          return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Imate tri ili vise penala!Ne mozete vrsiti rezervisanje do prvog sledeceg u mesecu.'
           });
        }
         if (response.data === "Vec ste jednom zapazali i otkazali ovu vikendicu u ovom periodu!Ne mozete ponovo!"){
         return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Vec ste jednom zapazali i otkazali ovu vikendicu u ovom periodu!Ne mozete ponovo!'
           });
         }
      }). catch(
        error => {
          console.log(error)
          return new Swal({
             title:"Obavestenje",
             type: "warning",
             text:' Ne mozete u ovom trenutku da izvrsite rezervaciju, neko je vec zakazuje!Pokusajte ponovo za koji minut!'
           });
        }
      );
       this.closeModal();
       this.goToCottages();
    }
    },
    getImgUrl(img) {
      var images = require.context('../../../assets/cottageImages/', false, /.jpg$/)
    return images('./' + img + ".jpg")
    },
    goToCottage(){
      this.$router.push({ name: "CottageProfile" });
    },
    sort(sortParam) {
      if (sortParam == "NazivRastuce")
        this.cottages.sort(function (a, b) {
          return a.name - b.name;
        });
      if (sortParam == "NazivOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.name - a.name;
        });
      if (sortParam == "OcenaRastuce")
        this.cottages.sort(function (a, b) {
          return a.grade - b.grade;
        });
      if (sortParam == "OcenaOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.grade - a.grade;
        });
        if (sortParam == "UlicaRastuce")
        this.cottages.sort(function (a, b) {
          return a.address.street - b.address.street;
        });
      if (sortParam == "UlicaOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.address.street - a.address.street;
        });
      if (sortParam == "GradRastuce")
        this.cottages.sort(function (a, b) {
          return a.address.city - b.address.city;
        });
      if (sortParam == "GradOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.address.city - a.address.city;
        });
    },
    async filterData(param){
      this.cottages = this.cottages.filter((c) => c.grade == param);
      if (param == -1) {
        this.cottages =  await this.fetchCottages();
      }
    },
    openModal(cottage) {
      console.log(cottage)
      //this.modalOpened = true;
      this.selectedEntity = cottage;
      this.price = this.selectedEntity.price;
      this.additionalServices = this.getAdditionalServices();
      this.maxPeople = this.getNumberPeople();
      this.dateEnd = this.getDateEnd();
      this.r = true;
    },
    closeModal() {
      this.modalOpened = false;
      this.r = false;
      this.date = "";
      this.name = "";
      this.country = "";
      this.city = "";
      this.number = "";
      this.price = "";
      this.today = "";
      this.additionalServices = [];
      this.additionalServicesReservation = [];
    },
    dates(){
      var today = new Date();
      var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
      var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds(); 
      var dateTime = date+' '+time;
      return dateTime;
    },
    format_date(value){
      if (value) {
        return moment(value).format('MM/DD/YYYY')
      }
    },
    getAdditionalServices(){
      const headers = {
        "Content-type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token"),
      };
      axios.get("http://localhost:8081/api/additionalService/services/" + this.selectedEntity.id ,{headers})
      .then (response => { 
        console.log(response.data);
        this.additionalServices = response.data;
      })
    }
  },
  async created() {
    this.cottages = await this.fetchCottages();
    this.maxLength = this.cottages.length;
    this.today = this.dates();
    console.log(this.today)
  }
};
</script>

<style scoped>
.row-boats {
  display: flex;
}

.text {
  text-align: left;
  font-size: 15px;
  font-weight: 600;
  margin-top: 1%;
  margin-bottom: 1%;
}

.col-info {
  margin-left: 30px;
  margin-top: 10px;
}

.row {
  width: 660%;
  padding-left: 60px;
  height: 10%;
  margin-top: 100px;
  margin-left: 5000px;
  margin-right: 500px;
  opacity: 1.2;
  border-radius: 10px;
  align-content: center;
  background-color: rgba(255, 255, 255, 0.288);
  box-shadow: 0 5px 4px rgb(0 0 0 / 30%), 0 1px 1px rgb(0 0 0 / 22%);
}
/* kartica u okviru stavke menija  */
.containerInfo {
  width: 97%;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 10px;
  opacity: 1;
  border-radius: 10px;
  align-content: center;
  position: relative;
  background-color: fff;
  box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
}
.col-with-picture {
  margin-top: 1%;
  margin-bottom: 1%;
}

.custom-modal-overlay{
  background-color: rgba(0, 0, 0, 0.6);
  position: fixed;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 100;
}

.custom-modal {
  width: 50vw;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 101;

  padding: 1.5rem;
  background-color: white;
  border-radius: 5px;
}

.custom-modal-header {
  display: flex;
  justify-content: space-between;

  border-bottom: 1px solid rgb(202, 202, 202);
}

.custom-modal-header > h1 {
  font-size: 2rem;
}

.custom-modal-header > button {
  background-color: transparent;
  border: none;
  font-weight: bold;
  outline: none;
}

.custom-modal-content {
  padding: 1rem 0;
}
</style>
