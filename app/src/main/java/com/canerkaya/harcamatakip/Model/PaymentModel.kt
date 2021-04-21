package com.canerkaya.harcamatakip.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class PaymentModel(

    @ColumnInfo(name = "paymentType")
    val paymentType:String,

    @ColumnInfo(name = "paymentName")
    val paymentName:String,

    @ColumnInfo(name = "tlCost")
    val tlCost:Int,

    @ColumnInfo(name = "dolarCost")
    val dolarCost:Int,

    @ColumnInfo(name = "sterlinCost")
    val sterlinCost:Int,

    @ColumnInfo(name = "euroCost")
    val euroCost:Int
)
{
    @PrimaryKey(autoGenerate = true)
    var primaryKey:Int = 0
}