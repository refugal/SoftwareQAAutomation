package testDataBuild;

import pojo.PostRequest;

public class TestData {

	public PostRequest addPayLoad(String name,String job) {
		PostRequest p=new PostRequest();
		p.setName(name);
		p.setJob(job);
		return p;
	}
}
