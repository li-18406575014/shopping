package com.hyxy.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Upload {
	public List<Map<String, String>> uploadfile(HttpServletRequest request, MultipartFile[] files) {
		List<Map<String, String>> list = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		String savePath = request.getServletContext().getRealPath("/serverimg");
		map.put("savePath", savePath);
		System.out.println(savePath);
		// F:\eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\springmvc\serverimg

		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String imgname = files[i].getOriginalFilename();
				if (imgname != null && imgname != "") {
					String fileExtName = imgname.substring(imgname.lastIndexOf("."));
					String filename = makeFileName(fileExtName);
					String filePath = savePath + "/" + filename;

					String path = filename;

					File saveDir = new File(filePath);
					if (!saveDir.getParentFile().exists()) {
						saveDir.getParentFile().mkdirs();
					}

					try {
						files[i].transferTo(saveDir);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					map.put("path", path);
				}
			}
		}
		list.add(map);
		return list;
	}

	private String makeFileName(String fileExtName) {
		return UUID.randomUUID().toString().replace("-", "") + fileExtName;
	}
}
