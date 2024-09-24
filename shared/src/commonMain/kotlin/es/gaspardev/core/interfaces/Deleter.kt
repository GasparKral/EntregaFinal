package es.gaspardev.core.interfaces

interface Deleter<T> {

    fun undo(): T
    fun undo(index: Int): T
    fun clear()
    
}