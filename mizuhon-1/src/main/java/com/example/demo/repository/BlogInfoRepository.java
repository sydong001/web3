package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BlogInfo;

@Repository
public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long> {
	BlogInfo findByUserId(Long userId);
	BlogInfo saveAndFlush(BlogInfo  blogInfo);
}
