package com.example.kopringworkshop.mysqldb.repository

import com.example.kopringworkshop.mysqldb.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long>
