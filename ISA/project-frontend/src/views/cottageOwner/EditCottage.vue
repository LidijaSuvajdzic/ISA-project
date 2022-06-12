<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div>
    <div class="tab-pane active containerInfo">
      <div class="row">
        
          <div class="col-md-1">
                <div class="md-form mb-0">
                  <label for="name" style=" margin-top: 0.5rem; margin-left: 2rem">Naziv </label>
                </div>
           </div> 
          
            <div class="col-md-2">
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


              <div class="col-md-1">
                <div class="md-form mb-0">
                  <label for="name" style="margin-left: 3rem; margin-top: 0.5rem">Adresa </label>
                </div>
           </div> 
           <div class="col-md-2">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name" class="form-control" v-model="cottage.address.street"/>
                </div>
              </div>   
               <div class="col-md-1">
                <div class="md-form mb-0">
                 <input type="number" id="name" name="name" class="form-control" v-model="cottage.address.number"/>
                </div>
              </div> 

                <div class="col-md-2">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name" class="form-control" v-model="cottage.address.city"/>
                </div>
              </div> 

              <div class="col-md-2">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name" class="form-control" v-model="cottage.address.country"/>
                </div>
              </div>
        </div>

    
       
    

      <div class="row rowPic" style="width: 90rem">
        <div class="column columnPic">
          <!-- 1. red malih slika-->
          <div class="column columnPic" style="width: 100rem; height: 14em">
            <div class="images">
              <figure>
                <img
                  src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                  alt="Photo"
                  class="smallImages"
                />
                <input
                  id="input-b6"
                  name="input-b6[]"
                  type="file"
                  accept="image/jpg, image/png "
                  multiple
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
            <div class="images">
              <figure>
                <img
                  src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                  alt="Photo"
                  class="smallImages"
                />
                <input
                  id="input-b6"
                  name="input-b6[]"
                  type="file"
                  accept="image/jpg, image/png "
                  multiple
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
                <input
                  id="input-b6"
                  name="input-b6[]"
                  type="file"
                  accept="image/jpg, image/png "
                  multiple
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
                <input
                  id="input-b6"
                  name="input-b6[]"
                  type="file"
                  accept="image/jpg, image/png "
                  multiple
                />
              </figure>
            </div>
            <div class="clearfix"></div>
          </div>
        </div>
      </div>

      <div class="row aboutCottage">
        <div class="column columnAbout">
          <h4>Opis smestaja</h4>
          <textarea
            type="text"
            id="message"
            name="message"
            rows="8"
            class="form-control md-textarea"
            v-model="cottage.promoDescription"
          ></textarea>
            <h4>Pravila ponasanja</h4>
          <textarea
            type="text"
            id="message"
            name="message"
           
            class="form-control md-textarea"
            v-model="cottage.rules"
          ></textarea>
        </div>

        <div class="column" style="padding-left: 5rem; width: 60em">
          <h4>Informacije</h4>
          <div class="row" style="height: 20rem; background: whitesmoke">

              
        
          
            <div class="column" style="width: 23rem">
              <table>
                <tr>
                  <td style="width: 10rem"><p>Cena:</p></td>
                  <td><input type="text"      class="form-control" v-model="cottage.price"/> </td>
                     <td> <div class="input-group-append">
                    <span class="input-group-text">RSD</span>
                  </div></td>
                </tr>
              
                
                <tr>
                  <td><p>Broj soba:</p></td>
                  <td><input type="number"     class="form-control"  v-model="cottage.roomsNumber" /></td>
                  <td></td>
                </tr>
                <tr>
                  <td><p>Broj kreveta po sobi:</p></td>
                  <td><input type="number"     class="form-control"  v-model="cottage.bedsByRoom" /></td>
                </tr>
            
              </table>
            </div>

            <div
              class="column"
              style="
                width: 25rem;
                height: 18rem;
                background: white;
                margin-left: 3rem;
              "
            >
              <p class="pStyle">dodatne usluge:</p>


                 <div class="row" style="margin-bottom: 1%; width: 40rem" >
              <!--Grid column-->
             
                     <!--Grid column-->
              <div class="col-md-3">
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
            <div class="col-md-3">
              <div class="md-form mb-5">
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
             
            </div>


        

                 <div class="col-md-10" >
                  <p v-if="this.cottage.additionalServices.length  != 0"> Trenutno definisane dodatne usluge </p>
                <div class="form control" v-for="(adS, index) in cottage.additionalServices" :key="index">
                 <table> 
                  
                    <tr style="width: 25rem;"> <td style="width: 8rem;">{{adS.name}} </td> <td> </td><td style="width: 5rem;">{{adS.price}} din  </td>  <td> <button type="button" class="btn btn-secondary" @click="remove(index)">x </button></td> </tr>
                   </table> 
                </div>
              </div>

              <!-- <table>
                <tr>
                  <td><p>WIFI:</p></td>
                  <td><input class="smallInput" type="text" /></td>
                  <td><button>ukloni</button></td>
                </tr>
                <tr>
                  <td><p>PARKING:</p></td>
                  <td><input class="smallInput" type="text" /></td>
                  <td><button>ukloni</button></td>
                </tr>
                <tr>
                  <td><input type="text" style="width: 150px" /></td>
                  <td><input class="smallInput" type="text" /></td>
                  <td>
                    <button class="btn btn-outline-success">dodaj</button>
                  </td>
                </tr>
              </table> -->
            </div>
          </div>
        </div>
      </div>
     
    </div> 
     <div class="column" style="width: 18rem; height: 3rem; margin-left: 80%; margin-top: -10%">
          <button class="btn btn-success" type="button" @click="saveCottage()">Sacuvaj izmene</button>
        </div>
    
  </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import axios from 'axios';
