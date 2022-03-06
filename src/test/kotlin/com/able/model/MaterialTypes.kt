package com.able.model

import com.able.core.Db
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object MaterialTypes : Table<Nothing>("material_types") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val units_measurement_volume_id = int("units_measurement_volume_id")
}

class MaterialTypesActions() {
    companion object {
        fun remove_material_type_with_id(id: Int) {
            Db.connect.delete(MaterialTypes) {
                it.id eq id
            }
        }
    }
}