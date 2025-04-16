package com.drewjya.projecthill.controller

import com.drewjya.projecthill.entity.Project
import com.drewjya.projecthill.model.CreateProjectRequest
import com.drewjya.projecthill.model.UpdateProjectRequest
import com.drewjya.projecthill.model.toResponse
import com.drewjya.projecthill.service.ProjectService
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(ProjectsController::class)
@ExtendWith(SpringExtension::class)
internal class ProjectControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
) {
    @MockitoBean
    private lateinit var mockProjectService: ProjectService

    private lateinit var projects: List<Project>
    private lateinit var createProjectRequest: CreateProjectRequest
    private lateinit var updateProjectRequest: UpdateProjectRequest

    @BeforeEach
    fun beforeEach() {
        this.projects =
            listOf(
                Project(
                    id = 1L,
                    name = "Project 1",
                    description = "Description 1",
                ),
            )

        this.createProjectRequest =
            CreateProjectRequest(
                name = "name",
                description = "desc",
            )

        this.updateProjectRequest =
            UpdateProjectRequest(
                name = "name",
                description = "desc",
            )
    }

    @Test
    fun `dependencies are not null`() {
        assertThat(mockMvc).isNotNull
        assertThat(mockProjectService).isNotNull
    }

    @Test
    fun `success getAllProject`() {
        val data = projects.toResponse(message = "All projects retrieved successfully")
        whenever(mockProjectService.getAllProjects()).thenReturn(data)
        mockMvc
            .get("/projects") {
                contentType = MediaType.APPLICATION_JSON
            }.andDo {
                print()
            }.andExpect {
                status { isOk() }
            }
    }
}
