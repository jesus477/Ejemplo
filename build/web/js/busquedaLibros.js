// Agrega el estilo existente
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
        display: inline-flex;
        justify-content: center;
        align-items: center;
        text-decoration: none;
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

function cargarLibros() {
    fetch(`http://localhost:8080/ProyectoAS/api/libros/getAllViewModel`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => response.json())
    .then(data => {
        mostrarLibros(data);
    })
    .catch(error => {
        console.error('Error:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'No se pudieron cargar los libros.',
        });
    });
}

function mostrarLibros(data) {
    const tbody = document.querySelector('#tablaUsuarios tbody');
    tbody.innerHTML = '';

    if (data.length > 0) {
        data.forEach(libros => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${libros.titulo}</td>
                <td>${libros.universidad}</td>
                <td>${libros.genero}</td>
                <td>
                    <a href="${libros.pdf}" target="_blank" class="download-btn">
                        <svg id="download" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="M14.29,17.29,13,18.59V13a1,1,0,0,0-2,0v5.59l-1.29-1.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,.33.21.94.94,0,0,0,.76,0,1,1,0,0,0,.33-.21l3-3a1,1,0,0,0-1.42-1.42ZM18.42,6.22A7,7,0,0,0,5.06,8.11,4,4,0,0,0,6,16a1,1,0,0,0,0-2,2,2,0,0,1,0-4A1,1,0,0,0,7,9a5,5,0,0,1,9.73-1.61,1,1,0,0,0,.78.67,3,3,0,0,1,.24,5.84,1,1,0,1,0,.5,1.94,5,5,0,0,0,.17-9.62Z"></path>
                        </svg>
                    </a>
                </td>
            `;
            tbody.appendChild(fila);
        });
    } else {
        const fila = document.createElement('tr');
        fila.innerHTML = `<td colspan="5">No hay libros para mostrar.</td>`;
        tbody.appendChild(fila);
    }
}

function buscarLibros() {
    const input = document.querySelector('.input__search--variant').value.toLowerCase();

    fetch(`http://localhost:8080/ProyectoAS/api/libros/getAllViewModel`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => response.json())
    .then(data => {
        const resultadoFiltrado = data.filter(libro => 
            libro.titulo.toLowerCase().includes(input) ||
            libro.genero.toLowerCase().includes(input)
        );
        mostrarLibros(resultadoFiltrado);
    })
    .catch(error => {
        console.error('Error:', error);
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'No se pudieron cargar los libros.',
        });
    });
}

document.querySelector('.input__button__shadow--variant').addEventListener('click', buscarLibros);
document.querySelector('.input__search--variant').addEventListener('input', buscarLibros);

window.onload = cargarLibros;
