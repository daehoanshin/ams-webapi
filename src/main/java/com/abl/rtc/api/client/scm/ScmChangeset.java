package com.abl.rtc.api.client.scm;

public class ScmChangeset implements ScmCommand {

	@Override
	public void execute() {
		System.out.println("# 1 changeset 만들기");
		System.out.println("# 2 주석번호 만들");
		System.out.println("# 3 set changeset --comment 주석달기");
		System.out.println("# 5 changeset move -[changes]실제물리적 파일경로");
	}

}
