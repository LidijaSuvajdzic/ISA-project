<template>
  <div  v-if="this.role === 'ROLE_ISTRUCTOR'" >
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarClient />
    <AdventureDetails />
  </div>

  <!--OBICNI KROISNIK-->
  <div v-if="this.role === ''">
    <HeaderLogAndRegister />
    <HeaderStartPage />
    <NavBarStartPage />
    <AdventureDetails />
  </div>

  <!--KLIJENT-->
  <div v-if="this.role === 'ROLE_CLIENT'">
    <NavBarClient />
    <AdventureDetails />
  </div>

</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarLogOut from "../../components/client/NavBarLogOut.vue";
import AdventureDetails from "../../components/adventure/AdventureDetails.vue";
import NavBarClient from "../../components/client/NavBarClient.vue";
import HeaderLogAndRegister from "../../components/startPage/HeaderLogAndRegister.vue";
import NavBarStartPage from "../../components/startPage/NavBarStartPage.vue";

export default {
  name: "MyService",
  components: {
    HeaderStartPage,
    NavBarLogOut,
    AdventureDetails,
    NavBarClient,
    HeaderLogAndRegister,
    NavBarStartPage
  },
   data() {
    return {
      role:""
    };
    },
    async created() {
    this.role = localStorage.getItem("role");
  },
};
</script>
