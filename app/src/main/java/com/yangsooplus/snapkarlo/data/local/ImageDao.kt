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

    @Query("SELECT * FROM image")
    fun getAll(): Flow<List<ImageEntity>>

    @Query("SELECT * FROM image WHERE keyword LIKE :keyword")
    fun findByKeyword(keyword: String): Flow<List<ImageEntity>>

    @Delete
    suspend fun delete(imageEntity: ImageEntity)

    @Query("DELETE FROM image WHERE id = :id")
    suspend fun deleteById(id: String)
}
