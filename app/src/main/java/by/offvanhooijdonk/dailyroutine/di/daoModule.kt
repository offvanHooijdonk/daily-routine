package by.offvanhooijdonk.dailyroutine.di

import by.offvanhooijdonk.dailyroutine.domain.dao.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val daoModule = module {
    single { AppDatabase.create(androidContext()) }
    single { get<AppDatabase>().taskDao() }
}