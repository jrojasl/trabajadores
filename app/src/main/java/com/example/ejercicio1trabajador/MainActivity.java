package com.example.ejercicio1trabajador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import clases.Trabajador;

public class MainActivity extends AppCompatActivity {
    Calendar myCalendar = Calendar.getInstance();
    private EditText etNombres;
    private EditText etApellidos;
    private EditText etFechaNacimiento;
    private EditText etFechaIngreso;
    private EditText etSueldoBase;
    private EditText etHorasExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            casteo();
            setListeners(etFechaNacimiento);
            setListeners(etFechaIngreso);
        }

    public void casteo(){
        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etFechaNacimiento = (EditText) findViewById(R.id.etFechaNacimiento);
        etFechaIngreso = (EditText) findViewById(R.id.etFechaIngreso);
        etSueldoBase = (EditText) findViewById(R.id.etSueldoBase);
        etHorasExtras = (EditText) findViewById(R.id.etHorasExtras);
    }

    private void setListeners(EditText etFecha){

        DatePickerDialog.OnDateSetListener dat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizarCampos(etFecha);
            }
        };
        etFecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    new DatePickerDialog(MainActivity.this, dat, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                }

            }
        });

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, dat, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void actualizarCampos(EditText etFecha){
        String formatoDeFecha = "MM/dd/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(formatoDeFecha, Locale.US);
        etFecha.setText(formato.format(myCalendar.getTime()));
    }

    public void calcular(View view){
        if(etNombres.getText().toString().isEmpty() || etApellidos.getText().toString().isEmpty() || etFechaIngreso.getText().toString().isEmpty() ||
                etFechaNacimiento.getText().toString().isEmpty() || etSueldoBase.getText().toString().isEmpty() || etHorasExtras.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_LONG);
            return;
        }

        String nombres = etNombres.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String fechaNacimiento = etFechaNacimiento.getText().toString();
        String fechaIngreso = etFechaIngreso.getText().toString();
        Double sueldoBase = Double.parseDouble(etSueldoBase.getText().toString());
        int horasExtra = Integer.parseInt(etHorasExtras.getText().toString());


        Trabajador trabajador = new Trabajador(nombres, apellidos, fechaNacimiento, fechaIngreso, sueldoBase, horasExtra);
        trabajador.calcularEdad();

        if(trabajador.getEdad() == 0 ){
            Toast.makeText(this, "La fecha de nacimiento no es coherente", Toast.LENGTH_LONG).show();
            return;
        }

        Double pago = trabajador.calcularPago();

        Intent intent = new Intent(MainActivity.this, Resultado.class);
        intent.putExtra( "trabajador", trabajador );
        intent.putExtra( "pago", pago );
        startActivity(intent);

    }
}