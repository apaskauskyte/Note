package lt.ausra.note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import lt.ausra.note.databinding.NoteBinding

class CustomAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    fun add(vararg note: Note) {
        list.addAll(note)
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

        binding.idTextView.text = list[position].id.toString()
        binding.nameTextView.text = list[position].name
        binding.detailsTextView.text = list[position].details

        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size
}