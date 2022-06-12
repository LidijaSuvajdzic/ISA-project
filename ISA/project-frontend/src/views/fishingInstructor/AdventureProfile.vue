<template>
    <div v-if="this.role === 'ROLE_INSTRUCTOR'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
    <div v-if="this.role != 'ROLE_INSTRUCTOR'">
      <HeaderLogAndRegister />
      <HeaderStartPage />
      <NavBarStartPage />
      <button class="btn btn-success btn-block" @click="goBack()">Vrati se</button>
    </div>
  <div>
        <div  style="width: 22rem; height: 3rem">
        <table> 
          <tr> <td><h3>Naziv avanture</h3></td><td><h4>{{adventure.nameOfAdventure}}</h4></td> </tr>
          <tr></tr>
        <tr> <td><h3>Adresa avanture</h3></td><td><h4>{{adventure.address.street}} {{adventure.address.number}}, {{adventure.address.city}}, {{adventure.address.country}}</h4></td></tr>
        <tr><td><h3>Opis smestaja</h3></td><td><h4>{{adventure.promoDescription}}</h4></td></tr>
        <tr><td><h3> Pravila ponasanja</h3></td><td><h4>{{adventure.rules}} </h4></td></tr>
        <tr>
         <table>
          <tr><td><h3> Informacije</h3></td><h4>Cena: {{adventure.price}}</h4><td></td></tr>
          <tr><td></td><td><h4>Ocena: {{adventure.grade}}</h4></td></tr>
          <tr><td></td><td><h4>Navigaciona oprema: {{adventure.fishingEquipment}}</h4></td></tr>
          <tr><td></td><td><h4>Dozvoljen broj ljudi: {{adventure.maxNumberOfPeople}}</h4></td></tr>
          <tr><td></td><td><h4>Biografija instruktora: {{adventure.instructorBiografy}}</h4></td></tr>
          
          <tr><td><h3>Dostupne dodatne usluge za ovu avanturu su:</h3></td></tr>
          <tr v-for="(additionalService, index) in additionalServices" :key="index">
                 <td></td> <td><h4>{{additionalService.name}}-{{additionalService.price}}e</h4></td>
           </tr>

                     <tr><td><h3>Slike sa predhodnih druzenja:</h3></td></tr>
        <tr>
        <div >
        <div  v-for="im in adventure.images" :key="im"  >
          <img :src="im" style="height: 70%; width: 80%" />
        </div>
      </div></tr>
          <tr v-if="this.role === 'ROLE_INSTRUCTOR'"><td></td><td> <button class="btn btn-success btn-block" @click="AddService()">Dodaj uslugu</button></td></tr>
          <tr v-if="this.role === 'ROLE_INSTRUCTOR'"><td></td><td> <button class="btn btn-success btn-block" @click="AddAction()">Dodaj akciju</button></td></tr> 
                    <tr v-if="this.role === 'ROLE_INSTRUCTOR'"><td></td><td> <button class="btn btn-success btn-block" @click="AddReservation()">Dodaj rezervaciju</button></td></tr> 
          </table>

        </tr>
        
        </table>
        </div>
  </div>
        
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
export default ({
    name: "MyAdventureReservations",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
          role:"",
          nameOfAdventure:"",
          adventure:"",
          additionalSetting: {name:"", price:0.0, id:0},
          additionalServices:"",
          additionalService:{name:"",price:0.0}
        }
    },
    methods: {
        async getAdventuree() {
                            const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res1 = await fetch("http://localhost:8081/api/adventures/getAdventure/"+this.nameOfAdventure,{headers});
           const data1 = await res1.json();
      return data1;
        },
        async goBack() {
          this.$router.push({ name: "AdventuresStartPage" });          
        },
        async AddService() {
          localStorage.setItem("nameOfAdventure",this.nameOfAdventure);
          this.$router.push({ name: "AddService" });
        },
        async AddAction() {
          localStorage.setItem("nameOfAdventure",this.nameOfAdventure);
          this.$router.push({ name: "CreateAction" });

        },
        async AddReservation() {
          localStorage.setItem("nameOfAdventure",this.nameOfAdventure);
          this.$router.push({ name: "CreateReservation" });
        },
            async getAdditionalServices() {
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/additionalService/servicess/"+this.nameOfAdventure,{headers});
      const data = await res.json();
      return data;
    },
    },
    async created() {
        this.nameOfAdventure = localStorage.getItem("nameOfAdventure");
        this.adventure =  await this.getAdventuree();
         this.additionalServices = await this.getAdditionalServices();
         this.role = localStorage.getItem("role");
        console.log(this.adventure.nameOfAdventure)
    } 
})
</script>

<style scoped>
h5,h4,label,p {

    font-family: sans-serif;   
}
h3 {
    background-color: #4caf50;
    color: #ffffff;
    text-align: left;
}
button {
  background-color: #4caf50; /* Green */
}

.styled-table thead tr {
    background-color: #4caf50;
    color: #ffffff;
    text-align: left;
}
.styled-table th,
.styled-table td {
    padding: 12px 15px;
}
</style>

