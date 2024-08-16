package com.example.notesapp
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModel
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.FragmentHomeScreenBinding



class Homescreen : Fragment(R.layout.fragment_home_screen) {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    lateinit var adaptor : NoteAdaptor
    private lateinit var islogged : SharedPreferences
    private lateinit var users : SharedPreferences
    private lateinit var sharedpref : SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeScreenBinding.bind(view)
        islogged = requireActivity().getSharedPreferences("loginActivity", Context.MODE_PRIVATE)

        users = requireActivity().getSharedPreferences("Users",Context.MODE_PRIVATE)


        sharedpref = requireActivity().getSharedPreferences("Notes",Context.MODE_PRIVATE)
        val editor = sharedpref.edit()
        editor.putString("Note1"," Drink Water\nGo to Office")
        editor.putString("Note2"," Drink Water\nGo to Office")
        editor.apply()



        update_notes()

        onclicklisterns()


    }

    fun update_notes(){

        binding.textname.text = "Welcome\n${users.getString(islogged.getString("PhnNumber",null),"---")}"
        var notes : MutableList<Note> = mutableListOf()
        val allentries = sharedpref.all
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

    private fun onclicklisterns(){
        binding.addnotes.setOnClickListener{
            val trans = requireActivity().supportFragmentManager.beginTransaction()
            trans.replace(R.id.framelt,CreateNote())
            trans.addToBackStack("null")
            trans.commit()
        }

        binding.btnLogout.setOnClickListener{
            val editor = islogged.edit()
            editor.putString("PhnNumber",null)
            editor.putInt("is_logged", 0)
            editor.apply()
            logout()
        }
    }

    private fun logout() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


}