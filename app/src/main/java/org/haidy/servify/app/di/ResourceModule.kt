package org.haidy.servify.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.haidy.servify.app.resources.DrawableResources
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.resources.strings.IStringResources
import org.haidy.servify.app.utils.LocalizationManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {

    @Singleton
    @Provides
    fun provideStringResource(): IStringResources {
        return LocalizationManager.getStringResources(Resources.languageCode.value)
    }

    @Singleton
    @Provides
    fun provideDrawableResource() = DrawableResources()
}