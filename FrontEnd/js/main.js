const url = "http://localhost:8080"

let listaDentista = document.querySelector('#data-dentista tbody')
let listaPaciente = document.querySelector('#data-paciente tbody')
let listaConsulta = document.querySelector("article#listaConsulta")

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

let logradouro_paciente = document.querySelector("#endereco-paciente>input#logradouro")
let numero_paciente = document.querySelector("#endereco-paciente>input#numero")
let bairro_paciente = document.querySelector("#endereco-paciente>input#bairro")
let complemento_paciente = document.querySelector("#endereco-paciente>input#complemento")
let cidade_paciente = document.querySelector("#endereco-paciente>input#cidade")
let estado_paciente = document.querySelector("#endereco-paciente>input#estado")
let cep_paciente = document.querySelector("#endereco-paciente>input#cep")

let selectDentista = document.querySelector("select#consulta_dentista")
let selectPaciente = document.querySelector("select#consulta_paciente")
let data = document.querySelector("input#data")
let horario = document.querySelector("input#horario")

let requestConfiguration = {
    headers: {
        //cabeçalho da requisição
        'Content-Type': 'application/json'
    },
};

const templteSelect = `
<option ></option>
<option ></option>
<option ></option>
<option ></option>
                `

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

const templteListaConsulta = `
            <section>
            <div class="status"><p></p></div>
            <div class="conteiner">
            <p class="doutor"></p>
            <div>
                <p class="hora"></p>
                <p class="data"></p>
            </div>
            <p class="paciente"></p>
            </div>
            </section>
                `

function getDentistas() {
    fetch(url + '/dentista', requestConfiguration).then(
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
    fetch(url + `/dentista/buscaDentistaPorId?id=${dentistaId}`, requestConfiguration).then(
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
    fetch(url + '/paciente', requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (pacientes) => {
                        console.log("Lista com ", pacientes.length, " Pacientes")
                        listaPaciente.innerHTML = ''
                        for (let paciente of pacientes) {
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
    fetch(url + `/paciente/buscaId?id=${pacienteId}`, requestConfiguration).then(
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

function getConsultaPacientes() {
    fetch(url + '/paciente', requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (pacientes) => {
                        selectPaciente.innerHTML = '<option value="" selected>Paciente</option>'
                        for (let paciente of pacientes) {
                            selectPaciente.innerHTML += `<option value="${paciente.id}">${paciente.nome} ${paciente.sobrenome}</option>`
                        }
                    }
                )
            } else {
                selectPaciente.innerHTML = templteSelect
                console.log("Lista vazia")
            }
        }
    )
}

function getConsultaDentistas() {
    fetch(url + '/dentista', requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (dentistas) => {
                        selectDentista.innerHTML = '<option value="" selected>Dentista</option>'
                        for (let dentista of dentistas) {
                            selectDentista.innerHTML += `<option value="${dentista.id}">${dentista.nome} ${dentista.sobrenome}</option>`
                        }
                    }
                )
            } else {
                selectDentista.innerHTML = templteSelect
                console.log("Lista vazia")
            }
        }
    )
}

function getConsulta() {
    fetch(url + '/consulta', requestConfiguration).then(
        (response) => {
            if (response.ok) {
                response.json().then(
                    (consultas) => {
                        listaConsulta.innerHTML = ''
                        for (let consulta of consultas) {
                            listaConsulta.innerHTML += `
                            <section>
                            <div class="status" id="status" onclick="statusConsulta(${consulta.id})"><p></p></div>
                            <div class="conteiner" id="conteiner">
                              <p class="doutor">Dr.(Dra.): ${consulta.dentista.nome} ${consulta.dentista.sobrenome}</p>
                              <div>
                                <p class="hora">${consulta.horaConsulta} Hs</p>
                                <p class="data">${formatDate(consulta.dataConsulta)}</p>
                              </div>
                              <p class="paciente">Paciente: ${consulta.paciente.nome} ${consulta.paciente.sobrenome}</p>
                            </div>
                          </section>`
                        }
                    }
                )
            } else {
                listaConsulta.innerHTML = templteListaConsulta
                console.log("Lista vazia")
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

function modalAgendaOpen() {
    document.querySelector("#agenda")
        .classList.add("active");
    getConsultaPacientes();
    getConsultaDentistas();
}

function modalAgendaClose() {
    document.querySelector("#agenda")
        .classList.remove("active")
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

    fetch(url + '/dentista', requestOptions).then(
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

function salvarPaciente() {

    let paciente = {
        rg: rg.value,
        nome: nome_paciente.value,
        sobrenome: sobrenome_paciente.value,
        email: email_paciente.value,
        endereco: {
            rua: logradouro_paciente.value,
            numero: numero_paciente.value,
            bairro: bairro_paciente.value,
            complemento: complemento_paciente.value,
            cidade: cidade_paciente.value,
            estado: estado_paciente.value,
            cep: cep_paciente.value
        }
    }

    const requestOptions = {
        method: 'POST',
        body: JSON.stringify(paciente),
        headers: {
            'Content-Type': 'application/json'
        }
    }

    fetch(url + '/paciente', requestOptions).then(
        (response) => {
            if (response.ok) {
                console.log("Paciente cadastrado")
            } else {
                console.log("Erro ao cadastrar Paciente")
            }
            getPacientes()
        }
    )
}

function salvarConsultar(event) {
    event.preventDefault()
    let consulta = {
        dentista: { id: consulta_dentista.value },
        paciente: { id: consulta_paciente.value },
        dataConsulta: data.value,
        horaConsulta: horario.value + ":00"
    }

    const requestOptions = {
        method: 'POST',
        body: JSON.stringify(consulta),
        headers: {
            'Content-Type': 'application/json'
        }
    }

    fetch(url + '/consulta', requestOptions).then(
        (response) => {
            if (response.ok) {
                console.log("consulta cadastrado")
            } else {
                console.log("Erro ao cadastrar consulta")
            }
        }
    )
}

function statusConsulta(idConsulta) {
    document.querySelector("section>#status")
        .classList.toggle("statusOff");
    document.querySelector("section>#conteiner")
        .classList.toggle("conteinerOff");
    console.log(idConsulta)
}

function formatDate(data) {
    let ArrData = data.replaceAll("-", "")
    let mes = ArrData.slice(4, 6)
    let ano = ArrData.slice(0, 4)
    let dia = ArrData.slice(6, 8)
    let dataFormatada = `${dia}/${mes}/${ano}`
    return dataFormatada
}

window.addEventListener('load', () => {
    getConsulta();
})
