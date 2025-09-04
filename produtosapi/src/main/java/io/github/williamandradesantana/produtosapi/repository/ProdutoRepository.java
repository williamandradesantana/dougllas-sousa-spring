package io.github.williamandradesantana.produtosapi.repository;

import io.github.williamandradesantana.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
