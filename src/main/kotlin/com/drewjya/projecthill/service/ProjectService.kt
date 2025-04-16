package com.drewjya.projecthill.service

import com.drewjya.projecthill.entity.Project
import com.drewjya.projecthill.exception.DefaultNotFoundException
import com.drewjya.projecthill.model.CreateProjectRequest
import com.drewjya.projecthill.model.UpdateProjectRequest
import com.drewjya.projecthill.model.toProject
import com.drewjya.projecthill.repository.ProjectRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProjectService(
    var projectRepository: ProjectRepository,
) {
    fun getAllProjects(): List<Project> = projectRepository.findAll()

    fun getProjectById(id: Long): Project = projectRepository.findByIdOrNull(id) ?: throw DefaultNotFoundException()

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
