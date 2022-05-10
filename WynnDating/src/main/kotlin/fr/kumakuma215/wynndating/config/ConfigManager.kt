package fr.kumakuma215.wynndating.config

object ConfigManager {
    var isModEnabled = true

    fun toggleMod(){
        this.isModEnabled = !this.isModEnabled
    }
}