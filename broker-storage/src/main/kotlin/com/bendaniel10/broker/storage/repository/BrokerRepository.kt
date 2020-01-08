package com.bendaniel10.broker.storage.repository

interface BrokerRepository<T> {
    fun save(model: T)
    fun getById(id: Long) : T
    fun getItems(rowStart: Int, maxItems: Int) : Iterable<T>
    fun update(model: T)
    fun delete(model: T)
}