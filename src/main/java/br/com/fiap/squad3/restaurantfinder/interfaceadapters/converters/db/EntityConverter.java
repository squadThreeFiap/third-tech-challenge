package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db;

public interface EntityConverter<DOMAIN, ENTITY> {
    public ENTITY toEntity(DOMAIN domainObj);

    public DOMAIN toDomainObj(ENTITY entity);
}
