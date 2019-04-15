<template>
  <div id="app" is="sui-container">
    <navbar />
    <main style="padding-top: 3.5rem">
      <h1 is="sui-header">Checklist App</h1>
      <p>Aplicação simples para controle de tarefas desenvolvidas.</p>
      <div class="ui main container">
        <router-view></router-view>
      </div>
    </main>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>

<style>
body {
  margin: 0;
}

</style>
