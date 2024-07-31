package net.thanhnguyen.z_note

import android.app.Application
import net.thanhnguyen.z_note.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
            modules(AppModule)
        }
    }
}