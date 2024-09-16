package net.thanhnguyen.z_note

import android.app.Application
import net.thanhnguyen.z_note.di.AppDataModule
import net.thanhnguyen.z_note.di.RepositoryModule
import net.thanhnguyen.z_note.di.UseCaseModule
import net.thanhnguyen.z_note.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.lazyModules

class NoteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
            lazyModules(AppDataModule)
        }
    }
}