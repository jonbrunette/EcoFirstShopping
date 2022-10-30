chrome.runtime.onMessage.addListener(function (request, sender) {
    if (request.action == "getSource") {
        message.innerText = request.source;
    }

    if (request.action == "getProductDetails") {
        message.innerText = request.source;
    }

    if (request.action == "appendMessage") {
        message.innerHTML += request.source;
        //message.innerText += request.source;
        //console.log(request.source);
    }

    if (request.action == "appendBasketContent") {
        var tbl = document.getElementById("tblBasketClone");
        row = tbl.insertRow(tbl.rows.length);
        row.innerHTML = request.source;
    }

    if (request.action == "getBasketContent") {
        basketClone.innerHTML = request.source;
    }

    if (request.action == "openBuyOffset") {
        openCreditPage();
    }
});

function attachLinksForOffsets() {

    //document.getElementById("message").innerHTML += "What is this?";
    //var message = document.querySelector('#message');

    var list = document.getElementsByClassName("myOffsetButtonLink");

    //message.innerHTML += "Found myOffsetButtonLink:" + list.length;

    for (i = 0; i < list.length; i++) {
        list[i].addEventListener('click', openCreditPage);
    }
}

function printLocalStorage() {
    chrome.storage.local.get(null, function (data) {

        if (Object.keys(data).length === 0) {
            message.innerHTML += "Nothing in local storage";
        }

        for (var k in data) {
            message.innerHTML += "<p>Found [" + k + "," + data[k] + "]</p>";
        }
    });
}

function clearLocalStorage() {
    chrome.storage.local.clear(function () {
        var error = chrome.runtime.lastError;
        if (error) {
            console.error(error);
        }
        else {
            console.log("Cleared local cache");
            message.innerHTML = "";
        }
    });
}

function openCreditPage() {
    console.log("Opening Page");
    var id = "blah";
    var newURL = `chrome-extension://${chrome.runtime.id}/buyoffset.html?productId=${id}`;
    window.open(newURL);
}

function removeItem() {
    var id = event.target.getAttribute("item-id");
    removeProductInLocalCache(id);
    var row = document.getElementById("tr" + id);

    console.log(`Request to remove: ${id}`);

    //TODO: Remove completely not just hide
    if (typeof row !== 'undefined' && row != null)
        row.style.display = "none";
}

function changeUserPrefs() {
    //var checkedState = event.target.getAttribute("checked");
    var checkedState = event.target.checked;
    saveUserPrefLocalCache(checkedState, 5);
}

function onWindowLoad() {

    var message = document.querySelector('#message');
    //document.getElementById('btnShowCreditOptions').addEventListener('click', openCreditPage);
    //message.innerHTML = "<b>TRhis text is for free</b>";

    document.getElementById("actionDiv").style.display = "none";
    //document.getElementById("btnShowPageContent").style.display = "none";

    //chrome.tabs.executeScript(null, {
    //    file: "StoreScripts/CommonBasket.js"
    //}, function () {
    //    // If you try and inject into an extensions page or the webstore/NTP you'll get an error
    //    if (chrome.runtime.lastError) {
    //        message.innerText = 'There was an error injecting script : \n' + chrome.runtime.lastError.message;
    //    }
    //});

    var range = document.querySelector('.input-range');
    var value = document.querySelector('.range-value');

    if (range === null) {
        console.warn("range is null");
    }

    if (value === null) {
        console.warn("value is null");
    }

    var chkUseExtension = document.querySelector('#chkUseExtension');

    chrome.storage.local.get(["userPref"], function (data) {

        var userPrefs = JSON.parse(data["userPref"]);
        chkUseExtension.checked = userPrefs["useExtension"];
    });
    
    chkUseExtension.addEventListener('click', changeUserPrefs);
}

function addRemoveItemClickHandlers() {

    var buttonList = document.getElementsByClassName("removeButton");

    if (buttonList === 'undefined' || buttonList.length == 0)
        return;

    for (var i = 0; i < buttonList.length; i++) {
        buttonList[i].addEventListener('click', removeItem);
    }
}

function addItemLinkClickHandlers() {

    var buttonList = document.getElementsByClassName("itemLink");

    if (buttonList === 'undefined' || buttonList.length == 0)
        return;

    for (var i = 0; i < buttonList.length; i++) {
        buttonList[i].addEventListener('click', gotoItem);
    }
}

function saveUserPrefLocalCache(useExtension, numberResults) {
    var userPref = { useExtension: useExtension, numberResults: numberResults };
    var strUserPref = JSON.stringify(userPref);

    try {
        chrome.storage.local.set({ "userPref": strUserPref }, function () {
            console.log('Value is set to ' + strUserPref);
        });
    }
    catch (e) {
        console.error("Error saving user prefs: " + e);
    }
}

function getUserPrefFromLocalCache() {
    chrome.storage.local.get(["userPref"], function (data) {

        if (Object.keys(data).length === 0) {
            return;
        }

        var userPrefs = JSON.parse(data["userPref"]);
        return userPrefs;
    });
}

window.onload = onWindowLoad;



//var range = $('.input-range'),
//    value = $('.range-value');
//value.setAttribute("value", url);
//value.innerText = range.value;

//range.on('input', function () {
//    value.html(this.value);
//});

