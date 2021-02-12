package com.example.ejercicio1trabajador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import clases.Trabajador;

public class Resultado extends AppCompatActivity {

    private TextView tvNombres;
    private TextView tvEdad;
    private TextView tvSueldoBase;
    private TextView tvHorasExtras;
    private TextView tvFechaIngreso;
    private TextView tvFechaNac;
    private TextView tvResultado;
    private Trabajador trabajador;
    private Double pago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        trabajador = getIntent().getParcelableExtra("trabajador");
        Bundle recoge_datos = getIntent().getExtras();
        pago = recoge_datos.getDouble("pago");
        Log.d("Resultado", ""+trabajador.getEdad());
        casteo();
        imprimir();
    }

    public void casteo(){
        tvNombres = (TextView) findViewById(R.id.tvNombreCompleto);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvSueldoBase = (TextView) findViewById(R.id.tvSueldoBase);
        tvHorasExtras = (TextView) findViewById(R.id.tvHorasExtras);
        tvFechaIngreso = (TextView) findViewById(R.id.tvFechaIngreso);
        tvFechaNac = (TextView) findViewById(R.id.tvFechaNac);
        tvResultado = (TextView) findViewById(R.id.tvTotal);
    }

    public void imprimir(){
        tvNombres.setText(trabajador.getNombres()+ " " + trabajador.getApellidos());
        tvEdad.setText("Edad: "+trabajador.getEdad());
        tvSueldoBase.setText("Sueldo: "+ trabajador.getSueldoBase());
        tvHorasExtras.setText("Horas Extras: "+trabajador.getHorasExtras());
        tvFechaIngreso.setText("Fecha ingreso: "+trabajador.getFechaIngreso());
        tvFechaNac.setText("Tú cumpleaños: "+trabajador.getFechaNacimiento());
        tvResultado.setText("Total Pago: "+pago);
    }

    public void volver(View view){
        startActivity(new Intent(Resultado.this, MainActivity.class));
    }
}