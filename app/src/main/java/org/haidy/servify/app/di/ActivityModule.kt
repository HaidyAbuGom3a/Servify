package org.haidy.servify.app.di

import androidx.activity.ComponentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.haidy.servify.app.MainActivity

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @Provides
    fun provideComponentActivity(): ComponentActivity {
        return MainActivity()
    }
}