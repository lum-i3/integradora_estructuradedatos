function ordenar(t) {
    fetch(`/api/tareas/ordenar?tipo=${t}`)
        .then(r => r.json())
        .then(tareas => {
            const cont = document.querySelector(".window");
            cont.innerHTML = "";
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
