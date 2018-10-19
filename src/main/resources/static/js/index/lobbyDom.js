let lobbyDom = {
    petsDiv: null,
    roomsDiv: null,
    init() {
        lobbyService.requestAllPets();
        lobbyService.requestAllRooms();
        $("#petsDiv").hide();
        $("#roomsDiv").hide();
        this.petsDiv = $("#petsDiv").get(0);
        this.roomsDiv = $("#roomsDiv").get(0);
        this.addEventHandlerToButtons();
    },
    showPets(pets) {
        pets.forEach(pet => {
            let div = document.createElement("div");
            div.className = "pet";
            div.appendChild(document.createTextNode( lobbyDom.petStatsToString(pet) ));
            lobbyDom.petsDiv.appendChild(div);
        });
    },
    showRooms(rooms) {
        rooms.forEach(room => {
            let div = document.createElement("div");
            let a = document.createElement("a");
            div.className = "room";
            a.href = "/room/" + room.id;
            a.appendChild(document.createTextNode( lobbyDom.roomToString(room) ));
            div.appendChild(a);
            lobbyDom.roomsDiv.appendChild(div);
        });
    },
    addEventHandlerToButtons() {
        $("#showPets").click(function() {
            $("#roomsDiv").hide();
            $("#petsDiv").show();
        });
        $("#showRooms").click(function() {
            $("#petsDiv").hide();
            $("#roomsDiv").show();
        });
        $("#addRoom").click(function () {
           lobbyService.addNewRoom();
           lobbyService.fetchNewRoom().then(null, null, room => {
               window.location.href = window.location.href + "room/" + room.id;
           });
        });
    },
    petStatsToString(pet) {
        return "Name: " + pet.name +
            "\nExp: " + pet.exp +
            "\nAttack: " + pet.attack +
            "\nDefence: " + pet.defence +
            "\nSpeed: " + pet.speed +
            "\nType: " + pet.type;
    },
    roomToString(room) {
        return "id: " + room.id;
    },
    addRoom(room) {
        let div = document.createElement("div");
        let a = document.createElement("a");
        div.className = "room";
        a.href = "/room/" + room.id;
        a.appendChild(document.createTextNode( lobbyDom.roomToString(room) ));
        div.appendChild(a);
        lobbyDom.roomsDiv.appendChild(div);
    }
};