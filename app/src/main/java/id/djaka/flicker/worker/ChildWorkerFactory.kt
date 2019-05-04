package id.djaka.flicker.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): Worker
}