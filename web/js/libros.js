const style = document.createElement('style');
style.textContent = `
    .download-btn {
        border: 2px solid #5A7D9A;
        background-color: white;
        width: 50px;
        height: 50px;
        border-radius: 10px;
        position: relative;
        z-index: 1;
        transition: all 0.2s ease;
        cursor: pointer;
        display: inline-flex; /* Para centrar el SVG */
        justify-content: center; /* Centrar horizontalmente */
        align-items: center; /* Centrar verticalmente */
        text-decoration: none; /* Eliminar el subrayado */
    }
    .download-btn svg {
        width: 25px;
        height: 25px;
        transition: all 0.3s ease;
    }
    .download-btn:hover svg {
        fill: white;
    }
    .download-btn:hover {
        background-color: #5A7D9A;
    }
`;
document.head.appendChild(style);


async function saveLibro() {
    const titulo = document.getElementById("nombreLibros").value;
    const autor = document.getElementById("autor").value;
    const universidad = document.getElementById("universidad").value;
    const genero = document.getElementById("genero").value;
    const estatus = document.getElementById("estatus").value;
    const archivoInput = document.getElementById("pdf");

    if (!archivoInput.files[0]) {
        Swal.fire('Error', 'Por favor selecciona un archivo PDF.', 'warning');
        return;
    }

    const archivoBase64 = await convertirArchivoABase64(archivoInput.files[0]);

    let libro = {
        titulo: titulo,
        autor: autor,
        universidad: universidad,
        genero: genero,
        estatus: estatus,
        pdf: archivoBase64
    };

    let url = "http://localhost:8080/ProyectoAS/api/libros/save";
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
        Swal.fire('Error al guardar los datos del libro.', datos.error, 'warning');
        return;
    }

    Swal.fire('Movimiento Realizado', 'Libro guardado correctamente.', 'success');

    cargarLibros();
}


function convertirArchivoABase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
        reader.readAsDataURL(file);
    });
}


function cargarLibros() {
    fetch(`http://localhost:8080/ProyectoAS/api/libros/getAll`, {
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
                    data.forEach(libros => {
                        const fila = document.createElement('tr');
                        fila.innerHTML = `
                    <td>${libros.titulo}</td>
                    <td>${libros.autor}</td>
                    <td>${libros.universidad}</td>
                    <td>${libros.genero}</td>
                    <td>${libros.estatus}</td>
                    <td>
                    <a href="${libros.pdf}" target="_blank" class="download-btn">
                      <svg
                        id="download"
                        viewBox="0 0 24 24"
                        data-name="Layer 1"
                        xmlns="http://www.w3.org/2000/svg"
                      >
                        <path
                          d="M14.29,17.29,13,18.59V13a1,1,0,0,0-2,0v5.59l-1.29-1.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,.33.21.94.94,0,0,0,.76,0,1,1,0,0,0,.33-.21l3-3a1,1,0,0,0-1.42-1.42ZM18.42,6.22A7,7,0,0,0,5.06,8.11,4,4,0,0,0,6,16a1,1,0,0,0,0-2,2,2,0,0,1,0-4A1,1,0,0,0,7,9a5,5,0,0,1,9.73-1.61,1,1,0,0,0,.78.67,3,3,0,0,1,.24,5.84,1,1,0,1,0,.5,1.94,5,5,0,0,0,.17-9.62Z"
                        ></path>
                      </svg>
                    </a>
                  </td>

                <td>
            <button onclick="mostrarFormularioActualizar('${libros.idLibro}', '${libros.titulo}', '${libros.autor}','${libros.universidad}', '${libros.genero}', '${libros.estatus}')">
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

window.onload = cargarLibros;

function mostrarFormularioActualizar(idLibro, titulo, autor, universidad, genero, estatus) {
    document.getElementById("id").value = idLibro;
    document.getElementById("nombreLibros").value = titulo;
    document.getElementById("autor").value = autor;
    document.getElementById("universidad").value = universidad;
    document.getElementById("genero").value = genero;
    document.getElementById("estatus").value = estatus;

    document.getElementById("bookForm").scrollIntoView();
}

async function updateLibro() {
    const id = document.getElementById("id").value;  // Se asume que el campo `id` está en el formulario
    const titulo = document.getElementById("nombreLibros").value;
    const autor = document.getElementById("autor").value;
    const universidad = document.getElementById("universidad").value;
    const genero = document.getElementById("genero").value;
    const estatus = document.getElementById("estatus").value;
    const archivoInput = document.getElementById("pdf");

    if (!archivoInput.files[0]) {
        Swal.fire('Error', 'Por favor selecciona un archivo PDF.', 'warning');
        return;
    }

    const archivoBase64 = await convertirArchivoABase64(archivoInput.files[0]);

    let libro = {
        idLibro: id,
        titulo: titulo,
        autor: autor,
        universidad: universidad,
        genero: genero,
        estatus: estatus,
        pdf: archivoBase64
    };

    let url = "http://localhost:8080/ProyectoAS/api/libros/update";
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

    cargarLibros();  // Recargar la lista de libros
}

// Función auxiliar para convertir archivos a Base64
function convertirArchivoABase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
        reader.readAsDataURL(file);
    });
}


function toBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
}

async function convertirArchivoABase64(archivo) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onloadend = () => resolve(reader.result);
        reader.onerror = reject;
        reader.readAsDataURL(archivo);
    });
}
