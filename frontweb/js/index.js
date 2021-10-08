const get = () => {
  
  
  document.ontimeupdate
  event.preventDefault();
  var ajax = new XMLHttpRequest();

  ajax.open("GET", "http://localhost:8080/trades");
  ajax.send();

  ajax.addEventListener("readystatechange", function () {
    if (ajax.readyState === 4 && ajax.status === 200) {
      var response = ajax.response;
      const data = JSON.parse(response);
      var lista = document.querySelector(".container");

      for (var i = 0; i < response.length; i++) {
        lista.innerHTML +=
          "<li class= card>" +
          "<span>Id:</span>" +
          data.content[i].id +
          "</li>";
        lista.innerHTML +=
          "<li class= card>" +
          "<span>Nº do Pregão:</span>" +
          data.content[i].tradeNumber +
          "</li>";
        lista.innerHTML +=
          "<li class= card>" +
          "<span>Órgão:</span>" +
          data.content[i].organ +
          "</li>";
        lista.innerHTML +=
          "<li class= card>" +
          "<span>Info-Gerais:</span>" +
          data.content[i].object +
          "</li>";
     
      
      }
    }
  });

  var thumb = document.getElementById("content");

  if (thumb) {
    /*
 var node = document.getElementById("content");
if (node.parentNode) {
  node.parentNode.removeChild(node);
}

*/
    console.log("existe");
  } else console.log("não existe");
};

function handleSubmit() {
  event.preventDefault();
  form = document.getElementById("form").value;
  document.getElementById("form").value = "";
  get();

  post();
}

const post = () => {
  clean()
  const request = new XMLHttpRequest();
  request.open("POST", "http://localhost:8080/trades/search", true);
  request.setRequestHeader("Content-type", "application/json");
  request.send(JSON.stringify(form));
  request.onload = function () {
    console.log(this.responseText);
  };
  return request.responseText;
};
