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
            <form>
              <div class="form-group">
                <label for="name">Ime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite ime"
                  v-model="newUser.firstname"
                />
                <label for="name">Prezime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite prezime"
                  v-model="newUser.lastname"
                />
                <label for="name">Email </label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite mejl"
                  v-model="newUser.email"
                />

                <label for="text">Adresa</label>
                <div class="form-group">
                  <table>
                    <tr>
                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Ulica"
                          v-model="newUser.address.street"
                        />
                      </td>

                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Broj"
                          v-model="newUser.address.number"
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Grad"
                          v-model="newUser.address.city"
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Drzava"
                          v-model="newUser.address.country"
                        />
                      </td>
                    </tr>
                  </table>
                </div>

              </div>
                              <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span>
                  Broj telefona</label
                >
                <input
                  type="telephone"
                  class="form-control"
                  id="psw"
                  placeholder="Unesite broj telefona"
                  v-model="newUser.telephone"
                />
               <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span>
                  Lozinka</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Unesite lozinku"
                  v-model="newUser.password"
                />
              
              </div>
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Ponovite
                  unos lozinke</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Potvrdite lozinku"
                />
              </div>
              <button
                type="submit"
              class="btn btn-success"
              @click="AddAdmin()" >
                <span></span> Registruj admina
              </button>
            </form>
          </div>

</template>
<script>
import axios from 'axios'
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";

export default {
  name: "AddAdministrator",

  components: {
    NavBarLogOut,
    HeaderStartPage,
    NavBarAdministrator,
    NavBarPredefAdministrator,
  },
  data() {
      return {
      ime : "",
      userRole:"",
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        country: "",  
        city: "", 
        street: "",
        number:"",
        telephone: "",
      newUser: {
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        telephone: "",
        role:"ROLE_ADMIN",
        address: { country: "",  city: "", street: "",number:"" },
      }
      }
  },
  methods: {
async AddAdmin() {

        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/administrator/addAdministrator",{ firstname : this.newUser.firstname, 
       lastname : this.newUser.lastname,
        email : this.newUser.email,
        password: this.newUser.password,
        country: this.newUser.address.country,
        city: this.newUser.address.city,
        street: this.newUser.address.street,
        number: this.newUser.address.number,
        telephone: this.newUser.telephone,
        role:this.role
       },{
        headers
      })
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "AddAdministrator" });
      }) 

        alert("Dodali ste admina!")
      this.$router.go(0);
  }
  },
    async created() {
    this.userRole = localStorage.getItem("role");
  },
}

</script>
