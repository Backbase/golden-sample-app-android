package com.backbase.accounts_journey.common

interface DataToDomainMapper<DataModel, DomainModel> {

    fun mapToDomain(dataModel: DataModel): DomainModel
}

interface UiToDomainMapper<UiModel, DomainModel> {

    fun mapToDomain(uiModel: UiModel): DomainModel
}

interface DomainToUiMapper<UiModel, DomainModel> {

    fun mapToUi(domainModel: DomainModel): UiModel
}

interface DomainToDataMapper<DataModel, DomainModel> {

    fun mapToData(domainModel: DomainModel): DataModel
}
