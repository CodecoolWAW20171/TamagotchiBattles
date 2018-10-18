let lobbyDom = {
    petsDiv: null,
    roomsDiv: null,
    init() {
        this.petsDiv = $("#petsDiv");
        this.roomsDiv = $("#roomsDiv");
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
            div .className = "pet";
            div.appendChild(document.createTextNode( lobbyDom.roomToString(room) ));
            lobbyDom.petsDiv.appendChild(div);
        });
    },
    addEventHandlerToButtons() {
        $("#showPets").click(function() {
            lobbyDom.roomsDiv.hide();
            lobbyDom.petsDiv.show();
        });
        $("#showRooms").click(function() {
            lobbyDom.petsDiv.hide();
            lobbyDom.roomsDiv.show();
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
    }
};