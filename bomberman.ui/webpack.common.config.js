// 'path' will be used to resolve folders properly
var path = require('path');
// Webpack plugin provides some helpful things (for example HotModuleReplacementPlugin)
var webpack = require('webpack');
// Plugin that will help clean 'build' folder
var Clean = require('clean-webpack-plugin');
// Plugin that helps build 'index.html' and insert all js and css in there
var HtmlWebpackPlugin = require('html-webpack-plugin');

// Root folder. In our case its mps-react
var ROOT_PATH = path.resolve(__dirname);
// UI sources folder. In our case its mps-react/src/main/ui
var APP_PATH = path.resolve(ROOT_PATH, 'src/main/app');
// Folder where Webpack will build html,js and css
var BUILD_PATH = path.resolve(ROOT_PATH, 'build');

module.exports = {
    entry: path.join(APP_PATH, 'index.js'),
    output: {
        path: BUILD_PATH,
        filename: 'bundle-[hash:6].js'
    },
    devtool: 'source-map',
    module: {
        loaders: [
            {
                test: /\.html$/,
                loader: 'raw'
            },
            {
                test: /\.(png|jpg|jpeg|gif)$/,
                loader: 'file?name=img/[name].[ext]'
            },
            {
                test: /\.css$/,
                loaders: ['style', 'css']
            },
            {
                test: /\.scss$/,
                loaders: ['style', 'css', 'autoprefixer', 'sass']
            },
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                loaders: ['ng-annotate?add=true', 'babel']
            },
            {
                test: [/\.(svg|ttf|woff|woff2|eot)(\?.*$|$)/],
                loader: 'file?name=fonts/[name].[ext]'
            }
        ]
    },
    plugins: [
        new Clean(BUILD_PATH),
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: path.join(APP_PATH, 'index_template.html'),
            inject: true
        })
    ]
};