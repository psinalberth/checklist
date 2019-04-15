<template>
  <sui-form>
    <h1 is="sui-header">Editar Atividade</h1>
    <h4 class="ui dividing header">Dados da Atividade</h4>
    <sui-form-field>
      <label>Descrição</label>
      <input type="text" placeholder="Nome" v-model="atividade.nome">
    </sui-form-field>
    <sui-form-field>
      <label>Periodicidade</label>
      <input type="text" placeholder="Nome" v-model="atividade.periodicidade">
    </sui-form-field>
    <sui-form-field>
      <label>Data de Criação</label>
      <input type="text" v-model="atividade.dataCriacao" disabled>
    </sui-form-field>
    <sui-form-field>
      <h4 class="ui dividing header">Tarefas</h4>
      <sui-button compact icon="plus" @click.prevent="novaTarefa(atividade)">Adicionar Tarefa</sui-button>
      <br>
      <template v-for="tarefa in atividade.tarefas">
       <div class="two fields" :key="tarefa.id">
         <sui-form-field class="twelve wide field">
           <label>Tarefa</label>
           <input type="text" v-model="tarefa.descricao">
         </sui-form-field>
         <sui-form-field class="four wide field">
           <label>Ações</label>
           <div>
             <sui-button @click.stop="removerTarefa(tarefa)">Remover</sui-button>
             <sui-button @click.stop="concluirTarefa(tarefa)">Concluir</sui-button>
           </div>
         </sui-form-field>
       </div>
      </template>
    </sui-form-field>
    <br>
    <div>
      <sui-button compact primary @click.stop="salvar">Salvar</sui-button>
      <sui-button compact @click.stop="concluir(atividade)">Concluir</sui-button>
      <sui-button compact @click.stop="voltar">Voltar</sui-button>
    </div>
  </sui-form>
</template>
<script>
  import service from '@/services/atividade-service'
  export default {
    data () {
      return {
        atividade: {
          nome: '',
          dataCriacao: '',
          tarefas: []
        },
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
          .then(atividade => { this.atividade = atividade })
      }
    },
    methods: {
      salvar () {
        service.save(this.atividade)
        this.$router.push({ name: 'listar-atividades' })
      },
      concluir (atividade) {
        service.concluir(atividade)
        this.$router.push({ name: 'listar-atividades' })
      },
      concluirTarefa (tarefa) {
        service.concluirTarefa(tarefa)
      },
      novaTarefa (atividade) {
        atividade.tarefas.push({...this.tarefa})
      },
      removerTarefa (tarefa) {
        for (var i = 0; i < this.atividade.tarefas.length; i++) {
          if (this.atividade.tarefas[i].id === tarefa.id) {
            this.atividade.tarefas.splice(i, 1)
          }
        }
      },
      voltar () {
        this.$router.push({ name: 'listar-atividades' })
      }
    }
  }
</script>

