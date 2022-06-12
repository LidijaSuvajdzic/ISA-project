<template>
  <div>
    <nav class="navbar navbar-expand-sm navbar-light bg-light">
      <div class="collapse navbar-collapse">
        <a class="navbar-brand">
          <h4 style="color: #0b4025" class="font-italic">
            Vikendice & avanture
          </h4>
        </a>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item" style="margin-right: 15px">
            <button
              type="button"
              class="btn btn-outline-success"
              data-target="#prijava"
              data-toggle="modal"
            >
              Prijavi se
            </button>
          </li>

          <li class="nav-item">
            <button
              type="button"
              class="btn btn-success"
              data-target="#registracija"
              data-toggle="modal"
            >
              Registruj se
            </button>
          </li>
          
        </ul>
      </div>
    </nav>

    <!--Modal prijava-->
    <div class="modal fade" id="prijava" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Prijavljivanje
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="Login">
              <div class="form-group">
                <label for="name">E-mail:</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vas e-mail"
                  v-model="email"
                />
              </div>
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span>
                  Lozinka:</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Unesite lozinku"
                  v-model="password"
                />
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                @click="Login()"
              >
                <span></span> Uloguj se
              </button>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Odustani
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal registracija-->
    <div class="modal fade" id="registracija" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Registracija
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">

            <form role="form" @submit.prevent="register()">

              <div class="form-group">
                <label for="name">Ime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vase ime"
                  v-model="newUser.firstname"
                />
                <label for="name">Prezime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vase prezime"
                  v-model="newUser.lastname"
                />
                <label for="name">Email </label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vas mejl"
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
                <label for="name">Broj telefona</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vas telefon"
                  v-model="newUser.telephone"
                />

                <div class="form-group">
                  <label>Izaberite odgovarajuci tip registracije</label>
                  <select class="form-control" v-model="newUser.role">
                    <option value="ROLE_COTTAGE_OWNER">Vlasnik vikendice</option>
                    <option value="ROLE_BOAT_OWNER">Vlasnik broda</option>
                    <option value="ROLE_INSTRUCTOR">Instruktor</option>
                    <option value="ROLE_CLIENT">Korisnik</option>
                  </select>
                </div>
              </div>
                 <div class="form-group" v-if="newUser.role === 'ROLE_COTTAGE_OWNER' ||  newUser.role === 'ROLE_BOAT_OWNER' || newUser.role === 'ROLE_INSTRUCTOR'">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span>
                  Obrazlozenje registracije</label
                >
                <textarea
                  type="text"
                  class="form-control"
                 
                  placeholder="Unesite obrazlozenje za registraciju"
                  v-model="newUser.reasonForRegistration"
                />
                </div>
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
                  ><span class="glyphicon glyphicon-eye-open"
                  ></span> Ponovite
                  unos lozinke</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Potvrdite lozinku"
                  v-model="passwordAgain"
                />
              </div>
              <button
               type="submit"
                class="btn btn-success btn-block"
              
              >
                <span></span> Registruj se
              </button>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Odustani
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Swal from 'sweetalert2';
export default {
  data() {
    return {
      userAddress: { country: "",  city: "", street: "",number:0 },
      email: "",
      password: "",
      passwordAgain:"",
      newUser: {
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        address: { country: "",  city: "", street: "",number:0 },
        role: "",
        reasonForRegistration: "",
        telephone: "",
      },
      passwordRepeat:""
    };
  },
  computed: {
    userRole() {
      return this.$store.getters.getRole;
    },
  },
  methods: {
    async Login() {
      const headers ={
        "Content-type": "application/json",
      }; 
      axios.post("http://localhost:8081/api/auth/login",{ email: this.email, password: this.password }, {headers})
      .then (response => {
        if (response.data.enabled === true){
          console.log(response.data.role)
          localStorage.setItem("token", response.data.accessToken);
          localStorage.setItem("role", response.data.role);
          if (localStorage.getItem("role") == "ROLE_CLIENT") {
            this.$router.push({ name: "ClientAllCottages" });
            this.$router.go(0);
            console.log(localStorage.getItem("token"));
            console.log(localStorage.getItem("role"));
          } else if (localStorage.getItem("role") == "ROLE_COTTAGE_OWNER") {
            this.$router.push({ name: "MyCottages" });
             
            console.log(localStorage.getItem("token"));
            console.log(localStorage.getItem("role")); 
                this.$router.go(0);
          }
          else if (localStorage.getItem("role") == "ROLE_ADMIN") {
            console.log("HELOU" + (response.data.must_change_password));
              if (response.data.must_change_password === true) {
              this.$router.push({ name: "ChangePassword" });
              console.log(localStorage.getItem("token"));
              console.log(localStorage.getItem("role"));
              this.$router.go(0);
                
              }
              else {
              this.$router.push({ name: "AllRegistrationRequests" });
              console.log(localStorage.getItem("token"));
              console.log(localStorage.getItem("role"));
              this.$router.go(0); }
          } 
          else if (localStorage.getItem("role") == "ROLE_PREDEF_ADMIN") {
            this.$router.push({ name: "AllRegistrationRequests" });
            console.log(localStorage.getItem("token"));
            console.log(localStorage.getItem("role"));
            this.$router.go(0);
          }
           else if (localStorage.getItem("role") == "ROLE_BOAT_OWNER") {
            this.$router.push({ name: "MyBoats" });
            console.log(localStorage.getItem("token"));
            console.log(localStorage.getItem("role"));
            this.$router.go(0);
          }
          else if (localStorage.getItem("role") == "ROLE_INSTRUCTOR") {
            this.$router.push({ name: "FishingInstructorProfile" });
            this.$router.go(0);
          }
        } 
      })
      .catch( error => {
        console.log(error)
         return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Vas nalog jos uvek nije aktiviran ili ste pogresili prilikom unosa kredencijala!'
           });
      }) 
    },
    async register() {
      console.log(this.newUser.role);
      if ( !this.validFirstname()) {
        new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Ime mora da sadrzi bar jedan karakter!'
           });
      } else if (!this.validLastname()) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Prezime mora da sadrzi bar jedan karakter!',
           });
      }  else if (!this.validEmail()) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Email adresa nije u dobroj formi!',

           });     
           } 
           else if (!this.validPassword()) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Sifra nije u dobroj formi! Sifra mora da sadrzi bar jedno malo,jedno veliko slovo,specijalni karakter i jedan broj!',
           });     
           } else if (this.newUser.password != this.passwordAgain ) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Ne podudaraju se sifre!',
           });   
           } else if (!this.validPhoneNumber()) {
            new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Broj telefona ne sadrzi space-ove i slova!',
           });  
           }
      else{
      axios.post("http://localhost:8081/api/auth/signup",{ firstname : this.newUser.firstname, 
        lastname : this.newUser.lastname,
        role: this.newUser.role,
        reasonForRegistration: this.newUser.reasonForRegistration,
        email : this.newUser.email,
        password: this.newUser.password,
        country: this.newUser.address.country,
        city: this.newUser.address.city,
        street: this.newUser.address.street,
        number: this.newUser.address.number,
        address: this.newUser.address,
        telephone: this.newUser.telephone
       })
       .then(response => { 
       console.log(response.status);
       this.$router.push({ name: "BoatsStartPage" });
      }) 
            .catch( error => {
        console.log(error)
         return new Swal({
             title:"Nije uspesno",
             type: "warning",
             text:'Vec postoji user sa istim email-om!'
           });
      }) 
             this.$router.go(0);
    }
    },
        validFirstname() {
      if (this.newUser.firstname.length < 1) {
        return false;
      }
      return true;
    },
        validPassword() {
      if (this.newUser.password.length < 8) {
        return false;
      } else if (this.newUser.password.length > 30) {
        return false;
      } else if (!this.newUser.password.match(/[a-z]/g)) {
        return false;
      } else if (!this.newUser.password.match(/[A-Z]/g)) {
        return false;
      } else if (!this.newUser.password.match(/[0-9]/g)) {
        return false;
      } else if (!this.newUser.password.match(/[!@#$%^&*.,:'+-/\\"]/g)) {
        return false;
      }
      return true;
    },
            validLastname() {
      if (this.newUser.lastname.length < 1) {
        return false;
      }
      return true;
    },
    validPhoneNumber() {
      if (this.newUser.telephone.match(/[a-zA-Z]/g)) {
        return false;
      } else if (this.newUser.telephone.match(/[ ]/g)) {
        return false;
      } 
      return true;
    },
    validEmail() {
      if (
        !/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(
          this.newUser.email
        )
      ) {
        return false;
      } else if (this.newUser.email.length > 35) {
        return false;
      }
      return true;
    },
  },
};
</script>