class WebSocket {
    /**
     * Create new client
     * @param url server endpoint prefix
     */
    constructor(url) {
        this.client = Stomp.over(new SockJS(url));
    }

    connect() {
        return new Promise((resolve, reject) => {
            if (!this.client) {
            reject("Client not created.");
        } else {
            this.client.connect({}, function (frame) {
                resolve(frame);
            }, function (error) {
                reject("Connection error." + error);
            });
        }
    });
    }

    disconnect() {
        this.client.disconnect();
    }

    subscribeSingle(destination) {
        return new Promise((resolve, reject) => {
            if (!this.client) {
            reject("Client not created.");
        } else {
            this.client.subscribe(destination, function (message) {
                resolve(JSON.parse(message.body));
            });
        }
    });
    }

    subscribe(destination) {
        let deferred = $.Deferred();
        if (!this.client) {
            deferred.reject("STOMP client not created");
        } else {
            this.client.subscribe(destination, function (message) {
                deferred.notify(JSON.parse(message.body));
            });
        }
        return deferred.promise();
    }

    send(destination, headers, object) {
        this.client.send(destination, headers, object);
    }
}
