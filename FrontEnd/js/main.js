function modalD() {
    document.querySelector("#dentista")
        .classList.toggle("active")
        ,            // limpar fomulario
        Form.clearFlields();
}

function modalP() {
    document.querySelector("#paciente")
        .classList.toggle("active")
        ,            // limpar fomulario
        Form.clearFlields();

}

function modalAgenda() {
    document.querySelector("#agenda")
        .classList.toggle("active")
        ,            // limpar fomulario
        Form.clearFlields();

}