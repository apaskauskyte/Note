package lt.ausra.note

import java.time.LocalDateTime

data class Note(
    val id: Int,
    var name: String,
    var details: String,
    private val creationDate: LocalDateTime = LocalDateTime.now(),
    private var updateDate: LocalDateTime = LocalDateTime.now()
) {
    fun setNewName(name: String) {
        this.name = name
        updateDate = LocalDateTime.now()
    }

    fun setNewDetails(details: String) {
        this.details = details
        updateDate = LocalDateTime.now()
    }
}