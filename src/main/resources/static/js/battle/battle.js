(function () {
    battleService.connect().then(function (response) {
        battleDom.init();
        battleService.fetchLogs().then(null, null, log => {
            console.log("###\nfetchLogs log: " + log + "\n###");
            battleDom.addLog(log.log);
        });
    })
})();