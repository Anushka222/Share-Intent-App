package com.example.myintentapp


import android.util.Log
import java.lang.Thread
import java.util.concurrent.Executors
import java.util.concurrent.Future

public class AsyncTaskHelper {
    companion object {
        private final val TAG = "ENAsyncTaskHelper";
        private var nameCounter = 0;

        public fun <T> run(withUniqueName: String? = null, task: () -> T): Future<T> {
            val taskName = withUniqueName ?: "${nameCounter++}"
            return Executors.newSingleThreadExecutor { r -> Thread(r, "$TAG-$taskName") }.submit({
                task()
            }) as Future<T>
        }

        public fun cancelTasks(withNames: List<String>) {
            if (withNames.isNullOrEmpty()) return
            val tasks = getActiveTasks()
            withNames.forEach { name ->
                if (tasks[name] != null) {
                    tasks[name]?.interrupt()
                    Log.e(TAG, "Task canceled: $name")
                }
            }
        }

        public fun getActiveTasks(): Map<String, Thread> {
            return getTasks().filterValues { !(it.getState() == Thread.State.TERMINATED || it.isInterrupted()) }
        }

        private fun getTasks(): Map<String, Thread> {
            val tasks = Thread.getAllStackTraces().filterKeys { it.getName().startsWith("$TAG-") }
            return tasks.entries.associate { it.key.getName().split("$TAG-").last() to it.key }
        }
    }
}