export default {
  name: "EditCottage",
  components: {
    NavBarLogOut,
    NavBarHomePage,
  },
  data(){
    return{
      cottage: "",
      id: 0,
       adServ: {name:"", price: 0},
    }
  },
  methods:{

    addService(){
      this.cottage.additionalServices.push({ name: this.adServ.name, price: this.adServ.price});
      console.log(this.cottage.additionalServices)
        },
   async getCottage(id) {
      const res = await fetch("http://localhost:8081/api/cottages/cottage/" + id);
      const data = await res.json();  
       console.log(data) ;
       this.cottage= data;  //samo u created nije radilo
       this.capacity = this.cottage.bedsByRoom * this.cottage.roomsNumber;
      return data;
    },
      saveCottage(){
        const headers = {
       Authorization: "Bearer " + localStorage.getItem("token"),
         };

  
      axios.post("http://localhost:8081/api/cottages/editCottage", this.cottage,  {headers}).then( response => response.json());
      this.$router.push({name: "MyCottages"});
      },

     remove(index){
      this.cottage.additionalServices.splice(index, 1);
    }
  },
  async created() {
this.role = localStorage.getItem("role");
    this.id = this.$route.params.id;
    this.cottage = this.getCottage(this.id);
  }
};
</script>

<style scoped>
.containerInfo {
  margin-left: 2%;
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
  width: 45em;
}
.aboutCottage {
  margin-top: 3.5em;
  height: 35em;
}
.column {
  float: left;
  width: 45em;
  padding: 10px;
  height: 16em;
}
.columnPic {
  float: left;
  width: 80em;
  padding: 10px;
  height: 16em;
}
.columnAbout {
  float: left;
  width: 35em;
  padding: 10px;
  height: 28em;
}
.columnSmall {
  float: left;
  width: 45em;
  margin-left: 10px;
  margin-top: -12px;
  height: 14em;
}
.columnSmall2 {
  float: left;
  width: 45em;
  margin-left: 10px;
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
  height: 18em;
  width: 16em;
  overflow: hidden;
  text-align: center;
}

.clearfix {
  clear: both;
}

.smallInput {
  width: 100px;
}
</style>
