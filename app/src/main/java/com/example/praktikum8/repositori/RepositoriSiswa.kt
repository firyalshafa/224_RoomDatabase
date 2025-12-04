package com.example.praktikum8.repositori

import com.example.praktikum8.room.Siswa
import com.example.praktikum8.room.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>
    fun getSiswaStream(id:int ):flow<Siswa?>



    suspend fun insertSiswa(siswa: Siswa)  // Fixed the syntax error here
}

class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao
): RepositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()

    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)

    override fun getSiswaStream(id: int): flow<Siswa?> = SiswaDao.getSiswa(id)
    override suspend fun  deteleSiswa (Siswa : Siswa) = siswaDao.delete(Siswa)
    override suspend fun  updateSiswa(Siswa: Siswa) = SiswaDao.update(Siswa)
}