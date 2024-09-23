package es.gaspardev.core.interfaces

 interface  Builder<T> {

    fun reset()

    fun build():T

    fun initialize(initialValue: T? )
}