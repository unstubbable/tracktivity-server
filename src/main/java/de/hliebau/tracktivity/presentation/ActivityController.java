package de.hliebau.tracktivity.presentation;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import de.hliebau.tracktivity.domain.Activity;
import de.hliebau.tracktivity.service.ActivityService;
import de.hliebau.tracktivity.service.UserService;

@Controller
@RequestMapping({ "/activity" })
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{activityId}", method = RequestMethod.GET)
	public String displayActivty(@PathVariable Long activityId, Model model) {
		Activity activity = activityService.retrieveActivityWithTrack(activityId);
		if (activity != null) {
			model.addAttribute(activity);
			model.addAttribute("durationNetto", activity.getTrack().getDuration(false));
			model.addAttribute("durationBrutto", activity.getTrack().getDuration(true));
		}
		return "activity";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String uploadActivity(Model model) {
		model.addAttribute(new UploadItem());
		return "upload";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String uploadActivity(UploadItem uploadItem, BindingResult bindingResult) {
		try {
			validateFile(uploadItem.getFileData());
			InputStream in = uploadItem.getFileData().getInputStream();
			Activity activity = activityService.importGpxForUser(in, userService.retrieveUser("testUser"));
			in.close();
			return "redirect:" + activity.getId();
		} catch (FileUploadException e) {
			bindingResult.addError(new FieldError("uploadItem", "fileData", e.getMessage()));
		} catch (IOException e) {
			bindingResult.addError(new FieldError("uploadItem", "fileData", e.getMessage()));
		}
		return "upload";
	}

	private void validateFile(MultipartFile file) {
		if (file.isEmpty()) {
			throw new FileUploadException("You need to select a GPX file.");
		}
		if (!file.getOriginalFilename().endsWith(".gpx") || !file.getContentType().equals("application/octet-stream")) {
			throw new FileUploadException("Only GPX files are accepted.");
		}
	}

}
