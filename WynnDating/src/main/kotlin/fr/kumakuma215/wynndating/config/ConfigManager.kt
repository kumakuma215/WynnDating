package fr.kumakuma215.wynndating.config

object ConfigManager {
    var isModEnabled = true

    init {

    }

    fun toggleMod(){
        this.isModEnabled = !this.isModEnabled

    }
}