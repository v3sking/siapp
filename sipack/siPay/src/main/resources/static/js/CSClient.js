function CSClient(url) {
        this.id = this._getId();
        this._init(url);
        this._origin = this._getOrigin(url);
        this._callbacks = {
            _get: {},
            _set: {},
            _del: {}
        }
        this._bindEvent();
    }
    
    CSClient.prototype._getId = function () {
        id = 0;
        return function () {
            return ++id;
        }
    }();
    
    CSClient.prototype._init = function (url) {
        var frame = document.createElement('iframe');
        frame.style.display = 'none';
        frame.src = url;
        document.body.appendChild(frame);
        this._hub = frame.contentWindow;
    }
    
    CSClient.prototype._getOrigin = function(url) {
        var uri, origin;
        uri = document.createElement('a');
        uri.href = url;
        origin = uri.protocol + '//' + uri.host;
        return origin;
    };
    
    CSClient.prototype._parseMessage = function (method, key, value) {
        return JSON.stringify({
            method: method,
            key: key,
            value: value
        });
    }
    
    
    CSClient.prototype._bindEvent = function () {
        var _this = this;
        window.addEventListener('message', function (event) {
            var data = JSON.parse(event.data);
            var error = data.error;
            var result = data.result  && JSON.parse(data.result) || null;
            try {
                _this._callbacks['_' + data.method][data.key](error, result);
            }
            catch (e){
                console.log(e);
            }
        }, false);
    }
    
    CSClient.prototype.get = function (key, callback) {
        this._hub.postMessage(this._parseMessage('get', key), this._origin);
        this._callbacks._get[key] = callback;
    }
    
    CSClient.prototype.set = function (key, value, callback) {
        this._hub.postMessage(this._parseMessage('set', key, value), this._origin);
        this._callbacks._set[key] = callback;
    }
    
    CSClient.prototype.del = function (key, callback) {
        this._hub.postMessage(this._parseMessage('del', key), this._origin);
        this._callbacks._del[key] = callback;
    }