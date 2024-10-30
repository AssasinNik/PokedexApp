package com.nikitacherenkov.pokedexapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger
import com.nikitacherenkov.pokedexapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class PokedexApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApp)
            androidLogger()
            modules(appModule)
        }
    }
    class PokedexApp : Application(), ImageLoaderFactory {
        override fun newImageLoader(): ImageLoader {
            return ImageLoader.Builder(this)
                .memoryCache {
                    MemoryCache.Builder(this)
                        .maxSizePercent(0.1)
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .maxSizePercent(0.03)
                        .directory(cacheDir.resolve("image_cache"))
                        .maxSizeBytes(5 * 1024 * 1024)
                        .build()
                }
                .logger(DebugLogger())
                .respectCacheHeaders(false)
                .build()
        }
    }
}