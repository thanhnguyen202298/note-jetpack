package net.thanhnguyen.z_note.core.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface INoteViewModel {
    val coroutineScope: CoroutineScope
    val jobManager: MutableList<Job>

    fun runCoroutine(runner: suspend ()->Unit) = coroutineScope.launch { runner.invoke() }.let { jobManager.add(it) }

    fun destroyJob() = jobManager.map { job: Job -> job.cancel() }.let { jobManager.removeAll { true } }
}