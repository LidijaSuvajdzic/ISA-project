<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
    <ChangePassword />
  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import ChangePassword from "../../components/fishingInstructor/ChangePassword.vue";

export default {
  name: "ChangePasswordFishingInstructor",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut,
    ChangePassword,
  },
};
</script>
