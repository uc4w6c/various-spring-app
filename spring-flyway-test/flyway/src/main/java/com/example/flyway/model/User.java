package com.example.flyway.model;

public class User {
	public long id;
	public String name;

	@Override
	public String toString() {
		return this.id + ", " + this.name;
	}
}
