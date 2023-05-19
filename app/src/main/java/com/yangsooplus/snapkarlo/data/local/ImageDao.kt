package com.yangsooplus.snapkarlo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yangsooplus.snapkarlo.data.local.model.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert
    suspend fun insert(imageEntity: ImageEntity)

    @Query("SELECT * FROM gallery")
    suspend fun getAll(): Flow<List<ImageEntity>>

    @Query("SELECT * FROM gallery WHERE keyword LIKE :keyword")
    suspend fun findByKeyword(keyword: String): Flow<List<ImageEntity>>

    @Delete
    suspend fun delete(imageEntity: ImageEntity)

    @Query("DELETE FROM gallery WHERE id = :id")
    suspend fun deleteById(id: String)
}
