let battleDom = {
    actionBar: null,
    logs: null,
    init() {
        this.actionBar = $("#actionBar");
        this.logs = $("#log");
        this.addEventHandlerToButtons();
    },
    addLog(log) {
        console.log("###\naddLog log: " + log + "\n###");
        let msg = document.createElement("div");
        msg.className = "log";
        msg.appendChild(document.createTextNode( log ));

        this.logs.append(msg);
    },
    addEventHandlerToButtons() {
        this.actionBar.children().each(function( index, element ) {
            element.addEventListener("click", () => battleService.sendAction(this.id));
        });
    },
};