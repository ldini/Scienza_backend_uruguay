package ar.com.scienza.remote.json.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OneSignalRequestRDto {

	@SerializedName("app_id")
	private String appId;
	
	@SerializedName("include_player_ids")
	private List<String> players = new ArrayList<String>();
	
	@SerializedName("data")
	private OneSignalDataRequestRDto data;
	
	@SerializedName("headings")
	private OneSignalHeaderRequestRDto header;
	
	@SerializedName("small_icon")
	private String icon;
	
	@SerializedName("contents")
	private OneSignalContentRequestRDto content;
	
	@SerializedName("url")
	private String url;

	@SerializedName("ios_badgeType")
	private String badgeType;

	@SerializedName("ios_badgeCount")
	private int badgeCount;
	
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}

	public OneSignalDataRequestRDto getData() {
		return data;
	}

	public void setData(OneSignalDataRequestRDto data) {
		this.data = data;
	}

	public OneSignalHeaderRequestRDto getHeader() {
		return header;
	}

	public void setHeader(OneSignalHeaderRequestRDto header) {
		this.header = header;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public OneSignalContentRequestRDto getContent() {
		return content;
	}

	public void setContent(OneSignalContentRequestRDto content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBadgeType() {
		return badgeType;
	}

	public void setBadgeType(String badgeType) {
		this.badgeType = badgeType;
	}

	public int getBadgeCount() {
		return badgeCount;
	}

	public void setBadgeCount(int badgeCount) {
		this.badgeCount = badgeCount;
	}
}
