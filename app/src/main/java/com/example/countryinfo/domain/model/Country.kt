package com.example.countryinfo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.countryinfo.domain.model.countryDetails.CapitalInfo
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Car
import com.example.countryinfo.domain.model.countryDetails.CoatOfArms
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Demonyms
import com.example.countryinfo.domain.model.countryDetails.Flags
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Language
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Gini
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Idd
import com.example.countryinfo.domain.model.countryDetails.Maps
import com.example.countryinfo.domain.model.countryDetails.Name
import com.example.countryinfo.domain.model.countryDetails.PostalCode
import com.example.countryinfo.domain.model.countryDetails.Translations
import com.example.countryinfo.domain.model.countryDetails.currency.Currency

@Entity
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val altSpellings: List<String>?,
    val capitalInfo: CapitalInfo?,
    val car: Car?,
    val cca2: String?,
    val cca3: String?,
    val ccn3: String?,
    val cioc: String?,
    val coatOfArms: CoatOfArms?,
    val continents: List<String>?,
    val demonyms: Demonyms?,
    val fifa: String?,
    val flag: String?,
    val flags: Flags?,
    val gini: Gini?,
    val idd: Idd?,
    val independent: Boolean?,
    val landlocked: Boolean?,
    val latlng: List<Double>?,
    val maps: Maps?,
    val population: Int?,
    val startOfWeek: String?,
    val status: String?,
    val subregion: String?,
    val timezones: List<String>?,
    val tld: List<String>?,
    val translations: Translations?,
    val unMember: Boolean?,
    val area: Double?,
    val borders: List<String>?,
    val postalCode: PostalCode?,
    val capital: List<String>?,
    val languages: Language?,
    val name: Name?,
    val currencies: Currency?,
    val region: String?
)