import axios from "axios";
import { defineStore } from "pinia";
import {getJwtToken,getUserInfo} from "/httputils.js"

export const useTokenStore = defineStore("token", {
state: () => ({
    jwtToken: null,
    loggedInUser: null,
}),
persist: {
    storage: sessionStorage, // note that data in sessionStorage is cleared when the page session ends
},

actions: {
    async getTokenAndSaveInStore(username, password) {
        try{
            let response = await getJwtToken(username, password);
            console.log(response.data)
            let data = response.data;
            console.log('Dette er data fra response.data')
            console.log(data)
            if(data != null && data != '' && data != undefined){
                this.jwtToken = data;
                console.log('DETTE ER loggedInUser DATA')
                let userResponse = await getUserInfo(username, password,this.jwtToken);
                this.loggedInUser = userResponse.data
                console.log('DETTE ER loggedInUser DATA')
                console.log(this.loggedInUser)
            }
        } catch (err){
            console.log('FUCK DEN GIKK INN I ERROR')
            console.log(err)
        }
    }
},
});