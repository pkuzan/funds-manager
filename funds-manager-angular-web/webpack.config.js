'use strict';

const path = require('path');
const webpack = require('webpack');
const merge = require('webpack-merge');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin');

const commonConfig = (nodeEnv) => {
  return {

    resolve: {
      modules: [path.resolve('./src'), 'node_modules'],
      extensions: ['.js', '.csv', '.json', '.scss', '.css', '.html']
    },

    module: {
      rules: [
        {
          test: /\.js$/,
          exclude: /node_modules/,
          enforce: 'pre',
          use: [{loader: 'eslint-loader', options: {configFile: '.eslintrc'}}]
        },
        {
          test: /\.html$/,
          use: [{loader: 'htmlhint-loader', options: {configFile: '.htmlhintrc'}}],
          exclude: /node_modules/,
          enforce: 'pre'
        },
        {
          use: 'babel-loader',
          test: /\.js$/,
          exclude: /node_modules/
        },
        {
          test: /\.(png|jpg|jpeg|svg|gif|woff|woff2|ttf|eot)(\?v=\d+\.\d+\.\d+)?$/,
          use: 'file-loader'
        },
        {
          test: /\.html$/,
          use: 'html-loader'
        }
      ]
    },
    plugins: [
      new webpack.EnvironmentPlugin({
        NODE_ENV: nodeEnv
      }),
      new CopyWebpackPlugin([{
        from: __dirname + '/src/images',
        to: 'images'
      }]),
      new HtmlWebpackPlugin({
        template: 'src/index.html',
        chunksSortMode: 'dependency'
      })
    ],
    optimization: {
      minimizer: [
        new UglifyJsPlugin({
          cache: true,
          parallel: true,
          sourceMap: true // set to true if you want JS source maps
        }),
        new OptimizeCSSAssetsPlugin({}),
      ],
    },
    performance: {
      hints: false
    }
  };
};

const devConfig = {
  entry: {
    main: ['whatwg-fetch', 'core-js/es6', 'react-hot-loader/patch', 'main.js']
  },

  devtool: 'inline-source-map',

  output: {
    path: path.join(__dirname, '/build'),
    filename: '[name].bundle.js',
    publicPath: '/'
  },

  module: {
    rules: [
      {
        test: /\.scss$/,
        use: ['style-loader', 'css-loader', {loader: 'sass-loader', options: {sourceMap: true}}]
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      }
    ]
  },

  devServer: {
    contentBase: 'src',
    compress: true,
    hot: true,
    port: 9000,
    host: '0.0.0.0',
    disableHostCheck: true,
    historyApiFallback: {
      disableDotRule: true
    },
    stats: 'minimal',
    overlay: true,
    proxy: {
      '/api/**': {
        target: {
          port: 8080
        },
        secure: false
      },
      '/actuator/**': {
        target: {
          port: 8080
        },
        secure: false
      }
    }
  },

  plugins: [
    new webpack.HotModuleReplacementPlugin()
  ]
};

const prodConfig = {
  entry: {
    main: ['whatwg-fetch', 'core-js/es6', 'main.js']
  },

  devtool: 'source-map',

  output: {
    path: path.join(__dirname, '/build'),
    filename: '[name].[hash].bundle.js',
    publicPath: '/'
  },

  module: {
    rules: [
      {
        test: /\.scss$/,
        use: [
          MiniCssExtractPlugin.loader,
          'css-loader',
          {loader: 'sass-loader', options: {sourceMap: true}}
        ]
      },
      {
        test: /\.css$/,
        use: [
          MiniCssExtractPlugin.loader,
          'css-loader'
        ]
      }
    ]
  },

  plugins: [
    new MiniCssExtractPlugin({
      filename: 'styles.[contenthash].css',
      chunkFilename: '[id].css'
    })
  ]
};

module.exports = (env, argv) => {
  console.info('NODE_ENV:', argv.mode);
  return argv.mode === 'production' ? merge(commonConfig(argv.mode), prodConfig) : merge(commonConfig(argv.mode), devConfig);
};