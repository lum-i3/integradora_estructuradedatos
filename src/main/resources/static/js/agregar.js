function guardar() {
    const data = {
        titulo: document.querySelector("input[name=titulo]").value,
        descripcion: document.querySelector("input[name=descripcion]").value,
        prioridad: document.querySelector("select[name=prioridad]").value
    };

    fetch("/api/tareas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }).then(() => window.location.href = "ver.html");
}
