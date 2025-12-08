document.addEventListener("DOMContentLoaded", () => {
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

function guardar() {
    const titulo = document.querySelector("input[name=titulo]");
    const descripcion = document.querySelector("input[name=descripcion]");
    const prioridad = document.querySelector("select[name=prioridad]");

    const ok1 = validarCampo(titulo);
    const ok2 = validarCampo(descripcion);
    const ok3 = validarCampo(prioridad);

    // Bloquea el envío sin alertas
    if (!ok1 || !ok2 || !ok3) {
        return;
    }

    const data = {
        titulo: titulo.value,
        descripcion: descripcion.value,
        prioridad: prioridad.value
    };

    fetch("/api/tareas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }).then(() => window.location.href = "ver.html");
}
