package com.cibertec.examen.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cibertec.examen.UsuarioActivity;
import com.cibertec.examen.R;
import com.cibertec.examen.entidad.Usuario;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> users;

    public UsuarioAdapter(Context context, int resource, List<Usuario> users) {
        super(context, resource, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(final int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list, parent, false);


        TextView txtId = (TextView) rowView.findViewById(R.id.txtListViewID);
        TextView txtNombre = (TextView) rowView.findViewById(R.id.txtListViewName);

        txtId.setText(String.format("#ID: %d", users.get(pos).getIdusuario()));
        txtNombre.setText(String.format("NOMBRE: %s", users.get(pos).getNombre()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UsuarioActivity.class);
                intent.putExtra("var_id", String.valueOf(users.get(pos).getIdusuario()));
                intent.putExtra("var_nombre", users.get(pos).getNombre());
                intent.putExtra("var_apellido", users.get(pos).getApellido());
                intent.putExtra("var_dni", users.get(pos).getDni());
                intent.putExtra("var_login", users.get(pos).getLogin());
                intent.putExtra("var_password", users.get(pos).getPassword());
                intent.putExtra("var_metodo", "VER");
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
