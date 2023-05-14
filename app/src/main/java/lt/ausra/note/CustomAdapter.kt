package lt.ausra.note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Note>()

    override fun getCount() = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.note, parent, false)

        view.findViewById<TextView>(R.id.idTextView).text = list[position].id.toString()
        view.findViewById<TextView>(R.id.nameTextView).text = list[position].name
        view.findViewById<TextView>(R.id.detailsTextView).text = list[position].details

        return view
    }
}