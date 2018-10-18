(function () {
    lobbyService.connect().then(function (response) {
        lobbyService.requestAllPets();
        lobbyService.requestAllRooms();
        lobbyDom.init();
        lobbyService.fetchAllPets().then(null, null, pets => {
            lobbyDom.showPets(pets)
        });
        lobbyService.fetchAllRooms().then(null, null, rooms => {
            lobbyDom.showRooms(rooms)
        });
    })
})();