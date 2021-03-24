package com.example.recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VM:ViewModel() {
    val listaJuegos: MutableList<Juego> = mutableListOf()

    init{
        cargarJuegos()
    }

    fun cargarJuegos(){

        listaJuegos.add(Juego("Oca",4,"Conseguir llegar a la meta"))
        listaJuegos.add(Juego("parchis",4,"Conseguir llevar tus cuatro fichas a casa sin que el resto te coma"))
        listaJuegos.add(Juego("hotel",4,"Ir comprando terrenos, edificios y cobrando al resto de jugadores hasta que no puedan pagarte"))
        listaJuegos.add(Juego("sushi go",4,"Durante tres rondas ir consiguiendo puntos para ganar la mayor puntuación"))

    }

    fun insertar(juego:Juego){
        listaJuegos.add(juego)
    }

    fun borrar(posicion:Int){
        listaJuegos.removeAt(posicion)
    }

    fun modificar(juego:Juego, posicion:Int){
        listaJuegos[posicion].nombre=juego.nombre
        listaJuegos[posicion].numJugadores=juego.numJugadores
        listaJuegos[posicion].objetivo=juego.objetivo
    }


}