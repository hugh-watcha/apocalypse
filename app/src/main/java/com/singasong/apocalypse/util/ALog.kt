package com.singasong.apocalypse.util

import android.util.Log
import com.singasong.apocalypse.BuildConfig

class ALog private constructor(
    private val className: String?,
    private val methodName: String?,
    private val lineNumber: Int
) {
    companion object {

        fun v(log: String?) {
            if (isDebuggable()) {
                instance().printV(log)
            }
        }

        fun d(log: String?) {
            if (isDebuggable()) {
                instance().printD(log)
            }
        }

        fun i(log: String?) {
            if (isDebuggable()) {
                instance().printI(log)
            }
        }

        fun w(log: String?) {
            if (isDebuggable()) {
                instance().printW(log)
            }
        }

        fun e(log: String?) {
            if (isDebuggable()) {
                instance().printE(log)
            }
        }

        private fun isDebuggable(): Boolean {
            return BuildConfig.DEBUG
        }

        private fun instance(): ALog {
            val stackTrace = Throwable().stackTrace
            return ALog(
                stackTrace[2].fileName?.replace(".kt", "")?.replace(".java", ""),
                stackTrace[2].methodName,
                stackTrace[2].lineNumber
            )
        }
    }

    private fun printV(log: String?) {
        Log.v(className, createLog(log))
    }

    private fun printD(log: String?) {
        Log.d(className, createLog(log))
    }

    private fun printI(log: String?) {
        Log.i(className, createLog(log))
    }

    private fun printW(log: String?) {
        Log.w(className, createLog(log))
    }

    private fun printE(log: String?) {
        Log.e(className, createLog(log))
    }

    private fun createLog(log: String?): String {
        return "[$methodName():$lineNumber] $log"
    }
}