package com.richmondprojects.acegameapp.data.remote.dto

import com.richmondprojects.acegameapp.domain.model.MinimumSystemRequirements

data class MinimumSystemRequirementsDto(
    val graphics: String,
    val memory: String,
    val os: String,
    val processor: String,
    val storage: String
) {
    fun toMinRequirementDomain(): MinimumSystemRequirements {
        return MinimumSystemRequirements(
            graphics, memory, os, processor, storage
        )
    }
}