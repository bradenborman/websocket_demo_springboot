var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/timerresponse/response', function (webstats) {
            showTimer(JSON.parse(webstats.body));
        });
    });
}

function sendName() {
   if(stompClient.connected)
    stompClient.send("/app/timer", {}, "test");
}

$(document).ready(function(){
    connect()
    setInterval(function(){ sendName() }, 1000);

});


function showTimer(response) {
    $("#timer").text("Global time spent looking at site: " + response.globalTimeSpent + " seconds");
    $("#users").text("Online users: " + response.usersOnline);
}