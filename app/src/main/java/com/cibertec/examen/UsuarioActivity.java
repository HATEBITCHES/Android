package com.cibertec.examen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.cibertec.examen.entidad.Usuario;
import com.cibertec.examen.servicio.ServicioRest;
import com.cibertec.examen.util.ConnectionRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioActivity extends AppCompatActivity {

    ServicioRest servicio;
    EditText edtUId, edtNombre,edtApellido,edtDni,edtLogin,edtPassword;
    Button btnSave;
    Button btnDel;
    TextView txtUId;
    final String metodo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        setTitle("CRUD de Rol");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtIdUsuario);
        edtUId = (EditText) findViewById(R.id.edtIdUsuario);
        edtNombre = (EditText) findViewById(R.id.edtUsuarioNombre);
        edtApellido = (EditText) findViewById(R.id.edtUsuarioApellido);
        edtDni = (EditText) findViewById(R.id.edtUsuarioDni);
        edtLogin = (EditText) findViewById(R.id.edtUsuarioLogin);
        edtPassword = (EditText) findViewById(R.id.edtUsuarioPassword);
        btnSave = (Button) findViewById(R.id.btnPaisSave);
        btnDel = (Button) findViewById(R.id.btnPaisDel);

        servicio = ConnectionRest.getConnection().create(ServicioRest.class);
        Bundle extras = getIntent().getExtras();
        final String metodo = extras.getString("var_metodo");
        final String var_id = extras.getString("var_id");

        if (metodo.equals("VER")) {
            String var_nombre = extras.getString("var_nombre");
            String var_apellido = extras.getString("var_apellido");
            String var_dni = extras.getString("var_dni");
            String var_login = extras.getString("var_login");
            String var_password = extras.getString("var_password");

            edtUId.setText(var_id);
            edtNombre.setText(var_nombre);
            edtApellido.setText(var_apellido);
            edtDni.setText(var_dni);
            edtLogin.setText(var_login);
            edtPassword.setText(var_password);
            edtUId.setFocusable(false);
        }else if (metodo.equals("REGISTRAR")) {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario u = new Usuario();
                u.setNombre(edtNombre.getText().toString());
                u.setApellido(edtApellido.getText().toString());
                u.setDni(edtDni.getText().toString());
                u.setLogin(edtLogin.getText().toString());
                u.setPassword(edtPassword.getText().toString());
                if (metodo.equals("VER")) {
                    u.setIdusuario(Integer.parseInt(var_id));
                    mensaje("Se pulsó  actualizar");
                    update(u);
                } else if (metodo.equals("REGISTRAR")) {
                    mensaje("Se pulsó agregar");
                    add(u);
                }

                Intent intent = new Intent(UsuarioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó eliminar");
                delete(Integer.parseInt(var_id));
                Intent intent = new Intent(UsuarioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add(Usuario u) {
        Call<Usuario> call = servicio.agregaUsuario(u);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    mensaje("Registro exitoso");
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void update(Usuario u) {
        Call<Usuario> call = servicio.actualizaUsuario(u);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    mensaje("Actualización exitosa");
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                 Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void delete(int id) {
        Call<Usuario> call = servicio.eliminaUsuario(id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    mensaje("Eliminación exitosa");
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void mensaje(String msg) {
        Toast toast1 = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast1.show();
    }

}
