package com.rubyumbra.backend.controllers

import com.rubyumbra.backend.services.DataService
import com.rubyumbra.backend.models.User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/data")
class DataController(private val dataService: DataService) {
    @GetMapping("/{uid}")
    @PreAuthorize("hasRole('USER')")
    fun uid(@PathVariable uid: String): User = dataService.getUserByUid(uid.toLong())

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    fun profile(): User = dataService.getCurrentUser()

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    fun all(): List<User> = dataService.getAllUsers()

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun adminAccess(): String = "Admin page"
}
