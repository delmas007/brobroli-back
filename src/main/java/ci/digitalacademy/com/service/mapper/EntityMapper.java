package ci.digitalacademy.com.service.mapper;

public interface EntityMapper<D, E> {
    D fromEntity(E entity);
    E toEntity(D dto);
}
