package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.api;

public interface DtoConverter<REQUEST, DOMAIN, RESPONSE> {
    public RESPONSE toResponse(DOMAIN domainObj);

    public DOMAIN toDomain(REQUEST request);

    public void updateDomainFromDto(DOMAIN domainObj, REQUEST request);
}
