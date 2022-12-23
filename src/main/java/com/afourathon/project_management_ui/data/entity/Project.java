package com.afourathon.project_management_ui.data.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class Project {
	
	private Long id;
	
	private String name;
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	private String managerName;
	
	private String managerEmail;
	
	private Set<MailingList> mailingList = new HashSet<>();
	
	private List<WeeklyStatus> weeklyStatuses;
	
	public Project(String name, LocalDate startDate, LocalDate endDate, String managerName, String managerEmail) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.managerName = managerName;
		this.managerEmail = managerEmail;
	}
	
	public Project(String name, LocalDate startDate, LocalDate endDate, String managerName, String managerEmail, Set<MailingList> mailingList) {
		super();
		this.name = name;
		this.managerName = managerName;
		this.managerEmail = managerEmail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mailingList = mailingList;
	}

}
