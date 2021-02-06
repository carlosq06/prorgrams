package com.example.covid19finderv17.bdd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {e_Usuarios.class},version = 3, exportSchema = false)
public abstract class C19Finder_bdd extends RoomDatabase {
    //instanciamos el dao desde el cual se accedera a las consultas
    public abstract CFinder_Dao daoC19();
}
