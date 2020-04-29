package com.fms.authenticate.model;

public enum Role {

	ROLE_ADMIN("ADMIN"), ROLE_PMO("PMO"), ROLE_POC("POC"), ROLE_PARTICIPANT("PARTICIPANT");

	private final String identifier;

	Role(String identifier) {
		this.identifier = identifier;
	}

	public String toString() {
		return identifier;
	}

	public static Role getEnumNameForValue(String value) {
		Role[] values = Role.values();
		Role enumValue = null;
		for (Role role : values) {
			System.out.println(" Role from enum --- " + role);
			enumValue = role;

			if (enumValue.toString().equalsIgnoreCase(value)) {
				System.out.println(" Role value -- " + role.name());
				return role;
			}
		}
		return enumValue;
	}
}