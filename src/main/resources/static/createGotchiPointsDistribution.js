let pointsRemainingText = $("#points-left");

let attack = $("#attack-input");
let defence = $("#defence-input");
let speed = $("#speed-input");

let submitButton = $("#submit-button");

attack.addEventListener("input", updateRemainingPoints);
defence.addEventListener("input", updateRemainingPoints);
speed.addEventListener("input", updateRemainingPoints);

function updateRemainingPoints() {
    let attackPoints = Number(attack.value);
    let defencePoints = Number(defence.value);
    let speedPoints = Number(speed.value);
    let pointsRemaining = 200 - attackPoints - defencePoints - speedPoints;
    pointsRemainingText.innerText = pointsRemaining;

    if (pointsRemaining < 0) {
        submitButton.disabled = true;
    } else {
        submitButton.disabled = false;
    }

}