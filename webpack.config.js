
module.exports = {
  mode: 'production',
  entry: './src/js/index.js',
  output: {
    filename: 'index_bundle.js'
  },
  node: {
     fs: "empty",
     child_process: "empty",

  },

  module:{
          rules:[
              {
                  test:/\.css$/,
                  use:['style-loader','css-loader']
              }
         ]
      },

}