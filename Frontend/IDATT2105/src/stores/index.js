import { createStore } from 'vuex'

export default createStore({
  state () {
    return {
        name: "",
        email: ""
    }
  },
  mutations: {
    SET_NAME(state, name){
        state.name = name
    }, 
    SET_EMAIL(state, email){
        state.email = email
    }
  }
})