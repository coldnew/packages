/** @interface */
function Mime() {};
Mime.prototype.lookup = function(path, fallback) {};
Mime.prototype.define = function (map) {};
Mime.prototype.load = function(file) {};
Mime.prototype.extension = function(mimeType) {};

var mime;