<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
<div>
    <h1>Izmena usluge</h1>
          <table>
        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Naziv avanture</label></td>
                <td> <input class="form-control"  id="psw" v-model="adventure.nameOfAdventure"/></td></tr>

       <tr>
         <td>
           <label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Maksimalan broj ljudi </label></td>
          <td><input  type="number" class="form-control" id="psw" v-model="adventure.maxNumberOfPeople"/></td></tr>
       <tr>
          <td>
              <label ><span class="glyphicon glyphicon-eye-open"></span> Biografija instruktora</label></td>
          <td> <input class="form-control" id="psw" v-model="adventure.instructorBiografy" /></td></tr>

       <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Opis usluge</label></td>
          <td> <input class="form-control" id="psw" v-model="adventure.promoDescription"/></td></tr>

        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Potrebna oprema</label></td>
          <td> <input class="form-control" id="psw"  v-model="adventure.fishingEquipment"/></td></tr>

            <tr>
                <td><label ><span class="glyphicon glyphicon-eye-open"></span> Izaberi uslov otkaza rezervacije</label
                ></td>
                <td><select class="form-control" v-model="adventure.cancelationType">
                    <option value="BESPLATNO">Besplatno</option>
                    <option value="SA_PROCENTOM">Sa procentom</option>
                  </select></td>
            </tr>
          <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span>Pravila</label
                ></td>
                <td> <input
                  class="form-control"
                  id="psw"
                  v-model="adventure.rules" 
                /></td></tr>

        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Adresa</label></td>
          <td> <input class="form-control" id="psw"  v-model="adventure.address.street" placeholder="Ulica"/></td>
          <td> <input class="form-control" id="psw"  v-model="adventure.address.number"  placeholder="Broj"/></td>
          </tr>
        <tr>
          <td></td>
          <td> <input class="form-control" id="psw"  v-model="adventure.address.city"  placeholder="Grad"/></td>
          <td> <input class="form-control" id="psw"  v-model="adventure.address.country"  placeholder="Drzava"/></td>
          </tr>
                  <tr>
           Slike prethodnih druzenja:         
        <div >
        <div  v-for="im in adventure.images" :key="im"  >
          <img :src="im" style="height: 70%; width: 80%" />
        </div>
      </div></tr>
          <tr>
                   <div class="upload-images">
                    <input type="file"  @change="imageAdded"/>
                </div> <br/>
                <div v-if="imagesFrontend" class="images-preview">
                        <div v-for="image in imagesFrontend" :key="image">
                            <img :src="image" />
                        </div>
                </div>
          </tr>
              <tr><td><button class="btn btn-success btn-block" style="width:100px;margin-top:20px" @click="GoBack()">
                <span></span> Odustani
              </button></td><td><button type="submit" class="btn btn-success btn-block" style="width:100px;margin-top:20px" @click="Editt()">
                <span></span> Potvrdi
              </button></td></tr>

          </table>

      <!-- <form ref="uploadForm" @submit.prevent="submit">
        <input type="file" ref="uploadImage" @change="onImageUpload()" class="form-control" required>
        <input type="button" @click="startUpload" name="Upload" value="Upload" />
      </form> -->

</div>
</template>

<script>
import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import Swal from 'sweetalert2';

export default ({
    name: "EditAdventure",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
          imagesFrontend:[],
            formData:null,
            showImages:[],
            realName:"",
            adventure:{   
                images:[],  
                realName:"", 
                nameOfAdventure:"",
                maxNumberOfPeople:0,
                instructorBiografy:"",
                promoDescription:"",
                fishingEquipment:"",
                cancelationType:"",
                rules:"",
                address: {                
                    street:"",
                    number:"",
                    city:"",
                    country:""}
            },

            }
    },
    methods: {
    
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
                    this.adventure.images.push(img);
                }
                reader.readAsDataURL(file);
            }, 
        async getAdventure() {
                const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/adventures/getAdventure/"+ this.realName, {headers});
      const data = await res.json();
      return data;
        },
        async Editt() {
                    const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/adventures/editAdventure",{ 
        realName:this.realName,
        nameOfAdventure : this.adventure.nameOfAdventure, 
        maxNumberOfPeople : this.adventure.maxNumberOfPeople, 
        instructorBiografy : this.adventure.instructorBiografy, 
        promoDescription : this.adventure.promoDescription, 
        fishingEquipment : this.adventure.fishingEquipment, 
        cancelationType : this.adventure.cancelationType,
        rules:this.adventure.rules,
        address: this.adventure.address,
        images: this.adventure.images
       },{
        headers
      })
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "MyService" });

      }) 
      .catch(function (error) {
         console.log(error.response.status)
         console.log(error.response.status)
          if (error.response.status == 400) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Ovaj entitet je rezervisan! Nemoguce ga je izmeniti!',
           });
          }
        });  
        new Swal({
             title:"Uspesno",
             type: "warning",
             text:'Uspesno ste izmenili entitet!',
           }); 
        }
    },
    async created() 
    {
      this.realName = localStorage.getItem("nameOfAdventure");
      this.adventure =  await this.getAdventure();
      console.log(this.name);
      console.log(this.adventure.nameOfAdventure);
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

