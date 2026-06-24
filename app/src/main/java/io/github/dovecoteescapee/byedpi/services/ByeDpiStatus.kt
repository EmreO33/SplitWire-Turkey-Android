package io.github.dovecoteescapee.byedpi.services

import android.net.TrafficStats
import android.os.Process
import android.os.SystemClock
import io.github.dovecoteescapee.byedpi.data.AppStatus
import io.github.dovecoteescapee.byedpi.data.Mode

var appStatus = AppStatus.Halted to Mode.VPN
    private set

// Timestamp (elapsedRealtime) when the current connection came up, or null when halted.
var connectedSince: Long? = null
    private set

// Per-UID traffic counters captured at connect time, used to compute session totals.
var sessionBaselineRx: Long = 0L
    private set
var sessionBaselineTx: Long = 0L
    private set

fun setStatus(status: AppStatus, mode: Mode) {
    appStatus = status to mode

    if (status == AppStatus.Running) {
        if (connectedSince == null) {
            connectedSince = SystemClock.elapsedRealtime()
            sessionBaselineRx = TrafficStats.getUidRxBytes(Process.myUid())
            sessionBaselineTx = TrafficStats.getUidTxBytes(Process.myUid())
        }
    } else {
        connectedSince = null
    }
}
