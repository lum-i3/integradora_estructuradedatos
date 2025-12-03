document.addEventListener("DOMContentLoaded", cargarTarea);

function cargarTarea() {
    const id = new URLSearchParams(window.location.search).get("id");

    fetch(`/api/tareas/${id}`)
        .then(r => r.json())
        .then(t => {
            document.querySelector("input[name=id]").value = id;
            document.querySelector("input[name=titulo]").value = t.titulo;
            document.querySelector("input[name=descripcion]").value = t.descripcion;
            document.querySelector("select[name=prioridad]").value = t.prioridad;
        });
}

function guardarCambios() {
    const id = document.querySelector("input[name=id]").value;

    const data = {
        titulo: document.querySelector("input[name=titulo]").value,
        descripcion: document.querySelector("input[name=descripcion]").value,
        prioridad: document.querySelector("select[name=prioridad]").value
    };

    fetch(`/api/tareas/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }).then(() => window.location.href = "ver.html");
}
