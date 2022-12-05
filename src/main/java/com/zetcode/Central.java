package com.zetcode;

public class Central {
    public void iniciarSesion(String nombre, String password){

    }

    public void crearUsuario(String nombre, String correo, String password, String repetirPassword){

    }

    public void recuperarContrasena(String correo){

    }

    public void actualizarDatos(){

    }

    public void guardarPartida(int codUsuario, int codPartida){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        if(user.getEnJuego().getCodPartida() == codPartida) {
            PartidaGuardada partidaCreada = GestorUsuarios.getInstance().crearPartidaGuardada(user.getEnJuego(), user);
            GestorUsuarios.getInstance().anadirPartidaGuardada(user, partidaCreada);
            //FALTA LO DEL SGBD
        }
        user.partidasGuardadasUsuario();
    }

    public void cargarPartida(int codUsuario, int codPartida, String fechaHora){

    }

    public boolean obtPersonalizacion(int codUsu){
        return false;
    }
}
