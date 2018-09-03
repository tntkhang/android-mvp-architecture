package com.github.tntkhang.mvptemplate.models.network.responses;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AuthenResponse{

	@SerializedName("refresh_token")
	private String refreshToken;

	@SerializedName("token")
	private String token;

	public void setRefreshToken(String refreshToken){
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken(){
		return refreshToken;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}