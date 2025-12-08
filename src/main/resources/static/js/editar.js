document.addEventListener("DOMContentLoaded", () => {
    cargarTarea();

    const titulo = document.querySelector("input[name=titulo]");
    const descripcion = document.querySelector("input[name=descripcion]");
    const prioridad = document.querySelector("select[name=prioridad]");

    titulo.addEventListener("keypress", () => validarCampo(titulo));
    titulo.addEventListener("blur", () => validarCampo(titulo));

    descripcion.addEventListener("keypress", () => validarCampo(descripcion));
    descripcion.addEventListener("blur", () => validarCampo(descripcion));

    prioridad.addEventListener("change", () => validarCampo(prioridad));
});

function validarCampo(campo) {
    const mensaje = campo.nextElementSibling; // El <span> debajo del campo

    if (campo.value.trim() === "") {
        campo.style.border = "2px solid red";
        mensaje.textContent = "Este campo es obligatorio";
        return false;
    } else {
        campo.style.border = "2px solid green";
        mensaje.textContent = "";
        return true;
    }
}

function cargarTarea() {
    const id = new URLSearchParams(window.location.search).get("id");

    fetch(`/api/tareas/${id}`)
        .then(r => r.json())
        .then(t => {
            document.querySelector("input[name=id]").value = id;

            const titulo = document.querySelector("input[name=titulo]");
            const descripcion = document.querySelector("input[name=descripcion]");
            const prioridad = document.querySelector("select[name=prioridad]");
            const completada = document.querySelector("input[name=completada]");

            titulo.value = t.titulo;
            descripcion.value = t.descripcion;
            prioridad.value = t.prioridad;
            completada.checked = t.completada;

            validarCampo(titulo);
            validarCampo(descripcion);
            validarCampo(prioridad);
        });
}

function guardarCambios() {
    const titulo = document.querySelector("input[name=titulo]");
    const descripcion = document.querySelector("input[name=descripcion]");
    const prioridad = document.querySelector("select[name=prioridad]");

    const ok1 = validarCampo(titulo);
    const ok2 = validarCampo(descripcion);
    const ok3 = validarCampo(prioridad);

    if (!ok1 || !ok2 || !ok3) {
        return;
    }

    const id = document.querySelector("input[name=id]").value;

    const data = {
        titulo: titulo.value,
        descripcion: descripcion.value,
        prioridad: prioridad.value,
        completada: document.querySelector("input[name=completada]").checked
    };

    fetch(`/api/tareas/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }).then(() => window.location.href = "ver.html");
}
