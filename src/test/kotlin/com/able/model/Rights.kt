package com.able.model

import com.able.config.Config
import com.able.core.Db
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.schema.Table
import org.ktorm.schema.int

object Rights : Table<Nothing>("rights") {
    val id = int("id").primaryKey()
    var user_id = int("user_id")
    val is_admin = int("is_admin")
}

class RightsActions() {
    companion object: Config() {
        fun add_admin_rights(user_id: Int) {
            Db.connect.insert(Rights) {
                set(it.user_id, user_id)
                set(it.is_admin, 1)
            }
        }

        fun delete_rights_admin(user_id: Int) {
            Db.connect.delete(Rights) {
                it.user_id eq user_id
            }
        }
    }
}