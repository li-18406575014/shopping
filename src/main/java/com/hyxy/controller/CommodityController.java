package com.hyxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hyxy.entity.Commodity;
import com.hyxy.es.CommodityDao;
import com.hyxy.service.CommodityService;
import com.hyxy.util.Upload;

@Controller
@RequestMapping("CommodityController")
public class CommodityController {

	@Autowired
	private CommodityService CommodityService;
	@Resource
	private CommodityDao commodityDao;
	// 跳转到商品新增页面,并从数据库中查询数据
	@RequestMapping("skipCommodity")
	public String skipCommodity(Map<String, Object> map) {
		List<Commodity> list = CommodityService.servlceCommodity();
		map.put("ban", list);
		return "goods-category-add";
	}

	// 跳转到新增页面
	@RequestMapping("skipIcreased")
	public String skipIcreased() {
		return "good-category-add";
	}

	// 实现图片的预览上传
	@RequestMapping("imgPreview")
	@ResponseBody
	public List<Map<String, String>> imgPreview(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "banner", required = false) MultipartFile[] img) {
		Upload upload = new Upload();
		List<Map<String, String>> list = upload.uploadfile(request, img);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("list", list);
//			for (Map<String, String> map2 : list) {
//				String path = map2.get("path");
//				String savePath = map2.get("savePath");
//			}
		return list;
	}

	// 点击添加
	@ResponseBody
	@RequestMapping("addCommodity")
	public Map<String, String> addCommodity(@RequestParam Map<String, Object> map) {
		CommodityService.addCommodity(map	);
		Commodity commodity = new Commodity(Integer.parseInt((String) map.get("id")), (String)map.get("name"), (String)map.get("price"), (String)map.get("banner"));
		commodityDao.save(commodity);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("message", "成功");
		return map2;
	}
	
	//根据id查询数据库中的数据
	//点击编辑按id进行查询
		@RequestMapping("selectId")
		public String selectId(Map<String, Commodity> map,Integer id) {
			Commodity commodity = CommodityService.selectId(id);
			map.put("banner", commodity);
			return "good-category-add";
		}
		//修改
		@RequestMapping("updataId")
		public String updataId(@RequestParam Map<String, Object> map) {
			if (map.get("imgname")!=map.get("banner") && map.get("banner")!=null) {
				map.put("imgname", map.get("banner"));
			}
			Integer id = Integer.parseInt((String) map.get("id"));
			map.put("id", id);
			CommodityService.updataJS(map);
			Commodity commodity = new Commodity(id, (String)map.get("name"), (String)map.get("price"), (String)map.get("imgname"));
			commodityDao.save(commodity);
			return "forward:/CommodityController/skipCommodity";
		}
	//删除
		@RequestMapping("delectId")
		public String delectId(Integer id) {
			CommodityService.delectId(id);
			commodityDao.deleteById(id);
			 System.out.println("成功");
			return "forward:/CommodityController/skipCommodity";
		}
}
