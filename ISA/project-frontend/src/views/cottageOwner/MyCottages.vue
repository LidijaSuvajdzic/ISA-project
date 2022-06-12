<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div class="">
    <div class="tab-pane active containerInfo">
      <div> 

         <table style="width: 90%">
        <tr >
          <td style="width: 6%"> </td>
          <!-- <td v-if="this.cottages.length != 0" ><Search /></td> -->
            <nav class="navbar navbar-expand-sm navbar-dark">
    <form class="form-inline" @submit.prevent="searchCottages()">
      <div class="row">
        <div class="">
          <input
            class="form-control mr-bg-4 mr-sm-4"
            type="text"
            placeholder="naziv vikendice"
            v-model="search.name"
          />
          <input class="form-control mr-sm-2" type="text" placeholder="ulica" v-model="search.street"/>
           <input class="form-control mr-sm-2" type="text" placeholder="grad" v-model="search.city"/>
          <button class="btn btn-success" type="submit" @click="searchCottages()">Search</button>
        </div>
      </div>
    </form>
  </nav>
        <td v-if="this.cottages.length == 0" ><h4> Nemate vikendice. </h4></td> 
          <td style="width: 28%">
            <button class="btn btn-danger" style="margin-left: 39%">
              <router-link
                style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                "
                to="/newCottage"
              >
                Dodaj novu vikendicu</router-link
              >
            </button>
          </td>
          <td></td>
        </tr>
      </table>
      </div>
     
<div class="containerInfo" >
      <div class="row-boats"  v-for="cottage in cottages" :key="cottage" >
       
        <div class="col-with-picture" style="margin-right: 5%; margin-top: 1%">
          <div style="height: 10rem; width: 19rem">
            <img
              :src="cottage.images[0]"
              class="rounded img-thumbnail img-fluid"
              alt="Responsive image"
              style="margin-top: 1rem; max-height: 300px !important; max-width: 320px !important"
            />
          </div>
        </div>

        <div class="col-info" style="margin-top: 3%">
         
          <h4 style="width: 600px" class="text">Naziv:   {{cottage.name}}</h4>
          <h4 style="width: 600px" class="text">Adresa: {{ cottage.address.street }}, {{ cottage.address.number }},{{ cottage.address.city }},
              {{ cottage.address.country }}</h4>
          <h4 style="width: 600px" class="text">Prosecna ocena: {{cottage.grade}}</h4>
           <h4 style="width: 600px" class="text">Cena: {{cottage.price}} din</h4> 
           <h6 style="width: 600px" class="text">Promotivni opis:   {{cottage.promoDescription}}</h6>
        </div>
        <div class="col-info" style="margin-left: 10%; margin-top: 4%">
          <div class="row" style="margin-bottom: 15%">
            <button
              class="btn btn-secondary"
              data-target="#delete"
              data-toggle="modal"
              @click="getSelected(cottage.id)"
            >
              Obrisi vikendicu
            </button>
          </div>
          <div class="row" style="margin-bottom: 15%">
            <button class="btn btn-success" @click="showCottage(cottage)" >
             <!--  <router-link to="{name: 'CottageProfile', params: { id: 'cottage.id'} }"></router-link>
               <router-link  style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                " to="`/cottageProfile/${cottage.id}`"></router-link> -->
            Prikazi vikendicu
             
            </button>
          </div>
              <div class="row">
            <button class="btn btn-outline-danger"  data-target="#unavailable"
              data-toggle="modal"   @click="getSelected(cottage.id)" >
       
           Nedostupnost
             
            </button>
          </div>
        </div>
        
      </div>
</div>
    </div>

  <!-- Modal za nedostupnost-->
    <div class="modal fade" id="unavailable" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Definisi period nedostupnosti
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form"  @submit.prevent="unavailablePeriodDefine()">
              <div class="form-group">
                 <div class="row">
               <div class="col">
               <label> Od:  </label>
              </div>
               <div class="col">
               <input class="form-control mr-sm-2" type="date" v-model="unavailablePeriod.dateFrom"/>
              </div>
              </div>
              <div class="row">
                <div class="col">
               <label>Do: </label>
              </div>
               <div class="col">
                <input class="form-control mr-sm-2" type="date" v-model="unavailablePeriod.dateTo"/>
              </div>
              </div>
              </div>

              <button type="submit" class="btn btn-success btn-block">
                <span></span> Potvrdi
              </button>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Odustani
            </button>
          </div>
        </div>
      </div>
    </div>


    <!-- Modal za brisanje-->
    <div class="modal fade" id="delete" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Brisanje vikendice
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="deleteCottage()">
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Da li ste
                  sigurni da zelite da obrisete ovu vikendicu?</label
                >
              </div>

              <button type="submit" class="btn btn-success btn-block" @click="deleteCottage()">
                <span></span> Potvrdi
              </button>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Odustani
            </button>
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
import axios from 'axios'
import Swal from 'sweetalert2';

export default {
  name: "MyCottages",
  components: {
    // Search,
    NavBarLogOut,
    NavBarHomePage,
  },
  data() {
    return {
      cottages: [],
      search: { name: "", street: "", city: ""},
      name: "",
      city: "",
      cottageOwner: "",
      notEmpty: false,
      unavailablePeriod: { dateFrom: "", dateTo: "", entityId: "" }
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
   
    async getMyCottages(){
    fetch("http://localhost:8081/api/cottages/myCottages/"+ this.cottageOwner.id).then( response => response.json()).then(data => this.cottages = data );

   },

      async showCottage(cottage){
        this.$router.push({ name: 'CottageProfile', params: { id: cottage.id}})
   },
   
    getSelected(id){
      this.selectedId = id;
    },
 
     deleteCottage(){
       console.log(this.selectedId)
        axios.get("http://localhost:8081/api/reservation/check/"+ this.selectedId).then( 
           response => { 
             console.log(response)
            axios.get("http://localhost:8081/api/cottages/delete/"+ this.selectedId);
         }
         ).catch(error =>{
           console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce obrisati vikendicu jer imate rezervacije u narednom periodu!'
           });
         }
         )
   
      this.cottages = this.getMyCottages();
     this.$router.go(0);

       
   },
   searchCottages(){
       const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/cottages/searchCottageOwner", this.search, {headers}).then( response=> {
         console.log(response);
         this.cottages = response.data
      }
      )
   },
      unavailablePeriodDefine() {
     this.unavailablePeriod.entityId =this.selectedId;
       axios.post("http://localhost:8081/api/reservation/checkIfReservationExist", this.unavailablePeriod).then( 
           response => { 
             console.log(response)
              axios.post("http://localhost:8081/api/reservation/unavailablePeriodBoatOwner", this.unavailablePeriod).then(
                 response => { 
             console.log(response)
              return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Uspesno ste definisali nedostupnost!'
           });
             }
              ).catch(
                error =>{
        console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Greska pri cuvanju!'
           });
         }
              );
         }
            ).catch(error =>{
        console.log(error);
            return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Nije moguce definisati ovaj period jer je vikendica zauzeta tada!'
           });
         }
         )
   }
  },
   async created() {
     this.cottageOwner = await this.fetchOwner();
    this.cottages = await this.getMyCottages();
    
  },
};

</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 5%;
}
.row-boats {
  display: flex;
}

</style>
