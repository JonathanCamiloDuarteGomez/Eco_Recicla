package com.example.eco_recicla.back;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class UserManager {
    // Nombre del archivo de preferencias compartidas
    private static final String PREF_NAME = "UserData";

    // Claves utilizadas para almacenar y recuperar datos
    private static final String USER_KEY = "user";
    private static final String FACTURAS_KEY = "facturas";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

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
        String json = gson.toJson(usuario);
        editor.putString(USER_KEY, json);
        editor.putString(KEY_EMAIL, usuario.getEmail());
        editor.putString(KEY_PASSWORD, usuario.getPassword());
        editor.apply();
    }

    // Método para iniciar sesión
    public boolean loginUser(String email, String password) {
        String registeredEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String registeredPassword = sharedPreferences.getString(KEY_PASSWORD, null);
        return email.equals(registeredEmail) && password.equals(registeredPassword);
    }

    // Método para guardar un usuario
    public void saveUsuario(Usuario usuario) {
        String json = gson.toJson(usuario);
        editor.putString(USER_KEY, json);
        editor.apply();
    }

    // Método para recuperar un usuario
    public  Usuario getUsuario() {
        String json = sharedPreferences.getString(USER_KEY, "");
        return gson.fromJson(json, Usuario.class);
    }

    // Método para guardar facturas
    public void saveFacturas(List<Factura> facturas) {
        String json = gson.toJson(facturas);
        editor.putString(FACTURAS_KEY, json);
        editor.apply();
    }

    // Método para recuperar facturas
    public List<Factura> getFacturas() {
        String json = sharedPreferences.getString(FACTURAS_KEY, "");
        Type type = new TypeToken<List<Factura>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Método para cerrar sesión
    public void logoutUser() {
        editor.remove(USER_KEY);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_PASSWORD);
        editor.remove(FACTURAS_KEY);
        editor.apply();
    }
}
