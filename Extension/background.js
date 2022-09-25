var currentUrl = "";

window.onload = function () {
    console.log("Background Page load: " + window.location.href);
    currentUrl = window.location.href;
    setInterval(checkLocationChanged, 15000); //check every 15s
}

function checkLocationChanged() {
    if (currentUrl != window.location.href) {
        currentUrl = window.location.href;
        runScrapperForSelectSites();
    }
}

function runScrapperForSelectSites() {
    if (window.location.hostname.toLocaleLowerCase().includes("bestbuy.")) {
        extractBestBuyProductData(document);
    }
}

function extractBestBuyProductData(doc) {


}