document.addEventListener('DOMContentLoaded', function() {

    //var idpreferencia = generarPreferencia();

    //llave publica
    const mp = new MercadoPago('TEST-cdd4aff4-315b-4d01-bd19-b422d835c0b9',{
        locale:'es'
    })
    mp.bricks();


    mp.bricks().create("wallet", "wallet_container", {
        initialization: {
            preferenceId: "221225807-2fb3adef-86e1-4724-aa95-fe343b3dd1ff",
            redirectMode: "modal"
        },
    });
});

function generarPreferencia()
{
    fetch('/pago/preferencia', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error('Error en la solicitud');
        })
        .then(data => {
             return data;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}