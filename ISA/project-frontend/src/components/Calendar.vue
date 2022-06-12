<template>
  <div class="card bg-dark text-white" style="margin: 5%">
    <div class="content">
      <FullCalendar :options="calendarOptions" class="calendar" />

      <div class="info">
        <h2>Reservation</h2>
        <div style="margin: 2rem 0">
          <div class="element">
            <tr>Ime :</tr><input  v-model="this.user.firstname"/>
            <tr>Prezime :</tr><input  v-model="user.lastname"/>
            <tr>Email :</tr><input  v-model="user.email"/>
          </div>
        </div>
      </div>


    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;

export default {
  components: {
    FullCalendar, // make the <FullCalendar> tag available
  },
  setup() {
    const startDate = ref();
    const endDate = ref();

    return {
      startDate,
      endDate,
    };
  },
  data() {
    return {
      id:0,
        ret:"",
        user: {
          firstname:"",
          lastname:"",
          email:""
        },
      disabledPickers: false,
      selectDisabled: false,
      currentEvent: "",
      selectData: [],
      entityType: "",
      calendarOptions: {
        plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin],
        initialDate: new Date(),
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay",
        },
        selectable: true,
        eventClick: this.event,
        selectMirror: true,
        dayMaxEvents: true,
        initialView: "dayGridMonth",
        events: [
        ],
      },
    };
  },
  methods: {
    event: function (info) {
      console.log(info.event._def.extendedProps.clientID);
      this.id = info.event._def.extendedProps.clientID;
      this.getUser(this.id);
      console.log(this.user.firstname)
    },
    async getUser(id) {
      this.id = id;
      const res = await fetch("http://localhost:8081/api/reservation/getUserFromReservation/"+this.id);
      this.user  = await res.json();
      return this.user;
    }


  },
  async created() {
           const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.get( "http://localhost:8081/api/reservation/myRegularReservation",
        { headers }).then(
          response => {
                      for (let newData of response.data) {
                      newData.title = "Availability";
                      //newData.defId = newData.id;
                      //newData.serviceId =newData.id;
                      newData.start = new Date(newData.term.dateStart);
                      newData.end = new Date(newData.dateEnd);
                      this.calendarOptions.events.push(newData);
                    }
          }
        )
  }
  
  
  
  }
</script>













<style scoped>
.element {
  margin-bottom: 2rem;
}
.save {
  background-color: white;
}
.save:hover {
  background-color: #2c3e50 !important;
  color: white !important;
  border: 1px solid white !important;
}

.delete-btn {
  background-color: #2f1a1a !important;
}

.delete-btn:hover {
  background-color: #502c2c !important;
  color: white !important;
  border: 1px solid white !important;
}
.content {
  display: flex;
  justify-content: space-between;
}
.calendar {
  width: 70%;
}

.info {
  background: #212529;
  width: 28%;
  border-radius: 5px;
  border: 1px solid white;
  padding: 1rem;
}

select {
  background: transparent;
  color: white;
  border: 0;
  border-bottom: 1px solid white;
  width: 100%;
}

option {
  background: #27332b;
}

.datePricker {
  margin-top: 2%;
  border: 1px solid white;
  border-radius: 5px;
  width: 100%;
  box-shadow: none !important;
}
</style>
