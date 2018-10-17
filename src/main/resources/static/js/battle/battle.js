(function () {
    battleService.connect().then(function (response) {
        battleDom.init();
        battleService.fetchLogs().then(null, null, battleLog => {
            battleDom.addLog(battleLog.log);
            if(battleLog.unlockButtons) { battleDom.enableButtonsAfterTurn(); };
            if(battleLog.isGameOver) { battleDom.disableButtonsAfterChoosingAction() }
        });
    })
})();