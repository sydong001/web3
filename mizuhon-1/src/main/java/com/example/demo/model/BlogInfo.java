package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private long userId; 
	private String title;
	private String overview;//内容概要
	private String editorcontent;
	
}
