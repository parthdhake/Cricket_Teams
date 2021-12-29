package com.qourall.pplcricket

import android.app.Application
import com.qourall.pplcricket.data.localDB.TeamDatabase

class PPLApplication : Application() {
    val database : TeamDatabase by lazy { TeamDatabase.getDatabase(this) }
}