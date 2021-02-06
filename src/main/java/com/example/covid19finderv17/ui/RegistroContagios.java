package com.example.covid19finderv17.ui;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.covid19finderv17.MainActivity;
import com.example.covid19finderv17.R;
import com.example.covid19finderv17.bdd.C19Finder_bdd;
import com.example.covid19finderv17.bdd.e_Usuarios;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class RegistroContagios extends Fragment {

    public EditText nombres, apellidos;
    public EditText f_nac, f_sintomas;
    public RadioButton rb_pcr_si, rb_pcr_no, rb_masculino, rb_femenino, rb_condicion_si,
            rb_condicion_no, rb_discapacidad_si, rb_discapacidad_no;
    public Button bt_reg;
    public double lat;
    public double lng;

    //Instanciamos las clases para el uso de BDD

    private MapsFragment mMaps = new MapsFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_contagios, container, false);

        //instancia de objetos text, fecha de sintomas y edad
        nombres = (EditText)view.findViewById(R.id.et_nombre);
        apellidos = (EditText)view.findViewById(R.id.et_apellido);
        f_nac = (EditText)view.findViewById(R.id.et_fnacimiento);
        f_sintomas = (EditText)view.findViewById(R.id.dt_inicioSintomas);
        rb_masculino = (RadioButton)view.findViewById(R.id.rbt_sexo_mascu);
        rb_femenino = (RadioButton)view.findViewById(R.id.rbt_sexo_fem);
        rb_discapacidad_si = (RadioButton)view.findViewById(R.id.rbt_discapacidad_si);
        rb_discapacidad_no = (RadioButton)view.findViewById(R.id.rbt_discapacidad_no);
        rb_condicion_no = (RadioButton)view.findViewById(R.id.rbt_condicion_no);
        rb_condicion_si = (RadioButton)view.findViewById(R.id.rbt_condicion_si);
        rb_pcr_si = (RadioButton)view.findViewById(R.id.rbt_pcr_si);
        rb_pcr_no = (RadioButton)view.findViewById(R.id.rbt_pcr_no);

        bt_reg = (Button)view.findViewById(R.id.bt_registro);
        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double m = mMaps.getLat();
                nombres.setText(String.valueOf(m));
                Toast.makeText(getContext(),"Guardado correactamente",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    //obtiene un objeto usuario
    public void obtenerDatos(){

        //Asigna el valor obtenida de la interface a los objetos definidos
        String nom = nombres.getText().toString();
        String apell = apellidos.getText().toString();
        String fech_nac = f_nac.getText().toString();
        String fech_sint = f_sintomas.getText().toString();

        //mediantes una condicional determinamos el valor de los botones
        String genero="indefinido";
        if(rb_masculino.isChecked()){
             genero = "Masculino";
        }else{
            if(rb_femenino.isChecked()){
                genero = "Femenino";
            }
        }

        String discapacidad="indefinido";
        if(rb_discapacidad_si.isChecked()){
            discapacidad = "Si";
        }else {
            if(rb_discapacidad_no.isChecked()){
                discapacidad = "No";
            }
        }


        String cond_cronica="indefinido";
        if(rb_condicion_si.isChecked()){
            cond_cronica = "Si";
        }else{
            if(rb_condicion_no.isChecked()){
                cond_cronica = "No";
            }
        }

        String pcr="indefinido";
        if(!rb_pcr_si.isChecked()){
            pcr = "Si";
        }else{
            if(rb_pcr_no.isChecked()){
                pcr = "No";
            }
        }

        double lat = mMaps.getLat();
        double lng = mMaps.getLng();

        //se crea el registro del usuario
        e_Usuarios user = new e_Usuarios(nom,apell,fech_nac,genero,cond_cronica,discapacidad,fech_sint,pcr,lat,lng);
        //devolvemos el objeto con toda la informacion
        C19Finder_bdd bd = Room.databaseBuilder(getContext(),C19Finder_bdd.class, "BD-UserNew").allowMainThreadQueries().build();
        bd.daoC19().regUsuario(user);
        Toast.makeText(getContext(),"Guardado exitoso",Toast.LENGTH_LONG).show();
    }

    private  boolean inputCheck(String nom, String ape,String date){
        return !(TextUtils.isEmpty(nom)&&TextUtils.isEmpty(ape)&& TextUtils.isEmpty(date));
    }

}
