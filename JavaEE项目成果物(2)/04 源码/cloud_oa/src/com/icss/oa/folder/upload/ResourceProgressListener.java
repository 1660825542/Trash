package com.icss.oa.folder.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class ResourceProgressListener implements ProgressListener {

	private HttpSession session;

	public ResourceProgressListener(HttpServletRequest request) {
		session = request.getSession();
		ResourceFileUploadStatus newUploadStatus = new ResourceFileUploadStatus();
		session.setAttribute("currentUploadStatus", newUploadStatus);
	}

	@Override
	public void update(long readedBytes, long totalBytes, int currentItem) {
		ResourceFileUploadStatus status = (ResourceFileUploadStatus) session
				.getAttribute("currentUploadStatus");
		status.setReadedBytes(readedBytes);
		status.setTotalBytes(totalBytes);
		status.setCurrentItem(currentItem);
	}

}
