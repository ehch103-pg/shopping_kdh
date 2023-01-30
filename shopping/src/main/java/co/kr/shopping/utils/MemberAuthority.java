package co.kr.shopping.utils;

import lombok.Getter;

@Getter
public enum MemberAuthority {
	
	USER("USER"), ADMIN("ADMIN");

    private String name;

    private MemberAuthority(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
