package lt.ausra.note

import java.time.LocalDateTime

data class Note(
    val id: Int,
    var name: String,
    var details: String,
    private val creationDate: LocalDateTime = LocalDateTime.now(),
    private var updateDate: LocalDateTime = LocalDateTime.now()
) {
}