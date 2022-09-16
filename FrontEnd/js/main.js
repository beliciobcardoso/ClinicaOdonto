let listaDentista = document.querySelector('#data-dentista tbody')
let listaPaciente = document.querySelector('#data-paciente tbody')

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

let logradouro_paciente = document.querySelector("#endereco-paciente>input#lougradouro")
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

const templteTabelaTr = `
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                `

function getDentistas() {
    fetch('http://localhost:8080/dentista', requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (dentistas) => {
                        if (dentistas.length === 0) {
                            listaDentista.innerHTML = templteTabelaTr
                            console.log("Lista vazia")
                        } else {
                            console.log("Lista com ", dentistas.length, " Dentistas")
                            listaDentista.innerHTML = ''
                            for (let dentista of dentistas) {
                                listaDentista.innerHTML += `
                                                    <tr onclick="getDentistaById(${dentista.id})">                                                        
                                                        <td>${dentista.matricula}</td>
                                                        <td>${dentista.nome}</td>
                                                        <td>${dentista.sobrenome}</td>
                                                        <td>${dentista.email}</td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                    `
                            }
                        }
                    }
                )
            }
        }
    )
}

let dentistaId = 0
function getDentistaById(dentistaId) {
    fetch(`http://localhost:8080/dentista/buscaDentistaPorId?id=${dentistaId}`, requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (dentista) => {
                        nome_dentista.innerHTML = dentista.nome
                        console.log(dentista.nome)
                        console.log(dentista.sobrenome)
                        console.log(dentista.email)
                        //console.log(dentista.logradouro_dentista)
                    }
                )
            }

        }
    )
}

function getPacientes() {
    fetch('http://localhost:8080/paciente', requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (pacientes) => {
                        console.log("Lista com ", pacientes.length, " Pacientes")
                        listaPaciente.innerHTML = ''
                        for (let paciente of pacientes) {
                            console.log(paciente.id)
                            listaPaciente.innerHTML += `
                                                    <tr onclick="getPacienteById(${paciente.id})">
                                                        <td>${paciente.rg}</td>
                                                        <td>${paciente.nome}</td>
                                                        <td>${paciente.sobrenome}</td>
                                                        <td>${paciente.email}</td>
                                                        <td></td>
                                                         <td></td>
                                                    </tr>
                                                    `
                        }

                    }
                )
            } else {
                listaPaciente.innerHTML = templteTabelaTr
                console.log("Lista vazia")
            }
        }
    )
}

let pacienteId = 0
function getPacienteById(pacienteId) {
    fetch(`http://localhost:8080/paciente/buscaId?id=${pacienteId}`, requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (dentista) => {
                        console.log(dentista.rg)
                        console.log(dentista.logradouro_paciente)
                    }
                )
            }

        }
    )
}

function modalDentistaOpen() {
    document.querySelector("#dentista")
        .classList.add("active");
    getDentistas();
}

function modalDentistaClose() {
    document.querySelector("#dentista")
        .classList.remove("active")
}

function modalPacienteOpen() {
    document.querySelector("#paciente")
        .classList.add("active");
    getPacientes();
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

function salvarDentista() {
    let dentista = {
        matricula: matricula.value,
        nome: nome_dentista.value,
        sobrenome: sobrenome_dentista.value,
        email: email_dentista.value,
        endereco: {
            rua: logradouro_dentista.value,
            numero: numero_dentista.value,
            bairro: bairro_dentista.value,
            complemento: complemento_dentista.value,
            cidade: cidade_dentista.value,
            estado: estado_dentista.value,
            cep: cep_dentista.value
        }
    }

    const requestOptions = {
        method: 'POST',
        body: JSON.stringify(dentista),
        headers: {
            'Content-Type': 'application/json'
        }
    }

    console.log(matricula.value)
    fetch('http://localhost:8080/dentista', requestOptions).then(
        (response) => {
            if (response.ok) {
                console.log("Dentista cadastrado")
            } else {
                console.log("Erro ao cadastrar Dentista")
            }
            getDentistas()
        }
    )
}

function pacientesave(event) {
    event.preventDefault();
    console.log(rg.value)
}

// window.addEventListener('load', () => {
//     getDentistas();
// })

let testeId = 0

function getTestId(testeId) {
    console.log(testeId)
}