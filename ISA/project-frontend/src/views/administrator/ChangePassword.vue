<template>
    <div>
     <NavBarLogOut />
    <HeaderStartPage />
      <table>
        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Nova
                  lozinka</label
                ></td>
                <td> <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Nova lozinka" 
                   v-model="this.newPassword"
                /></td></tr>
                 <tr><td><label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Ponovite
                  unos lozinke</label
                ></td>
                <td><input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Potvrdite lozinku"  v-model="this.repeatPassword"
                /></td></tr>
              <tr><td><button type="submit" class="btn btn-success btn-block" @click="changePassword()" style="width:100px;margin-top:20px">
                <span></span> Potvrdi
              </button></td>
          </tr>
          </table>

    </div>
</template>

<script>
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";

export default {
        name: "ChangePassword",
          components: {
    HeaderStartPage,
    NavBarLogOut,
  },  data() {
    return {
          newPassword: "",
      repeatPassword: "",
    }
  },
   methods: {
    async changePassword(){
      console.log(localStorage.getItem("token"))
      if (this.repeatPassword != this.newPassword){
        alert("Niste dobro uneli ponovljenu sifru!Probajte ponovo!");
      } else {
        const res = await fetch("http://localhost:8081/api/user/changePassword" , {
          method: "POST",
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
            'Content-Type': 'application/json;charset=UTF-8',
            Accept: 'application/json',
          },
          body: JSON.stringify({password: this.newPassword})
        });
        const data = await res.json();
        localStorage.setItem("token", data.accessToken);
        alert("Molimo ulogujte se ponovo sa novom sifrom!")
        this.$router.push({ name: "BoatsStartPage" });
        }
    },

  }

    }

</script>
