package net.thanhnguyen.z_note.data.model

import net.thanhnguyen.z_note.core.dateFormatter
import net.thanhnguyen.z_note.core.toDateString
import org.mongodb.kbson.ObjectId
import java.util.Date

data class NoteItem(var id : ObjectId? = null,
                    var title: String = "",
                    var note: String = "",
                    var createdDate: Long = Date().time) {
    fun formatDate() = createdDate.toDateString(dateFormatter("yyyy-MM-dd"))
    fun toNote() = Note(id, title, note, createdDate)
}