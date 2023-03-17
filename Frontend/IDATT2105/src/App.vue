<template>
  <router-view/>
</template>
  
<script>
import { useTokenStore } from "./stores/token";

export default {
  name: 'App',
}
</script>





  
  <style>
  body{
    margin:0;
  }
  html{
    margin: 0;
  }
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    width: 100%;
    height: 100%;
  }
  </style>
  