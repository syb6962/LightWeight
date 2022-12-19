package com.example.lightweight.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class BodyPart(private val name: String) : Parcelable {
    fun getPart() = name

    @Parcelize class Chest : BodyPart("Chest")
    @Parcelize class Back : BodyPart("Back")
    @Parcelize class Leg : BodyPart("Leg")
    @Parcelize class Shoulder : BodyPart("Shoulder")
    @Parcelize class Biceps : BodyPart("Biceps")
    @Parcelize class Triceps : BodyPart("Triceps")
    @Parcelize class Abs : BodyPart("Abs")
}