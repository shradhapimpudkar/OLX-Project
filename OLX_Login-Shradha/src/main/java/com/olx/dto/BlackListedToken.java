package com.olx.dto;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "BLACKLIST_TOKEN")
public class BlackListedToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "blacklisted_token")
    private String blacklistedToken;

    public BlackListedToken() {

    }

	public BlackListedToken(int id, String blacklistedToken) {
		super();
		this.id = id;
		this.blacklistedToken = blacklistedToken;
	}
	public BlackListedToken(String blacklistedToken) {
		super();
		this.blacklistedToken = blacklistedToken;
	}
   
}
