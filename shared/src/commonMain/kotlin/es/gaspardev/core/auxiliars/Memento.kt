package es.gaspardev.core.auxiliars

import es.gaspardev.core.interfaces.Copyable

open class Memento<T : Copyable>(val data: T) {

    override fun equals(other: Any?): Boolean {
        if (other !is Memento<*>) return false
        return data == other.data
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
    
}