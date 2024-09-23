package es.gaspardev.core.auxiliars

import es.gaspardev.core.interfaces.Copyable

open class Memento<T : Copyable>(val data: T) {

}