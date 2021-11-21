package br.com.lab.impacta.investment.adapter.out.persistence.repository;

import br.com.lab.impacta.investment.adapter.out.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
