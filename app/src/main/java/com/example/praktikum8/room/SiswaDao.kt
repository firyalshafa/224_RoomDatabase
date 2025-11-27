package com.example.praktikum8.room

import androidx.room.Dao
import androidx.room.Query



@Dao
interface SiswaDao{
    @Query(value = "SELECT * from tblsiswa ORDER BY nama ASC")
    fun getAllsiswa():flow<List<siswa>>

    @insert(onConflict = onConflictstrategy.IGNORE)
    suspend fun  insert (siswa: Siswa)


}
