import Http from '@/services/shared/Http'

let uri = 'atividades/'

const service = {

  findAll () {
    return Http.get(uri)
  },

  findById (id) {
    return Http.get(uri + id)
  },

  save (tarefa) {
    Http.post(uri, tarefa)
  },

  remove (id) {
    Http.delete(uri + id)
  },

  concluir (atividade) {
    Http.post(uri + atividade.id + '/concluir')
  },

  concluirTarefa (tarefa) {
    Http.post(`/tarefas/${tarefa.id}/concluir`)
  }
}

export default service
