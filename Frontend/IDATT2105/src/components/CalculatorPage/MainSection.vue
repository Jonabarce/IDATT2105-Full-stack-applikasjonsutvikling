<template>
    <div class="Main">
        <div class="log">
              <nav>
                <ul>
                  <li v-for="log in logs" :key="log" @click="handleClick(log)">{{log}}</li>
                </ul>
              </nav>
            </div>
            <div class = "calculator">
            <div class = "input">
            <p id="currentNumber">{{ current || 0 }}</p>
            </div>
            <br>
            <br>
            <div class = "numbers">
            <button @click="clear('')" id="buttonClear">C</button>
            <button @click="sign" id="buttonPlusMin">+/-</button>
            <button @click="percent" id="buttonProsent">%</button>
            <button @click="divide" id="buttonDiv">÷</button><br>
            <button @click="append(7)" id="button7">7</button>
            <button @click="append(8)" id="button8">8</button>
            <button @click="append(9)" id="button9">9</button>
            <button @click="multiply" id="buttonMul">X</button><br>
            <button @click="append(4)" id="button4">4</button>
            <button @click="append(5)" id="button5">5</button>
            <button @click="append(6)" id="button6">6</button>
            <button @click="minus" id="buttonSub">-</button><br>
            <button @click="append(1)" id="button1">1</button>
            <button @click="append(2)" id="button2">2</button>
            <button @click="append(3)" id="button3">3</button>
            <button @click="add" id="buttonPlus">+</button><br>
            <button @click="append(0)" id="button0">0</button>
            <button @click="appendDot" id="buttonDot">.</button>
            <button @click="equal" id="buttonSolve">=</button>
            </div>
            </div>
        </div>
  
    
    
    
    
    
    </template>
    
    <script >
import axios, { Axios } from 'axios';
import { useTokenStore } from "../../stores/token";
import {getUserInfo} from "/httputils.js"



    export default {
      data() {
        return {
          previous: null,
          current: '',
          operator: null,
          whatOperator: '',
          operatorClicked: false,
          logs: [],
          testConnectionString: ''
        }
      },
      setup(){
        const tokenStore = useTokenStore();
        return { tokenStore };
      },
      methods: {
        changeRoute(string){
            this.$router.push({name:string})
        },
        handleClick(log) {
          // Split strengen på mellomrom for å få en liste over verdier
          const logValues = log.split(' ');
          
          if (logValues.length === 5) {
            // Hent ut tallene og operatoren fra loggen
            const firstNumber = parseFloat(logValues[0]);
            const operator = logValues[1];
            const secondNumber = parseFloat(logValues[2]);
            
            // Lagre tallene og operatoren som this.current, this.previous og this.whatOperator
            this.current = secondNumber;
            this.whatOperator = operator;
            this.previous = firstNumber;
          }

          this.calculate()

        },
        clear() {
          this.current = '';
        },
        sign() {
          this.current = this.current.charAt(0) === '-' ? 
            this.current.slice(1) : `-${this.current}`;
        },
        percent() {
          this.current = `${parseFloat(this.current) / 100}`;
        },
        append(number) {
          if (this.operatorClicked) {
            this.current = '';
            this.operatorClicked = false;
          }
          this.current = `${this.current}${number}`;
        },
        appendDot() {
          if (this.current.indexOf('.') === -1) {
            this.append('.');
          }
        },
        setPrevious() {
          this.previous = this.current;
          this.operatorClicked = true;
        },
        divide() {
          this.whatOperator = '/';
          this.setPrevious();
        },
        multiply() {
          this.whatOperator = '*';
          this.setPrevious();
        },
        minus() {
          this.whatOperator = '-';
          this.setPrevious();
        },
        add() {
          this.whatOperator = '+';
          this.setPrevious();
        },
        equal() {
          this.calculate()
          this.previous = null;
        },

        async calculate(){
          const postObject = {
            a: this.current,
            b: this.previous,
            operator: this.whatOperator,
            expression: "",
            result: "",
            user:{
              username: this.tokenStore.loggedInUser.username,
              password: "",
              equations: []
            }
          }

          const config = {
                headers: {
                    "Content-type": "application/json",
                    "Authorization" : "Bearer " + this.tokenStore.jwtToken
                },
            };

          console.log(postObject)
          this.current = await(await (axios.post("http://localhost:8080/CalculateAndSave", postObject,config))).data 
          this.logs.unshift(await(await (axios.post("http://localhost:8080/GetLog", postObject,config))).data )          

          }
        },
         async created(){
      
          const postObject = {
            a: this.current,
            b: this.previous,
            operator: this.whatOperator,
            expression: "",
            result: "",
            user:{
              username: this.tokenStore.loggedInUser.username,
              password: "",
              equations: []
            }
          }
          const config = {
                headers: {
                    "Content-type": "application/json",
                    "Authorization" : "Bearer " + this.tokenStore.jwtToken
                },
            };

          const equations = (await(await (axios.post("http://localhost:8080/GetCalculations", postObject,config))).data )
          this.logs = equations;
        },
        async mounted(){
          if(!this.tokenStore.jwtToken || this.tokenStore.jwtToken == null) {
              console.log("Unauthenticated context");
          } else {
              console.log("Authenticated context");
              
          }
        },setup() {
          const tokenStore = useTokenStore();

          function updateToken() {
            tokenStore.getTokenAndSaveInStore(tokenStore.loggedInUser.username, tokenStore.loggedInUser.password)
              .then(token => {
                console.log(token);
              });
          }

          updateToken();

          setInterval(() => {
            updateToken();
          }, 1000*60*5);

          return { tokenStore };
      }
    }

