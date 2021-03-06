let battleDom = {
    actionBar: null,
    logs: null,
    init() {
        this.actionBar = $("#actionBar");
        this.logs = $("#log");
        this.addEventHandlerToButtons();
    },
    addLog(log) {
        let msg = document.createElement("div");
        msg.className = "log";
        msg.appendChild(document.createTextNode( log ));

        this.logs.append(msg);
    },
    addEventHandlerToButtons() {
        this.actionBar.children().each(function( index, element ) {
            element.addEventListener("click", function() {
                battleService.sendAction(this.id);
                battleDom.disableButtonsAfterChoosingAction();
            });
        })
    },
    disableButtonsAfterChoosingAction() {
        this.actionBar.children().each(function(index, element) { element.style.display = "none"; });
    },
    enableButtonsAfterTurn() {
        this.actionBar.children().each(function(index, element) { element.style.display = "inline"; });
    }
};