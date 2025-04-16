package com.drewjya.projecthill.controller

import com.drewjya.projecthill.entity.Project
import com.drewjya.projecthill.service.ProjectService
import com.drewjya.projecthill.model.CreateProjectRequest
import com.drewjya.projecthill.model.ResultResponse
import com.drewjya.projecthill.model.UpdateProjectRequest
import com.drewjya.projecthill.model.toResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/project")
class ProjectsController(
    val projectService: ProjectService,
) {
    @GetMapping
    fun getAllProjects(): ResultResponse<List<Project>> =
        projectService.getAllProjects().toResponse(message = "All projects retrieved successfully")

    @GetMapping("/{projectId}")
    fun getProjectById(
        @PathVariable projectId: Long,
    ): ResultResponse<Project> =
        projectService.getProjectById(projectId).toResponse(
            message = "Project with id $projectId retrieved successfully",
        )

    @PostMapping
    fun createProject(
        @RequestBody project: CreateProjectRequest,
    ): ResultResponse<Project> =
        projectService.createProject(project).toResponse(
            message = "Project with name ${project.name} created successfully",
        )

    @PatchMapping("/{projectId}")
    fun updateProject(
        @PathVariable projectId: Long,
        @RequestBody project: UpdateProjectRequest,
    ): ResultResponse<Project> =
        projectService.updateProject(projectId, project).toResponse(
            message = "Project with id $projectId updated successfully",
        )

    @DeleteMapping("/{projectId}")
    fun deleteProject(
        @PathVariable projectId: Long,
    ): ResultResponse<Unit> =
        projectService.deleteProject(projectId).toResponse(message = "Project with id $projectId deleted successfully")
}
