function login() {
    const usuario = document.getElementById('txtUsuario').value;
    const contrasenia = document.getElementById('txtContrasenia').value;
    const perfil = document.getElementById('txtPerfil').value;

    if (usuario && contrasenia && perfil) {
        fetch(`http://localhost:8080/ProyectoAS/api/login/validar?nombreUsuario=${usuario}&contrasenia=${contrasenia}&perfil=${perfil}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(response => response.json())
        .then(data => {
            if (data.error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Datos de acceso incorrectos',
                });
            } else {
                localStorage.setItem('perfil', perfil);

                Swal.fire({
                    icon: 'success',
                    title: 'Acceso correcto',
                    text: `Bienvenido`,
                }).then(() => {
                    if (perfil === 'Alumno') {
                        window.location.href = 'admin/busquedaLibros.html';
                    } else if (perfil === 'Administrador') {
                        window.location.href = 'admin/catalogoUsuarios.html';
                    } else if (perfil === 'Bibliotecario') {
                        window.location.href = 'admin/catalogoLibros.html';
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Perfil no reconocido',
                        });
                    }
                });
            }
        })
        .catch(error => {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Ocurrió un error durante la conexión',
            });
            console.error('Error:', error);
        });
    } else {
        Swal.fire({
            icon: 'warning',
            title: 'Advertencia',
            text: 'Por favor, ingrese usuario, contraseña y perfil',
        });
    }
}
