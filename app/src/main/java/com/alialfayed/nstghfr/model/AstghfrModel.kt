package com.alialfayed.nstghfr.model

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/25/2020 - 10:38 PM
 */
class AstghfrModel {

    private lateinit var anstghfrID:String
    private lateinit var anstghfrTitle:String
    private lateinit var anstghfrText:String

    constructor(){}

    constructor(anstghfrID:String ,anstghfrTitle:String,anstghfrText:String){
        this.anstghfrID = anstghfrID
        this.anstghfrText = anstghfrText
        this.anstghfrTitle = anstghfrTitle
    }

    fun setAnstghfrID(anstghfrID: String) {
        this.anstghfrID = anstghfrID
    }
    fun getAnstghfrID(): String {
        return anstghfrID
    }

    fun setAnstghfrText(anstghfrText: String) {
        this.anstghfrText = anstghfrText
    }

    fun getAnstghfrText(): String {
        return anstghfrText
    }
    fun setAnstghfrTitle(anstghfrTitle: String) {
        this.anstghfrTitle = anstghfrTitle
    }
    fun getAnstghfrTitle(): String {
        return anstghfrTitle
    }

}