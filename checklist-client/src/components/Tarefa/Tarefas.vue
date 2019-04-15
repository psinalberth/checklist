<template>
  <div>
    <h1 is="sui-header">Tarefas</h1>
    <sui-button primary floated="right" icon="plus" @click.stop="novaTarefa">Nova Tarefa</sui-button>
    <br>
    <sui-list divided relaxed big>
      <sui-list-item v-for="tarefa in tarefas" :key="tarefa.id" @click.stop="editar(tarefa)">
        <sui-list-icon name="edit" size="big" vertical-align="middle" />
        <sui-list-content>
          <a is="sui-list-header">{{tarefa.descricao}}</a>
          <a is="sui-list-description">Criada em {{tarefa.dataCriacao}}</a>
          <a is="sui-list-description" v-text="tarefa.concluido ? 'Concluído' : 'Sendo Executada'"></a>
        </sui-list-content>
      </sui-list-item>
    </sui-list>
  </div>
</template>
<script>
  import service from '@/services/tarefa-service'
  export default {
    data () {
      return {
        tarefas: []
      }
    },
    mounted () {
      service.findAll()
        .then(response => response.data)
        .then(tarefas => { this.tarefas = tarefas })
    },
    methods: {
      novaTarefa () {
        this.$router.push({name: 'editar-tarefa', params: { id: 0 }})
      },
      editar (tarefa) {
        this.$router.push({name: 'editar-tarefa', params: { id: tarefa.id }})
      }
    }
  }
</script>

