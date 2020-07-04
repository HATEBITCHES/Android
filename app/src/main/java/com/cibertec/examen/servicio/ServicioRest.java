package com.cibertec.examen.servicio;

import com.cibertec.examen.entidad.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicioRest {

    //Crud de Rol
    @GET("usuario")
    public abstract Call<List<Usuario>> listaUsuario();

    @POST("usuario")
    public abstract Call<Usuario> agregaUsuario(@Body Usuario user);

    @PUT("usuario")
    public abstract Call<Usuario> actualizaUsuario(@Body Usuario user);

    @DELETE("usuario/{idusuario}")
    public abstract Call<Usuario> eliminaUsuario(@Path("idusuario") int id);

}
