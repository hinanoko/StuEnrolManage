import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import StudentPage from '../views/Student.vue'
import AdminPage from '../views/Admin.vue'
import LoginPage from '../views/Login.vue'
import PersonalPage from '../views/Personal.vue'
import EnrolPage from '../views/Enrol.vue'
import DropPage from '../views/Drop.vue'
import ShowPage from '../views/Show.vue'
import ChangePage from '../views/Change.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  { path: '/Student', component: StudentPage },
  { path: '/Admin', component: AdminPage },
  { path: '/Login', component: LoginPage },
  { path: '/Personal/:studentId/Enrol', name:'Enrol', component: EnrolPage },
  { path: '/Personal/:studentId/Drop', name:'Drop', component: DropPage },
  { path: '/Personal/:studentId/Show', name:'Show', component: ShowPage },
  { path: '/Personal/:studentId/Change', name:'Change', component: ChangePage },
  { path: '/Personal/:studentId', name: 'PersonalPage', component: PersonalPage },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
