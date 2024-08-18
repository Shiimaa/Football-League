package com.football.league

import android.app.Application
import com.football.commonModels.di.AppModule
import com.football.core.di.BaseComponent
import com.football.league.di.AppComponent
import com.football.league.di.DaggerAppComponent

class App : Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)

    }

    fun getBaseComponent(): BaseComponent {
        return appComponent
    }
}