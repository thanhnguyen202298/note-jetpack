package net.thanhnguyen.z_note.core.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import net.thanhnguyen.z_note.core.dateFormatter
import net.thanhnguyen.z_note.core.toDateString
import org.mongodb.kbson.ObjectId
import java.util.Date

class Note():RealmObject {
    @PrimaryKey
    var id : ObjectId? = null
    var title: String = ""
    var note: String = ""
    var createdDate: Long = Date().time

    constructor(id: ObjectId?, title: String, note: String, createdDate: Long = Date().time) : this() {
        this.title = title
        this.note = note
        this.createdDate = createdDate
        this.id = id
    }

    fun toNoteModel() = NoteModel(id, title, note, createdDate)
}

data class NoteModel(var id : ObjectId? = null,
                     var title: String = "",
                     var note: String = "",
                     var createdDate: Long = Date().time){
    fun formatDate() = createdDate.toDateString(dateFormatter("yyyy-MM-dd"))
    fun toNote() = Note(id, title, note, createdDate)
}