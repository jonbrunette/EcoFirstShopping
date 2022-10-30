console.log("Content Script loaded");

const baseServiceAddres = "http://localhost:8081/";

function appendMessage(message) {
    chrome.runtime.sendMessage({
        action: "appendMessage",
        source: "<p>" + message + "</p>"
    });
}

function checkToRun() {
    chrome.storage.local.get(["userPref"], function (data) {

        var userPrefs = JSON.parse(data["userPref"]);

        if (userPrefs["useExtension"]) {
            lookForProductType();
        }
        else {
            console.log("Extension set to not run, ignoring page.");
        }
    });
}

async function lookForProductType() {

    var breadcrumbArea = document.querySelector('#wayfinding-breadcrumbs_feature_div');
    var productTitle = document.querySelector('#productTitle');
    var searchText = document.querySelector('#twotabsearchtextbox');

    if (searchText !== null) {
        searchText = searchText.value;
    }

    if (productTitle !== null) {
        productTitle = productTitle.innerText;

        if (productTitle.indexOf("straws") > 0) {
            searchText = "straws";
        }

        if (productTitle.indexOf("notebook") || productTitle.indexOf("rocketbook") > 0) {
            searchText = "notebook";
        }
    }

    if (breadcrumbArea === null) {
        console.log("No categories found");
    }
    else {

        var breadcrumbList = breadcrumbArea.querySelectorAll('a');
        const itemCategories = new Array();

        for (var i = 0; i < breadcrumbList.length; i++) {
            itemCategories.push(breadcrumbList[i].innerText.trim());
        }

        var itemCategory = itemCategories.pop();
    }

    //while (itemCategories.length > 0) {
    //    var cat = itemCategories.pop();

    //    if (cat !== "Back to results")
    //        console.log("Found category: " + cat);
    //}

    //requestForAlternates("name", "Eco");



    var results = await loadFromFile(searchText);
    addCarouselItems(results);
    addSearchItems(results);
}

async function loadFromFile(search) {

    try {

        var jsonFileName = "";
        if (search === "straws") {
            jsonFileName = '/data/strawSearch.json';
        } else if (search === "notebook") {
            jsonFileName = '/data/notebookSearch.json';
        } else {
            return null;
        }

        const url = chrome.runtime.getURL(jsonFileName);
        let response = await fetch(url);

        if (response.ok) { // if HTTP-status is 200-299
            let json = await response.json();
            return json;
        } else {
            console.error("HTTP-Error: " + response.status);
        }
    }
    catch (e) {
        console.error("Error in loadFromFile: " + e);
    }
}

function createEcoBanner() {
    var a = document.createElement("a");
    a.setAttribute("class", "a-link-normal adReviewLink a-text-normal");
    a.setAttribute("target", "_top");
    a.setAttribute("href", "http://google.com/store/ecofirstshopping/");

    var banner = document.createElement("span");
    banner.setAttribute("class", "greenBannerText");
    
    var text = document.createElement("span");
    text.innerText = "Eco Friendly Choice";

    const url = chrome.runtime.getURL('/images/eco-bag-64.png');

    var img = document.createElement("img");
    img.setAttribute("src", url);
    img.setAttribute("height", "15");
    img.setAttribute("width", "15");
    img.setAttribute("style", "margin: 2px; margin-left: 8px");

    banner.appendChild(img);
    banner.appendChild(text);

    var spacer = document.createElement("span");
    spacer.setAttribute("style", "color: white");
    spacer.innerText = ".";
    
    a.appendChild(banner);
    a.appendChild(spacer);
    
    return a;
}

function createRating(item) {
    var rating = document.createElement("a");
    rating.setAttribute("class", "a-link-normal adReviewLink a-text-normal");
    rating.setAttribute("target", "_top");
    rating.setAttribute("href", item["link"]);

    var ratingi = document.createElement("i");
    ratingi.setAttribute("class", "a-icon a-icon-star " + item["rating"]["stars"]);

    var ratingspan = document.createElement("span");
    ratingspan.setAttribute("class", "a-color-link");
    ratingspan.innerText = item["rating"]["number"];

    rating.appendChild(ratingi);
    rating.appendChild(ratingspan);

    return rating;
}

