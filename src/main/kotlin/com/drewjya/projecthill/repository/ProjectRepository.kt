package com.drewjya.projecthill.repository

import com.drewjya.projecthill.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long>
