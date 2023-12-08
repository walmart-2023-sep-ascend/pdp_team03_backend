package com.ascend.capstone.pdp.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection ="wishlist")
public class Wishlist {
	
	 @Id
	    private Integer id ;
	    private String user;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		@Override
		public String toString() {
			return "Wishlist [id=" + id + ", user=" + user + "]";
		}
	    
}