package com.example.countryinfo.domain.model.countryDetails

import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Ara
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Bre
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Cym
import com.example.countryinfo.domain.model.countryDetails.nativeLanguage.Per

data class Translations(
    val ara: Ara,
    val bre: Bre,
    val cym: Cym,

    val per: Per,

    )