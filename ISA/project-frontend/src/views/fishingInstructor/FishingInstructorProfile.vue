<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";

export default {
  name: "FishingInstructorProfile",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut,
  },
};
</script>
