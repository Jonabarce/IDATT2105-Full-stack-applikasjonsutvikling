import { createRouter, createWebHistory} from 'vue-router'
import HomePageView from '../Views/HomePageView.vue'
import CalculatorPageView from '../Views/CalculatorPageView.vue'
import ContactViewVue from '../Views/ContactView.vue'


const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePageView
    },
    {
        path: '/CalculatorPage',
        name: 'CalculatorPage',
        component: CalculatorPageView
    },
    {
        path: '/ContactPage',
        name: 'ContactPage',
        component: ContactViewVue
    }
]


const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router