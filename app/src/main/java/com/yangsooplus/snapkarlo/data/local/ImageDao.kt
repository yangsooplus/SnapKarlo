package com.yangsooplus.snapkarlo.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yangsooplus.snapkarlo.data.local.model.ImageEntity

interface ImageDao {
    @Insert
    fun insert(imageEntity: ImageEntity)

    @Query("SELECT * FROM gallery")
    fun getAll(): List<ImageEntity>

    @Query("SELECT * FROM gallery WHERE keyword LIKE :keyword")
    fun findByKeyword(keyword: String): List<ImageEntity>

    @Delete
    fun delete(imageEntity: ImageEntity)

    @Query("DELETE FROM gallery WHERE id = :id")
    fun deleteById(id: String)
}
