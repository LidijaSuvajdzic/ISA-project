<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
    <MyServices />
  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import MyServices from "../../components/fishingInstructor/MyServices.vue";

export default {
  name: "MyService",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut,
    MyServices,
  },
};
</script>
