package com.drewjya.projecthill.model

import com.drewjya.projecthill.entity.Project

data class CreateProjectRequest(
    val name: String,
    val description: String,
)

fun CreateProjectRequest.toProject() = Project(name = name, description = description)
