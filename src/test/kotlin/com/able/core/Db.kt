package com.able.core

import com.able.config.Config
import org.ktorm.database.Database


open class Db {
    companion object : Config() {
        val connect = Database.connect(
            "${this.jdbc}://${this.host}:${this.port}/${this.dbName}",
            user = this.user,
            password = this.password
        )
    }
}
