package io.github.williamandradesantana.produtosapi.repository;

import io.github.williamandradesantana.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
