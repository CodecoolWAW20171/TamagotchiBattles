(function () {
    battleService.connect().then(function (response) {
        battleDom.init();
        battleService.fetchLogs().then(null, null, log => {
            console.log("###\nfetchLogs msg: " + log.content + "\n###");
            battleDom.addLog(log.content);
        });
    })
})();