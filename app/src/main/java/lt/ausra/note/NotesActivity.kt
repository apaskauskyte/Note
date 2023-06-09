package lt.ausra.note

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton
import lt.ausra.note.databinding.ActivityMainBinding

class NotesActivity : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notes = mutableListOf<Note>()

        setUpListView()
        updateAdapter(notes)
        openNoteDetails()
        setClickOpenNoteDetails()
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.noteListView.adapter = adapter
    }

    private fun updateAdapter(notes: MutableList<Note>) {
        adapter.add(
            Note(1, "Shopping list", "bananas, bread, milk")
        )
        adapter.add(
            Note(2, "Movies to watch", "Blade, Casablanca")
        )
    }

    private fun openNoteDetails() {
        binding.openNoteDetailsButton.setOnClickListener {
            startActivityForNewNoteResult.launch(
                Intent(this, NoteDetailsActivity::class.java)
            )
        }
    }

    private fun setClickOpenNoteDetails() {
        binding.noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note

            val noteIntent = Intent(this, NoteDetailsActivity::class.java)
            noteIntent.putExtra(NOTE_ID, note.id)
            noteIntent.putExtra(NOTE_NAME, note.name)
            noteIntent.putExtra(NOTE_DETAILS, note.details)
            noteIntent.putExtra(NOTE_POSITION, position)
            startActivityForExistingNoteResult.launch(noteIntent)
        }
    }

    private val startActivityForNewNoteResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val note = Note(
                        id = result.data?.getIntExtra(
                            NoteDetailsActivity.NOTE_DETAILS_ID, -1
                        ) ?: 0,
                        name = result.data?.getStringExtra(
                            NoteDetailsActivity.NOTE_DETAILS_NAME
                        ) ?: "",
                        details = result.data?.getStringExtra(
                            NoteDetailsActivity.NOTE_DETAILS_DETAILS
                        ) ?: "",
                    )
                    adapter.add(note)
                }
            }
        }

    private val startActivityForExistingNoteResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val position = result.data?.getIntExtra(
                        NOTE_POSITION, -1
                    ) ?: -1

                    val note = adapter.getItem(position) as Note

                    val name = result.data?.getStringExtra(
                        NoteDetailsActivity.NOTE_DETAILS_NAME
                    ) ?: ""
                    val details = result.data?.getStringExtra(
                        NoteDetailsActivity.NOTE_DETAILS_DETAILS
                    ) ?: ""
                    note.setNewName(name)
                    note.setNewDetails(details)
                    adapter.notifyDataSetChanged()
                }
            }
        }

    companion object {
        const val NOTE_ID = "Id"
        const val NOTE_NAME = "name"
        const val NOTE_DETAILS = "details"
        const val NOTE_POSITION = "position"
    }
}