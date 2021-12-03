package com.olx.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "logintoken")
public class LoginDocument {
	
//	    @Id
//	    private int _id;
	    @Indexed(direction = IndexDirection.ASCENDING)
	    private String token;
//		public LoginDocument(int id, String token) {
//			super();
//			//this._id = id;
//			this.token = token;
//		}
		public LoginDocument(String token) {
			super();
			this.token = token;
		}
		public LoginDocument() {
		}
//		public int getId() {
//			return _id;
//		}
//		public void setId(int _id) {
//			this._id = _id;
//		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		@Override
		public String toString() {
			return "LoginDocument [id=" +", token=" + token + "]";
		}
}
