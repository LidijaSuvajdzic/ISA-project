<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
        <h1> Kalendar zauzetosti</h1>
    <VCalendar></VCalendar>
</div>
  
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import VCalendar from "@/components/Calendar.vue";
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  name:"Calendar",
  components: { VCalendar,HeaderStartPage,NavBarFishingInstructor,NavBarLogOut },
  data: function () {
    return {
    };
  },
};
</script>

<style scoped>
.title {
  color: white;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin: 5%;
}
h1 {
  color: white !important;
}
</style>
