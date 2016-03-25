var common = require('./webpack.common.config.js');
// Merge plugin will help merge configs
var merge = require('webpack-merge');
// Webpack plugin provides some helpful things (for example HotModuleReplacementPlugin)
var webpack = require('webpack');

var dev = merge(common, {
    devtool: 'eval-source-map',
    // For DEV purposes we would love to run 'DEV' server that updates in a matter of second
    devServer: {
        historyApiFallback: true,
        hot: true,
        inline: true,
        progress: true,
        proxy: {
            '*': 'http://localhost:9090'
        },
    },
    // For DEV we would be happy to have Hot redeploy on dev server
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
});

module.exports = dev;

