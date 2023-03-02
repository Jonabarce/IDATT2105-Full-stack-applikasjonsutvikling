import { createApp } from 'vue'
import App from './App.vue'
import router from './router/routers';
import store from './stores'

createApp(App).use(store).use(router).mount('#app')
