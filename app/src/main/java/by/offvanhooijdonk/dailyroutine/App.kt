package by.offvanhooijdonk.dailyroutine

import android.app.Application
import by.offvanhooijdonk.dailyroutine.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(allModules)
        }
    }
}