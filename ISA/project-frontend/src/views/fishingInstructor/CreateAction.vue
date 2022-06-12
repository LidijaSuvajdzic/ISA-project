<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
<div>
    <h1>Dodavanje akcije</h1>
          <table>
        <tr>
            <td><label ><span class="glyphicon glyphicon-eye-open"></span>Datum pocetka</label></td>
            <td> <input class="form-control" id="psw" type="date" v-model="dateStart"/></td></tr>
        <tr>
            <td><label ><span class="glyphicon glyphicon-eye-open"></span>Vreme pocetka</label></td>
            <td> <input class="form-control" id="psw" type="time" v-model="timeStart"/></td></tr>
        <tr>
            <td><label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Vreme kraja </label></td>
            <td> <input class="form-control" id="psw" type="time" v-model="timeEnd"/></td></tr>
  
        <tr>
          <td><label ><span class="glyphicon glyphicon-eye-open"></span>Cena</label></td>
          <td> <input class="form-control" id="psw" type="number" v-model="price"/></td></tr>
        <tr>
            <td><label ><span class="glyphicon glyphicon-eye-open"></span>Validno od</label></td>
            <td> <input class="form-control" id="psw" type="date" v-model="validFrom"/></td></tr>
        <tr>
            <td><label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Validno do </label></td>
            <td> <input class="form-control" id="psw" type="date" v-model="validTo"/></td></tr>
        <tr><td><button type="submit" class="btn btn-success btn-block" style="width:100px;margin-top:20px" @click="AddAction()">
                <span></span> Potvrdi
              </button></td></tr>
          </table>
<div><table>
    <tbody>
      <tr><td><h2>Izaberi dostupne dodatne usluge:</h2></td></tr>
          <tr v-for="(additionalService, index) in additionalServices" :key="index">
                  <td>{{additionalService.name}}</td>
                  <td>{{additionalService.price}}</td>
                  <td><button class="btn btn-success btn-block" @click="addService(additionalService.name,additionalService.price)">Dodaj</button></td>
           </tr>

    </tbody>
</table></div>

         
                 <div class="col-md-10" >
                  <p v-if="this.List.length  != 0"> Trenutno definisane dodatne usluge </p>
                <div class="form control" v-for="(additionalService, index) in List" :key="index">
                 <table> 
                  
                    <tr style="width: 25rem;"> <td style="width: 8rem;">{{additionalService.name}} </td> <td> </td><td style="width: 5rem;">{{additionalService.price}} din  </td>  <td> <button type="button" class="btn btn-secondary" @click="remove(index)">x </button></td> </tr>
                   </table> 
                </div>
              </div> 
</div>
</template>

<script>
//import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import axios from "axios";
export default ({
    name: "CreateAction",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
                  client: { id: 0, firstname: "",mail:"", lastname: "",role:"", address: { id: "", street:"", number: 0, city: "", country: ""}, reasonForRegistration:"", telephone: ""},
        users:"",
        clients:"",
        role:"",
            checkedList:[],
            List:[],
            nameOfAdventure:"",
            name:"",
            entities:"",
            adventure: "",
            timeStart:"",
            timeEnd:"",
            dateStart:"",
            dateEnd:"",
            price:0,
            validFrom:"",
            validTo:"",
            additionalServices:"",
            additionalService: {name:"", price:0.0}
            }
    },
    methods: {
    async getEntities() {
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/adventures/getAdventure/"+this.nameOfAdventure,{headers});
      const data = await res.json();
      return data;
    },
    async getAdditionalServices() {
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/additionalService/servicess/"+this.nameOfAdventure,{headers});
      const data = await res.json();
      return data;
    },
           addService(name1,price1){
      this.List.push({ name: name1, price: price1});
      console.log(this.List)
        },
             remove(index){
      this.List.splice(index, 1);
    },
         async getClients() {
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        };
      const res = await fetch("http://localhost:8081/api/client/getAllCLients",{headers});
      const data = await res.json();
      return data;

    },
    async AddAction() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/reservation/addAction",{ 
        dateStart : this.dateStart, 
        dateEnd : this.dateStart,
        timeStart : this.timeStart, 
        timeEnd : this.timeEnd,
        price : this.price,
        name: this.nameOfAdventure,
        validFrom: this.validFrom,
        validTo: this.validTo,
        additionalServices: this.List,
        adventure: this.adventure
       },{
        headers
      })
      .then (response => { 
        console.log(response);
       // this.$router.push({ name: "" });
      }) 

        alert("Dodali ste admina!")
     // this.$router.go(0);
    }
    },
    async created() {
        this.nameOfAdventure = localStorage.getItem("nameOfAdventure");
        this.adventure = await this.getEntities();
        console.log(this.adventure.nameOfAdventure);
        this.additionalServices = await this.getAdditionalServices();
        this.clients = await this.getClients();
        this.role = localStorage.getItem("role");
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

h1 {
    margin: 25px 20px;
    font-family: sans-serif;
}
.tr {
    margin: 25px 20px;
    color: #ffffff;
    text-align: left;
}
button {
    margin: 25px 20px;
  background-color: #4caf50; /* Green */
}
th,
td {
    margin: 25px 20px;
    padding: 12px 15px;
}
</style>