package de.hliebau.tracktivity.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

import de.hliebau.tracktivity.util.GeometryUtils;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class TrackPoint extends AbstractEntity {

	private Point point;

	private Date utcTime;

	public TrackPoint() {
		super();
	}

	public TrackPoint(double lon, double lat) {
		this();
		this.point = GeometryUtils.getInstance().createPoint(lon, lat);
	}

	public TrackPoint(double lon, double lat, double ele) {
		this();
		this.point = GeometryUtils.getInstance().createPoint(lon, lat, ele);
	}

	@Transient
	@XmlElement(name = "ele")
	public Double getElevation() {
		if (!Double.isNaN(this.point.getCoordinate().z)) {
			return point.getCoordinate().z;
		}
		return null;
	}

	@Transient
	@XmlAttribute(name = "lat")
	public double getLatitude() {
		return point.getY();
	}

	@Transient
	@XmlAttribute(name = "lon")
	public double getLongitude() {
		return point.getX();
	}

	@Type(type = "org.hibernate.spatial.GeometryType")
	public Point getPoint() {
		return point;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement(name = "time")
	public Date getUtcTime() {
		return utcTime;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setUtcTime(Date utcTime) {
		this.utcTime = utcTime;
	}

}
