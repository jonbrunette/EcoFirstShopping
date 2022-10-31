function saveUserPrefLocalCacheC(useExtension, numberResults) {
    var userPref = { useExtension: useExtension, numberResults: numberResults};
    var strUserPref = JSON.stringify(userPref);

    var storage = chrome.storage.local;
    var obj = {};
    obj["userPrefs"] = strUserPref;
    storage.set(obj);
    console.log('Saved user preferences');
}