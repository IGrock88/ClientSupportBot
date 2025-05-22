//var currentHost = location.host;;
//console.log(currentHost);
//
//var webSocket = new WebSocket("ws://" + currentHost + "/gate_monitor");



var webSocket = new WebSocket("ws://localhost:1234");
webSocket.onmessage = function (msg) {

    console.log("Message: ", msg);


};
webSocket.onclose = function () {
    alert("WebSocket connection closed")
};

//Send message if "Send" is clicked
id("send").addEventListener("click", function () {
    sendMessage(id("message").value);
});

//Send message if enter is pressed in the input field
id("message").addEventListener("keypress", function (e) {
    if (e.keyCode === 13) { sendMessage(e.target.value); }
});

//Send a message if it's not empty, then clear the input field
function sendMessage(message) {
    if (message !== "") {
        webSocket.send(message);
        id("message").value = "";
    }
}

//Update the chat-panel, and the list of connected users

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id) {
    return document.getElementById(id);
}



getXMLHttpRequest();
    function getXMLHttpRequest(){
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:18800/gate/controllers_ip', true);
        xhr.setRequestHeader('Authorization', 'ncc3ZCxCIALQKu2weMXMwf7HccuayxcK7H7x');
        xhr.setRequestHeader('Content-Type', 'application/json');


        xhr.onload = function() {
            console.log(xhr.response);
        };

        xhr.send();

    }

