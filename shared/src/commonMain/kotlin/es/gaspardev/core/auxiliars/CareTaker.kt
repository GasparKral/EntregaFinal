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

    fun getState(): T? {
        return if (mementos.isNotEmpty()) mementos.last().data else null
    }

    fun getFirstState(): T? {
        return if (mementos.isNotEmpty()) mementos.first().data else null
    }

    protected fun <S> modifyField(data: T, field: KMutableProperty1<T, S>, newFieldValue: S): T {
        field.set(data, newFieldValue)
        saveState(data)
        return data
    }

    /**
     * Modifies a field of the given data object by adding or removing the newFieldValue.
     *
     * @param data The data object whose field will be modified.
     * @param field The field of the data object that will be modified.
     * @param newFieldValue The value to be added or removed from the field.
     *
     * @return The modified data object.
     */
    protected fun <E, S : Collection<E>> modifyField(
        data: T,
        field: KMutableProperty1<T, S>,
        newFieldValue: E
    ): T {

        val newDataList: S = when (val dataList = field.get(data)) {
            is Set<*> -> (if (dataList.contains(newFieldValue)) dataList.filter { d -> d != newFieldValue } else (dataList + newFieldValue)) as S
            is List<*> -> (if (dataList.contains(newFieldValue)) dataList.filter { d -> d != newFieldValue } else (dataList + newFieldValue)) as S
            else -> throw UnsupportedOperationException("Unsupported collection type")
        }
        field.set(data, newDataList)
        saveState(data)
        return data
    }
}