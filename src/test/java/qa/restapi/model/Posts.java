package qa.restapi.model;

public class Posts {
	private String userid;
	private String id;
	private String title;
	private String body;

	public Posts(String userid, String title, String body) {
		super();
		this.userid = userid;
		this.title = title;
		this.body = body;
	}
	@Override
	public String toString() {
		return "Posts [userid=" + userid + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}


}