function createPrice(item) {
    var div = document.createElement("div");
    div.setAttribute("class", "a-row a-color-price");
    
    var a = document.createElement("a");
    a.setAttribute("class", "a-link-normal a-text-normal");
    a.setAttribute("target", "_top");
    a.setAttribute("rel", "noopener");
    a.setAttribute("href", item["link"]);
    a.setAttribute("title", item["name"]);

    var span = document.createElement("span");
    span.setAttribute("class", "a-size-medium a-color-price");
    span.innerText = item["price"];

    a.appendChild(span);

    var a2 = document.createElement("a");
    a2.setAttribute("class", "a-link-normal a-text-normal");
    a2.setAttribute("target", "_top");
    a2.setAttribute("rel", "noopener");
    a2.setAttribute("href", item["link"]);
    a2.setAttribute("title", item["name"]);

    var span2 = document.createElement("span");
    span2.setAttribute("style", "position: relative; top: 2px;");

    var i = document.createElement("i");
    i.setAttribute("class", "a-icon a-icon-prime a-icon-small");
    i.setAttribute("role", "presentation");
    
    span2.appendChild(i);

    a2.appendChild(span2);
    div.appendChild(a);
    div.appendChild(a2);

    return div;
}

function createCarouselImage(item) {
    var a = document.createElement("a");
    a.setAttribute("class", "a-link-normal");
    a.setAttribute("target", "_top");
    a.setAttribute("href", item["link"]);

    var img = document.createElement("img");
    img.setAttribute("src", item["image"]);
    img.setAttribute("height", "160");
    img.setAttribute("width", "160");
    img.setAttribute("class", "a-dynamic-image");

    var div = document.createElement("div");
    div.setAttribute("aria-hidden", "true");
    div.setAttribute("data-rows", "4");
    div.setAttribute("class", "sponsored-products-truncator-truncated");

    var text = item["name"];

    if (text.length > 60) {
        text = text.substring(0, 57) + "...";
    }

    div.innerText = text;

    a.appendChild(img);
    a.appendChild(div);
    
    return a;
}

function createCarouselItem(item) {
    var li = document.createElement("li");
    li.setAttribute("class", "a-carousel-card");
    li.setAttribute("style", "width: 160px; margin-left: 27px;");

    var nlidiv = document.createElement("div");

    var banner = createEcoBanner();
    var image = createCarouselImage(item);
    var rating = createRating(item);
    var price = createPrice(item);

    nlidiv.appendChild(banner);
    nlidiv.appendChild(image);
    nlidiv.appendChild(rating);
    nlidiv.appendChild(price);
    li.appendChild(nlidiv);

    return li;
}

function createSearchItem(item) {
    var div = document.createElement("div");
    div.setAttribute("class", "sg-col-4-of-12 s-result-item s-asin sg-col-4-of-16 AdHolder sg-col s-widget-spacing-small sg-col-4-of-20");

    var div1 = document.createElement("div");
    div1.setAttribute("class", "sg-col-inner");
    div.appendChild(div1)

    var div2 = document.createElement("div");
    div2.setAttribute("class", "s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1");
    div1.appendChild(div2)

    var div3 = document.createElement("div");
    div3.setAttribute("class", "rush-component s-expand-height");
    div2.appendChild(div3)

    var div4 = document.createElement("div");
    div4.setAttribute("class", "rush-component s-featured-result-item s-expand-height");
    div3.appendChild(div4)

    var div5 = document.createElement("div");
    div5.setAttribute("class", "s-card-container s-overflow-hidden aok-relative puis-expand-height puis-include-content-margin puis s-latency-cf-section s-card-border");
    div4.appendChild(div5)

    var div6 = document.createElement("div");
    div6.setAttribute("class", "a-section a-spacing-base");
    div5.appendChild(div6)
    
    var div6a = document.createElement("div");
    div6a.setAttribute("class", "a-section a-spacing-none puis-status-badge-container aok-relative s-grid-status-badge-container puis-expand-height");
    div6a.setAttribute("style", "padding-top: 0px !important;");
    div6.appendChild(div6a)
    
    var span6a = document.createElement("span");
    span6a.setAttribute("class", "rush-component");
    span6a.setAttribute("data-component-type", "s-product-image");
    div6.appendChild(span6a)
   
    var banner = createEcoBanner();
    var image = createCarouselImage(item);
    var rating = createRating(item);
    var price = createPrice(item);

    div6.appendChild(banner);
    div6.appendChild(image);
    div6.appendChild(rating);
    div6.appendChild(price);

    var div6c = document.createElement("div");
    div6c.setAttribute("class", "a-section a-spacing-small puis-padding-left-small puis-padding-right-small");
    div6.appendChild(div6c)

    return div;
}

