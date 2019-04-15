import Http from '@/services/shared/Http'

let uri = 'tarefas/'

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

  concluir (tarefa) {
    Http.post(uri + tarefa.id + '/concluir')
  },

  reabrir (tarefa) {
    Http.post(uri + tarefa.id + '/reabrir')
  }
}

export default service
