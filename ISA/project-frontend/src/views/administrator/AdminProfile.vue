<template>
   <div v-if="this.role === 'ROLE_ADMIN'">
   <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
   </div>
     <div v-if="this.role === 'ROLE_PREDEF_ADMIN'">
       <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarPredefAdministrator />
       </div>
  
</div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
export default {
  name: "AdminProfile",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },
};
</script>
