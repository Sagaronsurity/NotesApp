package com.example.notesapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.FragmentCreateNoteBinding


class CreateNote : Fragment(R.layout.fragment_create_note) {

    private lateinit var HomescreenFragment : Homescreen

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateNoteBinding.bind(view)

        HomescreenFragment = Homescreen()

        binding.btnsave.setOnClickListener {
            val title = binding.inputtitle.text.toString()
            val message = binding.inputnote.text.toString()
            val sharedpref = requireActivity().getSharedPreferences("Notes", Context.MODE_PRIVATE)
            val editor = sharedpref.edit()
            editor.putString(title, message)
            editor.apply()
            parentFragmentManager.popBackStack()
        }

        binding.btnsavetofav.setOnClickListener{
            val title = binding.inputtitle.text.toString()
            val message = binding.inputnote.text.toString()
            val sharedpreffav = requireActivity().getSharedPreferences("Favs", Context.MODE_PRIVATE)
            val editor = sharedpreffav.edit()
            editor.putString(title, message)
            editor.apply()
            val sharedpref = requireActivity().getSharedPreferences("Notes", Context.MODE_PRIVATE)
            val editor1 = sharedpref.edit()
            editor1.putString(title, message)
            editor1.apply()
            parentFragmentManager.popBackStack()
        }


    }

}