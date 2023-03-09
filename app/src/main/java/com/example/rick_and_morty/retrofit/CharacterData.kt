package com.example.rick_and_morty.retrofit

import android.os.Parcel
import android.os.Parcelable


data class CharacterData(
    var results: List<ResultCharacter>
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(ResultCharacter)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterData> {
        override fun createFromParcel(parcel: Parcel): CharacterData {
            return CharacterData(parcel)
        }

        override fun newArray(size: Int): Array<CharacterData?> {
            return arrayOfNulls(size)
        }
    }
}


data class ResultCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readParcelable(Location::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readParcelable(Origin::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(created)
        parcel.writeStringList(episode)
        parcel.writeString(gender)
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(species)
        parcel.writeString(status)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultCharacter> {
        override fun createFromParcel(parcel: Parcel): ResultCharacter {
            return ResultCharacter(parcel)
        }

        override fun newArray(size: Int): Array<ResultCharacter?> {
            return arrayOfNulls(size)
        }
    }

}

data class Location(
    val name: String,
    val url: String
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}

data class Origin(
    val name: String,
    val url: String
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Origin> {
        override fun createFromParcel(parcel: Parcel): Origin {
            return Origin(parcel)
        }

        override fun newArray(size: Int): Array<Origin?> {
            return arrayOfNulls(size)
        }
    }

}