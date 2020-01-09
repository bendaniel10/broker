package com.bendaniel10.broker.storage.repository.impl

import com.bendaniel10.broker.storage.repository.BrokerRepository
import org.dizitart.kno2.sort
import org.dizitart.no2.NitriteId
import org.dizitart.no2.SortOrder
import org.dizitart.no2.objects.ObjectRepository

internal open class BrokerRepositoryDefaultImpl<T>(
    protected val objectRepository: ObjectRepository<T>
) : BrokerRepository<T> {

    override fun save(model: T) {
        objectRepository.insert(model)
    }

    override fun getById(id: Long): T {
        return objectRepository.getById(NitriteId.createId(id))
    }

    override fun getItems(rowStart: Int, maxItems: Int): Iterable<T> {
        val paginationOption = sort("id", SortOrder.Descending)
            .thenLimit(rowStart, maxItems)
        return objectRepository.find(paginationOption)
    }

    override fun update(model: T) {
        objectRepository.update(model)
    }

    override fun delete(model: T) {
        objectRepository.remove(model)
    }

}