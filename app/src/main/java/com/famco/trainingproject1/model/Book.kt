package com.famco.trainingproject1.model

data class Book(var userId:Int, var id:Int, var title:String, var body:String){
    constructor(): this(0,0,"","")
}
