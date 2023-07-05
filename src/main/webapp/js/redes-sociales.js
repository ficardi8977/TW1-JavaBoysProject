var copiarTexto = document.querySelector('.copiar-enlace');
var copiar = document.querySelector('.copiar');


$(document).ready(function() {
    $('.btn-share').click(function() {
        $('.copy-link').toggle();
    });

    $(copiar).click(function() {
        var url = window.location.href;
        copyToClipboard(url);
        $(copiarTexto).text('Enlace copiado');
        $(copiarTexto).css('color', 'white');
        $(copiar).css('background-color', 'green');
        $(copiar).unbind('mouseenter mouseleave')

        copiarTexto.disabled = true;
        setTimeout(function() {
            copiarTexto.innerHTML = "Copiar Enlace";
            copiarTexto.style.color = 'black';
            copiarTexto.disabled = false;
            copiar.style.backgroundColor = "#e6b9a0";
            $(copiar).hover(function(){
                $(copiar).css("background-color", "#b28a73");
            }, function(){
                $(copiar).css("background-color", "#e6b9a0");
            });
        }, 2000);
    });
});

function copyToClipboard(text) {
    var $temp = $('<input>');
    $('body').append($temp);
    $temp.val(text).select();
    document.execCommand('copy');
    $temp.remove();
}