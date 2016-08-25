/**
 * 
 */
package com.zlb.demos.androiddemos.commens.list.sample;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * @ClassName: NetTrip
 * @Description:
 * @author WangJun
 * @date 2012-6-13 下午05:22:07
 */
public class NetTrip implements Parcelable{
    
    public long id;
    public String name;
    public String coverImage;
    public String coverImage640;
    public String recommendCover;
    public String description;
    public String lastDay;
    public long dateAdded;
    public long dateComplete;//游记结束时间，如果为null表示进行中
    public long lastModified;
    public int recommendations;
    public int comments;
    public double mileage;
    public int device;
    public int dayCount;
    public int wayPoint;//足迹数
    public String country;
    public String province;
    public String city;
    public String[] cities;
    public boolean wifiSync;
    public double startLatitude;
    public double startLongitude;
    public String popularPlaceStr;
    public boolean isFeaturedTrip; // 表示游记是否为“精选”
    public boolean isHotTrip; // 表示游记是否为“热门”
    public String title;
    
    public String commentCount;//评论数
    public String viewCount;//预览数
    public String likeCount;//喜欢数
    public String collection;//收藏数
    public int privacySettings;//好友可见 1.公开0. 仅自己可见2
    public int spotCount;//面包诱惑新添加：spot总数地点故事数
    //面包诱惑添加 区分新老游记 2新游记 1老游记
    public int version;
    //面包诱惑添加 故事数
    public String spot_count;
    //面包诱惑使用 作为封面本地图片路径
    public String imagePath;

    /** 编辑改过的游记名称 **/
    public String indexTitle;
    /**是否是默认游记 true 默认游记 */
    public boolean isDefault;

	public NetTrip() {

	}

	protected NetTrip(Parcel in) {
		id = in.readLong();
		name = in.readString();
		coverImage = in.readString();
		coverImage640 = in.readString();
		recommendCover = in.readString();
		description = in.readString();
		lastDay = in.readString();
		dateAdded = in.readLong();
		dateComplete = in.readLong();
		lastModified = in.readLong();
		recommendations = in.readInt();
		comments = in.readInt();
		mileage = in.readDouble();
		device = in.readInt();
		dayCount = in.readInt();
		wayPoint = in.readInt();
		country = in.readString();
		province = in.readString();
		city = in.readString();
		cities = in.createStringArray();
		wifiSync = in.readByte() != 0;
		startLatitude = in.readDouble();
		startLongitude = in.readDouble();
		popularPlaceStr = in.readString();
		isFeaturedTrip = in.readByte() != 0;
		isHotTrip = in.readByte() != 0;
		title = in.readString();
		commentCount = in.readString();
		viewCount = in.readString();
		likeCount = in.readString();
		collection = in.readString();
		privacySettings = in.readInt();
		spotCount = in.readInt();
		version = in.readInt();
		spot_count = in.readString();
		imagePath = in.readString();
		indexTitle = in.readString();
		isDefault = in.readByte() != 0;
		last_modified_format = in.readByte() != 0;
		last_modified_text = in.readString();
	}

	public static final Creator<NetTrip> CREATOR = new Creator<NetTrip>() {
		@Override
		public NetTrip createFromParcel(Parcel in) {
			return new NetTrip(in);
		}

		@Override
		public NetTrip[] newArray(int size) {
			return new NetTrip[size];
		}
	};

	public String getLast_modified_text() {
		return last_modified_text;
	}

	public void setLast_modified_text(String last_modified_text) {
		this.last_modified_text = last_modified_text;
	}

	public boolean isLast_modified_format() {
		return last_modified_format;
	}

	public void setLast_modified_format(boolean last_modified_format) {
		this.last_modified_format = last_modified_format;
	}

	public boolean last_modified_format;

	public String last_modified_text;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getCoverImage640() {
		return coverImage640;
	}

	public void setCoverImage640(String coverImage640) {
		this.coverImage640 = coverImage640;
	}

	public String getRecommendCover() {
		return recommendCover;
	}

