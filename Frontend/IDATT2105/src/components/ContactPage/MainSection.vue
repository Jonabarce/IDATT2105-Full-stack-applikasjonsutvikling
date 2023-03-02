<template>
  <div class="Main">
    <div class="backdrop">
      <div class="contactform">
        <form @submit.prevent="submitForm">
          <div class="inputs">
            <div class="form-group">
              <div class="name-group">
                <input type="text" id="firstname" v-model="inputs.name" placeholder="Name" @input="updateName" required/>
              </div>
            </div>
            <div class="form-group">
              <input type="email" id="email" v-model="inputs.email" placeholder="Email" @input="updateEmail" required/>
            </div>
            <div class="form-group">
              <textarea id="message" v-model="inputs.message" placeholder="Type your message here" required></textarea>
            </div>
          </div>
          <div class="buttons">
            <p id="theSubmitText">{{ this.inputs.submitText }}</p>
            <button id="submit" type="submit" :disabled="!isFormValid" :class="{ 'disabled': !isFormValid }">Submit</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      inputs: {
        name: '',
        email: '',
        message: '',
        submitText: ''
      }
    };
  },
  computed: {
    isFormValid() {
      return !!this.inputs.name && /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.inputs.email) && !!this.inputs.message;
    }
  },
  methods: {
    async submitForm() {
  const self = this;
  this.$store.commit("SET_NAME", this.inputs.name);
  this.$store.commit("SET_EMAIL", this.inputs.email);
  axios.post('http://localhost:3000/form', this.inputs)
  .then(() => {
    self.inputs.submitText = "Success!";
    self.inputs.name = self.$store.state.name;
    self.inputs.email = self.$store.state.email;
    self.inputs.message = "";
  })
  .catch((error) => {
    self.inputs.submitText = "Error!"
  });
    },
    updateName(event) {
      this.$store.commit("SET_NAME", event.target.value);
    },
    updateEmail(event) {
      this.$store.commit("SET_EMAIL", event.target.value);
    }
  },
  created() {
    this.inputs.name = this.$store.state.name;
    this.inputs.email = this.$store.state.email;
  }
};
</script>

<style scoped>
.Main {
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: #87CEFA;
}

p {
  font-size: 30px;
  color: black;
}

input,
textarea {
  border-radius: 10px;
  background-color: #E6E6FA;
  color: black;
}

.inputs {
  grid-area: Top;
}

.buttons {
  grid-area: Bottom;
}

#submit {
  background-color: #4169E1;
  color: white;
  width: 200px;
  height: 50px;
  font-size: 20px;
  margin-bottom: 40px;
}

#submit.disabled {
  background-color: transparent;
  color: gray;
  cursor: not-allowed;
}
  
.backdrop {
  background-color: white;
  border: solid 3px black;
  width: 650px;
}


.form-group {
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-bottom: 20px;
}

.form-group input {
  margin-top: 10px;
  width: 600px;
  height: 35px;
  font-size: 25px;
  border: solid 5px 	#9370DB;
}

.form-group textarea {
  height: 200px;
  width: 600px;
  resize: none;
  font-size: 25px;
  border: solid 5px #7B68EE;
}

.name-group {
display: flex;
flex-direction: column;
justify-content: space-between;
}

.name-group input {
width: 600px;
margin-top: 10px;
}

.contactform{
  display: grid;
}


</style>
  
  
