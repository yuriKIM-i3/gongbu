$( document ).ready(function() {
    setInterval(randomWordCall, 5000);

    function randomWordCall() {
      $.ajax({
        url: '/word/random',
        type: 'get',
      }).done(function (data) {
        const randomWord = JSON.parse(data);
        var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)); 
        var url =window.location.protocol+"//"+ window.location.host +context+"/word/detail/" + randomWord.wordId;

        $("#cardWord").html(randomWord.wordName);
        $(".cardWordLink").attr("href", url);
      });
    }
});