package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

class ThirdFragment : Fragment() {

    var posicion:Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        val bInsertar=view.findViewById<Button>(R.id.frag2_bInsertar)
        val bBorrar=view.findViewById<Button>(R.id.frag2_bBorrar)
        val bModificar=view.findViewById<Button>(R.id.frag2_bModificar)
        val etNombre=view.findViewById<EditText>(R.id.frag2_etNombre)
        val etJugadores=view.findViewById<EditText>(R.id.frag2_etJugadores)
        val etObjetivo=view.findViewById<EditText>(R.id.frag2_etObjetivos)
        val tvId=view.findViewById<TextView>(R.id.frag2_tvId)
        posicion=arguments?.getInt("id") ?:-1
        var juego:Juego=Juego("",0,"")

        if(posicion==-1){
            bBorrar.isEnabled=false
            bModificar.isEnabled=false
            bInsertar.isEnabled=true
            activity?.setTitle("Insertar juegos")
        }
        else{
            bBorrar.isEnabled=true
            bModificar.isEnabled=true
            bInsertar.isEnabled=false
            activity?.setTitle("Modificar/Borrar juegos")
            juego=(activity as MainActivity).miViewModel.listaJuegos[posicion]
            Log.d("tercero",juego.nombre)
            tvId.text=String.format("ID: $posicion")
            etNombre.setText(juego.nombre)
            etJugadores.setText(juego.numJugadores.toString())
            etObjetivo.setText(juego.objetivo)
        }


        bInsertar.setOnClickListener {
            if(etNombre.text.isEmpty() || etJugadores.text.isEmpty() || etObjetivo.text.isEmpty()) Toast.makeText(activity,"Tienes que insertar todos los datos",
                Toast.LENGTH_SHORT).show()
            else{
                (activity as MainActivity).miViewModel.insertar(Juego(etNombre.text.toString(),etJugadores.text.toString().toInt(),etObjetivo.text.toString()))
                findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
            }
        }

        bBorrar.setOnClickListener {
                (activity as MainActivity).miViewModel.borrar(posicion)
                findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
        }

        bModificar.setOnClickListener {
            if(juego.nombre==etNombre.text.toString() && juego.numJugadores==etJugadores.text.toString().toInt() && juego.objetivo==etObjetivo.text.toString()){
                Toast.makeText(activity,"No has modificado nada", Toast.LENGTH_SHORT).show()
            }
            else{
                (activity as MainActivity).miViewModel.modificar(Juego(etNombre.text.toString(),etJugadores.text.toString().toInt(),etObjetivo.text.toString()),posicion)
                findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_add).isVisible=false
        menu.findItem(R.id.action_delete).isVisible=true
        menu.findItem(R.id.action_save).isVisible=true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete-> {
                (activity as MainActivity).miViewModel.borrar(posicion)
                findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
            }
        }
        return true
    }

}