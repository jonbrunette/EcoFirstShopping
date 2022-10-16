console.log("Content Script loaded");

function appendMessage(message) {
    chrome.runtime.sendMessage({
        action: "appendMessage",
        source: "<p>" + message + "</p>"
    });
}

function lookForProductType() {
    var breadcrumbArea = document.querySelector('#wayfinding-breadcrumbs_feature_div');
    var breadcrumbList = breadcrumbArea.querySelectorAll('a');
    const itemCategories = new Array();

    for (var i = 0; i < breadcrumbList.length; i++) {
        itemCategories.push(breadcrumbList[i].innerText.trim());
    }

    while (itemCategories.length > 0) {
        console.log("Found category: " + itemCategories.pop());
    }
}

function requestForAlternates(categoryName) {
    fetch('http://example.com/products/' + categoryName)
        .then((response) => response.json())
        .then((data) => console.log(data));
}

window.onload = lookForProductType;
//appendMessage("This from the background world");