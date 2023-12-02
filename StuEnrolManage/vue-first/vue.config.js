
module.exports = ({
  devServer: {
    port: 8085,
    proxy: {                 //Set up proxy, must be filled in
      '/api': {              //Set interceptor format: slash+interceptor name, name can be customized
          target: 'http://localhost:9095',     //The target address of the proxy
          changeOrigin: true,              //Is the same origin set? Enter Yes
          pathRewrite: {                   //rewrite the path
              '^/api': ''                     //Choose to ignore the content inside the interceptor
          }
      }
  }
  },
})
