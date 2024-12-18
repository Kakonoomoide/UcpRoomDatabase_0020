package com.example.pam_ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pam_ucp2.data.dao.DokterDao
import com.example.pam_ucp2.data.dao.JadwalDao
import com.example.pam_ucp2.data.entity.Dokter
import com.example.pam_ucp2.data.entity.Jadwal

@Database(entities = [Dokter::class,Jadwal::class], version = 1, exportSchema = false)
abstract class RsDatabase : RoomDatabase(){
    //aksess database
    abstract fun DokterDao(): DokterDao
    abstract fun JadwalDao(): JadwalDao

    companion object{
        @Volatile // nilai variable instance selalu sama
        private var Instance:RsDatabase? = null

        fun getDatabase(context: Context): RsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    RsDatabase::class.java, //class database
                    "RsDatabase" //nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}