package com.drewjya.projecthill.service

import com.drewjya.projecthill.entity.Project
import com.drewjya.projecthill.exception.DefaultNotFoundException
import com.drewjya.projecthill.model.CreateProjectRequest
import com.drewjya.projecthill.model.ResultResponse
import com.drewjya.projecthill.model.UpdateProjectRequest
import com.drewjya.projecthill.model.toProject
import com.drewjya.projecthill.model.toResponse
import com.drewjya.projecthill.repository.ProjectRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProjectService(
    var projectRepository: ProjectRepository,
) {
    fun getAllProjects(): ResultResponse<List<Project>> {
        val projects = projectRepository.findAll()
        return projects.toResponse(message = "All projects retrieved successfully")
    }

    fun getProjectById(id: Long): ResultResponse<Project> {
        val project = projectRepository.findByIdOrNull(id) ?: throw DefaultNotFoundException()
        return project.toResponse(
            message = "Project with id $id retrieved successfully",
        )
    }

    fun createProject(project: CreateProjectRequest): Project = projectRepository.save(project.toProject())

    fun updateProject(
        id: Long,
        project: UpdateProjectRequest,
    ): Project {
        val existingProject =
            projectRepository.findByIdOrNull(id)
                ?: throw DefaultNotFoundException() // Return null if the project doesn't exist

        existingProject.apply {
            name = project.name ?: this.name
            description = project.description ?: this.description
        }
        return projectRepository.save(existingProject)
    }

    fun deleteProject(id: Long) {
        projectRepository.deleteById(id)
    }
}
