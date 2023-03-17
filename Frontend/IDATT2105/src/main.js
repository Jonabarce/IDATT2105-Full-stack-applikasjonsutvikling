import { createApp } from 'vue'
import { createPinia } from "pinia";
import piniaPluginPersistedState from "pinia-plugin-persistedstate"
import App from './App.vue'
import router from './router/routers';
import store from './stores'

const pinia = createPinia();
pinia.use(piniaPluginPersistedState)


createApp(App).use(store).use(pinia).use(router).mount('#app')
