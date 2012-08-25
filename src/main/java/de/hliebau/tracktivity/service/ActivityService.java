package de.hliebau.tracktivity.service;

import java.io.File;
import java.util.List;

import de.hliebau.tracktivity.domain.Activity;
import de.hliebau.tracktivity.domain.Track;
import de.hliebau.tracktivity.domain.User;

public interface ActivityService {

	public void createTrack(Track track);

	public long getActivityCount();

	public List<Activity> getRecentActivities(int count);

	public List<Track> getRecentTracks(int count);

	public long getTrackCount();

	public List<Activity> getUserActivities(User user);

	public Activity importGpxForUser(File gpxFile, User user);

	public Activity retrieveActivity(Long id);

}