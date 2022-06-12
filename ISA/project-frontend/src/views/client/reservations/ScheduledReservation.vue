<template>
  <div>
    <NavBarClient />
  </div>
    <div class="container"  v-if="reservations.length != 0">
      <h2><i>Zakazane rezervacije:</i></h2>
      <div class="dropdown" style="margin-top: 40px ;margin-bottom:20px">
        <button
          class="btn btn-secondary dropdown-toggle"
          type="button"
          id="dropdownMenuButton"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
          v-if="reservations.length != 0"
        >
          Sortitaj
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <a class="dropdown-item" href="#" @click="sort('CenaRastuce')"
            >Cena - rastuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('CenaOpadajuce')"
            >Cena - opadajuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('DatumRastuce')"
            >Datum - rastuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('DatumOpadajuce')"
            >Datum - opadajuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('TrajanjeRastuce')"
            >Trajanje - rastuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('TrajanjeOpadajuce')"
            >Trajanje - opadajuce</a
          >
        </div>
      </div>
      <table
        class="table table-light table-striped table-hover"
        style="margin-bottom: 80px"
      >
        <thead>
          <tr>
            <th>Objekat rezervacije</th>
            <th>Datum pocetka</th>
            <th>Datum kraja</th>
            <th>Trajanje</th>
            <th>Cena</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(reservation, index) in reservations" :key="index">
            <td>{{ reservation.entityType }}</td>
            <td>{{ format_date(reservation.term.dateStart) }}</td>
            <td>{{ format_date(reservation.term.dateEnd) }}</td>
            <td>{{ reservation.duration }} 
              <span v-if="reservation.entityType === 'COTTAGE' || reservation.entityType === 'BOAT'"> dana </span>
              <span v-if="reservation.entityType === 'ADVENTURE'"> h </span>  
            </td>
            <td>{{ reservation.price }}</td>
            <td v-if="reservation.canceled == false">
              <button class="btn btn-danger"  data-target="#odjava" data-toggle="modal" @click="getSelected(reservation.id)">Otkazi</button>
            </td>
            <td v-if="reservation.canceled == true">
              OTKAZANA REZERVACIJA
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-if="reservations.length === 0">
      <p style="font-size: 20px; margin-top:60px; margin-left:80px">Nemate nijednu zakazanu rezervaciju.</p>
    </div>


<!-- Modal za otkazivanje rezervacije -->
  <div class="modal fade" id="odjava" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
              Otkazivanje rezervacije
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="Cancel">
              <div class="form-group">
                <label for="name">Da li ste sigurni da zelite da otkazete ovu rezervaciju?</label>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" @click="Cancel()" style="width:80px; margin-bottom:20px">
                      Potvrdi
                    </button>
                  </td>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px; margin-left:230px; margin-bottom:20px">
                      Otkazi
                    </button>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal za upozorenje o otkazivanje
    <div class="modal fade" id="obavestenje" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
                Obavestenje!
              </h5>
            </div>
            <div class="modal-body" style="padding: 15px 50px">
              <form role="form" @submit.prevent="Cancel">
                <div class="form-group">
                  <label for="name">Ne mozete da otkazete rezervaciju ako ima manje od tri dana do pocetka!</label>
                </div>
                <table>
                  <tr>
                    <td>
                      <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px">
                        Potvrdi
                      </button>
                    </td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div> -->

</template>

<script>
import NavBarClient from "../../../components/client/NavBarClient.vue";
import moment from 'moment';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  name: "ScheduledReservation",
  components: {
    NavBarClient,
  },

  data() {
    return {
      reservations: [],
    };
  },
  methods: {
     async getSelected(id){
      this.id = id;
    },
    Cancel(){
       const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.get( "http://localhost:8081/api/reservation/cancel/" + this.id ,
        { headers }).then(
          response => {
            console.log(response);
             return new Swal({
             title:"Obavestenje",
             type: "warning",
             text: response.data,
           });
          }
        );
        this.udpateReservations();
    },
    async udpateReservations(){
      this.reservations = await this.fetchScheduledReservations()
    },
    async fetchScheduledReservations() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/reservation/scheduledReservations",
        { headers }
      );
      const data = await res.json();
      return data;
    },
    format_date(value){
      if (value) {
        return moment(value).format('MM/DD/YYYY')
      }
    },
    sort(sortParam) {
      if (sortParam == "CenaRastuce")
        this.reservations.sort(function (a, b) {
          return a.price - b.price;
        });
      if (sortParam == "CenaOpadajuce")
        this.reservations.sort(function (a, b) {
          return b.price - a.price;
        });
      if (sortParam == "DatumRastuce")
        this.reservations.sort(function (a, b) {
          return a.term.dateStart - b.term.dateStart;
        });
      if (sortParam == "DatumOpadajuce")
        this.reservations.sort(function (a, b) {
          return b.term.dateStart - a.term.dateStart;
        });
      if (sortParam == "TrajanjeRastuce")
        this.reservations.sort(function (a, b) {
          return a.duration - b.duration;
        });
      if (sortParam == "TrajanjeOpadajuce")
        this.reservations.sort(function (a, b) {
          return b.duration - a.duration;
        });
    },
  },
  async created() {
    this.reservations = await this.fetchScheduledReservations();
  },
};
</script>

<style scoped>
.containerInfo {
  width: 97%;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 10px;
  opacity: 1;
  border-radius: 10px;
  align-content: center;
  position: relative;
  background-color: fff;
  box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
}
.container {
  margin-top: 3%;
}
.row {
  width: 660%;
  padding-left: 60px;
  height: 10%;
  margin-top: 100px;
  margin-left: 5000px;
  margin-right: 500px;
  opacity: 1.2;
  border-radius: 10px;
  align-content: center;
  background-color: rgba(255, 255, 255, 0.288);
  box-shadow: 0 5px 4px rgb(0 0 0 / 30%), 0 1px 1px rgb(0 0 0 / 22%);
}
</style>
