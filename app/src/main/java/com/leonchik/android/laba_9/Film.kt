package com.leonchik.android.laba_9

import java.util.Date
import java.util.UUID

data class Film (val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var watched: Boolean = false)