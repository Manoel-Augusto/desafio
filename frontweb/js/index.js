 //Recebe os dados do input.
 $("#formSearch").on("submit", function(e) {
     e.preventDefault();
     let data = {
         search: $("#form").val(),
     }

     //Função para validar input (campo vazio).
     if (data.search === "") {
         alert("Preencha o campo de busca!")
         return;
     }

     //seta o campo após com string fazia após submit.
     document.getElementById("form").value = "";

     //Objeto de configuração do AJAX.
     $.ajax({
         method: "POST",
         url: "http://localhost:8080/trades/search",
         dataType: "JSON",
         data: data.search

     }).done((search) => {

         //Faz requisição para  API-REST informando parâmetro para pesquisa.
         getSearch(search);

     }).fail((error) => {
         console.log("erro na requisição", error);
     }).always(() => {

         console.log("requisição finalizada");
     });
 });
 const getSearch = (search) => {
     var list = document.querySelector(".container");

     for (var i = 0; i < search.length; i++) {
         list.innerHTML += "<div class= card> " +
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
 }

 //Busca histórico de Pregões salvo no banco.
 const searchHistory = () => {
     event.preventDefault();
     $.ajax({
         method: "GET",
         url: "http://localhost:8080/trades",
         dataType: "JSON"
     }).done((response) => {
         var list = document.querySelector(".container");

         const formatDate = (dt) => {
             let date = new Date(dt);
             let day = date.toLocaleDateString();
             let hours = date.toLocaleTimeString();
             return day + "  " +
                 "Hora " + " " +
                 hours
         }

         for (var i = 0; i < response.content.length; i++) {
             list.innerHTML += "<div class= card> " +
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
     })
 }