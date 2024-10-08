package com.example.eco_recicla.back;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    // Nombre del archivo de preferencias compartidas
    private static final String PREF_NAME = "UserData";
    private static final String USERS_KEY = "users";
    private static final String CURRENT_USER_EMAIL = "current_user_email";
    private static final String NFACTURA_KEY = "nFactura1";

    // Claves utilizadas para almacenar y recuperar datos
    private static final String FACTURAS_KEY = "facturas";

    // Variables de instancia
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    // Constructor
    public UserManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
        this.editor = sharedPreferences.edit();
    }

    // Método para registrar un usuario
    public void registerUser(Usuario usuario) {
        // Recuperar el mapa de usuarios existente
        Map<String, Usuario> users = getUsers();
        // Añadir el nuevo usuario al mapa
        users.put(usuario.getEmail(), usuario);
        // Guardar el mapa de usuarios actualizado
        saveUsers(users);
        // Establecer el correo electrónico del usuario actual
        setCurrentUserEmail(usuario.getEmail());
    }

    // Método para iniciar sesión de usuario
    public boolean loginUser(String email, String password) {
        // Recuperar el mapa de usuarios
        Map<String, Usuario> users = getUsers();
        // Buscar el usuario por correo electrónico
        Usuario usuario = users.get(email);
        // Verificar si el usuario existe y si la contraseña coincide
        if (usuario != null && usuario.getPassword().equals(password)) {
            // Establecer el correo electrónico del usuario actual
            setCurrentUserEmail(email);
            return true;
        }
        return false;
    }

    // Método para obtener el usuario actual
    public Usuario getUsuario() {
        // Obtener el correo electrónico del usuario actual
        String email = getCurrentUserEmail();
        if (email != null) {
            // Recuperar el mapa de usuarios
            Map<String, Usuario> users = getUsers();
            // Devolver el usuario correspondiente al correo electrónico
            return users.get(email);
        }
        return null;
    }

    // Método para recuperar el mapa de usuarios
    private Map<String, Usuario> getUsers() {
        // Obtener la cadena JSON del mapa de usuarios
        String json = sharedPreferences.getString(USERS_KEY, "");
        Type type = new TypeToken<Map<String, Usuario>>() {}.getType();
        // Convertir la cadena JSON a un mapa de usuarios
        return gson.fromJson(json, type) != null ? gson.fromJson(json, type) : new HashMap<>();
    }

    // Método para guardar el mapa de usuarios
    private void saveUsers(Map<String, Usuario> users) {
        // Convertir el mapa de usuarios a una cadena JSON
        String json = gson.toJson(users);
        // Guardar la cadena JSON en preferencias compartidas
        editor.putString(USERS_KEY, json);
        editor.apply();
    }

    // Método para establecer el correo electrónico del usuario actual
    private void setCurrentUserEmail(String email) {
        editor.putString(CURRENT_USER_EMAIL, email);
        editor.apply();
    }

    // Método para obtener el correo electrónico del usuario actual
    private String getCurrentUserEmail() {
        return sharedPreferences.getString(CURRENT_USER_EMAIL, null);
    }

    // Método para cerrar sesión del usuario
    public void logoutUser() {
        editor.remove(CURRENT_USER_EMAIL);
        editor.apply();
    }

    // Método para guardar facturas
    public void saveFacturas(List<Factura> facturas) {
        // Convertir la lista de facturas a una cadena JSON
        String json = gson.toJson(facturas);
        // Guardar la cadena JSON en preferencias compartidas
        editor.putString(FACTURAS_KEY, json);
        editor.commit();
    }
    public void saveFacturasForUser(String email, List<Factura> facturas) {
        // Convertir la lista de facturas a una cadena JSON
        String json = gson.toJson(facturas);
        // Guardar la cadena JSON en preferencias compartidas
        editor.putString(FACTURAS_KEY + email, json);
        editor.commit();
    }


    // Método para recuperar facturas
    public List<Factura> getFacturasForUser(String email) {
        String json = sharedPreferences.getString(FACTURAS_KEY + email, "");
        Type type = new TypeToken<List<Factura>>() {}.getType();
        return gson.fromJson(json, type);
    }
    // Método para guardar el valor de nFactura
    public void saveNFactura(int nFactura) {
        editor.putInt(NFACTURA_KEY, nFactura);
        editor.apply();
    }

    // Método para recuperar el valor de nFactura
    public int getNFactura() {
        return sharedPreferences.getInt(NFACTURA_KEY, 1); // 1 es el valor predeterminado si no existe
    }

    // Método para actualizar la información del usuario actual
    public void updateUser(Usuario updatedUsuario) {
        // Recuperar el correo electrónico del usuario actual
        String email = getCurrentUserEmail();

        if (email != null) {
            // Recuperar el mapa de usuarios
            Map<String, Usuario> users = getUsers();

            // Buscar el usuario actual
            Usuario usuario = users.get(email);

            if (usuario != null) {
                // Actualizar la información del usuario
                usuario.setTelefono(updatedUsuario.getTelefono());
                usuario.setDireccion(updatedUsuario.getDireccion());
                usuario.setDireccionAlternativa(updatedUsuario.getDireccionAlternativa());

                // Guardar el usuario actualizado en el mapa
                users.put(email, usuario);

                // Guardar el mapa de usuarios actualizado en preferencias compartidas
                saveUsers(users);
            }
        }
    }
    //Método para actualizar los Coins del usuario actual
    public void updateCoins(Usuario updatedUsuario) {
        // Recuperar el correo electrónico del usuario actual
        String email = getCurrentUserEmail();

        if (email != null) {
            // Recuperar el mapa de usuarios
            Map<String, Usuario> users = getUsers();

            // Buscar el usuario actual
            Usuario usuario = users.get(email);

            if (usuario != null) {
                // Actualizar la información del usuario
                usuario.setCoins(updatedUsuario.getCoins());

                // Guardar el usuario actualizado en el mapa
                users.put(email, usuario);

                // Guardar el mapa de usuarios actualizado en preferencias compartidas
                saveUsers(users);
            }
        }
    }


}
