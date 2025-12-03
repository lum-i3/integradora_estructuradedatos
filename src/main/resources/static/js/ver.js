document.addEventListener("DOMContentLoaded", cargarTareas);

function cargarTareas() {
    fetch("/api/tareas")
        .then(res => res.json())
        .then(tareas => {
            const contenedor = document.getElementById("contenedor-tareas");
            contenedor.innerHTML = "";

            tareas.forEach((t, index) => {
                const card = document.createElement("div");
                card.classList.add("solicitud-card");

                card.innerHTML = `
                    <h4>Tarea <b>${index + 1}</b></h4>
                    <p><b>${t.titulo}</b></p>
                    <p>${t.descripcion}</p>
                    <p>Prioridad: <b>${t.prioridad}</b></p>

                    <button class="btn-action" onclick="editar(${t.id})">
                        Editar
                    </button>

                    <button class="btn-action" style="background-color:#e57373;" onclick="eliminar(${t.id})">
                        Eliminar
                    </button>
                `;

                contenedor.appendChild(card);
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