	public void setRecommendCover(String recommendCover) {
		this.recommendCover = recommendCover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastDay() {
		return lastDay;
	}

	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}

	public long getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(long dateAdded) {
		this.dateAdded = dateAdded;
	}

	public long getDateComplete() {
		return dateComplete;
	}

	public void setDateComplete(long dateComplete) {
		this.dateComplete = dateComplete;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public int getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(int recommendations) {
		this.recommendations = recommendations;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public int getDevice() {
		return device;
	}

	public void setDevice(int device) {
		this.device = device;
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public int getWayPoint() {
		return wayPoint;
	}

	public void setWayPoint(int wayPoint) {
		this.wayPoint = wayPoint;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String[] getCities() {
		return cities;
	}

	public void setCities(String[] cities) {
		this.cities = cities;
	}

	public boolean isWifiSync() {
		return wifiSync;
	}

	public void setWifiSync(boolean wifiSync) {
		this.wifiSync = wifiSync;
	}

	public double getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(double startLatitude) {
		this.startLatitude = startLatitude;
	}

	public double getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(double startLongitude) {
		this.startLongitude = startLongitude;
	}

	public String getPopularPlaceStr() {
		return popularPlaceStr;
	}

	public void setPopularPlaceStr(String popularPlaceStr) {
		this.popularPlaceStr = popularPlaceStr;
	}


	public boolean isFeaturedTrip() {
		return isFeaturedTrip;
	}

	public void setFeaturedTrip(boolean isFeaturedTrip) {
		this.isFeaturedTrip = isFeaturedTrip;
	}

	public boolean isHotTrip() {
		return isHotTrip;
	}

	public void setHotTrip(boolean isHotTrip) {
		this.isHotTrip = isHotTrip;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public String getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public int getPrivacySettings() {
		return privacySettings;
	}

	public void setPrivacySettings(int privacySettings) {
		this.privacySettings = privacySettings;
	}
	
    public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getSpotCount() {
		return spotCount;
	}

	public void setSpotCount(int spotCount) {
		this.spotCount = spotCount;
	}

	public String getSpot_count() {
		return spot_count;
	}

	public void setSpot_count(String spot_count) {
		this.spot_count = spot_count;
	}

	public boolean equals(Object obj) {
    	if(obj instanceof NetTrip) {
    		NetTrip netTrip = (NetTrip) obj;
    		return this.name.equals(netTrip.name) && this.id == netTrip.id;
    	}
    	return super.equals(obj);
    };

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(coverImage);
		dest.writeString(coverImage640);
		dest.writeString(recommendCover);
		dest.writeString(description);
		dest.writeString(lastDay);
		dest.writeLong(dateAdded);
		dest.writeLong(dateComplete);
		dest.writeLong(lastModified);
		dest.writeInt(recommendations);
		dest.writeInt(comments);
		dest.writeDouble(mileage);
		dest.writeInt(device);
		dest.writeInt(dayCount);
		dest.writeInt(wayPoint);
		dest.writeString(country);
		dest.writeString(province);
		dest.writeString(city);
		dest.writeStringArray(cities);
		dest.writeByte((byte) (wifiSync ? 1 : 0));
		dest.writeDouble(startLatitude);
		dest.writeDouble(startLongitude);
		dest.writeString(popularPlaceStr);
		dest.writeByte((byte) (isFeaturedTrip ? 1 : 0));
		dest.writeByte((byte) (isHotTrip ? 1 : 0));
		dest.writeString(title);
		dest.writeString(commentCount);
		dest.writeString(viewCount);
		dest.writeString(likeCount);
		dest.writeString(collection);
		dest.writeInt(privacySettings);
		dest.writeInt(spotCount);
		dest.writeInt(version);
		dest.writeString(spot_count);
		dest.writeString(imagePath);
		dest.writeString(indexTitle);
		dest.writeByte((byte) (isDefault ? 1 : 0));
		dest.writeByte((byte) (last_modified_format ? 1 : 0));
		dest.writeString(last_modified_text);
	}

}
