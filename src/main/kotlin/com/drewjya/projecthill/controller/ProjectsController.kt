package com.drewjya.projecthill.controller

import com.drewjya.projecthill.entity.Project
import com.drewjya.projecthill.model.CreateProjectRequest
import com.drewjya.projecthill.model.ResultResponse
import com.drewjya.projecthill.model.UpdateProjectRequest
import com.drewjya.projecthill.model.toResponse
import com.drewjya.projecthill.service.ProjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/project")
class ProjectsController(
    val projectService: ProjectService,
) {
    @GetMapping
    fun getAllProjects(): ResponseEntity<ResultResponse<List<Project>>> = ResponseEntity.ok(projectService.getAllProjects())

    @GetMapping("/{projectId}")
    fun getProjectById(
        @PathVariable projectId: Long,
    ): ResultResponse<Project> = projectService.getProjectById(projectId)

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
