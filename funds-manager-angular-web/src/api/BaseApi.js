export default class BaseApi{
    _json(response){
        return response.json();
    }

    _get(url){
        return fetch(url);
    }

    _withPayload(url, method, payload){
        return fetch(url, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type' 'application/json'
            },
            body: JSON.stringify(payload)
        });
    }

    _post(url, payload){
        return this._withPayload(url, POST, payload);
    }

    _put(url, payload){
        return this._withPayload(url, PUT, payload);
    }

    _delete(url){
        return _withPayload(url, method: 'DELETE');
    }
}