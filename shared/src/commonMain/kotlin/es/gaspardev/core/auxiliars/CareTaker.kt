package es.gaspardev.core.auxiliars

import kotlin.reflect.KMutableProperty1
import es.gaspardev.core.interfaces.Copyable

open class CareTaker<T : Copyable> {

    private val mementos: MutableList<Memento<T>> = mutableListOf()

    fun saveState(data: T) {
        mementos.add(Memento(data.copy() as T))
    }

    fun undo(): T? {
        return if (mementos.isNotEmpty()) mementos.removeLast().data else null
    }

    fun <S> modifyField(data: T, newFieldValue: S, field: KMutableProperty1<T, S>): T {
        field.set(data, newFieldValue)
        saveState(data)
        return data
    }
}