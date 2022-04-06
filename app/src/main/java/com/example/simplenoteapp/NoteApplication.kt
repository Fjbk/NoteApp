package com.example.simplenoteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //Giver Hilt adgang til applikationen, derfor det extender Application()
//Husk at det også skal registreres i AndroidManifests.xml under manifests via 'android:name=".NoteApplication"'
//Tilføj også @AndroidEntryPoint i MainActivity
class NoteApplication : Application() {

}