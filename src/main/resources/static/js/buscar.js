function buscar() {
    const query = document.querySelector("input[name=query]").value;

    fetch(`/api/tareas/buscar?query=${query}`)
        .then(r => r.json())
        .then(tareas => {
            const cont = document.querySelector(".window");
            cont.innerHTML = "<h3>Resultados:</h3>";

            tareas.forEach(t => {
                cont.innerHTML += `
                    <div class="solicitud-card">
                        <h4>${t.titulo}</h4>
                        <p>${t.descripcion}</p>
                        <p>Prioridad: <b>${t.prioridad}</b></p>
                    </div>`;
            });
        });
}
