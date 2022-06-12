<template>
  <div>
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>

  <div class="form-group containerInfo">
    <section class="mb-5">
      <!--Section heading-->

      <!--Section description-->

      <div class="row" style="margin-bottom: 1%">
        <!--Grid column-->
        <div class="col-md-7 mb-md-0 mb-3">
          <form id="contact-form" name="contact-form">
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Naziv broda</label>
                </div>
              </div>

              <!--Grid column-->
              <div class="col-md-7">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    placeholder="Naziv"
                    v-model="boat.name"
                    class="form-control"
                  />
                </div>
              </div>
            </div>
            <!--Grid row-->

            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Adresa</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-3">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                    placeholder="Ulica"
                      v-model="boat.address.street"
                  />
                </div>
              </div>
              <div class="col-md-2">
                <div class="md-form mb-2">
                  <input
                    type="number"
                    id="name"
                    name="name"
                    class="form-control"
                    placeholder="broj"
                      v-model="boat.address.number"
                  />
                </div>
              </div>
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                    placeholder="Grad"
                      v-model="boat.address.city"
                  />
                </div>
              </div>
            </div>

            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Slike</label>
                </div>
              </div>
              <!--Grid column   id="input-b6"  name="input-b6[]"-->
              <div class="col-md-6">
                <div class="md-form mb-0">
                  <div class="file-loading">
                    <input
                    
                   
                      type="file" 
                      v-bind="images"
                      accept="image/jpg, image/png "
                      multiple
                       
                    />
                  </div>
                </div>
              </div>
            </div>

            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class=""></label>
                </div>
              </div>
              <!--Grid column  OVDE DA SE IZLISTAJU UPLOADOVANE SLIKE-->
              <div class="col-md-6">
                <div class="md-form mb-0">
                  <div></div>
                </div>
              </div>
            </div>
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Opis smestaja</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-7">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="4"
                    class="form-control md-textarea"
                      v-model="boat.promoDescription"
                  ></textarea>
                </div>
              </div>
            </div>

            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Pravila ponasanja</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-7">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                      v-model="boat.rules"
                  ></textarea>
                </div>
              </div>
            </div>

    <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Pecaroska oprema </label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-7">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                      v-model="boat.fishingEquipment"
                  ></textarea>
                </div>
              </div>
            </div>

            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Dodatne usluge</label>
                </div>
              </div>
                     <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form">
                  <input
                    type="text"
                    id="message"
                    name="message"
                    placeholder="naziv"
                    rows="2"
                    class="form-control "
                      v-model="adServ.name"
                 /> 
                </div>
              </div>
                         <!--Grid column-->
                 <!--Grid column-->
            <div class="col-md-2">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control"
                      v-model="adServ.price"
                    aria-label="Amount (to the nearest dollar)"
                  />
                  <div class="input-group-append">
                    <span class="input-group-text">RSD</span>
                  </div>
                </div>
              </div>
            </div>
             
               <div class="col-md-1">
                <div class="md-form">
                 <button class="btn btn-secondary" type="button" @click="addService()">  Dodaj </button>
                </div>
                
              </div>
              <!--Grid column
              <div class="col-md-6">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                      v-model="cottage.additionalServices"
                  ></textarea>
                </div>
              </div>-->
            </div>


            <!--Tabela za ispis dodatih dodatnih usluga-->
         
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%;">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class=""></label>
                </div>
              </div>
                     <!--Grid column v-if="cottage.additionalServices.length != 0"-->
              <div class="col-md-10" >
                <div class="form control" v-for="(adS, index) in boat.additionalServices" :key="index">
                 <table> 
                   <tr style="width: 55rem"> <td >Naziv dodatne usluge:  </td><td style="width: 5rem"> </td> <td>Cena </td> </tr>
                   
                    <tr> <td>{{adS.name}} </td> <td> </td><td>{{adS.price}} din  </td>  <td> <button type="button" @click="remove(index)">x </button></td> </tr>
                   </table> 
                </div>
              </div>
         
               
              <!--Grid column
              <div class="col-md-6">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                      v-model="cottage.additionalServices"
                  ></textarea>
                </div>
              </div>-->
            </div>
          </form>
        </div>
        <!--Button inspo   <div class="text-center text-md-left">
                <a class="btn btn-primary" onclick="document.getElementById('contact-form').submit();">Send</a>
            </div>
            <div class="status"></div>      -->

        <!--Grid column-->
        <div class="col-md-5">
          <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Cena:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control"
                      v-model="boat.price"
                    aria-label="Amount (to the nearest dollar)"
                  />
                  <div class="input-group-append">
                    <span class="input-group-text">RSD</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Broj motora:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control"   v-model="boat.motorNumber"/>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Snaga:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.power"/>
                </div>
              </div>
            </div>
          </div>

                <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Kapacitet ljudi:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.quantity"/>
                </div>
              </div>
            </div>
          </div>
           <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Duzina:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.lenght"/>
                </div>
              </div>
            </div>
          </div>

              
                   <!--Grid row-->
            <div class="row" >
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form mb-0">
                  <label for="name" class="">Tip broda</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form">
             <select class="form-select" aria-label="Default select example" v-model="boatTypeString" style="height:2rem; width: 13rem;margin-bottom: 0.8rem; background-color: rgb(241, 241, 241); ">
                    <option selected>Izaberite tip</option>
                    <option value="0">jahta</option>
                    <option value="1">camac</option>
                    <option value="2">brod</option>
                    </select>
                </div>
              </div>
            </div>

              <!--Grid row-->
            <div class="row" >
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form mb-0">
                  <label for="name" class="">Odustanak od rezervacije: </label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form">
             <select class="form-select" aria-label="Default select example" v-model="boatCancelType" style="height:2rem; width: 13rem;margin-bottom: 0.8rem; background-color: rgb(241, 241, 241); ">
                    <option selected>Izaberite tip</option>
                    <option value="0">besplatno</option>
                    <option value="1">sa procentom</option>
                   
                    </select>
                </div>
              </div>
            </div>


                       <!--Grid row-->
            <div class="row" >
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form mb-0">
                  <label for="name" class="">Navigaciona oprema</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-5">
                <div class="md-form ">
             <select class="form-select" v-model="equipment" style="height:2rem; width: 13rem; margin-bottom: 0.8rem; background-color: rgb(241, 241, 241); " aria-label="Default select example" >
                   
                    <option value="GPS">Gps</option>
                    <option value="radar">radar</option>
                    <option value="VHF radio">VHF radio</option>
                      <option value="fishfinder">fishfinder</option>
                    </select>
                </div>
              </div>
            </div>
     
        
         <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Max brzina:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form ">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.maxSpeed"/>
                </div>
              </div>
            </div>
          </div>

 <div class="row" style="margin-left: 19.5em; margin-top: 5em">
            <!--Grid column-->
            <div class="col-md-5">
              <div class="md-form mb-2">
                <div class="input-group">
                  <button class="btn btn-success" type="button" @click="saveBoat()">Sacuvaj izmene</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--Grid column-->
      </div>
    </section>
  </div>

  <div></div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import axios from 'axios';
