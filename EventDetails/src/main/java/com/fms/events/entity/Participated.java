package com.fms.events.entity;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Participated
 */

@Table("eventsummary")
public class Participated {
	@Id
	private Long Id;
	private Integer rating;

	private String likes;

	private String disLikes;

	public Participated rating(Integer rating) {
		this.rating = rating;
		return this;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Participated likes(String likes) {
		this.likes = likes;
		return this;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public Participated disLikes(String disLikes) {
		this.disLikes = disLikes;
		return this;
	}

	public String getDisLikes() {
		return disLikes;
	}

	public void setDisLikes(String disLikes) {
		this.disLikes = disLikes;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Participated participated = (Participated) o;
		return Objects.equals(this.rating, participated.rating) && Objects.equals(this.likes, participated.likes)
				&& Objects.equals(this.disLikes, participated.disLikes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rating, likes, disLikes);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Participated {\n");

		sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
		sb.append("    likes: ").append(toIndentedString(likes)).append("\n");
		sb.append("    disLikes: ").append(toIndentedString(disLikes)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
}
