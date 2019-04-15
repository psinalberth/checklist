import Vue from 'vue'
import Router from 'vue-router'
import SuiVue from 'semantic-ui-vue'
import Navbar from '@/components/Shared/Navbar'

import 'semantic-ui-css/semantic.min.css'

Vue.use(Router)
Vue.use(SuiVue)

Vue.component('navbar', Navbar)

function route (path, source, component, name) {
  return {
    path: path,
    component: () => import(`@/components/${source}/${component}`),
    name: name,
    props: true
  }
}

export default new Router({
  linkActiveClass: 'active',
  mode: 'history',
  routes: [
    route('/tarefas', 'Tarefa', 'Tarefas', 'listar-tarefas'),
    route('/tarefas/:id', 'Tarefa', 'Tarefa', 'editar-tarefa'),
    route('/atividades', 'ListaAtividade', 'ListasAtividade', 'listar-atividades'),
    route('/atividades/:id', 'ListaAtividade', 'ListaAtividade', 'editar-atividade')
  ]
})
