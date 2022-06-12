<template>
  <div>
  <div v-if="this.userRole === 'ROLE_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
    <div v-if="this.userRole === 'ROLE_PREDEF_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarPredefAdministrator />
  </div>
  </div>
  <div>
      <table class="styled-table" >
    <thead>
        <tr>
            <th>Definisi procenat novca koji ce sistem da uzima od svake rezervacije:</th>
        </tr>
    </thead>
    <tbody>
          <tr>
                  <td><input v-model="percent"/></td>
                  <td><button class="btn btn-success btn-block" @click="define(percent)">Sacuvaj</button></td> 
                  </tr>
    </tbody>
</table>
 </div>
</template>

<script>
import axios from 'axios'
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";
export default {
  name: "Percentage",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    NavBarPredefAdministrator,
  },
  data() {
    return {
      userRole: "",
      percent: 0,  
    }
  },
  methods: {
      define(percent) {
        this.percent = percent;
        console.log(this.percent);
      axios
        .post("http://localhost:8081/api/percent/setPercentage/" + this.percent)
        .then((response) => {
          console.log(response);
        });
      this.$router.push({ name: "Percentage" });
      this.$router.go(0);
      }

  },
    async created() {
      this.userRole = localStorage.getItem("role");

  },
};
</script>
