<template>
    <div class="Main">
      <p>Welcome to the Calculator</p>
      <p v-if="!user">
        To start calculation please login below
      </p>
      <p v-if="user"> HELLO {{ this.username }}</p>
      <div v-if="!user" class="login-section">
        <div class="username">
          <input placeholder="Username" type="text" v-model="username">
        </div>
        <div class="password">
          <input placeholder="Password" type="password" v-model="password">
        </div>
        <div class="submit">
          <button @click="submit()">Login</button>
        </div>
        <div class="register">
          <button @click="register()">REGISTER</button>
        </div>
        <p>{{ error || '' }}</p>
      </div>
      <div v-if="user">
        <button @click="changeRoute('CalculatorPage')">CALC</button>
        <button @click="logout()">Logout</button>
      </div>
    </div>
  </template>
  
  <script>
  import axios, { Axios } from 'axios';
  import { useTokenStore } from "../../stores/token";
  import {getUserInfo} from "/httputils.js"
  
  export default {
    data() {
      return{
        username: '',
        password: '',
        access: '',
        error: '',
        user: null
      }
    },
    setup() {
    const tokenStore = useTokenStore();
    return { tokenStore };
    },
    methods: {
      logout(){
        this.tokenStore.jwtToken = null;
        this.tokenStore.loggedInUser = null;
        this.user = null;
        this.username = null;
      },
      changeRoute(string){
        this.$router.push({name:string})
      },
    async submit() {
    await this.tokenStore.getTokenAndSaveInStore(this.username, this.password);
    console.log(this.tokenStore.jwtToken)
    console.log('UNDER HER FINNER DU KARO')
    console.log(this.tokenStore.loggedInUser)
    if(this.tokenStore.jwtToken || this.tokenStore.jwtToken){
      this.changeRoute('CalculatorPage')
    } else {
      this.error = "Login failed!"
    }
    },
      async register(){
        const user = {
          username: this.username,
          password: this.password
        }

        this.access = await(await (axios.post("http://localhost:8080/Createuser",user))).data 
  
        if(this.access === true){
          this.error = 'User was created'
        }else{
          this.error = 'Error trying to create a new user'
        }
      }
    },
    async mounted(){
      if(!this.tokenStore.jwtToken || this.tokenStore.jwtToken == null) {
          console.log("Unauthenticated context");
      } else {
          console.log("Authenticated context");
          console.log(this.tokenStore.loggedInUser.username)
          let response  = await getUserInfo(this.tokenStore.loggedInUser.username,this.tokenStore.loggedInUser.password,this.tokenStore.jwtToken);
          this.user = response.data;
          this.username = response.data.username;
      }
    }
  }
  </script>
  

    
    
    <style scoped>
    
    
    .Main{
        align-items: center;
        background-color: black;
    }

    p{
    font-size: 40px;
    color: whitesmoke;
}

    button{
        width: 120px;
        height: 40px;
        font-size: 20px;
    }
    
    
    
    
    </style>