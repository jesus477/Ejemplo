async function saveUsuario() {

    const nombreUsuario = document.getElementById("nombreUsuario").value;
    const contrasenia = document.getElementById("password").value;
    const perfil = document.getElementById("perfil").value;

    let usuario = {
        nombreUsuario: nombreUsuario,
        contrasenia: contrasenia,
        perfil: perfil
    };

    let url = "http://localhost:8080/ProyectoAS/api/login/saveUsuario";
    let params = {
        datosUsuario: JSON.stringify(usuario)
    };

    let ctype = 'application/x-www-form-urlencoded;charset=UTF-8';
    let resp = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': ctype
        },
        body: new URLSearchParams(params)
    });

    let datos = await resp.json();
    if (datos.error) {
        Swal.fire('Error al guardar los datos del usuario.', datos.error, 'warning');
        return;
    }

    Swal.fire('Movimiento Realizado', 'Usuario agregado correctamente.', 'success');

    cargarUsuarios();
}

function cargarUsuarios() {
    fetch(`http://localhost:8080/ProyectoAS/api/login/getAllUsuario`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
            .then(response => response.json())
            .then(data => {
                const tbody = document.querySelector('#tablaUsuarios tbody');
                tbody.innerHTML = '';

                if (data.length > 0) {
                    data.forEach(usuarios => {
                        const fila = document.createElement('tr');
                        fila.innerHTML = `
                    <td>${usuarios.nombreUsuario}</td>
                    <td>${usuarios.contrasenia}</td>
                    <td>${usuarios.perfil}</td>
                <td>
            <button onclick="mostrarFormularioActualizar('${usuarios.idUsuario}', '${usuarios.nombreUsuario}', '${usuarios.contrasenia}','${usuarios.perfil}')">
                <i class="fas fa-edit"></i>
            </button>
        </td>
                `;
                        tbody.appendChild(fila);
                    });
                } else {
                    const fila = document.createElement('tr');
                    fila.innerHTML = `<td colspan="5">No hay libros para mostrar.</td>`;
                    tbody.appendChild(fila);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'No se pudieron cargar los usuarios.',
                });
            });
}

window.onload = cargarUsuarios;

function mostrarFormularioActualizar(idUsuario, nombreUsuario, contrasenia, perfil) {
    document.getElementById("idUsuario").value = idUsuario;
    document.getElementById("nombreUsuario").value = nombreUsuario;
    document.getElementById("password").value = contrasenia;
    document.getElementById("perfil").value = perfil;

    document.getElementById("bookForm").scrollIntoView();
}

async function updateUsuario() {
    const idUsuario = document.getElementById("idUsuario").value;  // Se asume que el campo `id` está en el formulario
    const nombreUsuario = document.getElementById("nombreUsuario").value;
    const contrasenia = document.getElementById("password").value;
    const perfil = document.getElementById("perfil").value;

    let libro = {
        idUsuario: idUsuario,
        nombreUsuario: nombreUsuario,
        contrasenia: contrasenia,
        perfil: perfil
    };

    let url = "http://localhost:8080/ProyectoAS/api/login/updateUsuario";
    let params = {
        datosLibro: JSON.stringify(libro)
    };

    let ctype = 'application/x-www-form-urlencoded;charset=UTF-8';
    let resp = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': ctype
        },
        body: new URLSearchParams(params)
    });

    let datos = await resp.json();
    if (datos.error) {
        Swal.fire('Error al actualizar los datos del libro.', datos.error, 'warning');
        return;
    }

    Swal.fire('Movimiento Realizado', 'Libro actualizado correctamente.', 'success');

    cargarUsuario();  // Recargar la lista de libros
}