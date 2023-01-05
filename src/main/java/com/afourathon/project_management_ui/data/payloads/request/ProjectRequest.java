package com.afourathon.project_management_ui.data.payloads.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String startDate;
	
	private String endDate;
	
	private String managerName;
	
	private String managerEmail;
	
}
