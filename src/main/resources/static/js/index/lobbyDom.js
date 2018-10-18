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
            let petDiv = document.createElement("div");
            petDiv.className = "pet";
            petDiv.appendChild(document.createTextNode( lobbyDom.petStatsToString(pet) ));
            lobbyDom.petsDiv.appendChild(petDiv);
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
    }
};