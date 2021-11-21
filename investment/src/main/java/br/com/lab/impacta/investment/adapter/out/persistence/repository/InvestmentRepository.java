package br.com.lab.impacta.investment.adapter.out.persistence.repository;

import br.com.lab.impacta.investment.adapter.out.persistence.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

}
