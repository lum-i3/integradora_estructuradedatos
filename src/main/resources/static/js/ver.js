document.addEventListener("DOMContentLoaded", cargarTareas);

function cargarTareas() {
    fetch("/api/tareas")
        .then(res => res.json())
        .then(tareas => {

            const contPendientes = document.getElementById("contenedor-tareas");
            const contCompletadas = document.getElementById("contenedor-tareascompletadas");

            contPendientes.innerHTML = "";
            contCompletadas.innerHTML = "";

            tareas.forEach((t) => {
                const card = document.createElement("div");
                card.classList.add("solicitud-card");

                if (t.completada) {
                    card.classList.add("tarea-completada");
                }

                const botonEstado = t.completada
                    ? `<button class="btn-action btn-revertir" onclick="descompletar(${t.id})">
                           No completada
                       </button>`
                    : `<button class="btn-action" onclick="completar(${t.id})">
                           Completar
                       </button>`;

                card.innerHTML = `
                    <h4>Tarea</h4>
                    <p><b>${t.titulo}</b></p>
                    <p>${t.descripcion}</p>
                    <p>Prioridad: <b>${t.prioridad}</b></p>

                    <button class="btn-action" onclick="editar(${t.id})">Editar</button>

                    ${botonEstado}

                    <button class="btn-action" style="background-color:#e57373;"
                            onclick="eliminar(${t.id})">Eliminar</button>
                `;

                if (t.completada) {
                    contCompletadas.appendChild(card);
                } else {
                    contPendientes.appendChild(card);
                }
            });
        });
}

function editar(id) {
    window.location.href = `editar.html?id=${id}`;
}

function eliminar(id) {
    fetch(`/api/tareas/eliminar/${id}`, { method: "DELETE" })
        .then(() => cargarTareas());
}

function completar(id) {
    fetch(`/api/tareas/completar/${id}`, { method: "PUT" })
        .then(() => cargarTareas());
}

function descompletar(id) {
    fetch(`/api/tareas/descompletar/${id}`, { method: "PUT" })
        .then(() => cargarTareas());
}

function ordenar(tipo) {
    fetch(`/api/tareas/ordenar?tipo=${tipo}`)
        .then(res => res.json())
        .then(tareas => {

            const contPendientes = document.getElementById("contenedor-tareas");
            const contCompletadas = document.getElementById("contenedor-tareascompletadas");

            contPendientes.innerHTML = "";
            contCompletadas.innerHTML = "";

            const pendientes = tareas.filter(t => !t.completada);
            const completadas = tareas.filter(t => t.completada);

            pendientes.forEach(t => {
                const card = crearCard(t);
                contPendientes.appendChild(card);
            });

            completadas.forEach(t => {
                const card = crearCard(t);
                contCompletadas.appendChild(card);
            });
        });
}

function crearCard(t) {
    const card = document.createElement("div");
    card.classList.add("solicitud-card");

    if (t.completada) {
        card.classList.add("tarea-completada");
    }

    const botonEstado = t.completada
        ? `<button class="btn-action btn-revertir" onclick="descompletar(${t.id})">
               No completada
           </button>`
        : `<button class="btn-action" onclick="completar(${t.id})">
               Completar
           </button>`;

    card.innerHTML = `
        <h4>Tarea</h4>
        <p><b>${t.titulo}</b></p>
        <p>${t.descripcion}</p>
        <p>Prioridad: <b>${t.prioridad}</b></p>

        <button class="btn-action" onclick="editar(${t.id})">Editar</button>

        ${botonEstado}

        <button class="btn-action" style="background-color:#e57373;"
            onclick="eliminar(${t.id})">Eliminar</button>
    `;

    return card;
}
