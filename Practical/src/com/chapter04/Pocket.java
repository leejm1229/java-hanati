package com.chapter04;

public class Pocket<E extends Character> {
	enum AuthState{
		AUTHENTICATED, UNAUTHENTICATED, ATUHENTICATING;
	}
	
	public static void main(String[] args) {
		AuthState authState = AuthState.UNAUTHENTICATED;
		
		if(authState == AuthState.AUTHENTICATED) {
			System.out.println("로그인 됨");
		}else if(authState == AuthState.UNAUTHENTICATED) {
			System.out.println("로그아웃 됨");
		}else {
			System.out.println("로그인 중");
		}
	}	
}

