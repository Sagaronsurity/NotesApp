package com.example.notesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.FragmentFavBinding
import com.example.notesapp.databinding.FragmentHomeScreenBinding


class Fav : Fragment(R.layout.fragment_fav) {


    lateinit var adaptor : NoteAdaptor
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavBinding.bind(view)
        val sharedpref = requireActivity().getSharedPreferences("Favs", Context.MODE_PRIVATE)
        val editor = sharedpref.edit()
        editor.putString("Note2"," Drink Water\nGo to Office")
        editor.apply()

        fun update_notes(){
            var notes : MutableList<Note> = mutableListOf()
            var allentries = sharedpref.all
            for(entry in allentries){
                notes.add(
                    Note(
                        title = entry.key,
                        message = entry.value.toString()
                    )
                )
            }
            adaptor = NoteAdaptor(notes)
            binding.rcview.adapter = adaptor
            binding.rcview.layoutManager = LinearLayoutManager(requireContext())
            adaptor.notifyItemInserted(notes.size-1)
        }

        update_notes()

    }


}