</script>
    
    
<style scoped>

  #currentNumber{
    text-align: right;
    font-size: 30px;
    color: white;
  }
  
.Main{
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
  "LeftSide   RightSide";
  place-items: center;
}

.log{
  grid-area: RightSide;
  place-items: left;
  padding: 10px;
  width: 350px;
  height: 450px;
  position: relative;
  background: white;
  border-radius: 7px;
  border: 5px solid grey;
}

.log ul{
  flex-direction: column;
  justify-content:space-between;
  text-align: left;
  font-size: larger;
  color: black;
  list-style: none;
  font-size: 20px;
}


.calculator{
  grid-area: LeftSide;
  margin: auto;
  padding: 10px;
  width: 350px;
  height: 450px;
  border: 5px solid grey;
  border-radius: 10px;
  position: relative;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
  "Top  Top"
  "Bottom Bottom";
  place-items: center;
  grid-row-gap: 5px;
  background-color: black;
}


.numbers{
grid-area: Bottom;
border: solid 1px white;
border-radius: 5px;
width: 340px;
}



.input{
border: solid 1px white;
width: 340px;
grid-area: Top;
background-color: #696969;
border-radius: 5px;
}


input{
      margin: auto;
      padding: 10px;
      width: 320px;
      height: 60px;
      border-radius: 6px;
  }
button{
    font-size: 20px;
    width: 85px;
    height: 70px;
    align-items: center;
    padding: 6px 14px;
    font-family: -apple-system, BlinkMacSystemFont, 'Roboto', sans-serif;
    border-radius: 6px;
    box-shadow: 0px 0.5px 1px rgba(0, 0, 0, 0.1), inset 0px 0.5px 0.5px rgba(255, 255, 255, 0.5), 0px 0px 0px 0.5px rgba(0, 0, 0, 0.12);
    color: whitesmoke;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    cursor:pointer;
  }
  #button0{
      width: 170px;
  }
  #button0,#button1,#button2,#button3,#button4,#button5,#button6,#button7,#button8,#button9{
      background: #6E6D70;
  }
  #buttonDiv,#buttonMul,#buttonSub,#buttonPlus,#buttonSolve{
      background-color:darkgoldenrod;
  }
  #buttonClear,#buttonPlusMin,#buttonProsent,#buttonDot{
      background-color: #A9A9A9;
  }
  
  
</style>