function addCarouselItems(items) {

    var carousel = document.querySelector('#anonCarousel1 ol');

    if (items === null || carousel === null)
        return;

    var carouselItems = carousel.childNodes;
    var nextItem = carouselItems[0];

    for (var i = 0; item = items[i]; i++) {
        var carouselItem = createCarouselItem(item);
        carouselItems[0].parentNode.insertBefore(carouselItem, nextItem);
        nextItem = carouselItem;
    }
}

function addSearchItems(items) {

    var searchGrid = document.querySelector('.s-search-results');

    if (items === null || searchGrid === null)
        return;

    var searchItems = searchGrid.childNodes;
    var nextItem = searchItems[3];

    for (var i = 0; item = items[i]; i++) {
        var searchItem = createSearchItem(item);
        searchItems[0].parentNode.insertBefore(searchItem, nextItem);
        nextItem = searchItem;
    }
}

function addTestCarouselItems() {

    var carousel = document.querySelector('#anonCarousel1 ol');

    if (carousel === null)
        return;

    var carouselItems = carousel.childNodes;

    //https://purepng.com/public/uploads/large/purepng.com-green-leavesleaffoliageautumn-foliagephotosynthetic-function-1411527056660ro4dm.png
    
    var item1 = createCarouselItem("This is our 1st recommendation");
    var item2 = createCarouselItem("This is our 2nd recommendation");
        
    carouselItems[0].parentNode.insertBefore(item2, carouselItems[0])
    carouselItems[0].parentNode.insertBefore(item1, item2)
}

function removeCarouselItems(numToRemove) {

    var carousel = document.querySelector('#anonCarousel1 ol');

    if (carousel === null)
        return;

    var carouselItems = carousel.childNodes;
    for (var i = 0; li = carouselItems[i]; i++) {
        li.parentNode.removeChild(li);
    }
}

async function requestForAlternates(field, keyword) {
    keyword = encodeURI(keyword);
    try {
       
        //let response = await fetch(baseServiceAddres + "search?inField=" + field + "&keyword=" + keyword, {
        //let response = await fetch(baseServiceAddres + "products/", { mode: "no-cors" });
        //let response = await fetch(baseServiceAddres + "products/");
        let response = await fetch("http://localhost:8081/search?inField=name&keyword=Eco");

        if (response.ok) { // if HTTP-status is 200-299
            // get the response body (the method explained below)
            let json = await response.json();
        } else {
            console.error("HTTP-Error: " + response.status);
        }
    }
    catch (e) {
        console.error("Error in fetch alternatives: " + e);
    }
}

function requestForAlternates2(field, keyword) {
    keyword = encodeURI(keyword);
    try {
        fetch(baseServiceAddres + "search?inField=" + field + "&keyword=" + keyword, {
            mode: "no-cors"
        })
            //.then((response) => response)
            .then((response) => response.json())
            .then((data) => console.log(data));
    }
    catch (e) {
        console.error("Error in fetch alternatives: " + e);
    }
}

if (window.location.hostname.toLocaleLowerCase().includes("amazon.")) {
    window.onload = checkToRun;
}