package com.haazer.school.dto


import com.fasterxml.jackson.annotation.JsonProperty

data class ScheduleDTO(
    @JsonProperty("id") val id: Long? = null,
    @JsonProperty("startTime") val startTime: String,
    @JsonProperty("endTime") val endTime: String
)
