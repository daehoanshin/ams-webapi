package com.abl.rtc.api.client.scm;

public class ScmAddWorkitem implements ScmCommand {

	@Override
	public void execute() {
		System.out.println("# 4 변경 세트를 작업 항목과 연관 [changeset] [workitemid]");
	}

}
