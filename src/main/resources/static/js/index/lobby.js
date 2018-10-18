(function () {
    lobbyService.connect().then(function (response) {
        lobbyDom.init();
        lobbyService.fetchAllPets().then(null, null, pets => {
            lobbyDom.showPets(pets)
        });
        lobbyService.fetchAllRooms().then(null, null, rooms => {
            lobbyDom.showRooms(rooms)
        });
        lobbyService.fetchNewRoom().then(null, null, room => {
            lobbyDom.addRoom(room);
        });
    })
})();