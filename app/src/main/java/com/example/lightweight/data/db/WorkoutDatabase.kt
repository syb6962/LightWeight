package com.example.lightweight.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lightweight.data.db.dao.WorkoutDao
import com.example.lightweight.data.db.entity.Workout
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.data.db.entity.WorkoutList

@Database(
    entities = [Workout::class, WorkoutSetInfo::class, WorkoutList::class],
    version = 1
)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        private var INSTANCE : WorkoutDatabase? = null
        @Synchronized //TODO : DB이름바꾸기
        fun getDatabase(context: Context): WorkoutDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutDatabase::class.java,
                    "WorkoutDB"
                )
                    .createFromAsset("database/workoutDB.db")
//                    .addCallback(WorkoutListCallback(context))
//                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance // 싱글톤 객체니까 INSTANCE에 instance를 대입함으로써 다음에 non-null이 되게 위함인듯.
                instance.openHelper.writableDatabase // TODO: 강제 열기, 근데 이게 맞는 방식인가.
                // return instance
                instance
            }
        }
//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Test ADD COLUMN isFinished INTEGER DEFAULT 0 NOT NULL")
//            }
//        }

    }
}
