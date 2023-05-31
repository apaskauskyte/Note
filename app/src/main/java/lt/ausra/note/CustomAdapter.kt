package lt.ausra.note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import lt.ausra.note.databinding.NoteBinding
import lt.ausra.note.repository.Note

class CustomAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    fun set(notes: List<Note>) {
        list.clear()
        list.addAll(notes)
        notifyDataSetChanged()
    }

    fun add(notes: List<Note>) {
        list.addAll(notes)
        notifyDataSetChanged()
    }

    fun add(note: Note) {
        list.add(note)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        val binding: NoteBinding

        if (view == null) {
            binding = NoteBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as NoteBinding
        }

        binding.note = list[position]

        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size
}