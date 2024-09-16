package net.thanhnguyen.z_note.di

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import net.thanhnguyen.z_note.data.model.Note

fun provideNoteDatabase(application: Application): Realm {
    val config = RealmConfiguration.Builder(
        schema = setOf(Note::class)
    ).name(application.applicationInfo.name)
        .schemaVersion(0)
        .directory(application.dataDir.path)
        .deleteRealmIfMigrationNeeded()
        .encryptionKey(keyEncrypt)
        .build()
    return Realm.open(config)
}

private val testKey = "EIDzweymFJDFmioLSKuynWA".encodeToByteArray()
private val keyEncrypt = ByteArray(64).let {
    if (testKey.size < 64) {
        testKey.copyInto(it)
        it
    }
    else testKey
}