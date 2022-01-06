package com.example.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlincountries.model.Country

@Dao
interface CountryDao {
    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>

    //Insert -> INSERT INTO
    // suspend -> coroutıne, pause &resume
    //vararg -> multıple country objects
    //List<Long> -> primary keys

    @Query("SELECT * FROM Country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM Country WHERE uuid= :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()


}