$("#formSearch").on("submit", function(e) {
    e.preventDefault();
    let data = {
        search: $("#form").val(),
    }

    if (data.search === "") {
        alert("Preencha o campo de busca!")
        return;
    }
    document.getElementById("form").value = ""
        /* teste = () => {
        if (document.querySelector(".card")) {
            document.querySelector(".card").replaceChild;


        }
    }*/

    $.ajax({
        method: "POST",
        url: "http://localhost:8080/trades/search",
        dataType: "JSON",
        data: data.search


    }).done((search) => {


        console.log("executando a função SEARCH")

        var lista = document.querySelector(".container");

        let load = false

        if (!load) {

            for (var i = 0; i < search.length; i++) {




                lista.innerHTML += "<div class= card> " +
                    "<li>" +
                    "<span>Nº do Pregão:</span>" +
                    search[i].tradeNumber +
                    "</li>" +
                    "<li>" +
                    "<span>Órgão:</span>" +
                    search[i].organ +
                    "</li>" +
                    "<li>" +
                    "<span>Info-Gerais:</span>" +
                    search[i].info +
                    "</li>"

                "/div"
            }

        } else lista.innerHTML = "<span>Carregando...</span>"



    }).fail((error) => {
        console.log("erro na requisição", error);
    }).always(() => {

        console.log("requisição finalizada");
    });
});

const refresh = () => {
    if (document.querySelector(".card")) {

    }
}

//////////////////////////////////////////////////////////////////////////////////////////////


const searchHistory = () => {
    event.preventDefault();

    $.ajax({
            method: "GET",
            url: "http://localhost:8080/trades",
            dataType: "JSON"
        }).done((response) => {
            var lista = document.querySelector(".container");

            let load = false


            const formatDate = (dt) => {
                let date = new Date(dt);
                let day = date.toLocaleDateString();
                let hours = date.toLocaleTimeString();
                return day + "  " +
                    "Hora " + " " +
                    hours

            }


            if (!load) {


                for (var i = 0; i < response.content.length; i++) {

                    lista.innerHTML += "<div class= card> " +
                        "<li>" +
                        "<span>Id:</span>" +
                        response.content[i].id +
                        "</li>" +
                        "<li>" +
                        "<span>Nº do Pregão:</span>" +
                        response.content[i].tradeNumber +
                        "</li>" +
                        "<li>" +
                        "<span>Órgão:</span>" +
                        response.content[i].organ +
                        "</li>" +
                        "<li>" +
                        "<span>Info-Gerais:</span>" +
                        response.content[i].info +
                        "</li>" +

                        "<li>" +
                        "<span > Data e hora da pesquisa: </span>" + "<span class=searchDate>" +
                        formatDate(response.content[i].instant) + "</span>" +
                        "</li>"

                    "/div"

                }


            } else lista.innerHTML = "<span>Carregando...</span>"

        })
        // window.location.reload(true);
        // window.location.reload()
        // location.reload()

}