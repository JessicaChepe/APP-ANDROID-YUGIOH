package com.example.proyecto.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.clases.Hash;
import com.example.proyecto.sqlite.yugioh;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtCorreo, txtClave;
    Button btnIngresar, btnSalir;
    CheckBox chkRecordar;
    TextView lblRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //aqui se hace un llamado a los id de activity_login.xml
        txtCorreo = findViewById(R.id.logtxtCorreo);
        txtClave = findViewById(R.id.logtxtClave);
        btnIngresar = findViewById(R.id.logbtnIngresar);
        btnSalir = findViewById(R.id.logbtnSalir);
        chkRecordar = findViewById(R.id.logchkRecordar);
        lblRegistrar = findViewById(R.id.loglblRegistro);
        //configura el evento on click, tienen eventos
        btnIngresar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        lblRegistrar.setOnClickListener(this);

        //Validar si se recordo sesion
        yugioh yugioh = new yugioh(getApplicationContext());
        if(yugioh.recordoUsuario()){
            iniciarSesion(yugioh.buscarCampo("CORREO"), yugioh.buscarCampo("CLAVE"), true);

        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.logbtnIngresar:
                iniciarSesion(txtCorreo.getText().toString(),txtClave.getText().toString(), false);
                break;
            case R.id.logbtnSalir:
                salir();
                break;
            case R.id.loglblRegistro:
                registrar();
                break;
        }



    }

    private void salir() {
        //limpiar temporales o cerrar archivos abiertos
        //validar si hay algo pendiente
        System.exit(0);
    }

    private void iniciarSesion(String correo, String clave, boolean recordo) {

        Hash hash = new Hash();
        clave = recordo == true ? clave : hash.StringToHash(clave, "SHA1");

        if(correo.equals("cuto@upn.edu.pe") && clave.equals("e05adfb80b6cc009a848215b506ae63f6fd05b97")){
            Intent iBienvenida = new Intent(this, BienvenidaActivity.class);
            iBienvenida.putExtra("nombre","Cuto");
            if(chkRecordar.isChecked()){
                yugioh yugioh = new yugioh(getApplicationContext());
                yugioh.agregarUsuario(1, correo, clave);
            }
            startActivity(iBienvenida);
        }else{
            Toast.makeText(this,"Usuario o clave incorrecta", Toast.LENGTH_SHORT).show();
        }
    }



    private void registrar() {
        Intent iRegistro = new Intent(this, RegistroActivity.class);
        startActivity(iRegistro);
    }
}