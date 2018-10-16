let battleService = {
    client: new WebSocket("/room/info"),
    connect() {
        return this.client.connect();
    },
    fetchLogs() {
        return this.client.subscribe("/battle/log");
    },
    sendAction(action) {
        return this.client.send("/app/action", {}, action);
    }
};