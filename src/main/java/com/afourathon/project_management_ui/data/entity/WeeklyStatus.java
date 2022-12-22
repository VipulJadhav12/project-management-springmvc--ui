package com.afourathon.project_management_ui.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WeeklyStatus {
	
	private Long id;
	
	private String status;
	
	private String highlight;
	
	private String risk;
	
	private LocalDate weeklyEndDate;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime lastModifiedOn;
	
	public WeeklyStatus(String status, String highlight, String risk, LocalDate weeklyEndDate, LocalDateTime createdOn, LocalDateTime lastModifiedOn) {
		super();
		this.status = status;
		this.highlight = highlight;
		this.risk = risk;
		this.weeklyEndDate = weeklyEndDate;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
	}
	
}
