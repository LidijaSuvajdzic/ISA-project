<template>

  <div v-if="this.role ==='ROLE_BOAT_OWNER'">
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>

  <div v-if="this.role ==='ROLE_COTTAGE_OWNER'">
   <NavBarLogOut />
    <NavBarHomePage />

  </div>
  <div class="divStyle">

    <div class="row" >
      <div class="column" style="width: 22rem; margin-top:1rem"> 
        <label> Pocetni period za koji kreirate izvestaj:</label>
      </div>
      <div class="column">
         <input 
                                class="form-control mr-sm-2"
                                type="date"
                                placeholder="Vreme"
                                v-model="report.dateFrom"
                              />
        
       </div>
       </div>

           <div class="row" >
      <div class="column" style="width: 22rem; margin-top:1rem"> 
        <label> Krajnji period za koji kreirate izvestaj:</label>
      </div>
      <div class="column">
         <input 
                                class="form-control mr-sm-2"
                                type="date"
                                placeholder="Vreme"
                                v-model="report.dateTo"
                              />
        
       </div>
       </div>
       <button type="button" style="margin-left: 10%" class="btn btn-success" @click="showChart()"> Generisi </button>
     </div>

     <div style="width:100%; height: 200px">  
      <!-- <canvas id="krofna" width="400px" height="400px" style="width: 50%, height: 20%" > </canvas> -->

      <div class="row"> 
          <div class="col">
             <canvas id="ljudi" width="400" height="400" style="width: 50%, height: 10%" > </canvas>
             </div>  
              <div  class="col">
                 <canvas id="zarada" width="400" height="400" style="width: 50%, height: 10%" > </canvas>
                </div>
                 
                  <div  class="col">
                    <canvas id="ocena" width="400" height="400" style="width: 50%, height: 10%" > </canvas>
                </div>
                </div>
     
     
      <div class="row"> 

                </div>
 
     </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import Chart from 'chart.js/auto';
import axios from 'axios'



export default  ({
  name: "Statistic",
  components: {
    NavBarLogOut,
    NavBarHomePage,
     NavBarBoatOwner,

  },
  props: {


  },
  
  data(){
    return {
      role: "",
      report: { type: 1,  dateTo: "", dateFrom: ""},
      cottages: "",
      boats: "",
      owner: "",
      id: 0,
      dates: "",
      earnings : [],
      names: [],
      peopleNum: [],
      grades: [],
     
     
 


    
  }
  },

  methods:{

    showChart(){
    
   if(this.role == "ROLE_COTTAGE_OWNER"){

this.report.type = 1;
   
     const headers = {
       Authorization: "Bearer " + localStorage.getItem("token"),
      };
   axios.post("http://localhost:8081/api/reportOwner/reportOwner", this.report, {headers})
   .then(response => {
   this.dates = response.data,

this.names = this.dates.names ,
   this.earnings = this.dates.earnings,
this.grades = this.dates.grade,
this.peopleNum = this.dates.numberOfPeople,

   this.printChart()}
   );
  
   }
else {
  this.report.type = 0;
  const headers = {
       Authorization: "Bearer " + localStorage.getItem("token"),
      };
   axios.post("http://localhost:8081/api/reportOwner/reportOwner", this.report, {headers})
   .then(response => {
   this.dates = response.data,
   this.names = this.dates.names ,
   this.earnings = this.dates.earnings,
this.grades = this.dates.grade,
this.peopleNum = this.dates.numberOfPeople,
   
   this.printChart()}
   );
}

 
    },

   printChart( ){

// const ctx = document.getElementById('krofna');
// const data= {
//   labels:this.names
// ,
// datasets: [{
//   label: 'Zarada',
//   data: this.earnings,
//   backgroundColor: [
//                 'rgba(255, 99, 132, 0.2)',
//                 'rgba(54, 162, 235, 0.2)',
//                 // 'rgba(255, 206, 86, 0.2)',
//                 // 'rgba(75, 192, 192, 0.2)',
//                 //  'rgba(153, 102, 255, 0.2)',
//                 //  'rgba(255, 159, 64, 0.2)'
//             ],
//     hoverOffSet: 4
  
// }]
// };

// const krofnica = new Chart(ctx, {
//   type: 'doughnut',
//   data: data,
// });
// krofnica;
console.log("imena")
console.log(this.names);
console.log("zarade")
console.log(this.earnings);
console.log("ljdi")
console.log(this.peopleNum);
console.log("ocene")
console.log(this.grades);

     const ctx1 = document.getElementById('ljudi').getContext('2d');
  
const ljudi = new Chart(ctx1, {
    type: 'bar',
    data: {
        labels: this.names, //ime entiteta
        datasets: [{
             label: 'Posecenost', //moze ime entiteta
            data: this.peopleNum,             //posecenost po entitetu
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                 'rgba(153, 102, 255, 0.2)',
                 'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                 'rgba(255, 159, 64, 1)'
            ],
      
        },
        
        ],
         options: {
        maintainAspectRatio: false,
    },
    },
    

});

ljudi;

   const ctx2 = document.getElementById('zarada').getContext('2d');
const zarada = new Chart(ctx2, {
    type: 'bar',
    data: {
        labels: this.names, //ime entiteta
        datasets: [{
           label: 'Zarada', //moze ime entiteta
            data: this.dates.earnings,             //posecenost po entitetu
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                 'rgba(153, 102, 255, 0.2)',
                 'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                 'rgba(255, 159, 64, 1)'
            ],
      
        },
        
        ]
    },
    

});

zarada;

   const ctx3 = document.getElementById('ocena').getContext('2d');
const ocena = new Chart(ctx3, {
    type: 'bar',
    data: {
        labels: this.names, //ime entiteta
        datasets: [{
             label: 'Ocena', //moze ime entiteta
            data: this.grades,             //posecenost po entitetu
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                 'rgba(153, 102, 255, 0.2)',
                 'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                 'rgba(255, 159, 64, 1)'
            ],
      
        },
        
        ]
    },
    

});

ocena;
   },

   getMyCottages(){
           const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
   axios.get("http://localhost:8081/api/cottages/ownerCottages", {headers}).then(response => this.cottages = response.data );
 console.log(this.cottages);
 
   },
    
   createReport(){

   }

  },  
  //created
   async created() {
     console.log("CREATED")
    this.role = localStorage.getItem("role");

    if(this.role =="ROLE_COTTAGE_OWNER"){

    this.getMyCottages();
    console.log(this.cottages);
    }
 


   }
 //////////
});
</script>

<style scoped>
.divStyle{
  margin-left: 20%;
  margin-top: 3%;
  font-size: larger;
}

</style>
