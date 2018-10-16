let attack = document.getElementById("attack-input");
let defence = document.getElementById("defence-input");
let speed = document.getElementById("speed-input");

attack.addEventListener("input", updateRemainingPoints);
defence.addEventListener("input", updateRemainingPoints);
speed.addEventListener("input", updateRemainingPoints);

function updateRemainingPoints() {
    let attackPoints = Number(attack.value);
    let defencePoints = Number(defence.value);
    let speedPoints = Number(speed.value);
    let pointsRemaining = 200 - attackPoints - defencePoints - speedPoints;
    document.getElementById("points-left").innerText = pointsRemaining;

    let isButtonDisabled;

    if (pointsRemaining != 0) {
        isButtonDisabled = true;
    } else {
        isButtonDisabled = false;
    }

    document.getElementById("submit-button").disabled = isButtonDisabled;

}