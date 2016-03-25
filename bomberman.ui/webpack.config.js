var dev = require('./webpack.dev.config.js');
var prod = require('./webpack.prod.config.js');
var TARGET = process.env.npm_lifecycle_event;

if (TARGET === 'dev') {
    module.exports = dev;
}

if (TARGET === 'build') {
    module.exports = prod;
}