package by.letum8658.homework.dz15

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: MutableList<Student>)

    @Query("SELECT * FROM $DZ15_TABLET_NAME")
    fun get(): MutableList<Student>
}