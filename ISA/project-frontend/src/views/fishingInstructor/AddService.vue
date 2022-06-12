<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
<div>
    <h1>Dodavanje dodatne usluge:</h1>
          <table>
        <tr>
            <td><label ><span class="glyphicon glyphicon-eye-open"></span>Naziv</label></td>
            <td> <input class="form-control" id="psw" type="text" v-model="name"/></td></tr>
        <tr>
            <td><label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Cena </label></td>
            <td> <input class="form-control" id="psw" type="text" v-model="price"/></td></tr>
        <tr><td><button type="submit" class="btn btn-success btn-block" style="width:100px;margin-top:20px" @click="AddAdditionalService()">
                <span></span> Potvrdi
              </button></td></tr>
          </table>

</div>
</template>

<script>
//import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import axios from "axios";
import Swal from 'sweetalert2';

export default ({
    name: "AddService",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
            name:"",
            price:0.0
            }
    },
    methods: {
    async getAdditionalServices() {
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/additionalService/servicess/"+this.nameOfAdventure,{headers});
      const data = await res.json();
      return data;
    },
    async AddAdditionalService() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/additionalService/creating",{ 
        nameOfAdventure: this.nameOfAdventure,
        name : this.name, 
        price : this.price,
       },{
        headers
      })
      .then (response => { 
        console.log(response);
      }) 
            new Swal({
             title:"Uspesno",
             type: "warning",
             text:'Dodali ste uslugu!',
           });
    }
    },
    async created() {
        this.nameOfAdventure = localStorage.getItem("nameOfAdventure");
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