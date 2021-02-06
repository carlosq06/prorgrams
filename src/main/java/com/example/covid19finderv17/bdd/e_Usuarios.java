package com.example.covid19finderv17.bdd;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usuarios")
public class e_Usuarios {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String u_nombres;
    private String u_apellidos;
    private String f_nacimiento;
    private String sexo;
    private String enf_cronic;
    private String discapacidad;
    private String f_sintomas;
    private String pcr;
    private double lat;
    private double lng;

    //Constructor sin el atributo primary key
    public e_Usuarios(String u_nombres, String u_apellidos, String f_nacimiento, String sexo
            ,String enf_cronic, String discapacidad, String f_sintomas, String pcr, double lat, double lng) {
        this.u_nombres = u_nombres;
        this.u_apellidos = u_apellidos;
        this.f_nacimiento = f_nacimiento;
        this.sexo = sexo;
        this.enf_cronic = enf_cronic;
        this.discapacidad = discapacidad;
        this.f_sintomas = f_sintomas;
        this.pcr = pcr;
        this.lat = lat;
        this.lng = lng;
    }

    //getters para acceder a los atributos

    public int getU_id() {
        return id;
    }

    public String getU_nombres() {
        return u_nombres;
    }

    public String getU_apellidos() {
        return u_apellidos;
    }

    public String getF_nacimiento() {
        return f_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEnf_cronic() {
        return enf_cronic;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public String getF_sintomas() {
        return f_sintomas;
    }

    public String getPcr() {
        return pcr;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
