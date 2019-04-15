<template>
  <div>
    <h1 is="sui-header">Atividades</h1>
    <sui-button primary floated="right" icon="plus" @click.stop="novaAtividade">Nova Atividade</sui-button>
    <br>
    <sui-list divided relaxed big>
      <sui-list-item v-for="atividade in atividades" :key="atividade.id" @click.stop="editar(atividade)">
        <sui-list-icon name="edit" size="big" vertical-align="middle" />
        <sui-list-content>
          <a is="sui-list-header">{{atividade.nome}}</a>
          <a is="sui-list-description">Criada em {{atividade.dataCriacao}}</a>
          <a is="sui-list-description" v-text="atividade.concluido ? 'Concluído' : 'Sendo Executada'"></a>
        </sui-list-content>
      </sui-list-item>
    </sui-list>
  </div>
</template>
<script>
  import service from '@/services/atividade-service'
  export default {
    data () {
      return {
        atividades: []
      }
    },
    mounted () {
      service.findAll()
        .then(response => response.data)
        .then(atividades => { this.atividades = atividades })
    },
    methods: {
      novaAtividade () {
        this.$router.push({name: 'editar-atividade', params: { id: 0 }})
      },
      editar (atividade) {
        this.$router.push({name: 'editar-atividade', params: { id: atividade.id }})
      }
    }
  }
</script>

