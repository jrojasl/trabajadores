package clases;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Trabajador implements Parcelable {

    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String fechaIngreso;
    private Double sueldoBase;
    private int horasExtras;
    private int edad;

    public Trabajador(String nombres, String apellidos, String fechaNacimiento, String fechaIngreso, Double sueldoBase, int horasExtras) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.sueldoBase = sueldoBase;
        this.horasExtras = horasExtras;
    }


    protected Trabajador(Parcel in) {
        nombres = in.readString();
        apellidos = in.readString();
        fechaNacimiento = in.readString();
        fechaIngreso = in.readString();
        if (in.readByte() == 0) {
            sueldoBase = null;
        } else {
            sueldoBase = in.readDouble();
        }
        horasExtras = in.readInt();
        edad = in.readInt();
    }

    public static final Creator<Trabajador> CREATOR = new Creator<Trabajador>() {
        @Override
        public Trabajador createFromParcel(Parcel in) {
            return new Trabajador(in);
        }

        @Override
        public Trabajador[] newArray(int size) {
            return new Trabajador[size];
        }
    };

    public void calcularEdad(){
        String[] fecha = fechaNacimiento.split("/");
        String month = fecha[0];
        String day = fecha[1];
        String year = fecha[2];

        Log.d("fecha", year);

        Calendar calendar = Calendar.getInstance();
        String formatoDeFecha = "MM/dd/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(formatoDeFecha, Locale.US);
        String fechaActual = formato.format(calendar.getTime());

        String[] fecha2 = fechaActual.split("/");
        String month2 = fecha2[0];
        String day2 = fecha2[1];
        String year2 = fecha2[2];

        int mesNacimiento = Integer.parseInt(month);
        int mesActual = Integer.parseInt(month2);

        int diaNacimiento = Integer.parseInt(day);
        int diaActual = Integer.parseInt(day2);

        int anioNacimiento = Integer.parseInt(year);
        int anioActual = Integer.parseInt(year2);
        int edad = anioActual - anioNacimiento;

        if(edad < 14){
            this.setEdad(0);
            return;
        }

        if( mesNacimiento == mesActual ){
            if(diaNacimiento > diaActual ){
                edad = edad - 1;
            }
        }else if (mesNacimiento > mesActual){
                edad = edad - 1;
        }

        setEdad(edad);
    }

    public Double calcularPago(){

        Double pago;

        pago = sueldoBase + (horasExtras * (sueldoBase * 0.02));

        return pago;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(Double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombres);
        dest.writeString(apellidos);
        dest.writeString(fechaNacimiento);
        dest.writeString(fechaIngreso);
        if (sueldoBase == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(sueldoBase);
        }
        dest.writeInt(horasExtras);
        dest.writeInt(edad);
    }
}
