document.addEventListener('DOMContentLoaded', function() {
    const mp = new MercadoPago('TEST-d12d7ea5-a2c4-41c7-a48b-ed86da6605d8')
    mp.bricks();


    mp.bricks().create("wallet", "wallet_container", {
        initialization: {
            preferenceId: "793324980",
        },
    });


});