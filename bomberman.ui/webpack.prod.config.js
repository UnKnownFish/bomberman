var common = require('./webpack.common.config.js');
// Merge plugin will help merge configs
var merge = require('webpack-merge');
// Webpack plugin provides some helpful things (for example HotModuleReplacementPlugin)
var webpack = require('webpack');

var production = merge(common, {
    plugins: [
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.optimize.DedupePlugin(),
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            }
        })
    ]
});

module.exports = production;

