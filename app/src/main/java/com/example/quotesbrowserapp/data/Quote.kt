package com.example.quotesbrowserapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quote(
    val id: String,
    val author: String,
    val text: String,
    val imageUrl: String
) : Parcelable