//import $ from "jquery";

export default {
  name: "EditBoat",
  data(){
    return {
        boatTypeString: "",
        boatCancelType: "",
        equipment: "",
      images: "", 
      adServ: {name:"", price: 0},
      boat: {id: 0, name: "", address: { street: "", number: 0, city: "", country: "Srbija", id: 0}, promoDescription:"", rules: "", grade: 1.0 , images: [], price: 0, additionalServices: [], boatType: 0, lenght: 0, motorNumber: 0, power: 0, maxSpeed: 0, navigationEquipment: "", fishingEquipment: "", quantity: 0, cancelationType: 0 },
    }

  },
  
    components: {
    NavBarLogOut,
    NavBarBoatOwner,
  },
  methods: {
     addService(){
      this.boat.additionalServices.push({ name: this.adServ.name, price: this.adServ.price});
      console.log(this.boat.additionalServices)
        },

      saveBoat(){
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };

      this.boat.boatType = parseInt(this.boatTypeString);
      this.boat.cancelationType = parseInt(this.boatCancelType);
      console.log( this.boat.boatType )
      this.boat.navigationEquipment = this.equipment
      console.log(this.boat.navigationEquipment)

      console.log(this.images);
      axios.post("http://localhost:8081/api/boats/editBoat", this.boat,  {headers}).then( response => response.json());
       this.$router.push({name: "MyBoats"});
      },

      async getBoat(id) {
      const res = await fetch("http://localhost:8081/api/boats/boat/" + id);
      const data = await res.json();  
       console.log(data) ;
       this.boat= data;  //samo u created nije radilo
       this.capacity = this.boat.quantity;
      return data;
    },
    removeService(service){
      console.log(service)
        this.boat.additionalServices.forEach(element => {
        if(service.name == element.name){
          this.$delete(service)
          console.log(this.boat.additionalServices);
        }
      });
    },
    remove(index){
      this.boat.additionalServices.splice(index, 1);
    }

  },
  async created(){
  this.role = localStorage.getItem("role");
    this.id = this.$route.params.id;
    this.boat = this.getBoat(this.id);  
    console.log(this.boat)
  }

};

// $(document).ready(function () {
//   $("#input-b6").fileinput({
//     showUpload: true,
//     dropZoneEnabled: false,
//     maxFileCount: 7,
//     mainClass: "input-group-lg",
//   });
// });
</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 2%;
}

table {
  max-width: 50%;
  background-color: rgb(241, 241, 241);
}
</style>
