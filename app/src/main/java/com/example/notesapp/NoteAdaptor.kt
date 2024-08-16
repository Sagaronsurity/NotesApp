package com.example.notesapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NoteItemBinding

data class Note(
    var title : String ,
    var message : String
)

class NoteAdaptor(
    val notes: List<Note>
) : RecyclerView.Adapter<NoteAdaptor.ViewHolder>() {

    inner class ViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            noteheading.text = notes[position].title
            notemessage.text = notes[position].message
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
