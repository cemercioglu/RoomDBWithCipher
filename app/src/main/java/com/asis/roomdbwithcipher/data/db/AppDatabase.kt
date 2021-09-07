package com.asis.roomdbwithcipher.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.asis.roomdbwithcipher.data.model.User
import com.asis.roomdbwithcipher.data.db.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.sqlcipher.database.SupportFactory


/**
 * Created by cemercioglu on 25.08.2021.
 * Copyright (c) 2021  Asis Elektronik Bilişim Sis. A.Ş. All rights reserved.
 */
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao())
                }
            }
        }

        suspend fun populateDatabase(userDao: UserDao) {
            //userDao.deleteAll()
            var user = User("CEM", "ERCİ")
            userDao.insert(user)
            user = User("AHMET", "MEHMET")
            userDao.insert(user)

        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val data = byteArrayOf(0x90.toByte(), 0x00.toByte())
        var passphrase: ByteArray? = "Cem123".toByteArray()
        private val factory = SupportFactory(passphrase)
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            val db = INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AsisDB"
                )
                    .openHelperFactory(factory)
                    .addCallback(AppDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
            return db
        }


    }
}