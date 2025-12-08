function buscar() {
    const query = document.querySelector("input[name=query]").value.trim();

    fetch(`/api/tareas/buscar?query=${encodeURIComponent(query)}`)
        .then(r => r.json())
        .then(tareas => {

            const cont = document.querySelector(".window");
            cont.innerHTML = "<h3>Resultados:</h3>";

            tareas.forEach(t => {
                const card = crearCard(t);
                cont.appendChild(card);
            });

            if (tareas.length === 0) {
                cont.innerHTML += "<p>No se encontraron tareas.</p>";
            }
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

function editar(id) {
    window.location.href = `editar.html?id=${id}`;
}

function eliminar(id) {
    fetch(`/api/tareas/eliminar/${id}`, { method: "DELETE" })
        .then(() => buscar());
}

function completar(id) {
    fetch(`/api/tareas/completar/${id}`, { method: "PUT" })
        .then(() => buscar());
}

function descompletar(id) {
    fetch(`/api/tareas/descompletar/${id}`, { method: "PUT" })
        .then(() => buscar());
}

