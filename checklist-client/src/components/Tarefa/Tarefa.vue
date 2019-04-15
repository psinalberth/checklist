<template>
  <sui-form>
    <h1 is="sui-header">Editar Tarefa</h1>
    <sui-form-field>
      <label>Descrição</label>
      <input type="text" placeholder="Descrição" v-model="tarefa.descricao">
    </sui-form-field>
    <sui-form-field>
      <label>Data de Criação</label>
      <input type="text" placeholder="Descrição" v-model="tarefa.dataCriacao" disabled>
    </sui-form-field>
    <br>
    <div>
      <sui-button type="button" compact primary @click.stop="salvar">Salvar</sui-button>
      <sui-button type="button" compact @click.stop="concluir(tarefa)">Concluir</sui-button>
      <sui-button type="button" compact @click.stop="reabrir(tarefa)">Reabrir</sui-button>
      <sui-button type="button" compact @click.stop="voltar">Voltar</sui-button>
    </div>
  </sui-form>
</template>
<script>
  import service from '@/services/tarefa-service'
  export default {
    data () {
      return {
        tarefa: {
          descricao: '',
          dataCriacao: ''
        }
      }
    },
    mounted () {
      if (this.$route.params.id) {
        service.findById(this.$route.params.id)
          .then(response => response.data)
          .then(tarefa => { this.tarefa = tarefa })
      }
    },
    methods: {
      salvar () {
        service.save(this.tarefa)
        this.voltar()
      },
      concluir (tarefa) {
        service.concluir(tarefa)
        this.$router.push({ name: 'listar-tarefas' })
      },
      reabrir (tarefa) {
        service.reabrir(tarefa)
        this.$router.push({ name: 'listar-tarefas' })
      },
      voltar () {
        this.$router.push({ name: 'listar-tarefas' })
      }
    }
  }
</script>

