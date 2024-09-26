package es.gaspardev.core.interfaces

import kotlin.reflect.KProperty

interface Observation {

    interface Observable {

        val listOfObservables: ArrayList<Observer>
        fun add(observer: Observer) {
            this.listOfObservables.add(observer)
        }

        fun remove(observer: Observer) {
            this.listOfObservables.remove(observer)
        }

        fun <T> notifyAll(data: T?) {
            this.listOfObservables.forEach { it.update(data) }
        }

        fun <T> notify(observer: Observer, data: T?) {
            observer.update(data)
        }

    }

    interface Observer {

        fun <T> update(data: T?): T? {
            return data
        }


    }
}