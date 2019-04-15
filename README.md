# checklist

> Projeto simples de testes para controle de atividades desenvolvidas.

## Build Setup 

### Checklist-API

Pré-Requisitos

* Maven 3+
* Java 8
* Porta 9000 liberada

### Checklist-Client

Pré-Requisitos

* Node
* NPM
* Porta 8080 liberada

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

```
## Como funciona

Rotas da API
``` bash
* /tarefas -- ver tarefas (GET), salvar (POST) e alterar (PUT)
* /tarefas/{:id} -- consultar tarefa por id
* /tarefas/{:id}/concluir -- concluir tarefa
* /tarefas/{:id}/reabrir -- reabrir tarefa

* /atividades -- ver atividades (GET), salvar (POST) e alterar (PUT)
* /atividades/{:id} -- consultar tarefa por id
* /atividades/periodicidade/{:periodo} -- ver atividades de acordo com a periodicidade
* /atividades/encerradas/{:periodo} -- ver atividades encerradas de acordo com a periodicidade
```

A classe *Agendador* possui métodos agendados que representam a checagem de atividades para serem concluídas de maneira automatizada, configurando-se a frequência a qual cada método será executado, bem como pode ser configurado para enviar e-mails contendo a quantidade de tarefas concluídas e/ou atividades que acabaram de ser concluídas.
