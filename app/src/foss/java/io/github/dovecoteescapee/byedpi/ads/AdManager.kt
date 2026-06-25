package io.github.dovecoteescapee.byedpi.ads

import android.app.Activity
import android.view.View
import android.view.ViewGroup

// FOSS edition: no ads. The banner slot is simply hidden.
object AdManager {
    fun attachBanner(activity: Activity, container: ViewGroup) {
        container.visibility = View.GONE
    }
}
