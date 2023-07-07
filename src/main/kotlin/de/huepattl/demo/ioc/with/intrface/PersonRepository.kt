package de.huepattl.demo.ioc.with.intrface

import de.huepattl.demo.ioc.common.Person

interface PersonRepository {

    fun getById(id: String): Result<Person>

}