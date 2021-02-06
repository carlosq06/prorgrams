package com.example.covid19finderv17.bdd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface CFinder_Dao {
    //onConflictStrategy evita que si se ingresa un valor repetido, simplemente se ignore
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void regUsuario(e_Usuarios user);

    @Update
    void actualizarUser(e_Usuarios user);

    @Delete
    void borrarUser(e_Usuarios user);

    @Query("SELECT * FROM Usuarios WHERE u_nombres LIKE :nom_busq")
    e_Usuarios consultaUsuarios(String nom_busq);

    @Query("SELECT * FROM Usuarios ORDER BY id ASC")
    List<e_Usuarios> getAlphabetizedUser();

    @Query("SELECT * FROM Usuarios")
    List<e_Usuarios> getAll();
}
