package co.edu.unbosque.xtreme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.xtreme.entity.Proveedor;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

}
