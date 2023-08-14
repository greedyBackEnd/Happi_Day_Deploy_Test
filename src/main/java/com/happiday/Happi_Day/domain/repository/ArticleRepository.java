package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
