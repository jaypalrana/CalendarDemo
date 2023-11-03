package com.example.calendar.base

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code") var code: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var result: T ? =null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(status)
        parcel.writeInt(code!!)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseResponse<Any?>> {
        override fun createFromParcel(parcel: Parcel): BaseResponse<Any?> {
            return BaseResponse(parcel)
        }

        override fun newArray(size: Int): Array<BaseResponse<Any?>?> {
            return arrayOfNulls(size)
        }
    }
}