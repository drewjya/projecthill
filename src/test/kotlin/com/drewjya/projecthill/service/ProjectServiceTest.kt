package com.drewjya.projecthill.service

import com.drewjya.projecthill.entity.Project
import com.drewjya.projecthill.repository.ProjectRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import kotlin.test.assertNotNull

@SpringBootTest(classes = [ProjectService::class])
@ExtendWith(MockitoExtension::class)
@ActiveProfiles("test")
internal class ProjectServiceTest {
    @MockitoBean
    private lateinit var projectRepository: ProjectRepository

    @Test
    fun `dependencies are not null`() {
        assertThat(projectRepository).isNotNull
    }

    @Test
    fun `success getAllProjects`() {
        val project =
            listOf(
                Project(
                    id = 1L,
                    name = "Project 1",
                    description = "Description 1",
                ),
            )
        whenever(projectRepository.findAll()).thenReturn(project)

        val projects = projectRepository.findAll()

        assertNotNull(projects)
    }
}
