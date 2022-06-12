<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>

  <div class="form-group containerInfo">
    <section class="mb-5">
      <!--Section heading-->

      <!--Section description-->

      <div class="row" style="margin-bottom: 1%">
        <!--Grid column-->
        <div class="col-md-8 mb-md-0 mb-3">
          <form id="contact-form" name="contact-form">
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Naziv vikendice</label>
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
                    v-model="cottage.name"
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
                      v-model="cottage.address.street"
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
                      v-model="cottage.address.number"
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
                      v-model="cottage.address.city"
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

                   <div class="upload-images">
                    <input type="file"  @change="imageAdded"/>
                </div> <br/>
                <div v-if="imagesFrontend" class="images-preview">
                        <div v-for="image in imagesFrontend" :key="image">
                            <img :src="image" />
                        </div>
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
                      v-model="cottage.promoDescription"
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
                      v-model="cottage.rules"
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
                <div class="form control" v-for="adS in cottage.additionalServices" :key="adS">
                 <table> 
                   <tr style="width: 55rem"> <td >Naziv dodatne usluge:  </td><td style="width: 5rem"> </td> <td>Cena </td> </tr>
                   
                    <tr> <td>{{adS.name}} </td> <td> </td><td>{{adS.price}} din  </td> </tr>
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
        <div class="col-md-4">
          <div class="row">
            <!--Grid column-->
            <div class="col-md-2">
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
                      v-model="cottage.price"
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
            <div class="col-md-2">
              <div class="md-form mb-0">
                <label for="name" class="">Broj soba:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="text" class="form-control"   v-model="cottage.roomsNumber"/>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <!--Grid column-->
            <div class="col-md-2">
              <div class="md-form mb-0">
                <label for="name" class="">Broj kreveta:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="text" class="form-control" v-model="cottage.bedsByRoom"/>
                </div>
              </div>
            </div>
          </div>
          <div class="row" style="margin-left: 20%; margin-top: 40%">
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-md-5">
              <div class="md-form mb-2">
                <div class="input-group">
                  <button class="btn btn-success" type="button" @click="createCottage()">Sacuvaj vikendicu</button>
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
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import axios from 'axios';
//import $ from "jquery";

export default {
  name: "NewCottage",
  data(){
    return {
      images: "", 
       imagesFrontend: [],
      adServ: {name:"", price: 0},
      cottage: {id: 0, name: "",roomsNumber: 0, bedsByRoom: 0, address: { street: "", number: 0, city: "", country: "Srbija", id: 0}, promoDescription:"", rules: "", grade: 1.0 , images: [], price: 0, additionalServices: [] },
    }

  },
  
    components: {
    NavBarLogOut,
    NavBarHomePage,
  },
  methods: {
     addService(){
      this.cottage.additionalServices.push({ name: this.adServ.name, price: this.adServ.price});
      console.log(this.cottage.additionalServices)
        },

      createCottage(){
   
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      console.log(this.images);
      axios.post("http://localhost:8081/api/cottages/newCottage/", this.cottage,  {headers}).then( response => response.json());
   this.$router.push({name: "MyCottages"});
      },
               imageAdded(e) {
        
                const file = e.target.files[0];  
                console.log(file)        
                this.createBase64Image(file);
                this.imagesFrontend.push(URL.createObjectURL(file));
            },
            createBase64Image(file){
                const reader= new FileReader();
            
                reader.onload = (e) =>{
                    let img = e.target.result;
                     console.log(img)  
                    this.cottage.images.push(img);
                }
                reader.readAsDataURL(file);
            }, 

  },
    

};

</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 2%;
}

table {
  max-width: 50%;
}
    .images-preview img{
        width:10%;
        height: 10%;
    }
</style>
