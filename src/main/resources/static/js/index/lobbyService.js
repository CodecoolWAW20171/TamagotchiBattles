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
    fetchAllRooms() {
        return this.client.subscribe("/lobby/battles")
    },
    requestAllRooms() {
        return this.client.send("/app/requestAllBattles");
    },
    addNewRoom() {
        return this.client.send("/app/addNewRoom");
    },
    fetchNewRoom() {
        return this.client.subscribe("/lobby/newBattles")
    },
};