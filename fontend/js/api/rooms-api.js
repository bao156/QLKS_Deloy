const baseAPI = "http://localhost:8082/api/rooms";

async function getListRoom() {
    fetch(baseAPI + "/EMPTY",{
        mode : "no-cors"
    })
    .then(function(response){
        return response.json();
    })
    .then(function(posts){
        console.log(posts);
    });
}

