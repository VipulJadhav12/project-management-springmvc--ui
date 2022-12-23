package com.afourathon.project_management_ui.data.entity;

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
public class MailingList {
	
	private Long id;
	
	private String recipientName;
	
	private String email;
	
	public MailingList(String recipientName, String email) {
		super();
		this.recipientName = recipientName;
		this.email = email;
	}
	
}
