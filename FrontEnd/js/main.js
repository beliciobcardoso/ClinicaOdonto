let listaDentista = document.querySelector('#data-dentista')

let matricula = document.querySelector("#dados-dentista>input#matricula")
let nome_dentista = document.querySelector("#dados-dentista>input#nome")
let sobrenome_dentista = document.querySelector("#dados-dentista>input#sobrenome")
let email_dentista = document.querySelector("#dados-dentista>input#email")

let logradouro_dentista = document.querySelector("#endereco-dentista>input#logradouro")
let numero_dentista = document.querySelector("#endereco-dentista>input#numero")
let bairro_dentista = document.querySelector("#endereco-dentista>input#bairro")
let complemento_dentista = document.querySelector("#endereco-dentista>input#complemento")
let cidade_dentista = document.querySelector("#endereco-dentista>input#cidade")
let estado_dentista = document.querySelector("#endereco-dentista>input#estado")
let cep_dentista = document.querySelector("#endereco-dentista>input#cep")

let rg = document.querySelector("#dados-paciente>input#rg")
let nome_paciente = document.querySelector("#dados-paciente>input#nome")
let sobrenome_paciente = document.querySelector("#dados-paciente>input#sobrenome")
let email_paciente = document.querySelector("#dados-paciente>input#email")

let lougradouro_paciente = document.querySelector("#endereco-paciente>input#lougradouro")
let numero_paciente = document.querySelector("#endereco-paciente>input#numero")
let bairro_paciente = document.querySelector("#endereco-paciente>input#bairro")
let complemento_paciente = document.querySelector("#endereco-paciente>input#complemento")
let cidade_paciente = document.querySelector("#endereco-paciente>input#cidade")
let estado_paciente = document.querySelector("#endereco-paciente>input#estado")
let cep_paciente = document.querySelector("#endereco-paciente>input#cep")


let id_dentista = document.querySelector("select#dentista")
let id_paciente = document.querySelector("select#paciente")
let data = document.querySelector("input#data")
let horario = document.querySelector("input#horario")

let requestConfiguration = {
    headers: {
        //cabeçalho da requisição
        'Content-Type': 'application/json'
    },
};

function getDentistas() {
    fetch('http://localhost:8080/dentista', requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (dentistas) => {
                        const tbody = document.createElement("tbody")

                        for (let dentista of dentistas) {
                            let matricula = document.createTextNode(dentista.matricula)
                            let nome = document.createTextNode(dentista.nome)
                            let sobrenome = document.createTextNode(dentista.sobrenome)

                            const tr = document.createElement("tr")
                            const td1 = document.createElement("td")
                            const td2 = document.createElement("td")
                            const td3 = document.createElement("td")

                            td1.appendChild(matricula)
                            td2.appendChild(nome)
                            td3.appendChild(sobrenome)

                            tr.appendChild(td1)
                            tr.appendChild(td2)
                            tr.appendChild(td3)
                            //console.log(tr)

                            tbody.appendChild(tr)

                        }
                        listaDentista.appendChild(tbody)

                    }
                )
            }
        }
    )
}



function modalDentistaOpen() {
    document.querySelector("#dentista")
        .classList.add("active"),
        getDentistas();
}

function modalDentistaClose() {
    document.querySelector("#dentista")
        .classList.remove("active"),
        matricula = ""
    nome = ""
    sobrenome = ""

    //listaDentista.removeChild(tr)
    console.log(listaDentista)
}

function modalPacienteOpen() {
    document.querySelector("#paciente")
        .classList.add("active")
}
function modalPacienteClose() {
    document.querySelector("#paciente")
        .classList.remove("active")
}

function modalAgenda() {
    document.querySelector("#agenda")
        .classList.toggle("active")
}




function consultasave(event) {
    event.preventDefault();
    console.log(id_dentista.value)
    console.log(id_paciente.value)
    console.log(data.value)
    console.log(horario.value)
}

function dentistasave(event) {
    event.preventDefault();
    console.log(matricula.value)
}

function pacientesave(event) {
    event.preventDefault();
    console.log(rg.value)
}

// window.addEventListener('load', () => {
//     getDentistas();
// })