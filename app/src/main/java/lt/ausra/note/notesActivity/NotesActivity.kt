package lt.ausra.note.notesActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.ausra.note.CustomAdapter
import lt.ausra.note.repository.Note
import lt.ausra.note.noteDetailsActivity.NoteDetailsActivity
import lt.ausra.note.R
import lt.ausra.note.databinding.NotesActivityBinding

class NotesActivity : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    private lateinit var binding: NotesActivityBinding
    private val activityViewModel: NotesActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.notes_activity)
        binding.notesActivity = this
        binding.viewModel = activityViewModel

        activityViewModel.notesLiveData.observe(
            this,
            Observer { listOfNotes ->
                adapter.set(listOfNotes)
            }
        )

        setUpListView()
        setupSearchView()
        setClickOpenNoteDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchNotes()
    }

    fun openNewNoteDetails(view: View) {
        startActivity(
            Intent(
                this, NoteDetailsActivity::class.java
            )
        )
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.noteListView.adapter = adapter
    }

    private fun setupSearchView() {
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                activityViewModel.filterNotesList(binding.searchView.text.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setClickOpenNoteDetails() {
        binding.noteListView.setOnItemClickListener { adapterView, view, position, l ->
            val note: Note = adapterView.getItemAtPosition(position) as Note

            val intent = Intent(this, NoteDetailsActivity::class.java)
            intent.putExtra(NOTES_ACTIVITY_NOTE_INTENT_ID, note.id)

            startActivity(intent)
        }
    }

    companion object {
        const val NOTES_ACTIVITY_NOTE_INTENT_ID = "item_intent_object"
    }
}