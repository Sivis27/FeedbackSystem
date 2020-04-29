package com.fms.events.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * UnregisteredFeedback
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnregisteredFeedback {
	private String disclose;

	private String personalCommitment;

	private String officialwork;

	private String notexpected;

	private String aboutEvent;

	private String incorrectlyRegistered;
}
