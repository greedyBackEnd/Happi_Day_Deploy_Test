const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  outputDir: '../src/main/resources/static',
  devServer:{
    proxy: 'http://localhost:8080'
  }
})
