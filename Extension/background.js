console.log("Background Script loaded");
const baseServiceAddres = "http://localhost:8081/";

async function requestForAlternates(field, keyword) {
    keyword = encodeURI(keyword);
    try {

        let response = await fetch(baseServiceAddres + "search?inField=" + field + "&keyword=" + keyword);

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

//requestForAlternates("name", "Eco");