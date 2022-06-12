<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
  <form role="form">
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Slobodan od:</label
      >
      <input type="date" class="form-control" id="psw" placeholder="Datum" v-model="freeFromDate"/>
      <input type="time" class="form-control" id="psw" placeholder="Vreme" v-model="freeFromTime"/>
    </div>
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Slobodan do:</label
      >
      <input type="date" class="form-control" id="psw" placeholder="Datum" v-model="freeToDate"/>
      <input type="time" class="form-control" id="psw" placeholder="Vreme" v-model="freeToTime"/>
    </div>
    <button type="submit" class="btn btn-success" @click="AddAvailablePeriod()"><span></span>Potvrdi</button>
  </form>  </div>
</template>

<script>
import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";

export default {
  name: "AvailablePeriod",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut,
  },
      data() {
        return {
          freeFromDate:"",
          freeFromTime:"",
          freeToDate:"",
          freeToTime:"",
        }
    },
    methods:{
      async AddAvailablePeriod() {
        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/freeTerms/period",{ 
        freeFromDate : this.freeFromDate, 
        freeFromTime : this.freeFromTime, 
        freeToDate : this.freeToDate, 
        freeToTime : this.freeToTime, 
       },{
        headers
      })
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "AvailablePeriod" });
      }) 
      this.$router.go(0);


      }
    }
};
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
</style>


