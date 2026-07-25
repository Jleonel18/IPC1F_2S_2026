# IPC1F_2S_2026
Repositorio de Ejemplos de Segundo Semestre de IPC1

Guía básica de Git trabajando en la rama main
1. Requisitos previos
Antes de comenzar, los estudiantes deben:

Tener Git instalado.
Tener una cuenta en GitHub.
Haber creado un repositorio en GitHub (vacío o con README).
Haber generado un Personal Access Token (PAT) en GitHub.
2. Autenticación con Token (Personal Access Token)
GitHub ya no permite usar contraseña al hacer push o pull.

Se debe usar un Personal Access Token.

Crear un token en GitHub
Ir a GitHub.
Settings.
Developer Settings.
Personal Access Tokens.
Tokens (classic) → Generate new token.
Dar permisos al menos de tipo repo.
Generar y copiar el token (solo se muestra una vez).
Guárdalo en un lugar seguro.

3. Configuración inicial (solo se hace una vez por computadora)
Configurar nombre y correo:

git config --global user.name"Tu Nombre"
git config --global user.email"tuemail@example.com"
Opcional: guardar credenciales para no pegar el token cada vez:

git config --global credential.helper store
4. Clonar un repositorio
Este paso se hace una sola vez.

git clone https://github.com/usuario/repositorio.git
Entrar a la carpeta:

cd repositorio
Verificar que estamos en main:

git branch
Si no está en main:

git checkout main
5. Ver el estado del repositorio
git status
Este comando muestra:

Archivos modificados
Archivos nuevos
Archivos listos para commit
Rama actual
Es el comando más importante para entender qué está pasando.

6. Agregar archivos al área de preparación
Agregar un archivo específico:

git add archivo.js
Agregar todos los cambios:

git add .
Esto no guarda el cambio en GitHub, solo lo prepara para el commit.

7. Crear un commit
git commit -m"Mensaje descriptivo del cambio"
Ejemplo:

git commit -m"Agrega validación al formulario de registro"
El commit guarda los cambios localmente, aún no están en GitHub.

8. Traer cambios del repositorio remoto
Antes de subir cambios, es buena práctica ejecutar:

git pull origin main
Esto descarga los cambios más recientes del repositorio remoto.

Si hay conflictos, deben resolverse antes de continuar.

9. Subir cambios a GitHub
git push origin main
Cuando lo solicite:

Username: tu usuario de GitHub
Password: pega el token
Si configuraste credential.helper store, solo lo pedirá una vez.

10. Flujo de trabajo recomendado en main
Cada vez que trabajen:

git status
git add .
git commit -m"Descripción del cambio"
git pull origin main
git push origin main
Orden recomendado:

Ver estado.
Agregar cambios.
Hacer commit.
Hacer pull.
Hacer push.
11. Error común al hacer push
Si aparece:

error: failedto push some refs
Significa que el repositorio remoto tiene cambios que tú no tienes.

Solución:

git pull origin main
Resolver conflictos si aparecen, luego:

git push origin main
Resumen conceptual:
clone: descarga el proyecto por primera vez.
status: muestra qué cambió.
add: prepara cambios.
commit: guarda cambios localmente.
pull: trae cambios del servidor.
push: sube cambios al servidor.
