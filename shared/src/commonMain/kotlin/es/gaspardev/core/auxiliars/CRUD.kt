package es.gaspardev.core.auxiliars

import kotlin.reflect.KProperty1

abstract class CRUD<T> {

    protected val values: ArrayList<T> = arrayListOf()

    fun add(value: T) {
        values.add(value)
    }

    abstract fun delete(value: T)
    abstract fun modify()

    fun getAll(): List<T> {
        return values
    }

    fun <S> getByParameter(searchBy: KProperty1<T, S>, searchValue: S): T {
        return values.find { searchBy.get(it) == searchValue }!!
    }
}