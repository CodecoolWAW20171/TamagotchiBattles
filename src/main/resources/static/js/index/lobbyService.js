let lobbyService = {
    client: new WebSocket("/lobby/info"),
    connect() {
        return this.client.connect();
    },
    fetchAllPets() {
        return this.client.subscribe("/lobby/pets");
    },
    requestAllPets() {
        return this.client.send("/app/requestAllPets");
    },
};