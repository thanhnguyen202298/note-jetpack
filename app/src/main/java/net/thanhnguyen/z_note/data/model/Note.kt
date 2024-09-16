package net.thanhnguyen.z_note.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
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

    fun toNoteModel() = NoteItem(id, title, note, createdDate)
}